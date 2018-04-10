package com.upgma.cluster;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class FastaReader {
			
	public ArrayList<DNASequence> readFasta(String fastaFile) throws IOException{
		String header = "";
		String headerStart = ">";
		StringBuilder sequence = new StringBuilder();

		ArrayList<DNASequence> sequenceList = new ArrayList<DNASequence>();
		int seqNum = 0;


		
		
		Pattern p = Pattern.compile(headerStart);
		Matcher m;
		
		try (Scanner sc = new Scanner(new File(fastaFile))) {
		
            while (sc.hasNextLine()) {            	            	          	            	
                String line = sc.nextLine().trim();                
                m = p.matcher(line);
             
                if(m.lookingAt() == false){
                	sequence.append(line);
                }
                else{
                	
                	if(seqNum != 0){
                		sequenceList.add((seqNum-1), new DNASequence(header, sequence.toString())); 
                		sequence.setLength(0);
                	}
                	header = line;
                	seqNum++;
                	
                }
            }
            sequenceList.add((seqNum-1), new DNASequence(header, sequence.toString())); 
        }
     

		
		
		return(sequenceList);
		
	}
}
	


