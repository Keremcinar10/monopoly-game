package lab02;
import java.util.Scanner;
public class Lab02_Q2 {
    public static void main(String[] args) {
        Scanner sc=new Scanner(System.in);
    // sample output ( 90) 312- 266 - 1234

    // getting the phnone number from the user.
        System.out.print("Enter phone number: ");
        String phoneNumber=sc.nextLine();
    // getting some important indexes in the phone number
        int firstParentheses=phoneNumber.indexOf("(");
        int secondParentheses=phoneNumber.indexOf(")",firstParentheses);
        int firstDash=phoneNumber.indexOf("-");
    // seperating requested informations from the main string
        String countryCode=phoneNumber.substring(firstParentheses+1,secondParentheses);
        String cityCode= phoneNumber.substring(secondParentheses+1,firstDash);
        String phoneNo= phoneNumber.substring(firstDash+1);
    // printing the results (also getting rid of empty spaces)
        System.out.println("Country Code: "+countryCode.trim());
        System.out.println("City\\Area Code: "+cityCode.trim());
        System.out.println("Phone Number: "+phoneNo.replace(" ",""));
        sc.close();
    }
}
