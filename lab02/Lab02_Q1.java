package lab02;
import java.util.Scanner;
public class Lab02_Q1 {
public static void main(String[] args) {
    // sample inputs 55 160 25
        Scanner sc=new Scanner(System.in);
        int weight;
        int height;
        int age;
    // getting weight, height and age informations feom the user.
        System.out.print("Enter your weight(kg), height(cm) and age: ");
        weight=sc.nextInt();
        height=sc.nextInt();
        age=sc.nextInt();
    //calculating results
        double bmr=(10*(double)weight)+(6.25*(double)height)-(5*(double)age)-161.0;
        double PAL=1.4;
        double calories=bmr*PAL;
    //printing results
        System.out.printf("Calories required: %20.2f\n",calories);
        System.out.printf("%18s %20.2f\n","GRAMS PROTEIN:",(calories*25/100)/4);
        System.out.printf("%18s %20.2f\n","GRAMS FAT:",(calories*30/100)/9);
        System.out.printf("%18s %20.2f","GRAMS CARBS:",(calories*45/100)/4);
        sc.close();
    }
}