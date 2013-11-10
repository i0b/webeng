package de.uulm.mi.web.http;

import java.net.Socket;
import java.util.Date;

public class HTTPConnections {
	private Socket socket;
	private int max_connections;
	private long timestamp;
	private int timeout;
	
	public HTTPConnections(Socket socket, int timeout, int max_connections) {
		this.socket=socket;
		this.timeout = timeout;
		this.max_connections = max_connections;
	}
	public HTTPConnections(int timeout, int max_connections) {
		this.timeout = timeout;
		this.max_connections = max_connections;
	}
	
	public Socket getSocket() {
		return this.socket;
	}
	public void update() {
		timeout--;
		timestamp = new Date().getTime();
		
	}
	
	public int getTimeout() {
		return this.timeout;
	}
	public int getMaxConnections() {
		return this.max_connections;
	}
	public boolean keepAlive() {
		long new_timestamp = new Date().getTime();
		if( ((new_timestamp - timestamp)<timeout) &&(this.max_connections>0) ) {
				update();
				return true;
		}

		return false;
		
	}
}
