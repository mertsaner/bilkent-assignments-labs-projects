
public class BigInteger implements Comparable{
	int[] digits;
	String nums;
    public BigInteger(String nums) {
    	while(nums.charAt(0) == '0')
			nums = nums.substring(1);
    	this.nums = nums;
    	digits = new int[nums.length()];
    	for (int i = 0; i < digits.length; i++){
    		digits[i] = (int)nums.charAt(i);;
    	}
    }

    public int numberOfDigits(){
		return nums.length();
    }

    public int getDigit(int index){
    	return digits[index] - 48;
    }

    public int MID(){
		return digits[0]-48;
    }

    public int LID(){
    	return digits[digits.length-1]-48;
    }

	public int compareTo(Object newNum){
		int result = 0; int i = 0;
		if (numberOfDigits() > ((BigInteger)newNum).numberOfDigits())
			result = 1;
		else if (numberOfDigits() < ((BigInteger)newNum).numberOfDigits())
			result = -1;
		else if (numberOfDigits() == ((BigInteger)newNum).numberOfDigits())
		while(result == 0 && i < numberOfDigits() && i < ((BigInteger)newNum).numberOfDigits()){
			if (getDigit(i) > ((BigInteger)newNum).getDigit(i))
				result = 1;
			else if(getDigit(i) < ((BigInteger)newNum).getDigit(i))
				result = -1;
			i++;
		}
		return result;
	}
}