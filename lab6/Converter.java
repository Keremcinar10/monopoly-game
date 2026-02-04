package lab6;

import java.util.Random;

public class Converter {
    
    private static double aRate;
    private static double bRate;
    private static double cRate;
    private static double dRate;

    public Converter() {
    }

    public static void generateInitialRates(){
        Random random= new Random();
        aRate=(random.nextDouble()*4.8)+0.2;
        bRate=(random.nextDouble()*4.8)+0.2;
        cRate=(random.nextDouble()*4.8)+0.2;
        dRate=(random.nextDouble()*4.8)+0.2;
    }

    public static double convert(double money, char type) {

        if(type=='A') {
            return aRate*money;
        }
        else if(type=='B') {
            return money*bRate;
        }
        else if(type=='C') {
            return money*cRate;
        }
        else if(type=='D') {
            return money*dRate;
        }
        else return 0;

    }

    public static void setRates(char type, double rate) {
        if(type=='A') {
            aRate =rate; 
        }
        else if(type=='B') {
            bRate =rate; 
        }
        else if(type=='C') {
            cRate =rate; 
        }
        else if(type=='D') {
            cRate =rate; 
        }
    }

    public static void displayRates() {
        System.out.println("Current conversion rates:");
        System.out.println("A: "+aRate);
        System.out.println("B: "+bRate);
        System.out.println("C: "+cRate);
        System.out.println("D: "+dRate);
    }

}
