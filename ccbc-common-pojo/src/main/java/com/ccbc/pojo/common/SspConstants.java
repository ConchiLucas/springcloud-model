package com.ccbc.pojo.common;

import java.text.SimpleDateFormat;
import java.util.Date;

import lombok.extern.slf4j.Slf4j;


/**
 * 
 * @ClassName SspConstants
 * @Description 服务平台常量类
 * @author lintong1@yusys.com.cn
 * @date 2016年12月14日 下午5:59:24
 *
 */
@Slf4j
public class SspConstants {

    // 日志
    // sspContext中交易代码key值
    public static final String BSNCODE = "bsnCode";
    // sspContext中上送实体key值
    public static final String REQUEST = "request";
    // sspContext中返回实体key值
    public static final String RESPONSE = "response";
    // sspContext中交易状态的key
    public static final String TRAN_STATUS = "tranStatus";
    // sspContext中交易流水号的key
    public static final String FLOW_NO = "tranFlowNo";
    // sspContext中交易日志信息的key
    public static final String LOG_INFO = "logInfo";
    // sspContext中交易流水号的key
    public static final String AUTO_RESULT = "autoResult";
    // sspContext中交易日志信息的key
    public static final String AUTO_DESC = "autoDesc";
    // 系统标识
    public static final String SYS_FLAG = "0001";
    // 系统标识
    public static final String PARTNER_SYS_FLAG = "0006";
    // 交易成功返回码
    public static final String SUCCESS_RETURN_CODE = "0";
    // 业务组件交易成功返回码
    public static final String CCBC_SUCCESS_RETURN_CODE = "CCBC00000";
    // DES3加密公钥的路径
    public static final String DES_KEY_PATH = "app/usp/des3";
    // DES3加密公钥的key
    public static final String DES_KEY_KEY = "des_key";
    // DES3加密公钥的key
    public static final String DES_KEY = "12091359";

    // =======================session数据 key开始======================//
    // 存储在session中的手机号码
    public static final String MOBILE = "session_mobileNo";
    // 存储在session中的用户注册号
    public static final String USER_SEQ = "session_userSeq";
    // 存储在session中的客户号
    public static final String CUST_NO = "session_custNo";
    // 存储在session中的客户姓名
    public static final String CUST_NAME = "session_custName";
    // 存储在session中的身份证号码
    public static final String CERT_NO = "session_certNo";
    // 存储在session中的渠道号
    public static final String CHANNEL_ID = "session_channelId";
    // 存储在session中的资产方
    public static final String ASSERT_SIDE = "session_assetSide";
    // 存储在session中的资金方
    public static final String CAPITAL_SIDE = "session_capitalSide";
    // 存储在session中的ip
    public static final String IP = "session_ip";
    // 存储在session中的domain
    public static final String DOMAIN = "session_domain";
    // 存储在session中的httpSession对象
    public static final String HTTP_SESSION = "httpSession";
    // 存储在session中的影像平台信息
    public static final String IMG_INFO = "session_imgInfo";
    // 存储在session中的产品编号
    public static final String PRD_NO = "session_prdNo";
    // 存储在session中的证件类型
    public static final String ID_TYPE = "session_idType";
    // 存储在session中的账户类型
    public static final String USER_TYPE = "session_userType";
    // 存储在session中的客户状态
    public static final String CUST_STATUS = "session_custStatus";
    // 存储在session中的服务协议号
    public static final String PROT_NO = "session_serviceProtNo";
    // 存储在session中的注册日期
    public static final String REG_DATE = "session_reqDate";
    // 存储在session中的头像id
    public static final String IMG_ID = "session_imageId";
    // 存储在session中的头像id
    public static final String LAST_MODIFY_TIME = "session_lastModifyTime";
    // 存储在session中的借据号
    public static final String IOU_SEQ = "session_iouSeq";
    // 存储在session中的借据号
    public static final String CUST_SEQ_LIST = "session_custSeqList";
    // session状态
    public static final String SESSION_STATUS = "session_status";
    // session随机数（防重复提交用）
    public static final String RANDOM_NUM = "session_randomNum";
    // 存储在session中的申请序号
    public static final String APPLY_SEQ = "session_applySeq";
    // 存储在session中的申请状态
    public static final String APPLY_STATUS = "session_applyStatus";
    // 存储在session中的产品账户序号
    public static final String PRD_ACCT_SEQ = "session_prdAcctSeq";
    // 存储在session中的产品调额申请序号
    public static final String ALT_APPLY_SEQ = "session_altApplySeq";
    // 存储在session中的产品调额申请序号
    public static final String CARD_NO = "session_cardNo";
    // 存储在session中的转介绍申请序号
    public static final String INTRODUCE_APPLY_SEQ = "session_introduceApplySeq";
    // 存储在session中的登录密码
    public static final String LOGON_PWD = "session_logonPwd";

    // 存储在session中的用户经理编号
    public static final String CUST_MANAGER_ID = "session_custManagerId";

    public static final String CUST_MANAGER_NO = "session_custManagerNo";

    // 存储在session中的用户经理级别
    public static final String CUST_MANAGER_LEVEL = "session_custManagerLevel";

    // =======================session数据 key结束======================//

    // redis中记录日志序号的key
    public static final String LOG_SEQ_KEY = "SSP_LOG_SEQ_KEY";
    // redis中记录日志序号的长度
    public static final String LOG_SEQ_LEN = "12";

    // 缓存中客户脸部图片key
    public static final String CUST_FACE_IMG_KEY = "CUSTIMGFACE";
    // 缓存中客户身份证图片key
    public static final String CUST_IDCARD_IMG_KEY = "CUSTIMGIDCARD";

    public static final String SEAL_TYPE = "2"; // 签章类型
    public static final String PDF_URL = ""; // Pdf的URL地址
    public static final String SEAL_CODE = "zhtest1"; // 印章编码
    public static final String SEAL_PASSWORD = "123456"; // 印章密码
    public static final String SEAL_PERSON = "admin"; // 签章人
    public static final String SEAL_LOCATION = ""; // 签章地点
    public static final String SEAL_RESSON = ""; // 签章理由
    public static final String BUSINESS_CODE = ""; // 业务码
    public static final String BUSINESS_CODE_STYLE = "font-size:5;x-ratio:0.15;y-ratio:0.5;color:FF00AB;"; // 样式
    public static final String BLANK_FIELD_NAME = "Signature2"; // 空白标签字段名
    public static final String SEAL_PAGE = "6"; // 页数
    public static final String SEAL_LX = "250"; // 左侧的x坐标
    public static final String SEAL_LY = "640"; // 左侧的y坐标
    public static final String SEAL_KEYWORD = ""; // 关键字
    public static final String LOCATION_STYLE = "C"; // 位置风格
    public static final String OFFSET_X = "0"; // 横轴偏移
    public static final String OFFSET_Y = "0"; // 纵轴偏移
    public static final String READFILEPATH = "/u01/app/ecpusr/upload/avatar/A060000002.pdf"; // txt文件
    public static final String READFILEPATH_A060000006 = "/u01/app/ecpusr/upload/avatar/A060000006.pdf"; // txt文件
    public static final String READFILEPATH_A060000003 = "/u01/app/ecpusr/upload/avatar/A060000003.pdf"; // txt文件
    public static final String READFILEPATH_A060000011 = "/u01/app/ecpusr/upload/avatar/A060000011.pdf"; // txt文件
    public static final String READFILEPATH_A060000023DY = "/u01/app/ecpusr/upload/avatar/A060000023DY.pdf"; // txt文件
    public static final String READFILEPATH_A060000023DK = "/u01/app/ecpusr/upload/avatar/A060000023DK.pdf"; // txt文件
    public static final String READFILEPATH_A060000026 = "/u01/app/ecpusr/upload/avatar/A060000026.pdf"; // txt文件
    public static final String READFILEPATH_A060000027 = "/u01/app/ecpusr/upload/avatar/A060000027.pdf"; // txt文件
    public static final String READFILEPATH_A060000033DK = "/u01/app/ecpusr/upload/avatar/A060000033DK.pdf"; // txt文件
    public static final String READFILEPATH_A060000030DY = "/u01/app/ecpusr/upload/avatar/A060000030DY.pdf"; // txt文件
    public static final String READFILEPATH_A060000030DK2 = "/u01/app/ecpusr/upload/avatar/A060000030DK2.pdf"; // txt文件
    public static final String READFILEPATH_A060000030DY2 = "/u01/app/ecpusr/upload/avatar/A060000030DY2.pdf"; // txt文件
    public static final String READFILEPATH_A060000030DK = "/u01/app/ecpusr/upload/avatar/A060000030DK.pdf"; // txt文件    
    public static final String READFILEPATH_A060000030DY_HZ = "/u01/app/ecpusr/upload/avatar/A060000030DY-HZ.pdf"; // txt文件
    public static final String READFILEPATH_A060000030DK2_HZ = "/u01/app/ecpusr/upload/avatar/A060000030DK2-HZ.pdf"; // txt文件
    public static final String READFILEPATH_A060000030DY2_HZ = "/u01/app/ecpusr/upload/avatar/A060000030DY2-HZ.pdf"; // txt文件
    public static final String READFILEPATH_A060000030DK_HZ = "/u01/app/ecpusr/upload/avatar/A060000030DK-HZ.pdf"; // txt文件
    public static final String READFILEPATH_A060000030DY_TJ = "/u01/app/ecpusr/upload/avatar/A060000030DY-TJ.pdf"; // txt文件
    public static final String READFILEPATH_A060000030DK2_TJ = "/u01/app/ecpusr/upload/avatar/A060000030DK2-TJ.pdf"; // txt文件
    public static final String READFILEPATH_A060000030DY2_TJ = "/u01/app/ecpusr/upload/avatar/A060000030DY2-TJ.pdf"; // txt文件
    public static final String READFILEPATH_A060000030DK_TJ = "/u01/app/ecpusr/upload/avatar/A060000030DK-TJ.pdf"; // txt文件
    public static final String READFILEPATH_A060000030DY_CQ = "/u01/app/ecpusr/upload/avatar/A060000030DY-CQ.pdf"; // txt文件
    public static final String READFILEPATH_A060000030DK2_CQ = "/u01/app/ecpusr/upload/avatar/A060000030DK2-CQ.pdf"; // txt文件
    public static final String READFILEPATH_A060000030DY2_CQ = "/u01/app/ecpusr/upload/avatar/A060000030DY2-CQ.pdf"; // txt文件
    public static final String READFILEPATH_A060000030DK_CQ = "/u01/app/ecpusr/upload/avatar/A060000030DK-CQ.pdf"; // txt文件
    public static final String READFILEPATH_A060000030BKDDD001DK = "/u01/app/ecpusr/upload/avatar/A060000030BKDDD001DK.pdf"; // txt文件
    
    public static final String READFILEPATH_A060000032DK = "/u01/app/ecpusr/upload/avatar/A060000032DK.pdf"; // txt文件
    public static final String READFILEPATH_A060000035DY = "/u01/app/ecpusr/upload/avatar/A060000035DY.pdf"; // txt文件
    public static final String READFILEPATH_A060000035DK = "/u01/app/ecpusr/upload/avatar/A060000035DK.pdf"; // txt文件
    public static final String READFILEPATH_A060000035DK_YD_SH = "/u01/app/ecpusr/upload/avatar/A060000035YDDK_SH.pdf"; // txt文件
    public static final String READFILEPATH_A060000035DK_WD = "/u01/app/ecpusr/upload/avatar/A060000035WDDK.pdf"; // txt文件
    public static final String READFILEPATH_A060000030BZ = "/u01/app/ecpusr/upload/avatar/A060000030BZ.pdf";
    public static final String SIZEFILENAME_A060000035DK_YD = "/u01/app/sharefs/configmanage/contractConf/A060000035/A060000035YDDK.json"; // json文件
    public static final String SIZEFILENAME_A060000035DK_YD_SH = "/u01/app/sharefs/configmanage/contractConf/A060000035/A060000035YDDKSH.json"; // json文件
    public static final String SIZEFILENAME_A060000035DK_WD = "/u01/app/sharefs/configmanage/contractConf/A060000035/A060000035WDDK.json"; // json文件
    public static final String SIZEFILENAME_A060000030BZ = "/u01/app/sharefs/configmanage/contractConf/A060000030/A060000030BZ.json"; // json文件
    // public static final String WRITEFILEPATH_A060000006 = "c:/u01/";

    // //生成的pdf文件
    // public static final String READFILEPATH_A060000003 =
    // "C:\\u01\\app\\ecpusr\\upload\\avator\\A060000003.pdf"; //txt文件

    public static final String WRITEFILEPATH_A060000003 = "/u01/app/sharefs/applydoc/C060000001/A060000003/upload/temp_pdf_file/"; // 生成的pdf文件
    public static final String WRITEFILEPATH_A060000006 = "/u01/app/sharefs/applydoc/C060000001/A060000006/upload/temp_pdf_file/"; // 生成的pdf文件
    public static final String WRITEFILEPATH_A060000011 = "/u01/app/sharefs/applydoc/C060000001/A060000011/upload/temp_pdf_file/"; // 生成的pdf文件
    public static final String WRITEFILEPATH_A060000023 = "/u01/app/sharefs/applydoc/C060000001/A060000023/upload/temp_pdf_file/"; // 生成的pdf文件
    public static final String WRITEFILEPATH_A060000026 = "/u01/app/sharefs/applydoc/C060000001/A060000026/upload/temp_pdf_file/"; // 生成的pdf文件
    public static final String WRITEFILEPATH_A060000027 = "/u01/app/sharefs/applydoc/C060000001/A060000027/upload/temp_pdf_file/"; // 生成的pdf文件
    public static final String WRITEFILEPATH_A060000033 = "/u01/app/sharefs/applydoc/C060000001/A060000033/upload/temp_pdf_file/"; // 生成的pdf文件
    public static final String WRITEFILEPATH_A060000030 = "/u01/app/sharefs/applydoc/C060000001/A060000030/upload/temp_pdf_file/"; // 生成的pdf文件
    public static final String WRITEFILEPATH_A060000032 = "/u01/app/sharefs/applydoc/C060000001/A060000032/upload/temp_pdf_file/"; // 生成的pdf文件
    public static final String WRITEFILEPATH_A060000032L = "/u01/app/sharefs/applydoc/C060000001/A060000032/download/dkht/"; // 存储滴滴贷款合同路径
	public static final String WRITEFILEPATH_A060000035 = "/u01/app/sharefs/applydoc/C060000001/A060000035/upload/temp_pdf_file/"; // 生成的pdf文件    
	
	//贝壳合同版本号
	public static final String PRDCONTVERNUM_A060000030_TSHZDK="dk_bkhz_ZHTS592";//贝壳天顺杭州（地区）版贷款合同（2019-11）
	public static final String PRDCONTVERNUM_A060000030_TSTJDK="dk_bktj_ZHTS592";//贝壳天顺天津（地区）版贷款合同（2019-11）
	public static final String PRDCONTVERNUM_A060000030_TSBJDK="dk_bkbj_ZHTS592";//贝壳天顺北京（地区）版贷款合同（2019-11）
	public static final String PRDCONTVERNUM_A060000030_TSYDDK="dk_bkyd_ZHTS592";//贝壳天顺异地通用版贷款合同（2019-11）	
	public static final String PRDCONTVERNUM_A060000030_TWHZDK="dk_bkhz_ZHTW7";//贝壳天蔚杭州（地区）版贷款合同（2019-11）
	public static final String PRDCONTVERNUM_A060000030_TWTJDK="dk_bktj_ZHTW7";//贝壳天蔚天津（地区）版贷款合同（2019-11）
	public static final String PRDCONTVERNUM_A060000030_TWBJDK="dk_bkbj_ZHTW7";//贝壳天蔚北京（地区）版贷款合同（2019-11）
	public static final String PRDCONTVERNUM_A060000030_TWYDDK="dk_bkyd_ZHTW7";//贝壳天蔚异地通用版贷款合同（2019-11）	
	public static final String PRDCONTVERNUM_A060000030_TSHZDY="dy_bkhz_ZHTS592";//贝壳天顺杭州（地区）版抵押合同（2019-11）
	public static final String PRDCONTVERNUM_A060000030_TSTJDY="dy_bktj_ZHTS592";//贝壳天顺天津（地区）版抵押合同（2019-11）
	public static final String PRDCONTVERNUM_A060000030_TSYDDY="dy_bkyd_ZHTS592";//贝壳天顺异地通用版抵押合同（2019-11）	
	public static final String PRDCONTVERNUM_A060000030_TWHZDY="dy_bkhz_ZHTW7";//贝壳天蔚杭州（地区）版抵押合同（2019-11）
	public static final String PRDCONTVERNUM_A060000030_TWTJDY="dy_bktj_ZHTW7";//贝壳天蔚天津（地区）版抵押合同（2019-11）
	public static final String PRDCONTVERNUM_A060000030_TWYDDY="dy_bkyd_ZHTW7";//贝壳天蔚异地通用版抵押合同（2019-11）
	public static final String PRDCONTVERNUM_A060000030_DDDDK="dk_bkddd_lpx30";//贝壳店东贷贷款合同（2019-11）
	public static final String PRDCONTVERNUM_A060000030_TSWHDK="dk_bkwh_ZHTS592";//贝壳天顺武汉（地区）版贷款合同（2020-02）
	public static final String PRDCONTVERNUM_A060000030_TWWHDK="dk_bkwh_ZHTW7";//贝壳天蔚武汉（地区）版贷款合同（2020-02）
	
    // 签名源字段
    public static final String ZHXT_MER_ID = "merId=";// 商户代码
    public static final String ZHXT_PRO_NO = "&proNo=";// 协议号
    public static final String ZHXT_PAY_ORDER_NO = "&payOrderNo=";// 退款原订单号
    public static final String ZHXT_ORDER_NO = "&orderNo=";// 订单号
    public static final String ZHXT_ORDER_CNY = "&orderCny=";// 交易币种
    public static final String ZHXT_ORDER_AMT = "&orderAmt=";// 订单金额
    public static final String ZHXT_ORDER_DATE = "&orderDate=";// 订单日期
    public static final String ZHXT_ORDER_TIME = "&orderTime=";// 订单时间

    public static final String ZHXT_OP_TYPE = "&opType=";// 操作类型
    public static final String ZHXT_ACC_BANK = "&accBank=";// 交易币种
    public static final String ZHXT_ACC_NAME = "&accName=";// 订单金额
    public static final String ZHXT_ACC_NO = "&accNo=";// 订单日期
    public static final String ZHXT_CERT_TYPE = "&certType=";// 订单时间
    public static final String ZHXT_CERT_ID = "&certId=";// 订单时间
    public static final String ZHXT_PHONE_NO = "&phoneNo=";// 订单时间
    public static final String ZHXT_ORDER_LIST = "&orderList=";// 订单号集合

    public static final String BZTZ = "BZTZ";// 补件通知返回信息缓存标志
    public static final String PLWJ = "PLWJ";// 补件通知返回信息缓存标志
    public static final String PAY_STATUS = "处理中";// 支付通道返回处理中状态
    public static final String BDXD_WWJR_RESP_REDIS_KEY = "BDXD_WWJR_RESP_";// 支付通道返回处理中状态

    public static final String BDXD_CALL_BACK_TRADE_KEY = "BDXDCALLBACK";// 支付通道返回处理中状态

    public static final String BXYH_WWJR_RESP_REDIS_KEY = "BXYH_WWJR_RESP_";// 支付通道返回处理中状态

    public static final String BXYH_CALL_BACK_TRADE_KEY = "BXYHCALLBACK";// 支付通道返回处理中状态
    /**
     * 从缓存获取营业日期
     * 
     * @Description
     * @return 营业日期
     */
    /*
     * public static String getOpenDay(String capitalSide) { String openDayKey =
     * "CCBC_" + capitalSide + "_openDay"; DdpCacheManager cacheManager =
     * DdpEnv.getCacheManager(); cacheManager.declareNamespace(""); String
     * openDate = DdpForRedisCache.getInsance().get(openDayKey); if
     * (!StringUtils.isEmpty(openDate)) {//营业日期不为空 return openDate; } else {
     * return "2017-01-01";//默认日期 } }
     */


    /**
     * 获取交易时间格式 yyyy-MM-dd HH:mm:ss
     * 
     * @Description
     * @return
     */
    public static String getTradeTime() {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
    }

}
