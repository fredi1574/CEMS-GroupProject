package util;

import java.io.Serializable;

/**
 * This class is a class that stores the connect clients to our server
 * each time a client connects\disconnects to our server we will display his IP,Host,Connection Status
 * so we need to save the Clients data with this Entity
 **/
@SuppressWarnings("serial")
public class ConnectToClients implements Serializable {

    private final String ip;
    private String host;
    private String status;

    public ConnectToClients(String ip, String host, String status) {
        this.ip = ip;
        this.host = host;
        this.status = status;
    }

    //NOTE: for whatever reason capitalizing 'Ip' breaks the function...
    public String getIp() {
        return ip;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
