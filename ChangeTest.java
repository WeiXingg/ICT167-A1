/*
ICT167 Assignment 1
Ong Wei Xing 34444625
5/3/2022
ChangeTest.java
Main client for testing
name input has to be one word
option 1-6 has to be integer or program will end
*/

import java.util.*;

public class ChangeTest
{
    public static void main(String[] args)
    {
        Scanner input = new Scanner(System.in);
        Change[] change = null;
        int changeLength = 0;
        String name;
        int coin;
        int [] denoms = {100, 50, 20, 10, 5}; //determine the denominations that user want
        String [] denomString = {"1 dollar: ", "50 cents: ", "20 cents: ", "10 cents: ", "5 cents: "}; //create the string for the denominations
        studentInfo();
        System.out.println("Recommendation: Please enter at least 8 records to test the program.");
        
        while(true)
        {
            System.out.println("\nPlease enter a one word name of the person: ");
            name = input.next();
            System.out.println("\nPlease enter the coin value for the person (multiple of 5): ");
            coin = input.nextInt();
            if (Change.isMultipleOf5(coin) == false)
            {
                System.out.println("Incorrect coin value. Must be multiple of 5. Please enter again.");
                continue;
            }
            Change tempChange = new Change(name, coin); //create a temp array to store inputs
            Change[] dupCheck = new Change[changeLength+1];
            dupCheck[changeLength] = tempChange;//copy the new data from temp into new array of object last location
            
            boolean flag1 = true;
            for (int a = 0; a < changeLength; a++)
            {
                if (dupCheck[changeLength].equals(change[a])) //checking for duplicate
                {
                    int x = tempChange.getCoin();
                    int y = change[a].getCoin();
                    int z = x + y;
                    change[a].setCoin(z);
                    flag1 = false;
                }
            }
            
            if (flag1) //if no duplicate
            {
                Change[] newChange = new Change[changeLength+1]; //create a new array of object
                for(int i = 0; i < changeLength; i++)
                {
                    newChange[i] = change[i]; //copy old array into new array
                }
                newChange[changeLength] = tempChange; //copy the new data from temp into new array of object last location
                change = newChange; //copy new array to main array
                changeLength += 1;
            }
            
            System.out.println("\nDo you have more person to enter (Y/N): ");
            String choice = input.next();
            while (!choice.equalsIgnoreCase("y") && !choice.equalsIgnoreCase("n")) //check for y or n
            {
                System.out.println("\nInvalid option, please key either Y or N: ");
                choice = input.next();
            }
            if (choice.equalsIgnoreCase("n"))
            {
                break;
            }
        }
        
        System.out.println("\n1. Enter a name and display change to be given for each denomination.");
        System.out.println("2. Find the name with the largest amount and display change to be given for each denomination.");
        System.out.println("3. Find the name with the smallest amount and display change to be given for each denomination.");
        System.out.println("4. Calculate and display the total number of coins for each denomination.");
        System.out.println("5. Calculate and display the total amount for the sum of all denominations.");
        System.out.println("6. Exit");
        
        boolean flag2 = true;
        while(flag2)
        {
            System.out.println("\nPlease enter your choice from 1-6: ");
            int choice;
            if (input.hasNextInt())
            {
                choice = input.nextInt();
            }
            else
            {
                System.out.println("You have entered an invalid response. The program will end.");
                return;
            }
            
            while (choice < 1 || choice > 6)
            {
                System.out.println("\nInvalid option, please enter your choice from 1-6: ");
                choice = input.nextInt();
            }
            switch (choice)
            {
                case 1:
                    System.out.println("\nPlease enter a name: ");
                    name = input.next();
                    boolean flag3 = true;
                    for (int i = 0; i < change.length; i++)
                    {
                        if (name.equalsIgnoreCase(change[i].getName())) //match inputted name to array
                        {
                            System.out.println("\nCustomer: \n" 
                                    + change[i].getName() + " " + change[i].getCoin());
                            int [] changeReturn = change[i].getChange(denoms);
                            System.out.println("\nChange:");
                            for (int j = 0; j < denomString.length; j++)
                            {
                                if (changeReturn[j] != 0)
                                {
                                    System.out.println(denomString[j] + changeReturn[j]);
                                    flag3 = false;
                                }
                            }
                        }
                    }
                    if (flag3)
                    {
                        System.out.println("\nName:" + name + "\nNot Found");
                    }
                    break;
                    
                case 2:
                    Change largest = change[0];
                    for (int i = 0; i < change.length; i++)
                    {
                        if (largest.getCoin() < change[i].getCoin()) //to find largest amount
                        {
                            largest = change[i];
                        }
                    }
                    System.out.println("\nCustomer: " + "\n\r"
                                    + largest.getName() + "\n\r" 
                                    + "Biggest Amount: " + largest.getCoin());
                    int [] largestReturn = largest.getChange(denoms);
                    System.out.println("\nChange:");
                    for (int j = 0; j < denomString.length; j++)
                    {
                        if (largestReturn[j] != 0)
                            System.out.println(denomString[j] + largestReturn[j]);
                    }
                    break;
                    
                case 3:
                    Change smallest = change[0];
                    for (int i = 0; i < change.length; i++)
                    {
                        if (smallest.getCoin() > change[i].getCoin()) //to find smallest amount
                        {
                            smallest = change[i];
                        }
                    }
                    System.out.println("\nCustomer: " + "\n\r"
                                    + smallest.getName() + "\n\r"
                                    + "Smallest Amount: " + smallest.getCoin());
                    int [] smallestReturn = smallest.getChange(denoms);
                    System.out.println("\nChange:");
                    for (int j = 0; j < denomString.length; j++)
                    {
                        if (smallestReturn[j] != 0)
                            System.out.println(denomString[j] + smallestReturn[j]);
                    }
                    break;
                    
                case 4:
                    int total = Change.totalAmt(change);
                    Change totalSum = new Change(null, total); //create new array to store the total amount
                    int [] totalReturn = totalSum.getChange(denoms);
                    System.out.println("\nTotal number of coins for each denomination:");
                    for (int i = 0; i < denomString.length; i++)
                    {
                        if (totalReturn[i] != 0)
                            System.out.println(denomString[i] + totalReturn[i]);
                    }
                    break;
                    
                case 5:
                    total = Change.totalAmt(change);
                    System.out.println("\nThe total amount for all user is: " + total);
                    break;
                    
                case 6:
                    System.out.println("\nThank you and hope to see you soon!");
                    flag2 = false;
                    break;
            }
        }
    }
    
    public static void studentInfo()
    {
        System.out.println("Name: Ong Wei Xing\n" 
                + "Student number: 34444625\n"
                + "Online enrolment\n"
                + "Tutor Name: Chong Siew Cheong\n"
                + "Every tuesday 1830-2030hrs\n");
    }
}