//Author Yasin Balcancý
public class Password {
private String password;

	public Password(String password){
		this.password=password;
}
    public boolean checkLength(){//method that checks the length
    	if (password.length()<=10 && password.length()>=6){
    	return true;
   		}
    	else {return false;}
    }

    public boolean checkDigit(){//method that checks the digit
    	char ch;
    	boolean found = false;
    	for(int i=0; i<password.length();i++){
    		ch = password.charAt(i);
    		if (ch>='0' && ch<='9')
    			found = true;
    	}
    		return found;
    }
    public boolean checkUpperCase(){//method that checks the upper case
    	int position = 0;//starts from position 0 and scans the string.
    	char ch = '²';//this is not an important value, it will change
    	boolean found = false;
    	while (!found && position < password.length()){
    		ch = password.charAt(position);
    		if (ch>=65 && ch<=90){found = true;}//65 is unique code of A and 90 is unique code of Z.
    		else{found = false;}
    		position++;
    	}
    		return found;
    	
    }

}
