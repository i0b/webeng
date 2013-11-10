package de.uulm.mi.web.http.impl;

import java.util.Map;

import de.uulm.mi.web.http.HttpMessage;
import de.uulm.mi.web.http.HttpMethod;
import de.uulm.mi.web.http.HttpRequest;
import de.uulm.mi.web.http.HttpVersion;

public class BasicHttpRequest implements HttpRequest
{
	private HttpMessage message = new BasicHttpMessage();
	private HttpMethod httpMethod;
	private String requestUri;
	public HttpVersion getHttpVersion()
	{
		return message.getHttpVersion();
	}
	
	public Map<String, String> getHeaders()
	{
		return message.getHeaders();
	}

	public byte[] getEntity()
	{
		return message.getEntity();
	}

	public HttpMethod getHttpMethod()
	{
		return httpMethod;
	}
	
	public String getRequestUri()
	{
		return requestUri;
	}

	public void setHttpVersion(HttpVersion httpVersion) {
		message.setHttpVersion(httpVersion);
	}

	public void setHeaders(Map<String, String> headers) {
		message.setHeaders(headers);
	}

	public void setEntity(byte[] entity) {
		message.setEntity(entity);
	}

	public void setHttpMethod(HttpMethod httpMethod) {
		this.httpMethod = httpMethod;
	}

	public void setRequestUri(String requestUri) {
		this.requestUri = requestUri;
	}

}
