package com.upgma.cluster;

import java.util.ArrayList;

public class DisMatrix {
	ArrayList<DNASequence> sequences = new ArrayList<DNASequence>();
	double[][] dis_score;
	
	
	public DisMatrix(ArrayList<DNASequence> seqEntries){
		this.sequences = seqEntries;
		
		this.dis_score = new double[sequences.size()][sequences.size()];
		
	}
	public void debug(){
		for (int i = 0; i < sequences.size(); i++) {
			sequences.get(i).getInfo();
		}	
	}
	//compare strings of equal length
	public double calcDistance(String seq1, String seq2){
		double distance = 0;
		char baseA;
		char baseB;
		for(int i =0; i < seq1.length(); i++){
			baseA = seq1.charAt(i);
			baseB = seq2.charAt(i);
			
			if (baseA != baseB){
				distance++;
			}						
		}
		return distance;
	}
	
	// Calculate dissimilarity scores (hamming distance)
	public double[][] calcScores() {
		String seq1_data;
		String seq2_data;
		for (int i = 0; i < sequences.size(); i++) {
			for (int j = 0; j < sequences.size(); j++) {
				seq1_data = sequences.get(i).getSequence();
				seq2_data = sequences.get(j).getSequence();
				dis_score[i][j] = calcDistance(seq1_data, seq2_data);
				System.out.println(i +"," + j +": " + dis_score[i][j]);
			}		
		}
		return dis_score;
	}
	
	
	
}