package lab04;

import java.util.Scanner;

public class Lab04_Q4 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int totalCourses = 1;
        double totalNumerator = 0;
        int totalCredits = 0;
         
        System.out.print("Course "+ totalCourses +" credits: ");
        int enteredCredit = sc.nextInt();
        System.out.println();

        while(enteredCredit > 0)
        {
            int totalContribution = 0;
            int enteredContribution = 0;
            int enteredGrade= 0; 
            double totalCourseGrade = 0;
            while(totalContribution < 100)
            {
                System.out.print("Enter the grade: ");
                enteredGrade= sc.nextInt();
                while(enteredGrade < 100 && enteredGrade < 0)
                {
                    System.out.println("The grade you entered is invalid ");
                    System.out.print("Enter the grade: ");
                    enteredGrade= sc.nextInt();
                }
                System.out.print("Enter the contribution (in %): ");
                enteredContribution = sc.nextInt();
                while(enteredContribution < 100 && enteredContribution < 0)
                {
                    System.out.println("The contribution you entered is invalid");
                    System.out.print("Enter the contribution (in %): ");
                    enteredContribution = sc.nextInt();
                }
                totalContribution += enteredContribution;
                double partialCourseGrade = ((double)enteredContribution / 100.0) * (double)enteredGrade; 
                totalCourseGrade += partialCourseGrade;
            }
            System.out.println("Total contribution has reached 100% for this course");
            System.out.println("Course grade out of 100: "+totalCourseGrade);
            double GPA;
            if(totalCourseGrade>=80)
            {
                GPA = 4;
            }
            else if(totalCourseGrade < 80 && totalCourseGrade >= 60)
            {
                GPA = 3;
            }
            else if(totalCourseGrade < 60 && totalCourseGrade >=40)
            {
                GPA = 3;
            }
            else if(totalCourseGrade < 40 && totalCourseGrade >=20)
            {
                GPA = 1;
            }
            else
            {
                GPA = 0;
            }
            System.out.println("Course GPA: "+GPA);
            
            totalCredits += enteredCredit;
            totalCourses++;

            double numerator = GPA * enteredCredit;
            totalNumerator += numerator; 

            System.out.print("Course "+ totalCourses +" credits: ");
            enteredCredit = sc.nextInt();
        }
        System.out.println("Total courses: "+(totalCourses-1));
        System.out.println("Total credits: "+ totalCredits);
        System.out.printf("Total GPA: %.2f", (totalNumerator / (double)totalCredits));



    } 
}
