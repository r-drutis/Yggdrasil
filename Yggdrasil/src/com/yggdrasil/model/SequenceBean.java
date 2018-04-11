package com.yggdrasil.model;

import java.io.Serializable;
import java.util.ArrayList;

import com.upgma.cluster.DNASequence;

public class SequenceBean implements Serializable {
	
	private ArrayList<DNASequence> sequenceList;

	/**
	 * 
	 */
	public SequenceBean() {
	}

	/**
	 * @return the sequenceList
	 */
	public ArrayList<DNASequence> getSequenceList() {
		return sequenceList;
	}

	/**
	 * @param sequenceList the sequenceList to set
	 */
	public void setSequenceList(ArrayList<DNASequence> sequenceList) {
		this.sequenceList = sequenceList;
	}
}
