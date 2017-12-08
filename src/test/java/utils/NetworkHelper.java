package utils;

import java.io.IOException;
import java.net.ServerSocket;

final class NetworkHelper {

    private NetworkHelper() {

    }

    static int getFreeLocalPort() {
        try {
            ServerSocket serverSocket = new ServerSocket(0);
            int port = serverSocket.getLocalPort();
            serverSocket.close();

            return port;
        } catch (IOException exception) {
            throw new RuntimeException(exception);
        }
    }
}