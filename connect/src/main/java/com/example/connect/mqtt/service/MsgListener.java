package com.example.connect.mqtt.service;

import lombok.extern.slf4j.Slf4j;
import net.dreamlu.iot.mqtt.core.server.event.IMqttMessageListener;
import net.dreamlu.iot.mqtt.core.server.model.Message;
import org.springframework.stereotype.Service;
import org.tio.core.ChannelContext;

/**
 * @author zgr
 * @version 1.0
 * @date 2022/1/27 17:51
 */

@Service
@Slf4j
public class MsgListener implements IMqttMessageListener {
    @Override
    public void onMessage(ChannelContext context, String clientId, Message message) {

    }
}
