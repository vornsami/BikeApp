package network;

import java.io.IOException;
import java.net.InetSocketAddress;

import com.sun.net.httpserver.HttpServer;

@SuppressWarnings("restriction")
public class BikeServer {
	private HttpServer server;
	private BikeServerHttpHandlers handlers;
	
	public BikeServer(int port) throws IOException {
		handlers = new BikeServerHttpHandlers();
		
		server = HttpServer.create(new InetSocketAddress("localhost", port), 0);
		createServerContexts();
		
	}
	
	public void start() {
		server.start();
	}
	public void stop() {
		server.stop(0);
	}
	
	private void createServerContexts() {
		server.createContext("/", handlers.new defaultHandler());
	}
	
	
}
