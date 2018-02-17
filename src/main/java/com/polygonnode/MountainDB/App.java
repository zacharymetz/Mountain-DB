package com.polygonnode.MountainDB;


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import com.polygonnodeMountainDB.database.*;
import org.json.*;
/**
 * 
 *
 */
public class App {
	
	private static Database database;
	private static Server server;
	
	
	
    @SuppressWarnings("unused")
	public static void main( String[] args ) throws IOException{
    	
    	JSONObject config;
    	if(args.length > 0) { // for the configuration file
    		String configFile = loadFile(args[0]);
    		if(configFile != null) {
    			//get the database block cuz that all we care about
    			config = new JSONObject(configFile).getJSONObject("database");
    			for(String key: config.keySet()) {
    				System.out.println(key);
    			}
    				
    				config("base", config.getJSONObject("base"));
    				config("seed", config.getJSONObject("seed"));
    				
    			
    		}else {
    			System.out.println("Configuration file could not be loaded");
    			return;// exit the program
    		}
    		
    	}
    	
    	
		
    }
    /*
     * this is where we take the config file and start the database
     */
    private static void config(String key, JSONObject config) {
    	
    	if(key.equals("base")) {
    		//create the database and the server
    		database = new Database();
    		try {
    			int port = Integer.valueOf(config.getString("port"));
    			String host = config.getString("host");
    			server = new Server(database,host,port);
    		}catch(IOException e) {
    			System.out.println("IO Error Starting server, shutting down");
    		}
    		
    	}else if(key.equals("seed")) {
    		//for each file in the 
    		for(String fileKey: config.keySet()) {
    			try {
    				System.out.println("Seeding Database with file:  " + fileKey);
					seedFile(config.getJSONObject(fileKey));
					System.out.println("Seeding Database complete with file:  " + fileKey);
				} catch (JSONException e) {
					
					e.printStackTrace();
				} catch (IOException e) {
					
					e.printStackTrace();
				}
    			
    		}
    	}
    	
    }
    private static void seedFile(JSONObject file) throws JSONException, IOException {
    	
    	//load the file
    	
        // FileReader reads text files in the default encoding.
        FileReader fileReader = new FileReader(file.getString("source"));
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        //get the header 
        String[] row = bufferedReader.readLine().split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)");
        String[] valueData;
        
       
        int primary_key = indexOf("appid",row);
        
        
        String line;
        while((line = bufferedReader.readLine()) != null) {
           row = line.split(",(?=([^\"]*\"[^\"]*\")*[^\"]*$)");
           valueData = new String[row.length -1]; // new array for everything minus the app id
           System.out.println();
           for(int i=0;i<row.length;i++) {
        	   if(i < primary_key) {
        		   valueData[i] = row[i];
        	   }else if(i > primary_key) {
        		   valueData[i-1] = row[i];
        	   }
           }
           System.out.println("Adding key:  "+row[primary_key]);
           database.insert(row[primary_key], Arrays.toString(valueData));
           
           
        }
         // Always close files.
            bufferedReader.close();         
        
        
    	
    	
    	
    }
    private static String loadFile(String fileName) {
		String to_return = "";
		String line;
		 try {
	            // FileReader reads text files in the default encoding.
	            FileReader fileReader = new FileReader(fileName);

	            // Always wrap FileReader in BufferedReader.
	            BufferedReader bufferedReader = new BufferedReader(fileReader);
	            
	            while((line = bufferedReader.readLine()) != null) {
	            	to_return = to_return +line.replace("\n", "").replace("\r", "").replace("\t", "").replace(" ", "");
	            }
	         // Always close files.
	            bufferedReader.close();         
	        }
	        catch(FileNotFoundException ex) {
	            System.out.println("Unable to open file '" + fileName + "'");
	            to_return = null;
	        }
	        catch(IOException ex) {
	            System.out.println("Error reading file '" + fileName + "'");                  
	            // Or we could just do this: 
	            // ex.printStackTrace();
	            to_return = null;
	        }
	     return to_return;
	}
    private static int indexOf(String key, String[] array) {
    	int index = -1;
    	for (int i=0;i<array.length;i++) {
    	    if (array[i].equals(key)) {
    	        index = i;
    	        break;
    	    }
    	}
    	return index;
    }
    
    
}


