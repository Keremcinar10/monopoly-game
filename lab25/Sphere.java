package lab25;

import java.util.Scanner;

public class Sphere extends GeometricShape3D{
    
    private float radius;

    public Sphere(float radius)
    {
        this.radius= radius;
    }

    public  float calculateArea(){
        return radius* radius* (float)Math.PI;
    }
    public  void printInfo(){
        System.out.println("-Sphere, "+radius);
    }
    public  float calculateVolume(){
        return (4*(float)Math.PI* radius*radius*radius)/3;
    }
    public String toString()
    {
        return "Sphere";
    }
    public void editShape()
    {
        Scanner sc= new Scanner (System.in);
        System.out.println("enter radius");
        this.radius= sc.nextFloat();
        
       
    }
}
