package com.upgma.cluster;

import java.util.ArrayList;


/**
 * Dissimilarity Matrix Class
 * 
 * This class generates a dissimilarity matrix based on the hamming distance between two DNA sequences
 * The hamming distance is simply the number of differences between the sequences
 * For example, two sequences "AAAA" and "AAAT" would have a hamming distance of 1
 * 
 * Comparison of sequences proceeds pairwise (each sequence is compared to each other sequence)
 * @param seqEntries:	A list of DNASequences
 */
public class DisMatrix {
	ArrayList<DNASequence> sequences = new ArrayList<DNASequence>();
	double[][] dis_score;
	
	
	public DisMatrix(ArrayList<DNASequence> seqEntries){
		this.sequences = seqEntries;
		
		this.dis_score = new double[sequences.size()][sequences.size()];
		
	}

	/**
	 * Calculate the pairwise distance between two strings
	 * This function calculates the hamming distance between two strings
	 * The hamming distance is simply the number of differences between the sequences
	 * 
	 * If one sequence is longer than the other, the difference in length is added to the distance score
	 * @param seq1:			The first string being compared
	 * @param seq2:			The second string being compared
	 * @return distance:	The number of differences between the two strings
	 */
	public double calcDistance(String seq1, String seq2){
		double distance = 0;
		char baseA;
		char baseB;
		
		String shortest;
		double difference;
		// Check if strings are different lengths
		if (seq1.length() >= seq2.length()) {
			shortest = seq2;
			difference = seq1.length() - seq2.length();
		}
		else {
			shortest = seq1;
			difference = seq2.length() - seq1.length();
		}
		// For each mismatched base, add 1 to the distance score
		for(int i =0; i < shortest.length(); i++){
			baseA = seq1.charAt(i);
			baseB = seq2.charAt(i);
			
			if (baseA != baseB){
				distance++;
			}						
		}
		distance += difference;	// Add the length difference to the distance score
		return distance;
	}
	
	/**
	 * Generate a dissimilarity matrix
	 * Populate a matrix of distance scores for each sequence pair
	 * 
	 * @return dis_score:	Matrix of dissimilarity scores
	 */
	public double[][] calcScores() {
		String seq1_data;
		String seq2_data;
		for (int i = 0; i < sequences.size(); i++) {
			for (int j = 0; j < sequences.size(); j++) {
				seq1_data = sequences.get(i).getSequence();
				seq2_data = sequences.get(j).getSequence();
				dis_score[i][j] = calcDistance(seq1_data, seq2_data);
				
			}		
		}
		return dis_score;
	}
	
	
	
}