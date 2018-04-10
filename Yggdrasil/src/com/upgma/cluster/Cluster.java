package com.upgma.cluster;

import java.util.ArrayList;
import java.util.Iterator;



public class Cluster {
	ArrayList<Node> nodes = new ArrayList<Node>();
		
	public Cluster(ArrayList<DNASequence> sequences) {
				
		// Generate array of leaf nodes to be clustered
		for (int i=0; i<sequences.size(); i++) {
			this.nodes.add(i, new Leaf(i, sequences.get(i)));	// The index corresponds to its place in its dissimilarity matrix
																// and will be clustered along with the indices of the matrix
		}
		
	}
	
	// Clusters the nearest nodes into a branch node 
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
	
	// Find the minimum distance in the dissimilarity matrix
	private ArrayList<Integer> findMin(double[][] dmatrix){
		ArrayList<Integer> closePair = new ArrayList<Integer>();
		closePair.add(0);
		closePair.add(1);
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
	
	
	// Join nearest neighbors and generate new matrix with cluster
	public void joinNearest(double[][] distMatrix) {
		
		int cSize = distMatrix.length;
		double[][] joinedMatrix = new double[cSize-1][cSize-1];	// new matrix with nearest neighbors clustered

		ArrayList<Integer> pair;	// two closes nodes
		 
		int iskew = 0;				// skew position when inserting into new matrix
		int jskew = 0;

		// Base case for recursive function
		if (joinedMatrix.length == 1) {
			System.out.print("(");
			for(int i=0; i<nodes.size(); i++) {
				System.out.print(nodes.get(i).getPos());
			}
			System.out.print(")");
			return;
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
			joinNearest(joinedMatrix);
		}
		
	}							
}