package cs102final;

import java.util.ArrayList;

public class Permutations {
   
   public static void main(String[] args) 
    { 
        for (String s : permutations("eat")) 
        {          
        System.out.println(s); 
        } 
    }
    public static ArrayList<String> permutations(String str) {

        ArrayList <String> result = new ArrayList<String>(); 

        if(str.length() == 0) {
            result.add(str);
            return result;
        }

        for (int i=0;i <str.length();i++) {

            String shorter = str.substring(0, i)+ str.substring(i+1);
            ArrayList<String> shorterPermutations= permutations(shorter); 

            for(String s : shorterPermutations) {

                result.add(str.charAt(i) + s);
            }
        }
        return result;
    }
}
