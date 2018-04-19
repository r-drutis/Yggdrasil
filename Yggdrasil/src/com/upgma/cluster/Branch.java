package com.upgma.cluster;

public class Branch extends Node {
	Node branch1;
	Node branch2;
	
	public Branch(Node child1, Node child2) {
		branch1 = child1;
		branch2 = child2;
	}
	
	@Override
	public String getPos() {
		String pos = "(" + branch1.getPos() + "," + branch2.getPos() + ")";
		return pos;
	}
	
	@Override
	public String getID() {
		String id = branch1.getID() + "," + branch2.getID();
		return id;
	}
	
}
