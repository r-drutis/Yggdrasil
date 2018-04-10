package com.upgma.cluster;

public abstract class Node {
	public abstract String getPos();
	
	public boolean equals(Node testNode) {
		if(this.getPos().equals(testNode.getPos())) {
			return true;
		}
		else {
			return false;
		}
	}
}
