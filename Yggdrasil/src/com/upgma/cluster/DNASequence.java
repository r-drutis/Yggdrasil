package com.upgma.cluster;

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
	public String getHeader(){
		return this.header;
	}
}
