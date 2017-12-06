package com.polygonnode.FileServer;

import java.io.IOException;

import com.polygonnode.FileServer.database.*;
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
    	Database database = new Database();
    	database.seed();
		Server server = new Server(database);
    }
}
