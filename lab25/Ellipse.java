package lab25;

import java.util.Scanner;

public class Ellipse extends GeometricShape2D{
    private float shortSide;
    private float longSide;

    public Ellipse(float longSide, float shortSide)
    {
        this.longSide= longSide;
        this.shortSide= shortSide;
    }
    public  float calculateArea(){
        return (float)Math.PI* longSide* shortSide;
    }
    public  void printInfo(){
        System.out.println("-Ellipse, "+shortSide+" by "+longSide);
    }
    public String toString()
    {
        return "Ellipse";
    }
    public void editShape()
    {
        Scanner sc= new Scanner (System.in);
        System.out.println("enter short radius");
        this.shortSide= sc.nextFloat();
        System.out.println("enter long radius");
        this.longSide= sc.nextFloat();
        
       
    }

}
