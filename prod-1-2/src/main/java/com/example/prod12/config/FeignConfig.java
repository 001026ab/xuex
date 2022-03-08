package com.example.prod12.config;

import feign.FeignException;
import feign.Response;
import feign.codec.DecodeException;
import feign.codec.Decoder;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.openfeign.support.ResponseEntityDecoder;
import org.springframework.cloud.openfeign.support.SpringDecoder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * implements Decoder
 *
 * @author zgr
 * @version 1.0
 * @date 2022/2/17 16:52
 * 解码器
 */

@SpringBootConfiguration
public class FeignConfig {

    /**
     * 一
     *
     * @return
     */
    @Bean
    public Decoder feignDecoder() {
        return new ResponseEntityDecoder(new SpringDecoder(feignHttpMessageConverter()));
    }

    public ObjectFactory<HttpMessageConverters> feignHttpMessageConverter() {
        final HttpMessageConverters httpMessageConverters = new HttpMessageConverters(new GateWayMappingJackson2HttpMessageConverter());
        return new ObjectFactory<HttpMessageConverters>() {
            @Override
            public HttpMessageConverters getObject() throws BeansException {
                return httpMessageConverters;
            }
        };
    }


    public class GateWayMappingJackson2HttpMessageConverter extends MappingJackson2HttpMessageConverter {
        GateWayMappingJackson2HttpMessageConverter() {
            List<MediaType> mediaTypes = new ArrayList<MediaType>();
            mediaTypes.add(MediaType.valueOf(MediaType.TEXT_HTML_VALUE + ";charset=UTF-8"));
            setSupportedMediaTypes(mediaTypes);
        }
    }

    /**
     * 二
     * 一与二不可共存
     *
     * @param response
     * @param type
     * @return
     * @throws IOException
     * @throws DecodeException
     * @throws FeignException
     */
/*    @Override
    public Object decode(Response response, Type type) throws IOException, DecodeException, FeignException {
        // 判断是否返回参数是否是异常
       *//* String resultStr = IOUtils.toString(response.body().asInputStream(), String.valueOf(StandardCharsets.UTF_8));
        if (StrUtil.isEmpty(resultStr)) {
            throw new MyException("接收MyResult结果为空");
        }
        // 拿到返回值，进行自定义逻辑处理
        MyResult myResult = JSONUtil.toBean(resultStr, MyResult.class);
        if (!myResult.isResultStatus()) {
            throw new MyException(myResult.getErrorMessage());
        }*//*
        return null;
    }*/

}



