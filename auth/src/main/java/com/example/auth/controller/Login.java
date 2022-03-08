package com.example.auth.controller;

import cn.hutool.json.JSONUtil;
import com.example.auth.model.User;
import com.example.common.util.JWTUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zgr
 * @version 1.0
 * @date 2022/2/28 15:09
 * 登录生成jwt
 */

@RestController
public class Login {
    @PostMapping("/auth/login")
    public String login() {
        User user = new User();
        user.setId(123213L);
        user.setName("张三");
        String s = JSONUtil.toJsonStr(user);
        //jwt生成token
        String token = JWTUtils.createJwt(s);
        return token;
    }
}
