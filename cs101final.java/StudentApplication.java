import java.util.ArrayList;
import java.util.Collections;

/**
 * 
 * @(#)StudentApplication.java
 *
 *
 * @author 
 * @version 1.00 2021/3/23
 */

public class StudentApplication {
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Student2 s1 = new Student2("Jala","Kantar", 123456, 2020, 45000 );
        Student2 s2 = new Student2("Ellie", "Baykal");
        Student2 s3 = new Student2();
        
        System.out.println( s1 );
        System.out.println( s2 );
        System.out.println( s3 );

        ArrayList<Integer> a = new ArrayList<Integer>();
        a.add(12);
        a.add(13);
        a.add(11);
        Collections.sort(a);
        System.out.println(a);
    }
}
