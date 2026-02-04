package lab03;

import java.util.Scanner;

public class Lab03_Q2 {
public static void main(String[] args) {
    Scanner sc= new Scanner (System.in);

    //initializing variables

    double GPA;
    double firstReferenceScore;
    double secondReferenceScore;
    double noOfPapers;
    double noOfFailedCourses;
    double bilkentProfessorsScore;
    double IELTSscore;

    // to check if eligable

    boolean isEligable=true;

    // to calculate total score

    int totalScore=0;

    // for final message

    String finalMessage="";
    
    // getting values

    System.out.print("GPA: ");
    GPA= sc.nextDouble();
    System.out.print("First reference score: ");
    firstReferenceScore= sc.nextDouble();
    System.out.print("Second reference score: ");
    secondReferenceScore= sc.nextDouble();
    System.out.print("Number of papers: ");
    noOfPapers= sc.nextDouble();
    System.out.print("Number of failed courses: ");
    noOfFailedCourses= sc.nextDouble();
    System.out.print("Bilkent professor’s score: ");
    bilkentProfessorsScore= sc.nextDouble();
    System.out.print("IELTS score: ");
    IELTSscore=sc.nextDouble();

    // checking GPA

    if(GPA>=3.3)
    {
        double diff=GPA-3.3;
        double pointsFromGPA=(diff/0.1)*20;
        totalScore+=pointsFromGPA;
    }
    else
    {
        isEligable=false;
        finalMessage+="- GPA is below the required threshold.\n";
    }

    // checking reference scores

    if(firstReferenceScore>=6&&secondReferenceScore>=6)
    {
        double pointsFromReference=(firstReferenceScore+secondReferenceScore)*10;
        totalScore+=pointsFromReference;
    }
    else
    {
        isEligable=false;
        if(firstReferenceScore<6)
        {
            finalMessage+="- The first reference score is below the required threshold.\n";
        }
        if(secondReferenceScore<6)
        {
            finalMessage+="- The second reference score is below the required threshold.\n";
        }
    }

    // checking papers

    if(noOfPapers>0)
    {
        double pointsFromPapers=(noOfPapers-1)*100;
        totalScore+=pointsFromPapers;
    }
    else
    {
        isEligable=false;
        finalMessage+="- Candidate must have at least one research paper.\n";
    }

    // checking number of failed courses

    if(noOfFailedCourses<=3)
    {
        double pointsDeductedFromFailingCourses=noOfFailedCourses*30;
        totalScore-=pointsDeductedFromFailingCourses;
    }

    else
    {
        isEligable=false;
        finalMessage+="- The number of failed courses exceeds the limit.\n";
    }

    // checking professor score

    if(bilkentProfessorsScore>=5&&!String.valueOf(bilkentProfessorsScore).isEmpty())
    {
        double pointsFromBilkent=bilkentProfessorsScore*30;
        totalScore+=pointsFromBilkent;
    }   

    else
    {
        isEligable=false;
        finalMessage+="- The score given by Bilkent professors is below the required threshold.\n";
    }

    // checking IELTS score

    if(IELTSscore>=6.5)
    {
        double diff2=IELTSscore-6.5;
        double pointsFromIELTS=(diff2/0.5)*10;
        totalScore+=pointsFromIELTS;
    }

    else
    {
        isEligable=false;
        finalMessage+="- IELTS score is below the required threshold.";
    }

    // printing the results

    System.out.println("Calculated Score: "+totalScore);
    if(!isEligable)
    {
        System.out.println("The candidate is NOT eligible.");
    }
    else
    {
        System.out.println("The candidate is eligible for admission.");
    }
    System.out.println(finalMessage);
    sc.close();
}
    
    

}
