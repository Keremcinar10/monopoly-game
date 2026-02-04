package lab25;

import java.util.Scanner;

public class Cuboid extends GeometricShape3D{
    private float side1;
    private float side2;
    private float side3;

public Cuboid(float side1, float side2, float side3)
{
    this.side1= side1;
    this.side2= side2;
    this.side3= side3;
}

    public  float calculateArea(){
        return side1*side2;
    }
    public  void printInfo(){
        System.out.println("-Cuboid, "+side1+" by "+side2+" by "+side3);
    }
    public  float calculateVolume(){
        return side1* side2*side3;
    }
    public String toString()
    {
        return "Cuboid";
    }
    public void editShape()
    {
        Scanner sc= new Scanner (System.in);
        System.out.println("enter first side");
        this.side1= sc.nextFloat();
        System.out.println("enter second side");
        this.side2= sc.nextFloat();
        System.out.println("enter third side");
        this.side3= sc.nextFloat();
       
    }
}
