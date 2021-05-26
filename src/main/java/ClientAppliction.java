import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.io.IOException;

/**
 * @Author: hataki
 * @Date: 2021/5/26
 * Time: 17:17
 * description:
 */
public class ClientAppliction {

    public static void main(String[] args) {
        new ClientAppliction().connect("127.0.0.1", 12345);
    }

    private void connect(String inetHost, int inetPort) {
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            Bootstrap b = new Bootstrap();
            b.group(workerGroup);
            b.channel(NioSocketChannel.class);
            b.option(ChannelOption.AUTO_READ, true);
            b.handler(new ClinetHandler());
            ChannelFuture f = b.connect(inetHost, inetPort).sync();
            while(true){
                System.in.read();
                f.channel().closeFuture().sync();
            }


        } catch (InterruptedException | IOException e) {
            e.printStackTrace();
        } finally {
            workerGroup.shutdownGracefully();
        }
    }
}
