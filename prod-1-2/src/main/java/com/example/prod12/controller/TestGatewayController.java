package com.example.prod12.controller;


import com.example.common.result.ReturnResult;
import com.example.common.util.ReturnResultUtil;
import com.example.prod1api.feign.Prod1Feign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zgr
 * @version 1.0
 * @date 2022/1/5 10:44
 */

@RestController
@RequestMapping(value = "/v1", produces = {"application/json;charset=UTF-8"})
public class TestGatewayController {

    @Autowired
    private Prod1Feign prod1Feign;
    @Value("${server.port}")
    String port;

    /**
     * openFeign
     * 做负载均衡测试与prod-1 ，，注解@EnableFeignClients(basePackages = {"com.example.**"})
     *
     * @return
     */
    @GetMapping("testGateway")
    public ReturnResult<String> testGateway() {
        System.out.println("测试网关");
        ReturnResult<String> stringReturnResult = prod1Feign.testFeign("12321");
        String data = stringReturnResult.getData();
        System.out.println(data);
        String sn = prod1Feign.testFeign2("sn").getData();
        System.out.println(sn);
        return ReturnResultUtil.success("测试网关" + port);
    }


}
