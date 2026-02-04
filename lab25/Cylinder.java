package lab25;

import java.util.Scanner;

public class Cylinder extends GeometricShape3D{
    
    private float height;
    private float radius;

    public Cylinder(float height, float radius)
    {
        this.height= height;
        this.radius= radius;
    }

    public  float calculateArea(){
        return radius* radius* (float)Math.PI;
    }
    public  void printInfo(){
        System.out.println("-Cylinder, radius:"+radius+", heigth:"+height);
    }
    public  float calculateVolume(){
        return radius* radius* (float)Math.PI* height;
    }
    public String toString()
    {
        return "Cylinder";
    }
    public void editShape()
    {
        Scanner sc= new Scanner (System.in);
        System.out.println("enter heigth");
        this.height= sc.nextFloat();
        System.out.println("enter radius");
        this.radius= sc.nextFloat();
        
       
    }
}
