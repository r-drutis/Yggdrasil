package com.upgma.cluster;

/**
 * Leaf node class
 * The leaf node represents a terminal node of a UPGMA tree
 * Leaf nodes have only a single DNA sequence
 * 
 * @param position: the position of the leaf's DNA sequence in the sequence list
 * @param id: 		The DNA sequence of the leaf
 */
public class Leaf extends Node {
	int leaf;
	DNASequence id;
	
	public Leaf(int position, DNASequence sequence) {
		leaf = position;
		id = sequence;
	}
	
	/**
	 * Get Position
	 * Returns the position of the DNA string
	 * @return:	String containing the DNA sequence header
	 */
	@Override
	public String getPos() {
		return id.getHeader();
		
	}
	/**
	 * Get ID
	 * Returns the header of the DNA sequence stored in the leaf
	 * @return: String representing the position of the DNA sequence in the list
	 */
	@Override
	public String getID() {
		return Integer.toString(leaf);
	}
	
}
