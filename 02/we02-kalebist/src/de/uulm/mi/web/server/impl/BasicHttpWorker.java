package de.uulm.mi.web.server.impl;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import de.uulm.mi.web.http.HttpMethod;
import de.uulm.mi.web.http.HttpRequest;
import de.uulm.mi.web.http.HttpResponse;
import de.uulm.mi.web.http.HttpStatusCode;
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
		
		
		//Restlichen HeaderInformationen in eine Map geschrieben...
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
		httprequest.setRequestUri(requestUri);
		
		//output
		return httprequest;
	}

	@Override
	protected HttpResponse handleRequest(HttpRequest request)
	{
		
		//TODO Keep-Alive in header..
		request.getHeaders();
		
		BasicHttpResponse response = new BasicHttpResponse();
		response.setHttpVersion(HttpVersion.VERSION_1_1);
		//response.setHeaders(headers);
		
		
		if (request.getHttpMethod().equals(HttpMethod.GET)){
			String requestUri = (request.getRequestUri().equals("/") ? "index.html" : request.getRequestUri());
			requestUri = BasicHttpServer.WEBROOT+requestUri;
			
			Path path = Paths.get(requestUri);
			System.out.println(requestUri);
			
			byte[] data;
			try {
				data = Files.readAllBytes(path);
				response.setEntity(data);
				response.setHttpStatusCode(HttpStatusCode.ACCEPTED);
				Map <String, String> headers = new HashMap<String, String>();
				headers.put("Server", BasicHttpServer.SERVER_NAME);
				response.setHeaders(headers);
				
			} catch (IOException e) {
				response.setStatusCode(HttpStatusCode.NOT_FOUND);
			}
			

		}
		
		else if (request.getHttpMethod().equals(HttpMethod.HEAD)){
			
		}
		
		
		return response;
	}

	@Override
	protected void sendResponse(HttpResponse response, OutputStream outputStream) throws IOException
	{
		//Init
		//PrintWriter out = new PrintWriter(outputStream, false);
		String statusline = response.getHttpVersion()+" "+response.getStatusCode();
		Vector<String> headers = new Vector<String>();

		//response.getHeaders();
		for(String key : response.getHeaders().keySet()) {
			String value = response.getHeaders().get(key);
			headers.add(key+": "+value);
		}
		
		
		//Output
		String httpresponse = statusline+"\n";
		for(String header_info : headers) {
			httpresponse += header_info+" \n";
		}
		httpresponse += "\r\n";
//		httpresponse += entity;
		outputStream.write(httpresponse.getBytes());
		outputStream.write(response.getEntity());
		//out.flush();
		outputStream.flush();

	}

	@Override
	protected boolean keepAlive(HttpRequest request, HttpResponse response)
	{
		// TODO Auto-generated method stub
		return false;
	}

	
}
