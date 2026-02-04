package lab01;

public class lab01_Q3 {
    public static void main(String[] args) {
        
        double x= (1.0+Math.sqrt(5.0))/2.0;
        int n=6;
        double y=(Math.pow(x, n)-( Math.pow(-1/x, n)))/Math.sqrt(5.0);
        System.out.println("Fibonacci ("+n+") is "+ y);




    }
}
