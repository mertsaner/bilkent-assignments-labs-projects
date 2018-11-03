/**
	Author: Yasin BALCANCI / 21501109 / CS102-1
	Asks user to type the number and name of the files. Then takes all the words of the file
	into Term and Document objects. Serves user a menu. User can select to see the tf-idf, most
	frequent terms or the frequency of a specific word that user provides.
*/

import java.util.Scanner;
import java.io.*;

public class DocumentAnalyzer {

	/*
		@param an array of Documents length of which will be typed by user.
		@param a Document object
		@param a word
		@return tf-idf of parameters.
		tf-idf(t,d,D) = tf(t,d) * idf(t,D)
		tf(t,d) = (number of times t appeared in d) / (total number of words in d)
		idf(t,D) = (number of total documents in D) / (number of document including t in D)
	*/
	public static double calculateTfidf(Document[] docs, Document doc, String word){
		double tf = doc.getFrequency(word);
		int numOfDocs›nclWord = 0;
		for(int i = 0; i < docs.length; i++)
			if(docs[i].getCount(word) > 0)
				numOfDocs›nclWord++;
		if(numOfDocs›nclWord == 0) return 0;
		else{
			double idf = Math.log((docs.length)/(double)numOfDocs›nclWord);
			return tf*idf;
		}
	}

    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(System.in);
        boolean quit = false;
        System.out.println("Enter the number of documents: ");
        int numOfDocs = in.nextInt();
        Document[] docs = new Document[numOfDocs];
        for(int i = 0; i < numOfDocs; i++){ // takes the files into Documents and processes them
        	System.out.println("Enter the name of the document " + (i+1));
        	docs[i] = new Document(in.next());
        	docs[i].processDocument();
        }
        do { // the menu
	        System.out.println("Enter an option (4 to quit): ");
	        int choice = in.nextInt();
	        if(choice == 1){ // This option shows the frequency of the word that user type
	        	System.out.println("Enter the word which you want to find the frequency: ");
	        	String word = in.next();
	        	for(int i = 0; i < numOfDocs; i++){
	        		System.out.println("Document " + (i+1));
	        		System.out.println("	word: " + word);
	        		System.out.println("	term frequency: " + docs[i].getFrequency(word));
	        		System.out.println("	word count: " + docs[i].getCount(word));
	        	}
	        }
	        else if(choice == 2){// This option shows the most frequent terms of each file
	        	for(int i = 0; i < numOfDocs; i++){
	        		System.out.println("Document " + (i+1));
	        		System.out.println("	word: " + docs[i].getMostFrequentTerm().getWord());
	        		System.out.println("	number of appearance: " + docs[i].getMostFrequentTerm().getCount());
	        	}
	        }
	        else if(choice == 3){// This option shows the tf-idf of a word that user types.
	        	System.out.println("Enter the word which you want to calculate tf-idf: ");
	        	String word = in.next();
	        	for(int i = 0; i < numOfDocs; i++){
	        		System.out.println("Document " + (i+1));
	        		System.out.println("	word: " + word);
	        		System.out.println("	tf-idf: " + calculateTfidf(docs,docs[i],word));
	        		System.out.println("	word count: " + docs[i].getCount(word));
	        	}
	        }
	        else{quit = true; System.out.println("Done!");}; // If the uses types a different number from 1,2,3, program exits.
        } while (!quit);
    }
}
