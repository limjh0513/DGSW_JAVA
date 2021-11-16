package nio;

import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Set;

public class NioNonblockingServer {

    private ServerSocketChannel serverSocketChannel;

    public void start(int port) throws Exception {
        Selector selector = Selector.open();

        serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.bind(new InetSocketAddress(port));
        serverSocketChannel.configureBlocking(false);

        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

        while (true) {
            int count = selector.select();
            Set<SelectionKey> keys = selector.keys();

            for (SelectionKey key : keys) {
                if (key.isAcceptable()) {
                    ServerSocketChannel channel = (ServerSocketChannel) key.channel();
                    SocketChannel socketChannel = channel.accept();
                } else if (key.isReadable()) {

                } else if (key.isWritable()) {

                }
            }
        }
    }

    public static void main(String[] args) {
        try {
            NioNonblockingServer server = new NioNonblockingServer();
            server.start(3200);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
