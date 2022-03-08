package com.example.prod1api.feign;

import com.example.common.result.ReturnResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author zgr
 * @version 1.0
 * @date 2022/2/17 15:39
 */

@FeignClient(value = "nacos-nginx-prod-1",contextId = "prod1Feign")
public interface Prod1Feign {
    String TEST_FEIGN = "/prod1/test1";
    String TEST_FEIGN_TWO = "/prod1/test2/{sn}";


    /**
     * @return
     */
    @GetMapping(TEST_FEIGN)
    ReturnResult<String> testFeign(@RequestParam("test") String test);
    /**
     * @return
     */
    @GetMapping(TEST_FEIGN_TWO)
    ReturnResult<String> testFeign2(@PathVariable("sn") String sn);
}
