package com.polygonnodeMountainDB.database;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.util.HashMap;
import java.util.Map;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

@SuppressWarnings("restriction")
public class Server {
	
	private static Database database;
	HttpServer server;
	
	
	public Server(Database database,String host,int port) throws IOException {
		Server.database = database;
		server = HttpServer.create(new InetSocketAddress(host,port), 0);
        server.createContext("/test", new MyHandler());
        server.createContext("/size", new sizeHandler());
        server.createContext("/get", new getHandler());
        server.createContext("/exists", new existsHandler());
        server.createContext("/delete", new deleteHandler());
        server.createContext("/insert", new insertHandler());
        server.createContext("/batchget", new batchGetHandler());
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
	 * use the batch get to get multiple values by seperating thier key values by ","
	 */
	static class batchGetHandler implements HttpHandler {
        
        public void handle(HttpExchange t) throws IOException {
            String response = "{";
            String uri = t.getRequestURI().toString();
        	uri = uri.substring(10, uri.length());
        	String[] uriArray = uri.split(",");
        	Map<String,String> responseMap = new HashMap<String,String>();
        	for(String key: uriArray) {
        		String getResult = database.get(key);
        		if(getResult != null) {
        			response = response + "\'"+key+"\':\'" + getResult + "\',";
        		}else {
        			response = response + "\'"+key+"\':\'\',";
        		}
        		
        	}
        	response = response.substring(0, response.length()-1) + "}";
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
	 * Method for seeing in an objects exists in the database, 1 means it exists,0 means it does not
	 */
	
	static class deleteHandler implements HttpHandler {
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
        	String response = "Error inserting into Database";
        	
        	String uri = t.getRequestURI().toString();
        	uri = uri.substring(8, uri.length());
        	String[] uriArray = uri.split("/");
        	
        	if(database.insert(uriArray[0], uriArray[1])) {
        		response = "Insert Successful";
        	}
        	
            
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
