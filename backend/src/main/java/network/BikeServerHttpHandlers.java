package network;

import java.io.IOException;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.apache.commons.io.IOUtils;

@SuppressWarnings("restriction")
public class BikeServerHttpHandlers {
	public class defaultHandler implements HttpHandler {
		@Override
		public void handle(HttpExchange exchange) throws IOException {
			exchange.sendResponseHeaders(200, 0);
			IOUtils.write("Hello world!".getBytes(), exchange.getResponseBody());
			exchange.close();
		}
	}
}

