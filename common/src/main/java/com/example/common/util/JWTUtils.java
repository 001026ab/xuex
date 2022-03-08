package com.example.common.util;

/**
 * @author zgr
 * @version 1.0
 * @date 2022/2/28 15:05
 */


import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.example.common.structure.TokenStructure;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * jwt工具类
 *
 * @author stwen_gan
 * @since
 */
@Slf4j
public class JWTUtils {


    /**
     * 生成jwt
     */
    public static String createJwt(String userId) {
        Algorithm algorithm = Algorithm.HMAC256(TokenStructure.SECRET_KEY);
        //设置头信息
        HashMap<String, Object> header = new HashMap<>(2);
        header.put("typ", "JWT");
        header.put("alg", "HS256");
        // 生成 token：头部+载荷+签名
        return JWT.create().withHeader(header)
                .withClaim(TokenStructure.TOKEN_INFO_KEY, userId)
                .withExpiresAt(new Date(System.currentTimeMillis() + TokenStructure.TOKEN_EXPIRE_TIME)).sign(algorithm);
    }

    /**
     * 解析jwt
     */
    public static Map<String, Claim> parseJwt(String token) {
        Map<String, Claim> claims = null;
        try {
            Algorithm algorithm = Algorithm.HMAC256(TokenStructure.SECRET_KEY);
            JWTVerifier verifier = JWT.require(algorithm).build();
            DecodedJWT jwt = verifier.verify(token);
            claims = jwt.getClaims();
            return claims;
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * 从token中获取用户信息
     */
    public static String getUserInfo() {
        return null;
    }

    public static String getToken() {
        //HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
        RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
        if (requestAttributes == null) {
            log.warn("获取请求requestAttributes为空");
            return null;
        }
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) requestAttributes;
        HttpServletRequest request = servletRequestAttributes.getRequest();

        if (request == null) {
            log.warn("获取请求HttpServletRequest为空");
            return null;
        }
        return request.getHeader("token");
    }
}