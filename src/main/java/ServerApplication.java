import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;

/**
 * 游戏服务器主入口类
 */
public class ServerApplication {
    /**
     * 应用主函数
     *
     * @param argArray 参数数组
     */
    static public void main(String[] argArray) {
        /**
         * 2个线程池
         * 一个用于接受请求的链接 但不处理
         * 一个用于处理链接的请求
         * 采用时间驱动 ---- 轮询
         */
        EventLoopGroup acceptGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();

        ServerBootstrap b = new ServerBootstrap();
        b.group(acceptGroup, workerGroup);
        b.channel(NioServerSocketChannel.class); // 服务器信道的处理方式
        b.childHandler(new ChannelInitializer<SocketChannel>() { // 客户端信道的处理器方式
            @Override
            protected void initChannel(SocketChannel ch) throws Exception {
                ch.pipeline().addLast(
                    new HttpServerCodec(), // Http 服务器编解码器
                    new HttpObjectAggregator(65535), // 内容长度限制
                    new WebSocketServerProtocolHandler("/websocket"), // WebSocket 协议处理器, 在这里处理握手、ping、pong 等消息
                    new MsgHandler() // 自定义的消息处理器
                );
            }
        });

        try {
            // 绑定 12345 端口,
            // 注意: 实际项目中会使用 argArray 中的参数来指定端口号
            ChannelFuture f = b.bind(12345).sync();

            if (f.isSuccess()) {
                System.out.println("服务器启动成功");
            }

            // 等待服务器信道关闭,
            // 也就是不要退出应用程序,
            // 让应用程序可以一直提供服务
            f.channel().closeFuture().sync();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
