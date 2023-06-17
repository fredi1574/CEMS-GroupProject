package util;

import java.io.Serializable;

/**
 * This class represents a connected client to the server. It stores the client's IP, host, and connection status.
 */
public class ConnectToClients implements Serializable {

    private final String ip;
    private String host;
    private String status;

    /**
     * Constructs a ConnectToClients object with the specified IP, host, and connection status.
     *
     * @param ip     The IP address of the client.
     * @param host   The host name of the client.
     * @param status The connection status of the client.
     */
    public ConnectToClients(String ip, String host, String status) {
        this.ip = ip;
        this.host = host;
        this.status = status;
    }

    /**
     * Retrieves the IP address of the client.
     *
     * @return The IP address of the client.
     */
    public String getIp() {
        return ip;
    }

    /**
     * Retrieves the host name of the client.
     *
     * @return The host name of the client.
     */
    public String getHost() {
        return host;
    }

    /**
     * Sets the host name of the client.
     *
     * @param host The host name of the client.
     */
    public void setHost(String host) {
        this.host = host;
    }

    /**
     * Retrieves the connection status of the client.
     *
     * @return The connection status of the client.
     */
    public String getStatus() {
        return status;
    }

    /**
     * Sets the connection status of the client.
     *
     * @param status The connection status of the client.
     */
    public void setStatus(String status) {
        this.status = status;
    }
}
