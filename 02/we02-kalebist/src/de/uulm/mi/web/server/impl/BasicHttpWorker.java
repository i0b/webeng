package de.uulm.mi.web.server.impl;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import de.uulm.mi.web.Http;
import de.uulm.mi.web.http.HttpMethod;
import de.uulm.mi.web.http.HttpRequest;
import de.uulm.mi.web.http.HttpResponse;
import de.uulm.mi.web.http.HttpVersion;
import de.uulm.mi.web.http.impl.BasicHttpRequest;
import de.uulm.mi.web.http.impl.BasicHttpResponse;
import de.uulm.mi.web.server.HttpWorker;

public class BasicHttpWorker extends HttpWorker
{
	public BasicHttpWorker(Socket socket, BasicHttpServer server)
	{
		super(socket, server);
	}

	@Override
	protected HttpRequest parseRequest(InputStream inputStream) throws IOException
	{
		// TODO Auto-generated method stub
		//Stefan
		//init vars
		String requestLine = this.readLine(inputStream);
		HttpMethod httpMethod = HttpMethod.extractMethod(requestLine);
		String requestUri = requestLine.split(" ")[1];
		HttpVersion httpVersion = HttpVersion.extractVersion(requestLine);
		
		Map<String, String> headers = new HashMap<String, String>();
		while(! (requestLine = this.readLine(inputStream)).equals("")) {
			String key = requestLine.split(": ")[0];
			String value = requestLine.split(": ")[1];
			headers.put(key, value);
		}
		
		
		//parse to Object httprequest
		BasicHttpRequest httprequest = new BasicHttpRequest();
		httprequest.setHttpVersion(httpVersion);
		httprequest.setHttpMethod(httpMethod);
		httprequest.setRequestUri(requestUri);
		httprequest.setHeaders(headers);
		//TODO setEntity
		httprequest.setEntity(null);
		
		
		//output
		return httprequest;
	}

	@Override
	protected HttpResponse handleRequest(HttpRequest request)
	{
		
		//TODO Keep-Alive in header..
		HttpResponse response = null;
		
		if (request.getHttpMethod().equals(HttpMethod.GET)){
			request.getRequestUri();
			
		}
		
		else if (request.getHttpMethod().equals(HttpMethod.HEAD)){
			
		}
		
		// TODO Auto-generated method stub
		return response;
	}

	@Override
	protected void sendResponse(HttpResponse response, OutputStream outputStream) throws IOException
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	protected boolean keepAlive(HttpRequest request, HttpResponse response)
	{
		// TODO Auto-generated method stub
		return false;
	}

	
}
