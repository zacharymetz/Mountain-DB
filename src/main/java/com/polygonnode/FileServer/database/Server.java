package com.polygonnode.FileServer.database;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

@SuppressWarnings("restriction")
public class Server {
	
	private static DB database;
	HttpServer server;
	
	
	public Server(DB database) throws IOException {
		Server.database = database;
		server = HttpServer.create(new InetSocketAddress(8000), 0);
        server.createContext("/test", new MyHandler());
        server.createContext("/size", new sizeHandler());
        server.createContext("/get", new getHandler());
        server.createContext("/exists", new existsHandler());
        server.createContext("/insert", new insertHandler());
        server.setExecutor(null); // creates a default executor
        server.start();
	}
	/*
	 * Method for getting objects from the database
	 */
	
	static class getHandler implements HttpHandler {
		public void handle(HttpExchange t) throws IOException {
        	String result = t.getRequestURI().toString();
        	result = result.substring(5, result.length());
        	result = database.get(result);
            if(result == null) {
            	result = "Object was not found in system";
            }
        	String response = result;
            t.sendResponseHeaders(200, response.length());
            OutputStream os = t.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }
	}
	/*
	 * Method for seeing in an objects exists in the database, 1 means it exists,0 means it does not
	 */
	
	static class existsHandler implements HttpHandler {
		public void handle(HttpExchange t) throws IOException {
        	String uri = t.getRequestURI().toString();
        	uri = uri.substring(8, uri.length());
        	boolean exists = database.exists(uri);
        	String response = "1";
            if(!exists) {
            	response = "0";
            }
            t.sendResponseHeaders(200, response.length());
            OutputStream os = t.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }
	}
	/*
	 * Method for inserting objects from the database
	 */
	static class insertHandler implements HttpHandler {
        
        public void handle(HttpExchange t) throws IOException {
            String response = "Not supported yet";
            t.sendResponseHeaders(200, response.length());
            OutputStream os = t.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }
    }
	static class sizeHandler implements HttpHandler {
        
        public void handle(HttpExchange t) throws IOException {
            String response = Integer.toString(database.size());
            t.sendResponseHeaders(200, response.length());
            OutputStream os = t.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }
    }
	
	static class MyHandler implements HttpHandler {
        
        public void handle(HttpExchange t) throws IOException {
            String response = "This is the response";
            t.sendResponseHeaders(200, response.length());
            OutputStream os = t.getResponseBody();
            os.write(response.getBytes());
            os.close();
        }
    }


}