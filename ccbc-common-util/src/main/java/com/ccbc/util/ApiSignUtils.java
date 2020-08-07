package com.ccbc.util;

import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import com.ccbc.util.base64.Base64;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;


import sun.misc.BASE64Decoder;

/**
 *	接口签名工具类 
 * @author louguanyang
 *
 */
@SuppressWarnings("restriction")
@Slf4j
public class ApiSignUtils {
    /**
     * 空字符串 “”
     */
    private static final String BLANK = "";
    /**
     * 字符 “&”
     */
    private static final String AND = "&";
    /**
     * 字符 “=”
     */
    private static final String EQUAL = "=";

    /**
     * MD5_WITH_RSA数字签名
     * @param content
     * @param sign
     * @param merId
     * @return
     */
    public static boolean rsaCheckContent(String content, String sign, String publicKey) {
        try {
            if (StringUtils.isEmpty(sign) || StringUtils.isEmpty(publicKey)) {
                log.error("验签失败，sign签名为空，或者商户未上传公钥！");
                return false;
            }
            PublicKey pubKey = getPublicKey(publicKey);
            Signature signature = Signature.getInstance("MD5withRSA");
            signature.initVerify(pubKey);
            signature.update(content.getBytes());
            return signature.verify(Base64.decode(sign.getBytes()));
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 获取公钥
     * @param merId
     * @return
     */
    private static PublicKey getPublicKey(String publicKey) {
        try {
            KeyFactory keyFactory = KeyFactory.getInstance("RSA");
            byte[] encodedKey = Base64.decode(publicKey);
            PublicKey pubKey = keyFactory.generatePublic(new X509EncodedKeySpec(encodedKey));
            return pubKey;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (InvalidKeySpecException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * RSA 签名
     * @param content
     * @param privateKey
     * @return
     */
    public static String rsaSign(String content, String privateKey) {
        try {
            PrivateKey priKey = getPrivateKeyFromPKCS8("RSA", privateKey);

            Signature signature = Signature.getInstance("MD5withRSA");

            signature.initSign(priKey);

            signature.update(content.getBytes());

            byte[] signed = signature.sign();

            return new String(Base64.encode(signed));
        } catch (InvalidKeySpecException e) {
            log.error("RSA私钥格式不正确，请检查是否正确配置了PKCS8格式的私钥！", e);
        } catch (Exception e) {
            log.error("签名错误！", e);
        }
        return null;
    }

    /**
     * 获取PKCS8格式私钥
     * @param algorithm
     * @param privateKey
     * @return
     * @throws Exception
     */
    public static PrivateKey getPrivateKeyFromPKCS8(String algorithm, String privateKey) throws Exception {
        PKCS8EncodedKeySpec priPKCS8 = new PKCS8EncodedKeySpec(new BASE64Decoder().decodeBuffer(privateKey));
        KeyFactory keyf = KeyFactory.getInstance(algorithm);
        PrivateKey priKey = keyf.generatePrivate(priPKCS8);
        return priKey;
    }

    /**
     * 从请求参数中获取待签名字符串
     * @param params
     * @return
     */
    public static String getSignCheckContent(Map<String, String> params) {
        if (params == null || params.isEmpty()) {
            return null;
        }
        StringBuffer content = new StringBuffer();
        List<String> keys = new ArrayList<String>(params.keySet());
        Collections.sort(keys);
        for (int i = 0; i < keys.size(); i++) {
            String key = keys.get(i);
            String value = params.get(key);
            value = StringUtils.isEmpty(value) ? "" : value;
            content.append((i == 0 ? BLANK : AND) + key + EQUAL + value);
        }
        return content.toString();
    }

}
