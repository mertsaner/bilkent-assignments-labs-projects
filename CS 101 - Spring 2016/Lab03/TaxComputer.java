import java.util.Scanner;

public class TaxComputer 
{
	private String status; //marital status of the person
	private double income;
    private double taxValue;
	
	public TaxComputer(String status, double income) 
	{
		this.income = income;
		this.status = status;
	}
	public double getTaxValue() //method that calculates and returns tax value
	{
		if (status.equals("Married")) 
    	{
    		if (income <= 16000)
    		{
    			taxValue = 5*income / 100;
    		}
    		else
    		{
    			taxValue = 1600 + 15*income / 100;
    		}
    	}
    	else //not married : single
    	{
    		if (income <= 8000)
    		{
    			taxValue = 10*income / 100;
    		}
    		else
    		{
    		taxValue = 800 + 15*income / 100;
    		}
    	}
    	return taxValue; //after calculating, returns tax value.
	}

}
