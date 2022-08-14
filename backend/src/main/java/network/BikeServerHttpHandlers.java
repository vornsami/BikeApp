package network;

import java.io.IOException;
import java.util.logging.Logger;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import database.DatabaseManager;

import org.json.JSONArray;
import org.json.JSONObject;

@SuppressWarnings("restriction")
public class BikeServerHttpHandlers {
	public class defaultHandler implements HttpHandler {
		@Override
		public void handle(HttpExchange exchange) throws IOException {
			
			Logger logger = Logger.getGlobal();
			
			try {
				
				setHeaders(exchange);
				exchange.sendResponseHeaders(200, 0);
				
				if(!"POST".equalsIgnoreCase(exchange.getRequestMethod())) {
					return;
				}
				
				byte[] buffer = new byte[65535];
				exchange.getRequestBody().read(buffer, 0, Integer.MAX_VALUE);
				JSONObject requestJson = new JSONObject(new String(buffer));
				
				DatabaseManager manager = DatabaseManager.getManager();
				JSONArray list = manager.getAsJSON(
						(int)requestJson.get("amount"), 
						(String)requestJson.get("sortBy"), 
						(int)requestJson.get("offset")
					);
				
				byte[] response = list.toString().getBytes("UTF-8");
				exchange.getResponseBody().write(response);
				
			} catch (Exception e) {
				logger.warning(e.getMessage());
			} finally {
				exchange.close();
			}
			
		}
	}
	/*
	 * Sets the headers needed for most http requests.
	 * 
	*/
	private static void setHeaders(HttpExchange exchange) {
		exchange.getResponseHeaders().add("Content-Type", "text/plain; charset=UTF-8");
		exchange.getResponseHeaders().add("Access-Control-Allow-Origin", "*");
		exchange.getResponseHeaders().add("Access-Control-Allow-Headers", "content-type");
	}
	
}

