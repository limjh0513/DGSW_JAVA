package nio;

import java.net.InetSocketAddress;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

public class NioBlockingServer {
    private ServerSocketChannel serverSocketChannel;

    public void start(int port) throws Exception {
        serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.bind(new InetSocketAddress(port));
        serverSocketChannel.configureBlocking(true);

        while (true) {
            try {
                SocketChannel socketChannel = serverSocketChannel.accept();
                SocketChannelAgent agent = new SocketChannelAgent(socketChannel);
                agent.init();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
