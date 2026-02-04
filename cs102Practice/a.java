package cs102Practice;

import java.util.Random;

public class a {
    
    public static void main(String[] args) {
        
        String []a= new String[3];
        changeA(a);
        System.out.println();
    }

    public static void changeA(String [] k)
    {
        String[]temp = new String[3];
        Random random = new Random();
        for (int i=0;i<3;i++)
        {
            int b= random.nextInt(10);
            temp[i]=String.valueOf(b);
        }
        k= temp;
    }
}
