package de.uulm.mi.web.server;

import de.uulm.mi.web.server.impl.BasicHttpServer;

public class Main
{
	public static void main(String[] args)
	{
		HttpServer server = new BasicHttpServer(8080);
		server.start();
	}
}
