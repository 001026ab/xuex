package com.example.connect.mqtt.handler;

import net.dreamlu.iot.mqtt.core.server.auth.IMqttServerAuthHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.tio.core.ChannelContext;

/**
 * 连接mqtt，并不是在配置文件中连接
 *
 * @author ReStartLin
 * @date 2021/9/24 16:51
 * @description: 功能描述:
 */
@Service
public class AuthHandler implements IMqttServerAuthHandler {
    private static final Logger logger = LoggerFactory.getLogger(IMqttServerAuthHandler.class);
    @Value("${mqtt.server.auth-username}")
    private String authUserName;

    @Value("${mqtt.server.auth-password}")
    private String authPassword;


    @Override
    public boolean authenticate(ChannelContext channelContext,
                                String s, String s1, String s2, String s3) {
        return authUserName.equals(s2) && authPassword.equals(s3);
    }
}
