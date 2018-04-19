package com.upgma.cluster;

import java.util.ArrayList;
import java.util.Iterator;


/**
 * Clustering class
 * 
 * In order to generate a UPGMA dendrogram, the sequences must be clustered based on their distance scores
 * Using the dissimilarity matrix, the sequences with the lowest distance are chosen and clustered together
 * The distances to remaining sequences are then calculated using the arithmetic mean
 * 
 * This is done so recursively, generating branching nodes of the dendrogram, and creating a string representation
 * called the newick format:
 * 
 * Example: ((a,b)(c,d))
 * @param sequence:	A list of DNA sequences to cluster together
 */
public class Cluster {
	ArrayList<Node> nodes = new ArrayList<Node>();
		
	public Cluster(ArrayList<DNASequence> sequences) {
				
		// Generate array of leaf nodes to be clustered
		for (int i=0; i<sequences.size(); i++) {
			this.nodes.add(i, new Leaf(i, sequences.get(i)));	// The index corresponds to its place in its dissimilarity matrix
																// and will be clustered along with the indices of the matrix
		}
		
	}
	
	/**
	 * Node clustering method
	 * Creates a branch node based on the two sequences with smallest distance
	 * @param nodePair:	A set of two ints denoting the two closest sequences
	 */ 
	private void clusterNodes(ArrayList<Integer> nodePair) {
										
		Node child1 = this.nodes.get(nodePair.get(0));	// closest node pair
		Node child2 = this.nodes.get(nodePair.get(1));
		
		this.nodes.add(new Branch(child1, child2));	// cluster node pair in new branch
		
		// Iterate through node list and remove the child nodes
		Iterator<Node> itr = this.nodes.iterator();		
		while (itr.hasNext()) {
			Node node = itr.next();
			
			if(node.equals(child1) || node.equals(child2)) {
				itr.remove();
			}
			
			
		}

	}
	
	/**
	 * Minimum Finding
	 * Iterate through the distance matrix and find the pair of sequences with the lowest distance score
	 * 
	 * @param dmatrix:		The dissimilarity matrix
	 * @return closePair: 	The pair of closest sequences
	 */
	private ArrayList<Integer> findMin(double[][] dmatrix){
		ArrayList<Integer> closePair = new ArrayList<Integer>();
		closePair.add(0);
		closePair.add(1);	// Make the pair
		
		double minDistance = Integer.MAX_VALUE;	// Minimum non-zero distance in dissimilarity matrix
		int mSize = dmatrix.length;
		
		// Array is symmetrical, so you can iterate through half
		for (int i=0; i<mSize; i++) {
			for (int j=i+1; j<mSize; j++) {
				// Initialize the minimum distance with a non-zero value
				if (minDistance == 0) {	
					minDistance = dmatrix[i][j];
				}
				// Find the minimum non-zero value in the array
				else if (dmatrix[i][j] != 0) {	
					if (dmatrix[i][j] < minDistance) {
						minDistance = dmatrix[i][j];
						closePair.set(0, i);
						closePair.set(1, j);
					}
				}
			}
		}

		return closePair;
	}
	
	
	/**
	 * Neighbor Joining Clustering Method
	 * Sequences with the lowest distance scores are clustered together in order to generate a dendrogram
	 * After clustering two sequences together, a new dissimilarity matrix is created in which
	 * the two sequences are removed, and a "cluster" is added in their place with an adveraged distance to the remaining
	 * sequences. 
	 * Node objects are created in tandem with the clustering.
	 * 
	 * This function is then called recursively until two clusters remain in the matrix.
	 * The newick format string is then generated.
	 * 
	 * @param distMatrix: The distance matrix to cluster: this is called recursively, and sends in the processed matrix as the parameter
	 * 						for the next function
	 * @param output:		The newick format string
	 */
	public String joinNearest(double[][] distMatrix) {
		String output;
		int cSize = distMatrix.length;
		double[][] joinedMatrix = new double[cSize-1][cSize-1];	// new matrix with nearest neighbors clustered

		ArrayList<Integer> pair;	// two closes nodes
		 
		int iskew = 0;				// skew position when inserting into new matrix
		int jskew = 0;

		// Base case for recursive function
		if (joinedMatrix.length == 1) {
			StringBuilder newick = new StringBuilder();
			newick.append("(");
			for(int i=0; i<nodes.size(); i++) {
				newick.append(nodes.get(i).getPos());
				newick.append(",");
			}
			newick.setLength(newick.length() - 1); // Gets rid of unneeded comma at the end
			newick.append(");");
			return newick.toString();
		}
		// Otherwise, recursively cluster the closest nodes
		else {
			pair = findMin(distMatrix);	// Find minimum distance in dissimilarity matrix
			clusterNodes(pair);
			
			//Cluster the nodes with minimum distance and generate a new array
			for (int i=0; i<cSize; i++) {												
				if (pair.contains(i)) {	// Skip columns that will be deleted
					iskew--;			// skew position in new matrix to account for deletions					
				}
				// Generate a new dissimilarity matrix by joining the closest neighbors and calculating pair means
				else {
					// Store clustered pair in the last index of new matrix 
					joinedMatrix[i+iskew][joinedMatrix.length -1] = ((distMatrix[i][pair.get(0)] + distMatrix[i][pair.get(1)]) /2);
					// move remaining values into new matrix
					for (int j=i+1; j<cSize; j++) {										
						if(j > pair.get(0) && j > pair.get(1)) {
							jskew = -2;
						}
						else if(j > pair.get(0)) {
							jskew = -1;
						}
						else {
							jskew = 0;
						}
						if(!pair.contains(j)) {
							// Reposition remaining values into new matrix							
							joinedMatrix[i+iskew][j+jskew] = distMatrix[i][j];					
						}
						
					}
					
				}			
						
			}
			output = joinNearest(joinedMatrix);
			return output;
		}
		
	}							
}