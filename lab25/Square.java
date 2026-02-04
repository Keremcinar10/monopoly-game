package lab25;

import java.util.Scanner;

public class Square extends Rectangle{
    
    private float side;

       public Square(float aSide)
       {
        super(aSide, aSide);
        this.side= aSide;
       }
       public void printInfo()
        {
            System.out.println("-Square, "+side);
        }
        public String toString()
    {
        return "Square";
    }
    public void editShape()
    {
        Scanner sc= new Scanner (System.in);
        System.out.println("enter side");
        this.side= sc.nextFloat();
        
        
       
    }
   
}
