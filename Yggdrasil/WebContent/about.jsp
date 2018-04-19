<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<style>

	body {
    	background-color: #e5e8d7;
	}

</style>
<title>Usage</title>
</head>
<body>
	<div>
		<h1><span style="font-family: monospace;">ABOUT</span></h1>
		-----------------------------<br/>
		<p><span style="font-family: monospace;">Yggdrasil is a simple and user-friendly web tool, that constructs phylogenetic trees (Newick Format) for analysis of relationships between multiple sequences alignments.</span><p>

		<h3><span style="font-family: monospace;">Features:</span></h3>
		<span style="font-family: monospace;">Read .FASTA sequences and outputs UPGMA trees<br/>
		Presenting Newick format for sequences<br/>
		Visually exploring trees with branch lengths that represent the distance between your samples, or see the topology only<br/>
		Changing sequences overtime by mutation rate or transition/transversion ratio<br/></span>
	</div>
	<div>
		<h1><span style="font-family: monospace;">USAGE</span></h1>
		-----------------------------<br/>
		<span style="font-family: monospace;"><p>1) To construct a phylogenetic tree, select the 'Browse' button and choose a FASTA format file which contains your sequences of interest. If you don't have a FASTA, simply hit submit to use a default FASTA file we have provided</p></br>

			<p>This web tool builds trees with the Unweighted Pair Group Method with Arithmetic Mean (UPGMA) algorithm. UPGMA is an effective hierarchical clustering method for phylogenetic reconstruction.</p>

			The UPGMA algorithm:<br/>
			1) Identify the minimum distance between any two taxa<br/>
			2) Combine these two taxa as a single pair<br/>
			3) Re-calculate the average distance between this pair and all other taxa to form a new matrix<br/>
			4) identifies the closest pair in the new matrix<br/>
			5) and so on, until the last two clusters are joined.<br/>


			<p>FASTA files can be obtained from databases such as NCBI, Ensembl or GenBank.</p>

			<p>FASTA format is a text-based format for representing either nucleotide sequences or peptide sequences, in which base pairs or amino acids are represented using single-letter codes. A sequence in FASTA format begins with a single-line description, followed by lines of sequence data. The description line is distinguished from the sequence data by a greater-than (">") symbol in the first column.</p>

			An example sequence in FASTA format is:<br/> 

			>sequence1<br/>
			ACTCCCCGTGCGCGCCCGGCCCGTAGCGTCCTCGTCGCCGCCCCTCGTCTCGCAGCCGCAGCCCGCGTGG<br/>
			ACGCTCTCGCCTGAGCGCCGCGGACTAGCCCGGGTGGCC<br/>
			>sequence2<br/>
			CAGTCCGGCAGCGCCGGGGTTAAGCGGCCCAAGTAAACGTAGCGCAGCGATCGGCGCCGGAGATTCGCGA<br/>
			ACCCGACACTCCGCGCCGCCCGCCGGCCAGGACCCGCGGCGCGATCGCGGCGCCGCGCTACAGCCAGCCT<br/>
			CACTGGCGCGCGGGCGAGCGCACGGGCGCTC<br/>
			>sequence3<br/>
			CACGACAGGCCCGCTGAGGCTTGTGCCAGACCTTGGAAACCTCAGGTATATACCTTTCCAGACGCGGGAT<br/>
			CTCCCCTCCCC<br/>
			>sequence4<br/>
			CAGCAGACATCTGAATGAAGAAGAGGGTGCCAGCGGGTATGAGGAGTGCATTATCGTTAATGGGAACTTC<br/>
			AGTGACCAGTCCTCAGACACGAAGGATGCTCCCTCACCCCCAGTCTTGGAGGCAATCTGCACAGAGCCAG<br/>
			TCTGCACACC<br/>

			<p>***NOTE: This web tool requires three or more sequences in the FASTA file to correctly construct a tree.</p><br/>



			2) Once, you have chosen your FASTA file, click on the 'Start Upload' button to construct your tree.<br/> 

			<p>In the following page, you will be greeted by your UPGMA Tree on the left and a sequence view on the right. Below the sequence view are sliders to control the mutation and transition/transversion rates of each sequence. To change the rates, select on a sequence to highlight it. Multiple sequences can be highlighted. Then adjust the slider (Hover over to show rate) to the desired rate and click on 'Mutate'. Sequences can be mutated several times and changes are reflected on the phylogenetic tree to the left. Mutated sequences can be reset by hitting the 'Clear' button.</p>

			Mutant Rate: The frequency of new mutations in a single gene or organism over time. The slider for mutation rate is a percent chance that each nucleotide base has to mutate.<br/>

			Transitions are point mutation that changes two-ring purines (A G) or of one-ring pyrimidines (C T): they therefore involve bases of similar shape. Transversions are substitutions of purine for pyrimidine bases, which therefore involve exchange of one-ring and two-ring structures. The transition/transversion slider represents a ratio. At 100% it will be 100% transition mutations. At 50% it will be 50/50 on transition or transversion. At 0% it will be 100% transversion.<br/>

		</span>
	</div>
</body>
</html>