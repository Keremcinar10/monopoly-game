package lab25;

import java.util.Scanner;

public class EquilateralTriangle extends GeometricShape2D{
    
    private float side;

    public EquilateralTriangle(float side)
    {
        this.side= side;
    }   
    public  float calculateArea(){
        return (side*side *(float)Math.sqrt(3))/4;
    }
    public  void printInfo(){
        System.out.println("-EquilateralTriangle, "+ side);
    }
    public String toString()
    {
        return "Equilateral Triangle";
    }
    public void editShape()
    {
        Scanner sc= new Scanner (System.in);
        System.out.println("enter side");
        this.side= sc.nextFloat();
        
        
       
    }


}
