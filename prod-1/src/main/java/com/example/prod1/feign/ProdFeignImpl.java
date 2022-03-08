package com.example.prod1.feign;

import com.example.common.result.ReturnResult;
import com.example.common.util.ReturnResultUtil;
import com.example.prod1api.feign.Prod1Feign;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

/**
 * @author zgr
 * @version 1.0
 * @date 2022/2/17 15:48
 */

@ApiIgnore
@RestController
@Slf4j
@RequiredArgsConstructor
@RequestMapping(produces = {"application/json;charset=UTF-8"})
public class ProdFeignImpl implements Prod1Feign {

    @Override
    @GetMapping(TEST_FEIGN)
    public ReturnResult<String> testFeign(String test) {
        System.out.println("Ceshifeignzzzz："+test);
        return ReturnResultUtil.success(test+"zzzzzz");
    }
    @Override
    @GetMapping(TEST_FEIGN_TWO)
    public ReturnResult<String> testFeign2(String sn) {
        System.out.println("Ceshifeignssss："+sn);
        return ReturnResultUtil.success(sn+"ssssssss");
    }
}
