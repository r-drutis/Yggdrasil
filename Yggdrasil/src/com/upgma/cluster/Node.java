package com.upgma.cluster;


/**
 * Abstract class Node
 * 
 * The node class represents the nodes of the UPGMA dendorgram
 * Nodes can be either branches (meaning they have 2 child nodes)
 * Or leaves, which have no child nodes and represent the clustered DNA sequences
 * 
 * Nodes are used to recursively generate the dendrogram and store the relative
 * positions of each sequence
 */

public abstract class Node {
	
	/**
	 * Get position
	 * Returns either the two child nodes of a branch node or
	 * the sequence of a leaf node
	 */
	public abstract String getPos();
	
	public abstract String getID();
		
	/**
	 * Object Equals method
	 * Checks if two node objects are the same node object by comparing their
	 * positions
	 * 
	 * @param testnode: the node you are checking against to see if they are the same
	 * @return boolean: whether or not the two objects are the same
	 */
	public boolean equals(Node testNode) {
		if(this.getID().equals(testNode.getID())) {
			return true;
		}
		else {
			return false;
		}
	}
}
