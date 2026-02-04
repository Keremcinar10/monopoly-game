package lab06;

import java.util.Scanner;

public class Lab06_Q1 {
    public static void main(String[] args) {
        Scanner sc= new Scanner (System.in);

        System.out.print("Enter a string: ");
        String enteredString= sc.nextLine();
        
        System.out.println("Reversed String: "+reverse(enteredString));
        System.out.println("Is Palindrome: "+isPalindrome(enteredString));
        System.out.println("Has all unique integers: "+hasAllUniqueIntegers(enteredString));
    }
    
    public static String reverse(String str)
    {
        String newString ="";
        for(int i=str.length()-1; i>=0; i--)
        {
            newString+=str.charAt(i);
        }
        return newString;
    }

    public static boolean isPalindrome(String str)
    {
    String newString= reverse(str);
        return newString.equals(str);
    }

    public static boolean hasAllUniqueIntegers(String str)
    {
        String oldString ="";
        
        for(int i=0;i<str.length();i++)
        {
            if(oldString.contains(str.substring(i,i+1)))
            {
                return false;
            }
            oldString+=str.charAt(i);
        }
        return true;
    }


}
