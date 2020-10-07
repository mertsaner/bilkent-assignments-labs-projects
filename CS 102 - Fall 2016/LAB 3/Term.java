/**
	Author: Yasin BALCANCI / 21501109 / CS102-1
	Each object of "Term"s has a word and the count of that word.
*/

public class Term {
	private String word;
	private int count;

    // @param word of Term object
    public Term(String word) {
    	this.word = word;
    	count = 0;
    }

    //increments count
    public void incrementCount(){
    	count++;
    }

    //@return count
    public int getCount(){
    	return count;
    }

    //@return word
    public String getWord(){
    	return word;
    }

    //@param new value to set count
    public void setCount(int newCount){
    	count = newCount;
    }


}