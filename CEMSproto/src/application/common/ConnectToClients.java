package application.common;

import java.io.Serializable;
import java.net.Inet4Address;
import java.net.UnknownHostException;

/**
*This class is a class that stores the connect clients to our server
*each time a client connects\disconnects to our server we will display his IP,Host,Connection Status
*so we need to save the Clients data with this Entity
 **/
@SuppressWarnings("serial")
public class ConnectToClients implements Serializable {


	private String IP;
	private String host;
	private String status;

	public ConnectToClients(String IP, String host, String status) {
		this.IP = IP;
		this.host = host;
		this.status = status;
	}
	public void setIP(String ip) {
		this.IP = IP;
	}
	public String getIP() {
		String IP = null;
		try {
			IP = Inet4Address.getLocalHost().getHostAddress();
		}catch (UnknownHostException exception){
			exception.printStackTrace();
		}


		return IP;
	}
	public void setHost(String host) {
		this.host = host;
	}
	public String getHost() {
		return host;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getStatus() {
		return status;
	}
}
