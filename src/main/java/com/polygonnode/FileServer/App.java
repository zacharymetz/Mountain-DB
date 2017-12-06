package com.polygonnode.FileServer;

import java.io.IOException;

import com.polygonnode.FileServer.database.DB;
import com.polygonnode.FileServer.database.Server;

/**
 * 
 *
 */
public class App 
{
    @SuppressWarnings("unused")
	public static void main( String[] args ) throws IOException
    {
    	DB database = new DB();
    	database.seed();
		Server server = new Server(database);
    }
}
