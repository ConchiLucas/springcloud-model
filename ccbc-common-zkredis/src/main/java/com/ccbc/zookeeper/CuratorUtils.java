package com.ccbc.zookeeper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.RetryLoop;
import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.api.transaction.CuratorTransaction;
import org.apache.curator.framework.recipes.cache.NodeCache;
import org.apache.curator.framework.recipes.cache.NodeCacheListener;
import org.apache.curator.framework.recipes.cache.PathChildrenCache;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheListener;
import org.apache.curator.framework.recipes.cache.TreeCache;
import org.apache.curator.framework.recipes.cache.TreeCacheListener;
import org.apache.curator.retry.RetryForever;
import org.apache.curator.retry.RetryNTimes;
import org.apache.zookeeper.data.Stat;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;



/**
 * Created by Administrator on 2017/4/21.
 */
@Component
public class CuratorUtils implements CommandLineRunner {

    // zookeeper客戶端,客户端实例化一次
    private static CuratorFramework zkClient;

    //监听缓存
    private static ConcurrentHashMap<String,
            List<ConcurrentHashMap<PathChildrenCache, PathChildrenCacheListener>>>
            pcListenerCache = new ConcurrentHashMap<>();
    private static ConcurrentHashMap<String,
            ArrayList<ConcurrentHashMap<NodeCache, NodeCacheListener>>>
            ncListenerCache = new ConcurrentHashMap<>();
    private static ConcurrentHashMap<String,
            ArrayList<ConcurrentHashMap<TreeCache, TreeCacheListener>>>
            tcListenerCache = new ConcurrentHashMap<>();

    /**
     * 初始化 CuratorUtils工具
     *
     * @param connectString 要连接的zookeeper地址
     * @param retryPolicy 重连策略，重连策略有：{@link RetryForever}, {@link RetryNTimes}, {@link
     * RetryLoop};如果改值为null则使用 RetryForever策略
     */
    public synchronized static void init(String connectString, RetryPolicy retryPolicy)
            throws Exception {

        if (connectString == null || connectString.length() == 0) {
            throw new Exception("zookeeper地址有误，请检查配置");
        } else {
            if (zkClient == null) {
                if (retryPolicy == null) {
                    zkClient = CuratorFrameworkFactory
                            .newClient(connectString, new RetryForever(6000));
                } else {
                    zkClient = CuratorFrameworkFactory
                            .newClient(connectString, retryPolicy);
                }
                zkClient.start();
            }
        }
    }

    /**
     * 在zookeeper上创建结点
     *
     * @param path 结点路径
     * @param data 结点数据
     * @param isCreateParent 如果父结点不存在是否创建父结点，false不创建，true创建
     * @return 执行成功则返回要创建的目录，分户其他表示创建失败
     * @throws Exception 链接未初始化、创建失败则抛出异常
     */
    public static String createNode(String path, byte[] data,
                                    boolean isCreateParent) throws Exception {
        if (zkClient != null) {
            String reult = "";
            if (!isCreateParent) {
                if (data == null) {
                    reult = zkClient.create().forPath(path);
                } else {
                    reult = zkClient.create().forPath(path, data);
                }

            } else {
                if (data == null) {
                    reult = zkClient.create().creatingParentsIfNeeded()
                            .forPath(path);
                } else {
                    reult = zkClient.create().creatingParentsIfNeeded()
                            .forPath(path, data);
                }
            }
            return reult;
        } else {
            throw new Exception("zkClient没有初始化，请初始化");
        }
    }

    /**
     * 删除指定结点
     *
     * @param path 要删除的结点路径
     * @param isDeleteChild 如果该结点下存在子结点是否产出子结点，true为删除子结点， false不删除子结点
     * @throws Exception 链接未初始化、创建失败则抛出异常
     */
    public static void deleteNode(String path, boolean isDeleteChild)
            throws Exception {
        if (zkClient != null) {
            if (!isDeleteChild) {
                zkClient.delete().forPath(path);
            } else {
                zkClient.delete().deletingChildrenIfNeeded().forPath(path);
            }
        } else {
            throw new Exception("zkClient没有初始化，请初始化");
        }
    }

    /**
     * 修改执行结点数据
     *
     * @param path 要修改节点的路径
     * @param data 修改的数据
     */
    public static void setData(String path, byte[] data) throws Exception {
        if (zkClient != null) {
            if (data == null) {
                zkClient.setData().forPath(path, "".getBytes());
            } else {
                zkClient.setData().forPath(path, data);
            }

        } else {
            throw new Exception("zkClient没有初始化，请初始化");
        }
    }

    /**
     * 获取指定结点的值
     *
     * @param path 节点路径
     * @throws Exception 链接未初始化、创建失败则抛出异常
     */
    public static byte[] getData(String path) throws Exception {
        if (zkClient != null) {
            byte[] result = null;
            if (path != null) {
                result = zkClient.getData().forPath(path);
            }
            return result;
        } else {
            throw new Exception("zkClient没有初始化，请初始化");
        }
    }

    /**
     * 判断指定结点是否存在
     *
     * @param path 结点路径
     * @return 如果返回为null，表示不存在
     * @throws Exception 链接未初始化、创建失败则抛出异常
     */
    public static Stat checkExists(String path) throws Exception {
        if (zkClient != null) {
            Stat stat = zkClient.checkExists().forPath(path);
            return stat;
        } else {
            throw new Exception("zkClient没有初始化，请初始化");
        }
    }

    /**
     * 获取指定结点下的孩子结点
     */
    public static List<String> getChildren(String path) throws Exception {
        if (zkClient != null) {
            List<String> paths = zkClient.getChildren().forPath(path);
            return paths;
        } else {
            throw new Exception("zkClient没有初始化，请初始化");
        }
    }

    /**
     * <pre>
     * 使用Path Cache方式监听节点监听特点如下
     *    监听一个路径下：
     *        1、孩子节点创建删除
     *      2、节点的数据更新
     *      3、如果本节点被删除则取消监听
     * </pre>
     */
    public static void watchNodeByPathCache(String path,
                                            PathChildrenCacheListener listener) throws Exception {
        if (zkClient != null) {
            PathChildrenCache wacther = new PathChildrenCache(zkClient, path, true);
            wacther.getListenable().addListener(listener);
            wacther.start();
            //加入缓存用于删除监听
            ConcurrentHashMap<PathChildrenCache, PathChildrenCacheListener> map =
                    new ConcurrentHashMap<>();
            map.put(wacther, listener);
            if (pcListenerCache.get(path) == null) {
                pcListenerCache.put(path,
                        new ArrayList<ConcurrentHashMap<PathChildrenCache,
                                PathChildrenCacheListener>>());
            }
            pcListenerCache.get(path).add(map);
        } else {
            throw new Exception("zkClient没有初始化，请初始化");
        }
    }

    /**
     * 删除PathCache中的监听器
     *
     * @param path 结点路径
     * @param listener 要删除的监听器
     */
    public static void removePathCacheListener(String path, PathChildrenCacheListener listener)
            throws IOException {
        if (listener == null) {
            return;
        }

        if (pcListenerCache.size() > 0) {
            List<ConcurrentHashMap<PathChildrenCache, PathChildrenCacheListener>>
                    watchertmp = new ArrayList<>();
            //关闭指定监听
            for (String pathTmp : pcListenerCache.keySet()) {
                if (pathTmp.equals(path)) {
                    for (ConcurrentHashMap<PathChildrenCache, PathChildrenCacheListener>
                            map : pcListenerCache.get(pathTmp)) {
                        for (PathChildrenCache pc : map.keySet()) {
                            if (map.get(pc) == listener) {
                                watchertmp.add(map);
                                pc.getListenable().removeListener(listener);
                                pc.close();
                            }
                        }
                    }
                }
            }
            //删除关闭的监听缓存
            for (Map pcTmp : watchertmp) {
                pcListenerCache.get(path).remove(pcTmp);
            }
            if (pcListenerCache.get(path).size() == 0) {
                pcListenerCache.remove(path);
            }
        }
    }

    /**
     * <pre>
     * 使用Node Cache:
     *   监听一个节点的创建、更新、删除、并将节点的数据缓存在本地
     * </pre>
     */
    public static void watchNodeByNodeCache(String path,
                                            NodeCacheListener listener) throws Exception {
        if (zkClient != null) {
            NodeCache wacther = new NodeCache(zkClient, path);
            wacther.getListenable().addListener(listener);
            wacther.start();
            //加入缓存用于删除监听
            ConcurrentHashMap<NodeCache, NodeCacheListener> map =
                    new ConcurrentHashMap<>();
            map.put(wacther, listener);
            if (ncListenerCache.get(path) == null) {
                ncListenerCache.put(path,
                        new ArrayList<ConcurrentHashMap<NodeCache,
                                NodeCacheListener>>());
            }
            ncListenerCache.get(path).add(map);
        } else {
            throw new Exception("zkClient没有初始化，请初始化");
        }
    }

    /**
     * 删除NodeCache中的监听器
     *
     * @param path 结点路径
     * @param listener 要删除的监听器
     */
    public static void removeNodeCacheListener(String path,
                                               NodeCacheListener listener) throws IOException {
        if (listener == null) {
            return;
        }

        if (ncListenerCache.size() > 0) {
            List<ConcurrentHashMap<NodeCache, NodeCacheListener>>
                    watchertmp = new ArrayList<>();

            for (String pathTmp : ncListenerCache.keySet()) {
                if (pathTmp.equals(path)) {
                    for (ConcurrentHashMap<NodeCache, NodeCacheListener>
                            map : ncListenerCache.get(pathTmp)) {
                        for (NodeCache nc : map.keySet()) {
                            if (map.get(nc) == listener) {
                                watchertmp.add(map);
                                nc.getListenable().removeListener(listener);
                                nc.close();
                            }
                        }
                    }
                }
            }

            for (Map map : watchertmp) {
                ncListenerCache.get(path).remove(map);
            }
            if (ncListenerCache.get(path).size() == 0) {
                ncListenerCache.remove(path);
            }
        }
    }

    /**
     * <pre>
     * 使用Node Cache:
     *   Path Cache和Node Cache的“合体”，监视路径下子结点、子结点的子结点的创建、
     *  更细、删除，并缓存路径下的所有孩子节点的数据
     * @param path
     * @param listener
     * @throws Exception
     */
    public static void watchNodeByTreeCache(String path,
                                            TreeCacheListener listener) throws Exception {
        if (zkClient != null) {
            TreeCache wacther = new TreeCache(zkClient, path);
            wacther.getListenable().addListener(listener);
            wacther.start();
            //加入缓存用于删除监听
            ConcurrentHashMap<TreeCache, TreeCacheListener> map =
                    new ConcurrentHashMap<>();
            map.put(wacther, listener);

            if (tcListenerCache.get(path) == null) {
                tcListenerCache.put(path,
                        new ArrayList<ConcurrentHashMap<TreeCache,
                                TreeCacheListener>>());
            }
            tcListenerCache.get(path).add(map);
        } else {
            throw new Exception("zkClient没有初始化，请初始化");
        }
    }

    /**
     * 删除NodeCache中的监听器
     *
     * @param path 结点路径
     * @param listener 要删除的监听器
     */
    public static void removeTreeCacheListener(String path, TreeCacheListener listener) {
        if (listener == null) {
            return;
        }

        if (tcListenerCache.size() > 0) {
            List<ConcurrentHashMap<TreeCache, TreeCacheListener>>
                    watchertmp = new ArrayList<>();

            for (String pathTmp : tcListenerCache.keySet()) {
                if (pathTmp.equals(path)) {
                    for (ConcurrentHashMap<TreeCache, TreeCacheListener>
                            map : tcListenerCache.get(pathTmp)) {
                        for (TreeCache tc : map.keySet()) {
                            if (map.get(tc) == listener) {
                                watchertmp.add(map);
                                tc.getListenable().removeListener(listener);
                                tc.close();
                            }
                        }

                    }
                }
            }

            for (Map map : watchertmp) {
                tcListenerCache.get(path).remove(map);
            }
            if (tcListenerCache.get(path).size() == 0) {
                tcListenerCache.remove(path);
            }
        }
    }

    /**
     * 删除zookeeper结点上的所用接听
     */
    public static void removeAllListener() throws IOException {
        for (String path : pcListenerCache.keySet()) {
            for (ConcurrentHashMap<PathChildrenCache, PathChildrenCacheListener>
                    map : pcListenerCache.get(path)) {
                for (PathChildrenCache key : map.keySet()) {
                    key.getListenable().removeListener(map.get(key));
                    key.close();
                }
            }
        }

        for (String path : ncListenerCache.keySet()) {
            for (ConcurrentHashMap<NodeCache, NodeCacheListener>
                    map : ncListenerCache.get(path)) {
                for (NodeCache key : map.keySet()) {
                    key.getListenable().removeListener(map.get(key));
                    key.close();
                }
            }
        }

        for (String path : tcListenerCache.keySet()) {
            for (ConcurrentHashMap<TreeCache, TreeCacheListener>
                    map : tcListenerCache.get(path)) {
                for (TreeCache key : map.keySet()) {
                    key.getListenable().removeListener(map.get(key));
                    key.close();
                }
            }
        }
        pcListenerCache.clear();
        ncListenerCache.clear();
        tcListenerCache.clear();
    }

    /**
     * 获取所连接zookeeper的一个事务，用于一次性处理多个操作
     */
    public static CuratorTransaction getTransaction() throws Exception {
        if (zkClient != null) {
            return zkClient.inTransaction();
        } else {
            throw new Exception("zkClient没有初始化，请初始化");
        }
    }

    /**
     * 关闭zookeeper客户端连接
     */
    public static void closeClient() {
        if (zkClient != null) {
            zkClient.close();
        }
    }
    @Override
    public void run(String... args) throws Exception {
        init("139.196.147.87:2181",null);
    }

}