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

public class BasicHttpWorker extends HttpWorker {
	public BasicHttpWorker(Socket socket, BasicHttpServer server) {
		super(socket, server);
	}

	@Override
	protected HttpRequest parseRequest(InputStream inputStream)
			throws IOException {
		// TODO Auto-generated method stub
		// Stefan
		// init vars
		String requestLine = this.readLine(inputStream);
		HttpMethod httpMethod = null;
		try {
			httpMethod = HttpMethod.extractMethod(requestLine);
		} catch (IllegalArgumentException e){
			return null;
		}
		String requestUri = requestLine.split(" ")[1];
		HttpVersion httpVersion = HttpVersion.extractVersion(requestLine);

		// Restlichen HeaderInformationen in eine Map geschrieben...
		Map<String, String> headers = new HashMap<String, String>();

		while (!(requestLine = this.readLine(inputStream)).equals("")) {
			String key = requestLine.split(": ")[0];
			String value = requestLine.split(": ")[1];
			headers.put(key, value);
		}

		// parse to Object httprequest
		BasicHttpRequest httprequest = new BasicHttpRequest();
		httprequest.setHttpVersion(httpVersion);
		httprequest.setHttpMethod(httpMethod);
		httprequest.setRequestUri(requestUri);
		httprequest.setHeaders(headers);
		// TODO setEntity
		httprequest.setRequestUri(requestUri);

		// output
		return httprequest;
	}

	@Override
	protected HttpResponse handleRequest(HttpRequest request) {
		// init
		String requestUri = "";
		byte[] data;

		BasicHttpResponse response = new BasicHttpResponse();
		Map<String, String> headers = response.getHeaders();

		// Statusline

		// Additional Headers

		// keepAlive TODO: KeepAlive in Header richtig??

		// headers.put("Connection", "close");//keep-Alive client und server
		// enden die verbindung nicht.
		// Set Response (Processing)
		// Body standardmaeig auf null, da einige header keinen body verwenden.

		
		if (request == null){
			response.setHttpStatusCode(HttpStatusCode.NOT_IMPLEMENTED);
			return response;
			
		}

		/*if (!request.getHeaders().get("Host").equals("localhost")) {
			response.setHttpStatusCode(HttpStatusCode.BAD_REQUEST);
			return response;
		}*/

		if (request.getHttpMethod().equals(HttpMethod.GET)) {
			try {
				// Try getting File
				requestUri = (request.getRequestUri().equals("/") ? "index.html"
						: request.getRequestUri());
				requestUri = BasicHttpServer.WEBROOT + requestUri;
				Path path = Paths.get(requestUri);
				data = Files.readAllBytes(path);

				response.setEntity(data);

				// Success
				response.setHttpStatusCode(HttpStatusCode.OK);

			} catch (IOException e) {
				// SEND 404-Error
				response.setStatusCode(HttpStatusCode.NOT_FOUND);
				requestUri = BasicHttpServer.ERROR_FOLDER + "404.html";
				headers.put("Connection", "close");
				Path path = Paths.get(requestUri);
				try {
					data = Files.readAllBytes(path);
					response.setEntity(data);

				} catch (IOException e1) {
					e1.printStackTrace();
				}
			}

		}

		else if (request.getHttpMethod().equals(HttpMethod.HEAD)) {
			try {
				requestUri = (request.getRequestUri().equals("/") ? "index.html"
						: request.getRequestUri());
				requestUri = BasicHttpServer.WEBROOT + requestUri;
				Path path = Paths.get(requestUri);
				String size = String.valueOf(Files.size(path));
				response.setHttpStatusCode(HttpStatusCode.OK);
				headers.put("Content-length", size);
			} catch (IOException e) {
				// TODO close connection!
				headers.put("Connection", "close");
				response.setHttpStatusCode(HttpStatusCode.NOT_FOUND);
				response.setHeaders(headers);
			}
		}

		else {
			response.setHttpStatusCode(HttpStatusCode.NOT_IMPLEMENTED);
		}

		return response;
	}

	@Override
	protected void sendResponse(HttpResponse response, OutputStream outputStream)
			throws IOException {
		// Init
		// PrintWriter out = new PrintWriter(outputStream, false);
		String statusline = response.getHttpVersion() + " "
				+ response.getStatusCode().getCode() + " "
				+ response.getStatusCode();
		Vector<String> headers = new Vector<String>();

		// response.getHeaders();
		for (String key : response.getHeaders().keySet()) {
			String value = response.getHeaders().get(key);
			headers.add(key + ": " + value);
		}

		// Output
		String httpresponse = statusline + "\n";
		for (String header_info : headers) {
			httpresponse += header_info + " \n";
		}
		httpresponse += "\r\n";
		// httpresponse += entity;
		outputStream.write(httpresponse.getBytes());
		outputStream.write(response.getEntity());
		// out.flush();
		outputStream.flush();

	}

	@Override
	protected boolean keepAlive(HttpRequest request, HttpResponse response) {
		Map<String, String> headers = request.getHeaders();
		boolean request_keepalive = (headers.containsKey("Connection") && headers
				.containsValue("keep-alive"));
		boolean response_keepalive = (headers.containsKey("Connection") && headers
				.containsValue("keep-alive"));
		if (request_keepalive && response_keepalive)
			return true;
		else
			return false;
	}

}
