package com.example.connect.websocket.holder;

import io.netty.channel.ChannelId;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * @author zgr
 * @version 1.0
 * @date 2022/1/17 15:58
 */


public class WsSuperise {
    private final static ChannelGroup globalGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
    private final static ConcurrentMap<String, Set<ChannelId>> snMap = new ConcurrentHashMap<>();
    private final static ConcurrentMap<ChannelId, String> channelMap = new ConcurrentHashMap<>();

    /**
     * 关联关系，绑定SN与前端的通道
     *
     * @param sn sn
     * @param id id
     */
    public static void bindChannel(String sn, ChannelId id) {
        if (sn == null || id == null) {
            return;
        }
        Set<ChannelId> channelIds = snMap.get(sn);
        if (channelIds == null) {
            channelIds = new HashSet<>();
        }
        channelIds.add(id);
        snMap.put(sn, channelIds);
        //反向映射
        channelMap.put(id, sn);
    }
}
