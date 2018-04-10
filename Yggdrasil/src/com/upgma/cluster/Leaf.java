package com.upgma.cluster;


public class Leaf extends Node {
	int leaf;
	DNASequence id;
	
	public Leaf(int position, DNASequence sequence) {
		leaf = position;
		id = sequence;
	}
	
	
	public String getPos() {
		return Integer.toString(leaf);
	}
	
	public void getID() {
		System.out.println(id);
	}
	
}
