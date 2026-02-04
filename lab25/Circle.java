package lab25;

import java.util.Scanner;

public class Circle extends GeometricShape2D{
    
    private float radius;

    public Circle(float radius)
    {
        this.radius= radius;
    }

    public  float calculateArea(){
        
        return radius* radius* (float)Math.PI;
    }
    public  void printInfo(){
        System.out.println("-Circle, radius "+radius);
    }
    public String toString()
    {
        return "Circle";
    }
    public void editShape()
    {
        Scanner sc= new Scanner (System.in);
        System.out.println("enter new radius");
        this.radius= sc.nextFloat();
    }
}
