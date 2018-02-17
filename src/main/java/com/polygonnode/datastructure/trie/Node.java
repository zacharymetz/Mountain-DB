package com.polygonnode.datastructure.trie;

import java.util.*;
import java.io.*;
import java.util.UUID; // for name generation

public class Node {
	private Map<Character,Node> data; //where all the characters will be stored
	private String file; //if null then no file exists
	
	
	
	public Node() { //make a new blank node
		this.data = new HashMap(); 
		this.file = null; //a node is made without a file
	}
	
	public Node(String file) { //make a new blank attached to a document
		this.data = new HashMap(); 
		this.file = file; //a node is made without a file
	}
	public boolean insert(char[] key,String file) {
		if(key.length == 0) { // end of the key so put file here
			
			this.file = file;
			return true;
			
		}
		if(data.containsKey(key[0])) { // if the first key in the string exists then
			return data.get(key[0]).insert(Arrays.copyOfRange(key,1,key.length), file); // call inset with a smaller length
		}else { // the key does not exist and we need to add it then keep going
			data.put(key[0], new Node());
			return data.get(key[0]).insert(Arrays.copyOfRange(key,1,key.length), file); // call inset with a smaller length
			
		
		}
	}
	

	public String find(char[] key) {
		if(key.length == 0) {// if we are a the end of the key see if a file exists
			if(this.file != null) {
			return this.file;
			}else {
				return null;
			}
		}
		if(data.containsKey(key[0])) { // if the first key in the string exists then
			return data.get(key[0]).find(Arrays.copyOfRange(key,1,key.length)); // call inset
		}else {
			return null;// if no file is found
		}
	
	}
	public boolean exists(char[] key) {
		if(key.length == 0) {// if we are a the end of the key see if a file exists
			if(this.file == null) {
				return false;
			}
			return true;
		}else 
		if(data.containsKey(key[0])) { // if the first key in the string exists then
			return data.get(key[0]).exists(Arrays.copyOfRange(key,1,key.length)); // call inset
		}else {
			return false;// if no file is found
		}
	}

	public boolean delete(char[] key) {
		if(key.length == 0) {// if we are a the end of the key see if a file exists
			if(this.file == null) {
				return false;
			}
			this.file = null; // with an actual file we would delete it
			return true;
		}else 
		if(data.containsKey(key[0])) { // if the first key in the string exists then
			return data.get(key[0]).exists(Arrays.copyOfRange(key,1,key.length)); // call inset
		}else {
			return false;// if no file is found
		}
	}
	private static String storeFile(String file) {
		//create a new file and write the byte steam to it
		
		//generate a random name with some info in it
		String fileName = UUID.randomUUID().toString().replace("-", "") + ".py";
		//save the file 
		try {
            // Assume default encoding.
            FileWriter fileWriter = new FileWriter(fileName);
            // Always wrap FileWriter in BufferedWriter.
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(file);
            // Always close files.
            bufferedWriter.close();
        }
        catch(IOException ex) {
            System.out.println(
                "Error writing to file '"
                + fileName + "'");
        }
		return fileName;
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
	        }
	        catch(IOException ex) {
	            System.out.println("Error reading file '" + fileName + "'");                  
	            // Or we could just do this: 
	            // ex.printStackTrace();
	        }
	     return to_return;
	}

}
