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


	private String ip;
	private String host;
	private String status;

	public ConnectToClients(String ip, String host, String status) {
		this.ip = ip;
		this.host = host;
		this.status = status;
	}

	//NOTE: for whatever reason capitalizing 'Ip' breaks the function...
	public String getIp() {

		//TODO: fix ip retrieval via this method (if it is necessary)
//		String IP = null;
//		try {
//			IP = Inet4Address.getLocalHost().getHostAddress();
//		}catch (UnknownHostException exception){
//			exception.printStackTrace();
//		}
//
//
//		return IP;
		return ip;
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
