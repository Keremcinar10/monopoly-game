package lab02;
import java.util.Scanner;
public class Lab02_Q3 {
    public static void main(String[] args) {
        Scanner sc= new Scanner(System.in);
    //initialize result string empty.
        String result="";
    //getting information
        System.out.print("Enter your name: ");
        String name= sc.next();
        System.out.print("Enter your favorite color: ");
        String color= sc.next();
    //mixing process
        String newName=name.charAt(name.length()-1)+name.substring(1,name.length()-1)+name.charAt(0);
        StringBuilder middle= new StringBuilder(color.substring(1, color.length()-1));
        String newColor = ""+color.charAt(0)+middle.reverse()+color.charAt(color.length()-1);
    //building the result string    
        result= result+ newName+ newColor+"ion";
    //printing the result
        System.out.println(result);
        sc.close();
    }
}
