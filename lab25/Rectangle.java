package lab25;

import java.util.Scanner;

public class Rectangle extends GeometricShape2D{
    
    protected float side1;
    protected float side2;


    public Rectangle(float side1, float side2)
    {
        this.side1=side1;
        this.side2=side2;
    }
    
    public  float calculateArea(){
        return side1*side2;
    }
    public  void printInfo(){
        System.out.println("-Rectangle, "+side1+" by "+side2);
    }
    public String toString()
    {
        return "Rectangle";
    }
    public void editShape()
    {
        Scanner sc= new Scanner (System.in);
        System.out.println("enter first side");
        this.side1= sc.nextFloat();
        System.out.println("enter second side");
        this.side2= sc.nextFloat();
        
       
    }
}
