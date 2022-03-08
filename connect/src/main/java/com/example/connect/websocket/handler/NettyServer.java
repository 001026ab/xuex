package com.example.connect.websocket.handler;


import com.example.connect.websocket.server.WsHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.stream.ChunkedWriteHandler;
import io.netty.handler.timeout.IdleStateHandler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.concurrent.TimeUnit;

/**
 * @author zgr
 * @version 1.0
 * @date 2022/1/11 14:21
 */

@Component
public class NettyServer {

    @Value("${ws.port}")
    private int port;

    /* @PostConstruct*/
    private void start() throws Exception {
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup group = new NioEventLoopGroup();
        try {
            ServerBootstrap sb = new ServerBootstrap();
            sb.option(ChannelOption.SO_BACKLOG, 1024);
            sb.group(group, bossGroup)
                    .channel(NioServerSocketChannel.class)
                    .localAddress(this.port)
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            System.out.println("收到新连接:" + ch.localAddress());
                         /*   ch.pipeline().addLast(new HttpServerCodec());
                            ch.pipeline().addLast(new ChunkedWriteHandler());
                            ch.pipeline().addLast(new HttpObjectAggregator(8192));
                            // ch.pipeline().addLast(new WebSocketServerProtocolHandler("/ws", "WebSocket", true, 65536 * 10));
                            ch.pipeline().addLast(new WsHandler(port));*/
                            //将请求和应答消息解码为http消息
                            ch.pipeline().addLast("http-codec", new HttpServerCodec());//设置解码器
                            //将http消息的多个部分合成一条完整的http消息
                            ch.pipeline().addLast("aggregator", new HttpObjectAggregator(65536));//聚合器，使用websocket会用到
                            //向客户端发送html5文件
                            ch.pipeline().addLast("http-chunked", new ChunkedWriteHandler());//用于大数据的分区传输
                            //设置心跳检测
                            ch.pipeline().addLast(new IdleStateHandler(60, 30, 60 * 30, TimeUnit.SECONDS));
                            //配置通道处理，来进行业务处理
                            ch.pipeline().addLast("handler", new WsHandler(port));
                        }
                    });
            //}).option(ChannelOption.SO_BACKLOG,1024).childOption(ChannelOption.SO_KEEPALIVE,true);
            ChannelFuture cf = sb.bind(port).sync();
            cf.channel().closeFuture().sync();
        } finally {
            //关闭资源
            group.shutdownGracefully().sync();
            bossGroup.shutdownGracefully().sync();
        }
    }

    /**
     * 需要开启一个新的线程来执行nettyServer服务器
     */
    @PostConstruct
    public void init() {
        new Thread(new Runnable() {
            public void run() {
                try {
                    start();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    /**
     * 需要开启一个新的线程来执行nettyServer服务器,线程池
     */
   /* @PostConstruct
    @Async
    public void initWsConnect() throws Exception {
        start();
    }*/
}