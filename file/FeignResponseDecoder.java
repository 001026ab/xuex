package com.ufix.xnestv3.live.config;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.alibaba.cloud.commons.io.IOUtils;
import com.ufix.xnestv3.common.base.exception.MyException;
import com.ufix.xnestv3.common.result.bean.MyResult;
import feign.Response;
import feign.codec.Decoder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;

import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;

/**
 * ClassName: q
 *
 * @Author: WangYiHai
 * @Date: 2021/11/17 15:42
 * @Description: TODO
 */
@Slf4j
public class FeignResponseDecoder implements Decoder {
    private final Decoder delegate;

    public FeignResponseDecoder(@Lazy Decoder delegate) {
        this.delegate = delegate;
    }

    @Override
    public Object decode(Response response, Type type) throws IOException {
        // 判断是否返回参数是否是异常
        String resultStr = IOUtils.toString(response.body().asInputStream(), StandardCharsets.UTF_8);
        if (StrUtil.isEmpty(resultStr)) {
            throw new MyException("接收MyResult结果为空");
        }
        // 拿到返回值，进行自定义逻辑处理
        MyResult myResult = JSONUtil.toBean(resultStr, MyResult.class);
        if (!myResult.isResultStatus()) {
            throw new MyException(myResult.getErrorMessage());
        }
        // 回写body,因为response的流数据只能读一次，这里回写后重新生成response
        return delegate.decode(response.toBuilder().body(resultStr, StandardCharsets.UTF_8).build(), type);
    }
}