package com.github.lalifeier.mall.cloud.common.utils.net;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ThreadLocalRandom;
import javax.net.ServerSocketFactory;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class SocketUtil {
    private static final Logger logger = LoggerFactory.getLogger(SocketUtil.class);

    public static final int PORT_RANGE_MIN = 1024;
    public static final int PORT_RANGE_MAX = 65535;

    private static final ThreadLocalRandom random = ThreadLocalRandom.current();

    /**
     * 测试端口是否空闲可用
     */
    public static boolean isPortAvailable(int port) {
        try (ServerSocket serverSocket =
                ServerSocketFactory.getDefault().createServerSocket(port, 1, InetAddress.getByName("localhost"))) {
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    /**
     * 从1024到65535，随机找一个空闲端口
     */
    public static int findRandomAvailablePort() {
        return findRandomAvailablePort(PORT_RANGE_MIN, PORT_RANGE_MAX);
    }

    /**
     * 在范围里随机找一个空闲端口
     *
     * @throws IllegalStateException 如果尝试次数超过范围或无空闲端口
     */
    public static int findRandomAvailablePort(int minPort, int maxPort) {
        int portRange = maxPort - minPort;
        int candidatePort;
        int searchCounter = 0;

        do {
            if (++searchCounter > portRange) {
                throw new IllegalStateException(String.format(
                        "Could not find an available tcp port in the range [%d, %d] after %d attempts",
                        minPort, maxPort, searchCounter));
            }
            candidatePort = minPort + random.nextInt(portRange + 1);
        } while (!isPortAvailable(candidatePort));

        return candidatePort;
    }

    /**
     * 从某个端口开始，递增直到65535，找一个空闲端口
     *
     * @throws IllegalStateException 如果范围内无空闲端口
     */
    public static int findAvailablePortFrom(int minPort) {
        for (int port = minPort; port < PORT_RANGE_MAX; port++) {
            if (isPortAvailable(port)) {
                return port;
            }
        }

        throw new IllegalStateException(
                String.format("Could not find an available tcp port in the range [%d, %d]", minPort, PORT_RANGE_MAX));
    }

    /**
     * 是否可以telnet
     *
     * @param ip      远程地址
     * @param port    远程端口
     * @param timeout 连接超时
     * @return 是否可连接
     */
    public static boolean canTelnet(String ip, int port, int timeout) {
        try (Socket socket = new Socket()) {
            socket.connect(new InetSocketAddress(ip, port), timeout);
            return socket.isConnected() && !socket.isClosed();
        } catch (Exception e) {
            return false;
        }
    }
}
