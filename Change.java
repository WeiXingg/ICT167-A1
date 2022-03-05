/*
ICT167 Assignment 1
Ong Wei Xing 34444625
5/3/2022
Change.java
Change class
*/

public class Change
{
    private String name;
    private int coin;
    
    public Change()
    {
        name = "";
        coin = 0;
    }
    
    public Change(String newName, int newCoin)
    {
        name = newName;
        coin = newCoin;
    }
    
    public String getName()
    {
        return name;
    }
    
    public int getCoin()
    {
        return coin;
    }
    
    public void setName(String newName)
    {
        name = newName;
    }
    
    public void setCoin(int newCoin)
    {
        coin = newCoin;
    }

    public static boolean isMultipleOf5(int x) //method to check multiple of 5
    {
        while (x > 0)
        {
            x = x - 5;
        }
        
        return x == 0;
    }
    
    public int[] getChange(int[] denoms) //method to count denominations
    {
        int [] newChange = new int[denoms.length];
        int count = this.coin;
        for(int i = 0; i < denoms.length; i++) 
        {
            int quotient = count / denoms[i];
            int remainder = count % denoms[i];
            newChange[i] = quotient;
            count = remainder;
        }
        return newChange;
    }
    
    public static int totalAmt(Change[] newChange) //method to count total sum of all value
    {
        int sum = 0;
        for (int i = 0; i < newChange.length; i++) //find total amount
            {
                sum += newChange[i].getCoin();
            }
        return sum;
    }
    
    public boolean equals(Change tempChange) //method to check for duplicates
    {
        return name.equalsIgnoreCase(tempChange.name);
    }

}