package com.upgma.cluster;

import java.util.ArrayList;
import java.util.Random;

/**
 * DNA Sequence Class
 * 
 * The DNA Sequence class is a class which holds the name (the header)
 * and nucleotide string (sequence) of a DNA sequence
 * 
 * These are generated from the FASTA files uploaded to the servlet
 * And used to generate the UPGMA tree.
 * 
 * 
 */
public class DNASequence {
	String header;
	String sequence;
	public DNASequence(String fastaHeader, String fastaSequence) {
		this.header = fastaHeader;
		this.sequence = fastaSequence;	    
	}
	public void getInfo(){
		System.out.println(this.header);
		System.out.println(this.sequence);
	}
	public String getSequence(){
		return this.sequence;
	}
	public void setSequence(String newSequence) {
		this.sequence = newSequence;
	}
	public String getHeader(){
		return this.header;
	}
	public void setHeader(String newHeader){
		this.header = newHeader;
	}
	
	 /**
	 * Mutant sequence generator method mutate()
	 * Randomly changes nucleotides 
	 * based on the specified parameters. The chance that a mutation event
	 * occurs is set by the user and is determined by generating a number between 1 and 100
	 * A transition is a change between two purines or two pyrimidines
	 * Purines (A <-> G) and Pyrimidines (C <-> T)
	 * Whereas a transversion is a change between pyrimadine to purine and vice versa
	 * For example, (A <-> T) and (A <-> C), (G <-> T) and (G <-> C)
	 * 
	 * This method checks each nucleotide individually and returns a new mutated sequence
	 * 
	 * @param rateMutation:	The chance of a mutation event occuring from 1-100
	 * @param rateTrBias:	Ratio of transitions to transversions. A higher % means more transitions likely
	 */
	public void mutate(int rateMutation, int rateTrBias) {
		Random rate = new Random();							// Probability of mutation
    	StringBuilder mutSeq = new StringBuilder();			// New mutant sequence
    	
		for(int i=0; i< sequence.length(); i++){ 
			
			//calculate probability of mutation event
			if (rateMutation >= rate.nextInt(100) + 1) {
				 
				// Mutation event occurs, calculate probability of transition or transversion
				
				//Transition event	
				if (rateTrBias >= rate.nextInt(100) + 1) {	
					// Interchange Purines (A <-> G) and Pyrimidines (C <-> T)
					if(sequence.charAt(i) == 'A') {
						mutSeq.append('G');
					}
					else if(sequence.charAt(i) == 'G') {
						mutSeq.append('A');
					}
					else if(sequence.charAt(i) == 'C') {
						mutSeq.append('T');
					}
					else if(sequence.charAt(i) == 'T') {
						mutSeq.append('C');
					}
				}
				//Transversion event
				
				// There are two possible transversion events for each nucleotide, selected randomly
				// For example, (A -> T) and (A -> C) are both possible
				else {
					// For our purposes, both are equally likely to happen
					if((rate.nextInt(2) + 1) == 1) {
						if(sequence.charAt(i) == 'A') {
    						mutSeq.append('T');
    					}
    					else if(sequence.charAt(i) == 'G') {
    						mutSeq.append('T');
    					}
    					else if(sequence.charAt(i) == 'C') {
    						mutSeq.append('A');
    					}
    					else if(sequence.charAt(i) == 'T') {
    						mutSeq.append('A');  
    					}
					}else {
						if(sequence.charAt(i) == 'A') {
    						mutSeq.append('C');
    					}
    					else if(sequence.charAt(i) == 'G') {
    						mutSeq.append('C');
    					}
    					else if(sequence.charAt(i) == 'C') {
    						mutSeq.append('G');
    					}
    					else if(sequence.charAt(i) == 'T') {
    						mutSeq.append('G');  		
    					}					
					}
				}	
			}
			// Otherwise, no mutation occurs
			else {
				mutSeq.append(sequence.charAt(i));
			}		 
    	}
		header += " Mutant";
		
	}
}
