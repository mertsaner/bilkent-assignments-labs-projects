/**
	Author: Yasin BALCANCI / 21501109 / CS102-1
	A Document object takes all the words of a file in an array of Terms.
*/

import java.util.Scanner;
import java.io.*;

public class Document {
	private String fileName;
	private Term[] terms;
	private int numOfWords = 0;
	private int currentSize = 0;
	private	Scanner fileScan;

	//@param file name that will be scanned
    public Document(String fileName) throws IOException{
    	this.fileName = fileName;
    	terms = new Term[10000];
    	fileScan = new Scanner(new File(fileName));
    	numOfWords++;
    }
	/*
	takes every word of the file in an array and increases the count. if the word that will
	be added has been added already, the count of the word increases only.
  	*/
  	public void processDocument() throws IOException {
		int i = 0;
		while (fileScan.hasNext()){
			boolean found = false;
			String word = fileScan.next().toLowerCase(); // in order to realize different cases of the same word
			for(int j = 0; j < i; j++){
				if(word.equals(terms[j].getWord())){
					terms[j].incrementCount();
					found = true; numOfWords++;
				}// if
			}// for
			if (!found){
				terms[i] = new Term(word);
				terms[i].incrementCount();
				i++; currentSize++; numOfWords++;
			}// if
  		}// while
  	}// method

	// @param word that will be counted
	// @return the count of the word
	public int getCount(String word){
		int num = 0;
		for (int i = 0; i < currentSize; i++)
			if ((terms[i].getWord()).equals(word)){
				num = terms[i].getCount();
			}
		return num;
	}

	//@param word of which the frequency will be found
	//@return the frequency of the word. (frequency = count of word / number of all the words)
	public double getFrequency(String word){
		return getCount(word)/(double)numOfWords;
	}

	//@return the most frequent word of the document
	public Term getMostFrequentTerm(){
		Term mft = terms[0];
		for(int i = 0; i < currentSize; i++){
			if(getFrequency(terms[i].getWord()) > getFrequency(mft.getWord()))
				mft = terms[i];
		}
		return mft;
	}


}
