package de.uulm.mi.web.http.impl;

import java.util.Map;

import de.uulm.mi.web.http.HttpMessage;
import de.uulm.mi.web.http.HttpVersion;

/**
 * An interface for basic HTTP message operations.
 * 
 * @author Leo Hnatek
 * 
 */
public class BasicHttpMessage implements HttpMessage {
	
	private HttpVersion httpVersion;
	private Map<String, String> httpHeaders;
	private byte[] entity;
	
	/**
	 * Returns the used protocol version of this message.
	 * 
	 * @return
	 */
	public HttpVersion getHttpVersion() {
		return httpVersion;
	}
	public void setHttpVersion(HttpVersion httpVersion) {
		this.httpVersion = httpVersion;
	}

	/**
	 * Returns a list of key-value header fields.
	 * 
	 * @return
	 */
	public Map<String, String> getHeaders() {
		return httpHeaders;
	}
	public void setHeaders(Map<String, String> httpHeaders) {
		this.httpHeaders = httpHeaders;
	}

	/**
	 * Returns the entity as a byte array or null, if no entity is available.
	 * 
	 * @return
	 */
	public byte[] getEntity() {
		return entity;
	}
	public void setEntity(byte[] entity) {
		this.entity = entity;
	}

}
