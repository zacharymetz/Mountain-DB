package com.polygonnodeMountainDB.database;

import com.polygonnode.datastructure.trie.Trie;

public class Database {
	private Trie data;
	private int numberOfObjects;

	public Database() {
		this.data = new Trie();
		this.numberOfObjects = 0;
	}
	
	
	//operations on the tire
	public boolean insert(String key, String file) {
		if(data.insert(key, file)) {
			this.numberOfObjects ++;
			return true;
		}
		return false;
	}
	public String get(String key) {
		return data.find(key);
	}
	public boolean delete(String key) {
		if(data.delete(key)) {
			this.numberOfObjects --;
			return true;
		}
		return false;
	}
	public boolean exists(String key) {
		return data.exists(key);
	}
	public int size() {
		return this.numberOfObjects;
	}

}
