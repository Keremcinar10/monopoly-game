package lab25;

import java.util.Scanner;

public class Pyramid extends GeometricShape3D{
    
    private float side;
    private float heigth;

    public Pyramid(float side, float heigth)
    {
        this.side= side;
        
        this.heigth= heigth;
    }
    public  float calculateArea(){
        return side* side;
    }
    public  void printInfo(){
        System.out.println("-Pyramid, side:"+side+", heigth:"+ heigth);
    }
    public  float calculateVolume(){
        return side * side* heigth;
    }
    public String toString()
    {
        return "Pyramid";
    }
    public void editShape()
    {
        Scanner sc= new Scanner (System.in);
        System.out.println("enter heigth");
        this.heigth= sc.nextFloat();
        System.out.println("enter side");
        this.side= sc.nextFloat();
        
       
    }

}
