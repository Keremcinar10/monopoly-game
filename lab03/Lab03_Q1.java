package lab03;
import java.util.Scanner;
public class Lab03_Q1 {
    public static void main(String[] args) {
        Scanner sc= new Scanner (System.in);

        //initializing x y coordinates of players

        double aliceX;
        double aliceY;
        double bobX;
        double bobY;

        // getting the values of coordinates

        System.out.print("Alice's coordinates: ");
        aliceX=sc.nextDouble();
        aliceY=sc.nextDouble();
        System.out.print("Bob's coordinates: ");
        bobX=sc.nextDouble();
        bobY=sc.nextDouble();

        // calculating the distance

        double xDistance=Math.abs(aliceX-bobX);
        double yDistance=Math.abs(aliceY-bobY);
        double distance=Math.sqrt(Math.pow(yDistance,2)+Math.pow(xDistance,2));

        // writing the result
        
        System.out.printf("Distance: %.2f meters\n",distance);
        if(distance<=5)
        {
            System.out.println("Alice and Bob have found each other :D");
        }
        else
        {
            System.out.println("Alice and Bob have not found each other :(");
        }
        sc.close();
    }

}
