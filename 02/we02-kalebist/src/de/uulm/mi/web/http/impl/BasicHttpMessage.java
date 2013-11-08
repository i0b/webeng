package de.uulm.mi.web.http.impl;

import java.util.Map;

import de.uulm.mi.web.http.HttpMessage;
import de.uulm.mi.web.http.HttpVersion;

/**
 * @author Leo Hnatek
 * 
 */
public class BasicHttpMessage implements HttpMessage {
	
	private HttpVersion httpVersion;
	private Map<String, String> httpHeaders;
	private byte[] entity;
	
	public HttpVersion getHttpVersion() {
		return httpVersion;
	}
	public void setHttpVersion(HttpVersion httpVersion) {
		this.httpVersion = httpVersion;
	}

	public Map<String, String> getHeaders() {
		return httpHeaders;
	}
	public void setHeaders(Map<String, String> httpHeaders) {
		this.httpHeaders = httpHeaders;
	}

	public byte[] getEntity() {
		return entity;
	}
	public void setEntity(byte[] entity) {
		this.entity = entity;
	}

}
