package de.uulm.mi.web.http.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import de.uulm.mi.web.http.HttpMessage;
import de.uulm.mi.web.http.HttpResponse;
import de.uulm.mi.web.http.HttpStatusCode;
import de.uulm.mi.web.http.HttpVersion;
import de.uulm.mi.web.server.impl.BasicHttpServer;

public class BasicHttpResponse implements HttpResponse {

	private HttpMessage message = new BasicHttpMessage();
	private HttpStatusCode status;

	public BasicHttpResponse(HttpMessage message, HttpStatusCode status) {
		this.message = message;
		this.status = status;
	}
	public BasicHttpResponse() {
		this.setHttpVersion(HttpVersion.VERSION_1_1);
		Map <String, String> headers = new HashMap<String, String>();
		headers.put("Server", BasicHttpServer.SERVER_NAME);
		headers.put("Date", new Date().toString());

		this.setHeaders(headers);
		this.setEntity(null);
	}

	public HttpVersion getHttpVersion() {
		return message.getHttpVersion();
	}

	public void setHttpVersion(HttpVersion httpVersion) {
		message.setHttpVersion(httpVersion);
	}

	public void setHeaders(Map<String, String> httpHeaders) {
		message.setHeaders(httpHeaders);
	}

	public void setEntity(byte[] entity) {
		message.setEntity(entity);
	}

	public void setHttpStatusCode(HttpStatusCode status) {
		this.status = status;
	}

	public Map<String, String> getHeaders() {
		return message.getHeaders();
	}

	public byte[] getEntity() {
		return message.getEntity();
	}

	public HttpStatusCode getStatusCode() {
		return status;
	}
	public void setStatusCode(HttpStatusCode statusCode) {
		this.status = statusCode;
	}

}
