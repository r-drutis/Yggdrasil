package com.yggdrasil.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.upgma.cluster.Cluster;
import com.upgma.cluster.DNASequence;
import com.upgma.cluster.DefaultFastaGenerator;
import com.upgma.cluster.DisMatrix;


/**
 * Servlet implementation class FastaReader
 */
@WebServlet("/FastaReader")
public class FastaReader extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FastaReader() {
        super();

    }
    
    /**
	 * FASTA reader method readFasta()
	 * Reads the FASTA file uploaded by the user on the index page
	 * A fasta file is delineated by a header (beginning with a ">" character) and
	 * a sequence of DNA nucleotides (ATCG and "-" if the sequences has been aligned)
	 * 
	 * The method reads the file line by line and concatenates lines of DNA sequence into a single string
	 * After reaching a header, the DNA sequence and previous header are used to construct a DNA sequence object,
	 * and the objects are stored in sequenceList, which is returned
	 * 
	 * @param fastaSeqs:		File item list containing uploaded FASTA file
	 * @return sequenceList:	List of DNA sequence objects with header and sequence strings
	 */
    private ArrayList<DNASequence> readFasta(List<FileItem> fastaSeqs) throws IOException {
    	ArrayList<DNASequence> sequenceList = new ArrayList<DNASequence>();
    	StringBuilder sequence = new StringBuilder();
    	int seqNum = 0;	// Number of sequences parsed
		
		// Parse FASTA format file for headers staring with ">" using Regexp
		String header = "";
		String headerStart = ">";
		Pattern p = Pattern.compile(headerStart);
		Matcher m;
    	
        // Read text from file line by line 
        for (FileItem item : fastaSeqs) {


        		InputStream fileContent = item.getInputStream();		                
            	BufferedReader reader = new BufferedReader(new InputStreamReader(fileContent));		                		           
            	String line;
            
            	while ((line = reader.readLine()) != null) {
                
            		m = p.matcher(line);	//Check if line is a header
                
            		// If it's not a header, it's a sequence
                	if(m.lookingAt() == false){
                		sequence.append(line);
                	}
                	else{
                		// Create a new DNASequence object using the header and sequence
                		if(seqNum != 0){
                			sequenceList.add((seqNum-1), new DNASequence(header, sequence.toString())); 
                			sequence.setLength(0);	// empty the sequence string for next sequence
                		}
                		header = line;	// read in next header
                		seqNum++;		                    	
                	}		                	  		                    		                   
            	}
            	// capture the final sequence
            	sequenceList.add((seqNum-1), new DNASequence(header, sequence.toString()));

        }        
        return sequenceList;
    }
    
    
    /**
	 * UPGMA Tree Generator method generateTree()
	 * 
	 * Generates a UPGMA distance tree from a given list of aligned DNA sequences
	 * The tree generated is a rooted dendrogram represented by a newick format string
	 * 
	 * The DNA sequences are first used to generate a pairwise dissimilarity matrix based on how many
	 * nucleotide differences there are between the sequence pairs 
	 * The dissimilarity matrix is used to cluster together sequences using the paired arithmetic mean
	 * and creates a tree made up of Branch objects. At the end of the branch objects are the sequences, or "leaves"
	 * of the tree, represented in the newick format
	 * Example: ((a,b),(c,d))
	 * 
	 * @param clusterSequences:	List of DNA sequences to be clustered into a phylogenetic tree
	 * @return newick:			The newick format string of the UPGMA tree generated through clustering
	 */
    private String generateTree(ArrayList<DNASequence> clusterSequences) {
    	
    	String newick;	// Newick format of generated UPGMA tree
    	
    	// Generate a dissimilarity Matrix
    	DisMatrix dmatrix = new DisMatrix(clusterSequences);	
		double[][] scores = dmatrix.calcScores();
		// Generate tree by clustering nearest neighbors in matrix	
		Cluster cluster = new Cluster(clusterSequences); 
		newick = cluster.joinNearest(scores);
		
		return newick;		
    }
    
    
    /**
	 * Mutant sequence generator method generateMutants()
	 * Accepts a list of DNA sequences selected by the user and randomly changes nucleotides 
	 * based on the specified parameters. The chance that a mutation event
	 * occurs is set by the user and is determined by generating a number between 1 and 100
	 * A transition is a change between two purines or two pyrimidines
	 * Purines (A <-> G) and Pyrimidines (C <-> T)
	 * Whereas a transversion is a change between pyrimadine to purine and vice versa
	 * For example, (A <-> T) and (A <-> C), (G <-> T) and (G <-> C)
	 * 
	 * This method checks each nucleotide individually and returns a new set of mutated sequences
	 * which are stored in the session object and displayed in the tree.jsp page
	 * 
	 * @param mutants:		Positions of sequences to mutate in session DNASequence list
	 * @param rateMutation:	The chance of a mutation event occuring from 1-100
	 * @param rateTrBias:	Ratio of transitions to transversions. A higher % means more transitions likely
	 * @param seqsToMutate:	The current list of DNA objects to be mutated
	 * @return mutantList:	DNASequence list with mutant sequences
	 */
    private ArrayList<DNASequence> generateMutants(String[] mutants, int rateMutation, int rateTrBias, ArrayList<DNASequence> seqsToMutate){
    	ArrayList<DNASequence> mutantList = seqsToMutate;	
    	Random rate = new Random();							// Probability of mutation
    	StringBuilder mutSeq = new StringBuilder();			// New mutant sequence
    	

    	// For each sequence the user chooses to mutate, calculate the the probability of a mutation event
    	for (String seq : mutants){
    		
    		String currentSeq = mutantList.get(Integer.parseInt(seq)).getSequence();
    		
    		for(int i=0; i< currentSeq.length(); i++){ 
    			
    			//calculate probability of mutation event
    			if (rateMutation >= rate.nextInt(100) + 1) {
    				 
    				// Mutation event occurs, calculate probability of transition or transversion
    				
    				//Transition event	
    				if (rateTrBias >= rate.nextInt(100) + 1) {	
    					// Interchange Purines (A <-> G) and Pyrimidines (C <-> T)
    					if(currentSeq.charAt(i) == 'A') {
    						mutSeq.append('G');
    					}
    					else if(currentSeq.charAt(i) == 'G') {
    						mutSeq.append('A');
    					}
    					else if(currentSeq.charAt(i) == 'C') {
    						mutSeq.append('T');
    					}
    					else if(currentSeq.charAt(i) == 'T') {
    						mutSeq.append('C');
    					}
    				}
    				//Transversion event
    				
    				// There are two possible transversion events for each nucleotide, selected randomly
    				// For example, (A -> T) and (A -> C) are both possible
    				else {
    					// For our purposes, both are equally likely to happen
    					if((rate.nextInt(2) + 1) == 1) {
    						if(currentSeq.charAt(i) == 'A') {
        						mutSeq.append('T');
        					}
        					else if(currentSeq.charAt(i) == 'G') {
        						mutSeq.append('T');
        					}
        					else if(currentSeq.charAt(i) == 'C') {
        						mutSeq.append('A');
        					}
        					else if(currentSeq.charAt(i) == 'T') {
        						mutSeq.append('A');  
        					}
    					}else {
    						if(currentSeq.charAt(i) == 'A') {
        						mutSeq.append('C');
        					}
        					else if(currentSeq.charAt(i) == 'G') {
        						mutSeq.append('C');
        					}
        					else if(currentSeq.charAt(i) == 'C') {
        						mutSeq.append('G');
        					}
        					else if(currentSeq.charAt(i) == 'T') {
        						mutSeq.append('G');  		
        					}					
    					}
    				}	
    			}
    			// Otherwise, no mutation occurs
    			else {
    				mutSeq.append(currentSeq.charAt(i));
    			}		 
        	}
    		
    		mutantList.get(Integer.parseInt(seq)).setSequence(mutSeq.toString());
    		mutantList.get(Integer.parseInt(seq)).setHeader(">MUTANT");
  		
    		mutSeq.setLength(0);
    	}    	
    	
    	return mutantList;
    	
    	
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * Servlet doPost method
	 * 
	 * This is the main method which handles requests from other pages.
	 * 
	 * 
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		HttpSession session = request.getSession();
		
		// Store DNA Sequences in fasta file 		
		ArrayList<DNASequence> fastaList = new ArrayList<DNASequence>();						
		String newickFormat;	// Newick format output
		
		// If user is uploading a FASTA file, parse it and store the DNA sequences
		if(ServletFileUpload.isMultipartContent(request)) {					
	
			try {
				// Get uploaded file 
				FileItemFactory itemFactory = new DiskFileItemFactory();
				ServletFileUpload upload = new ServletFileUpload(itemFactory);		        
				List<FileItem> items = upload.parseRequest(request);
				if (items.get(0).getSize() == 0) {
		            DefaultFastaGenerator dfg = new DefaultFastaGenerator();
		            fastaList = dfg.treesJoyceKilmer();
		        }
				else{
					// Get sequences to be clustered from FASTA file 
					fastaList = readFasta(items);
				}  		        
		    } catch (FileUploadException e) {
		        throw new ServletException("Cannot parse multipart request.", e);
		    }
		 
			// Store FASTA sequences in session object
			session.setAttribute("sequence", fastaList);
			// Generate UPGMA tree and store as newick format string
			newickFormat = generateTree(fastaList);
		 
			// Store newick format in session object		 		 
			session.setAttribute("newick", newickFormat);
		 		 
			String nextJSP = "/tree.jsp";
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJSP);
			dispatcher.forward(request,response);
		}
		
		// Otherwise, the user is generating mutants using an already parsed fasta file
		else if (request.getParameterValues("mutate") != null) {
			String[] checkedIds = request.getParameterValues("mutate");
			
			String[] mutRate = request.getParameterValues("mutRate");			
			int mutationRate = Integer.parseInt(mutRate[0]);
			
			String[] trBiasRate = request.getParameterValues("trBias");
			int transBias = Integer.parseInt(trBiasRate[0]);
			
			// Retrieve list of session DNA sequences and generate mutants
			fastaList = (ArrayList<DNASequence>)session.getAttribute("sequence");
			fastaList = generateMutants(checkedIds, mutationRate, transBias, fastaList);
			
			// Set the new DNA list in the session
			session.setAttribute("sequence", fastaList);
			
			// Create new cluster using mutants
			newickFormat = generateTree(fastaList);
			session.setAttribute("newick", newickFormat);
			// Forward to tree page
			String nextJSP = "/tree.jsp";
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJSP);
			dispatcher.forward(request,response);
		}
		
		else {
			String nextJSP = "/error.jsp";
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(nextJSP);
			dispatcher.forward(request,response);
		}
		
		
	}

}
