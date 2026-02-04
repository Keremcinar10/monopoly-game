package lab25;

import java.util.Scanner;

public class Cube extends GeometricShape3D{
    
    private float side;

    public Cube(float side)
    {
        this.side= side;
    }

    public  float calculateArea(){
        return side*side;
    }
    public  void printInfo(){
        System.out.println("-Cube, "+side);
    }
    public  float calculateVolume(){
        return side*side*side;
    }
    public String toString()
    {
        return "Cube";
    }
    public void editShape()
    {
        Scanner sc= new Scanner (System.in);
        System.out.println("enter new side");
        this.side= sc.nextFloat();
    }
}
