package com.example.common.structure;

/**
 * @author zgr
 * @version 1.0
 * @date 2022/2/28 17:56
 */


public interface TokenStructure {
    String TOKEN_INFO_KEY = "user";
    // token 签名的秘钥，可设置到配置文件中
    String SECRET_KEY = "secretKey:123456";
    // token过期时间
    long TOKEN_EXPIRE_TIME = 7200 * 1000;
}
