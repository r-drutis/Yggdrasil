package com.upgma.cluster;

import java.io.IOException;
import java.util.ArrayList;

public class MatrixGenerator {

	public static void main(String[] args) {
		String fasta = "C:\\Users\\Rob\\Desktop\\test.fasta";	// Get fasta file to read
		FastaReader reader = new FastaReader();
		ArrayList<DNASequence> fastaSequences = new ArrayList<DNASequence>();	// Generate list of DNA sequences and their labels
		
		try {
			fastaSequences = reader.readFasta(fasta);
		} catch (IOException e) {			
			e.printStackTrace();
		}
		
		DisMatrix dmatrix = new DisMatrix(fastaSequences);	// Generate a dissimilarity Matrix
		dmatrix.debug();
		double[][] scores = dmatrix.calcScores();
		
		Cluster cluster = new Cluster(fastaSequences); // Cluster nearest neighbors in matrix
		cluster.joinNearest(scores);
		
	}

}