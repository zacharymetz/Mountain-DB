package com.polygonnode.database.trie;

import java.util.*;


public class Trie { 
	// the data structure for the actual tree
	Node root;
	
	public Trie() {
		root = new Node();
	}
	public boolean insert(String key,String file) {
		//convert the key into an array list for easy removal 
		return root.insert(key.toCharArray(), file);
	}
	public String find(String key) {
		return root.find(key.toCharArray());
	}
	public boolean exists(String key) {
		return root.exists(key.toCharArray());
	}
	public boolean delete(String key) {
		return root.delete(key.toCharArray());
	}
	

}
