package com.upgma.cluster;

import java.util.ArrayList;

/**
 * Default Fasta Generator Class
 * 
 * Generates a default FASTA file if the user has not uploaded a FASTA file to process
 * The FASTA File format is simply a text file format of DNA sequences as follows:
 * 
 * ">Header"
 * "Sequence"
 * 
 */
public class DefaultFastaGenerator {
	
	public DefaultFastaGenerator() {
    }

	public ArrayList<DNASequence> treesJoyceKilmer(){
		ArrayList<DNASequence> defaultFasta = new ArrayList<DNASequence>();
		
		defaultFasta.add(new DNASequence(">I think that I shall never see","CTGAGCTAGCGTCTGCTCTCGAGCTAGCGTACTTAGAGCCTGAGCTGACGTACTATCATCAGCCTCCTAGTACTACACAGCTGACTACTAAGC"));
		defaultFasta.add(new DNASequence(">A poem lovely as a tree","ACTAGCGAGTGTCTAACAAGCATCTGTGTACTAATCGACAGCACTTGAAGCACTAGCTAGCACCTACTAACGAGC"));
		defaultFasta.add(new DNASequence(">A tree whose hungry mouth is prest","ACTAGCTAGCACCTACTAAGCATGCGTTGTTGACTAAGCCGTGATCTCGTCCACGACAGCACATGTGATTAGCGTAGCCTGTGAAGCGAGCACCTATGATAGAGC"));
		defaultFasta.add(new DNASequence(">Against the earth’s sweet flowing breast","ACTGTCACTCTGCTCTGATAGAGCTAGCGTCTAAGCCTAACTCACTAGCGTTGAAGCTGAATGCTACTATAGAGCGCTATCTGTATGCTGCTCGTCAGCCATCACCTAACTTGATAG"));
		defaultFasta.add(new DNASequence(">A tree that looks at God all day","ACTAGCTAGCACCTACTAAGCTAGCGTACTTAGAGCATCTGTTGTTCGTGAAGCACTTAGAGCGTCTGTTACAGCACTATCATCAGCTACACTGACAGC"));
		defaultFasta.add(new DNASequence(">And lifts her leafy arms to pray","ACTCTCTACAGCATCCTGGCTTAGTGAAGCCGTCTACACAGCATCCTAACTGCTGACAGCACTCACACATGAAGCTAGTGTAGCGAGCACACTGAC"));
		defaultFasta.add(new DNASequence(">A tree that may in Summer wear","ACTAGCTAGCACCTACTAAGCTAGCGTACTTAGAGCACAACTGACAGCCTGCTCAGCTGAGATACAACACTACACAGCATGCTAACTCACAGC"));
		
		defaultFasta.add(new DNASequence(">A nest of robins in her hair","ACTAGCCTCCTATGATAGAGCTGTGCTAGCCACTGTCATCTGCTCTGAAGCCTGCTCAGCCGTCTACACAGCCGTACTCTGCAC"));
		defaultFasta.add(new DNASequence(">Upon whose bosom snow has lain","GATGAGTGTCTCAGCATGCGTTGTTGACTAAGCCATTGTTGATGTACAAGCTGACTCTGTATGAGCCGTACTTGAAGCATCACTCTGCTCAGC"));
		defaultFasta.add(new DNASequence(">Who intimately lives with rain","ATGCGTTGTAGCCTGCTCTAGCTGACAACTTAGCTAATCGACAGCATCCTGGTACTATGAAGCATGCTGTAGCGTAGCCACACTCTGCTCACG"));
		defaultFasta.add(new DNASequence(">Poems are made by fools like me","GAGTGTCTAACATGAAGCACTCACCTAAGCACAACTTACCTAAGCCATGACAGCGCTTGTTGTATCTGAAGCATCCTGTCGCTAAGCACACTAAGC"));
		defaultFasta.add(new DNASequence(">But only God can make a tree","CATGATTAGAGCTGTCTCATCGACAGCGTCTGTTACAGCTCAACTCTCAGCACAACTTCGCTAAGCACTAGCTAGCACCTACTAACG"));
		
		return defaultFasta;
	}
	
}
