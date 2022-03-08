package com.example.connect.websocket.server;


import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketServerHandshaker;
import io.netty.handler.codec.http.websocketx.WebSocketServerHandshakerFactory;
import io.netty.util.concurrent.GlobalEventExecutor;

/**
 * @author zgr
 * @version 1.0
 * @date 2022/1/11 14:25
 */

@ChannelHandler.Sharable
public class WsHandler extends SimpleChannelInboundHandler<Object> {

    public static ChannelGroup channelGroup;

    private int port;

    public WsHandler(int port) {
        this.port = port;
    }

    static {
        channelGroup = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
    }

    /**
     * 客户端与服务器建立连接的时候触发
     *
     * @param ctx
     * @throws Exception
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("与客户端建立连接，通道开启！");
        // 添加到channelGroup通道组
        channelGroup.add(ctx.channel());
    }


    /**
     * 客户端与服务器关闭连接的时候触发
     */
    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("与客户端建立连接，通道关闭！");
        channelGroup.remove(ctx.channel());
    }

    /**
     * 第一次请求进来时要进行握手，否则连接将会断开
     * 服务器接受客户端的数据信息
     */
    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("服务器收到的数据：" + msg);
        if (msg instanceof FullHttpRequest) {
            //以http请求形式接入，但是走的是websocket
            handleHttpRequest(ctx, (FullHttpRequest) msg);
            return;
        }

        if (!(msg instanceof TextWebSocketFrame)) {
            return;
        }
        TextWebSocketFrame tsf = (TextWebSocketFrame) msg;
        String msgObj = tsf.text();

        if (!JSONUtil.isJson(msgObj)) {
            //如果不是json型数据则返回
            return;
        }

        //前端传过来的数据
        JSONObject jsonObject = JSONUtil.parseObj(msgObj);
        //System.out.println("jsonObject," + jsonObject);
        // TextWebSocketFrame tsf = (TextWebSocketFrame) msg;
        //信息处理方法，数据，数据中的动作，通道
        parseAction(jsonObject, null, ctx.channel());
        //sendMessage(ctx, (TextWebSocketFrame) msg);
        //sendAllMessage();
    }

    /**
     * 接收前端的Ws数据进行数据处理
     *
     * @param jsonObject
     * @param action
     * @param channel
     */
    private void parseAction(JSONObject jsonObject, String action, Channel channel) {
        sendMessage(channel, new TextWebSocketFrame(String.valueOf(jsonObject)));
        sendAllMessage();
    }

    /**
     * 给固定的人发消息
     */
    private void sendMessage(Channel channel, TextWebSocketFrame msg) {
        String message = msg.text() + " --- 你好，" + channel.localAddress() + " 给固定的人发消息";
        channel.writeAndFlush(new TextWebSocketFrame(message));
    }

    /**
     * 发送群消息，此时其他客户端也能收到群消息
     */
    private void sendAllMessage() {
        String message = "我是服务器，这里发送的是群消息";
        channelGroup.writeAndFlush(new TextWebSocketFrame(message));
    }


    /**
     * 唯一的一次http请求，用于创建websocket
     */
    private void handleHttpRequest(ChannelHandlerContext ctx,
                                   FullHttpRequest req) {
        //要求Upgrade为websocket，过滤掉get/Post
        if (!req.decoderResult().isSuccess()
                || (!"websocket".equals(req.headers().get("Upgrade")))) {
            return;
        }
        WebSocketServerHandshakerFactory wsFactory = new WebSocketServerHandshakerFactory(
                "ws://localhost:" + this.port + "/websocket", null, false);
        WebSocketServerHandshaker shaker = wsFactory.newHandshaker(req);
        if (shaker == null) {
            WebSocketServerHandshakerFactory
                    .sendUnsupportedVersionResponse(ctx.channel());
        } else {
            shaker.handshake(ctx.channel(), req);
        }
    }
}
