package com.example.connect.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zgr
 * @version 1.0
 * @date 2022/1/19 16:45
 */

@RestController
@Api(tags = "测试类")
public class TestController {

    @ApiOperation("测试swagger")
    @PostMapping("/test")
    public void test() {
        System.out.println("testSwagger");
    }
}
