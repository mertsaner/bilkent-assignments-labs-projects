import java.util.*;

public class BigIntegerList {

	ArrayList<BigInteger> numbers = new ArrayList<BigInteger>();

    public BigIntegerList(ArrayList<String> nums) {
    	for(int i = 0 ; i < nums.size(); i++)
    		numbers.add(new BigInteger(nums.get(i)));
    }

	public int getSize(){
		return numbers.size();
	}

	public BigInteger getBigInteger(int index){
		return numbers.get(index);
	}

	public void setBigIntegerAt(int index, BigInteger bigInt){
		numbers.set(index, bigInt);
	}

	public void addBigInteger(String number){
		numbers.add(new BigInteger(number));
	}

	public void removeBigInteger(BigInteger bigInt){
		for(int i = 0; i < numbers.size(); i++)
			if (numbers.get(i).compareTo(bigInt) == 0)
				numbers.remove(i);
	}

	public void removeBigInteger(int index){
		numbers.remove(index);
	}

//	public BigInteger min(int start, int end){
//		BigInteger asd = numbers.get(start);
//		if(asd.compareTo(min(start+1, end)) == 1 && start < end)
//			asd = min(start+1, end);
//		return asd;
//	}

	public BigInteger min(int start, int end){
		BigInteger asd = numbers.get(start);
		if(start < end && asd.compareTo(min(start+1, end)) == 1)
			return min(start+1, end);
		else return asd;
	}
}