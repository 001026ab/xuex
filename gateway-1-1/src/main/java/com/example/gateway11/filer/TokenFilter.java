package com.example.gateway11.filer;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.auth0.jwt.interfaces.Claim;
import com.example.common.result.ReturnResult;
import com.example.common.structure.TokenStructure;
import com.example.common.util.JWTUtils;
import com.example.common.util.ReturnResultUtil;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Map;
import java.util.function.Consumer;

/**
 * @author zgr token过滤器
 * @version 1.0
 * @date 2022/2/28 15:49
 * 走网关的都要经过token校验
 */

@Slf4j
@Component
public class TokenFilter implements GlobalFilter, Ordered {

    /**
     * 校验token
     *
     * @param exchange
     * @param chain
     * @return
     */
    @Value("${check.token}")
    private String ifCheckToken;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        //这个过滤器阻碍了swagger的展示
        ServerHttpRequest request = exchange.getRequest();
        String token = request.getHeaders().getFirst("token");
        System.out.println("token值" + token);
        ServerHttpRequest.Builder mutate = request.mutate();
        //不进行校验
        if (true) {
            //关闭校验：测试环境使用。默认用户:超级管理员
            return chain.filter(exchange);
            // mutate.header(JWT_TOKEN_HEADER, "eyJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJ1c2VySWQiOjE0MzczMjgxMTAwMDAyOTU5MzgsInRva2VuX2NyZWF0ZSI6MTYzMTk2MjU5MTAyMn0.mFP--YUUeTF85Nb40AmlW4U5LxTPEldq6VrLUrZUe5yxib0dhIypsg01Do8vsaLyzNBLDTEneiQQOwnOP-MGxW-L2Y1gd7CbmtjFaVWjhk4yCpcTrWvBV5Hd5B9zn00z_cz0wttpbo9lI7Z7mjbgqQSnD1PFDsScJk7W4OUeTx_xE9lNvL6JgsEwka9rFHQeUBjahuoGeKCKNlajYgC5yrqZL5y5D9uXkIT7yGq91t9R4yqPVwcbDHW4yeJonp01oU7yfUi1bOZcG3-vD4KC07BQKJeTQEWunNWoeZRBGxrwnYRswxiJkMlElU5XBHQXo3MBafXh0fYuC4e4rfrMxg");
        }

        //检查token是否为空
        if (StringUtils.isEmpty(token)) {
            return denyAccess(exchange, ReturnResultUtil.error("token为空"));
        }
        //jwt解析，获取加密之前的数据
        Map<String, Claim> claimMap1 = JWTUtils.parseJwt(token);
        if (claimMap1 == null) {
            return denyAccess(exchange, ReturnResultUtil.error("token错误"));
        }
        //token有误
      /*  assert claimMap1 != null;
        if (claimMap1.containsKey("exp")) {
            log.error(claimMap1.get("exp").toString());
            return denyAccess(exchange, ReturnResultUtil.error("token错误"));
        }*/

        //token无误，将用户信息设置进header中,传递到下游服务
        String userId = claimMap1.get(TokenStructure.TOKEN_INFO_KEY).asString();
        System.out.println("用户信息：" + userId);
        Consumer<HttpHeaders> headers = httpHeaders -> {
            httpHeaders.add(TokenStructure.TOKEN_INFO_KEY, userId);
        };
        request.mutate().headers(headers).build();
        // todo 权限校验

        return chain.filter(exchange);
    }

    /**
     * 拦截并返回自定义的json字符串
     */
    private Mono<Void> denyAccess(ServerWebExchange exchange, ReturnResult<String> resultCode) {
        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(HttpStatus.OK);
        //这里在返回头添加编码，否则中文会乱码
        response.getHeaders().add("Content-Type", "application/json;charset=UTF-8");
        byte[] bytes = JSON.toJSONBytes(resultCode, SerializerFeature.WriteMapNullValue);
        DataBuffer buffer = response.bufferFactory().wrap(bytes);
        return response.writeWith(Mono.just(buffer));
    }

    @Override
    public int getOrder() {
        return -1;
    }

}