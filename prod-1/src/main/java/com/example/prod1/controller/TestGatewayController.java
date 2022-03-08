package com.example.prod1.controller;

import com.example.common.util.JWTUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zgr
 * @version 1.0
 * @date 2022/1/5 10:44
 */
@Slf4j
@RestController
@RequestMapping(value = "/v1", produces = {"application/json;charset=UTF-8"})
@Api(tags = "测试控制类")
public class TestGatewayController {

    @Value("${server.port}")
    String port;
    @ApiOperation("测试网关")
    @GetMapping("testGateway")
    public String testGateway() {
        System.out.println("测试网关");
        //测试svn集成idea
        System.out.println(JWTUtils.getToken());
        return "测试网关"+port;
    }


}
