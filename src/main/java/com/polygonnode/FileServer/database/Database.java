package com.polygonnode.FileServer.database;

import com.polygonnode.database.trie.Trie;

public class Database {
	private Trie data;
	private int numberOfObjects;

	public Database() {
		this.data = new Trie();
		this.numberOfObjects = 0;
	}
	
	public void seed() {
		this.data.insert("12345", "value stored in ram1");
		this.data.insert("test", "value stored in ram2");
		this.data.insert("zach", "value stored in ram3");
		this.data.insert("125", "value stored in ram4");
		this.data.insert("1345", "value stored in ram5");
		this.data.insert("1245", "value stored in ram6");
		this.data.insert("2345", "value stored in ram7");
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
