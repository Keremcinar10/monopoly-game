package lab28;

import java.util.ArrayList;
import java.util.Scanner;

 class Question
{
    
    String answer;
    String text;

    public void Question()
    {
        this.text="";
        this.answer="";
    }

    public void setText(String a)
    {
        this.text= a;
    }

    public void setAnswer(String a)
    {
        this.answer= a;
    }

    public boolean checkAnswer(String a)
    {
        return this.answer.equals(a);
    }

    public void display()
    {
       System.out.println(text);
    }
}

class ChoiceQuestion extends Question
{
    ArrayList<String> choices;

    public ChoiceQuestion()
    {
    
        choices= new ArrayList<String>();
    }
    public void addChoice(String choice, boolean isCorrect)
    {
        choices.add(choice);
        if(isCorrect)
        {
            this.setAnswer(""+ choices.size());
        }
    }
    public void display()
    {
       super.display();
       int i=1;
       for(String nextChoice: choices)
       {
            System.out.println(i+") "+choices.get(i-1));
            i++;
       }
    }
}
 class QuesitonDemo
{
   public static void main(String[] args) {
    
    Question first = new Question();
    first.setText("Who is the inventor of Java?");
    first.setAnswer("James Gosling");

    ChoiceQuestion second = new ChoiceQuestion();
    second.setText("In which country the inventor of Java born?");
    second.addChoice("Australia", false);
    second.addChoice("Canada", true);
    second.addChoice("Denmark", false);
    second.addChoice("United States", false);

    
    Question a=(Question) second;
    presentQuestion(a);
    presentQuestion(first);
    presentQuestion(second);
   }

   public static void presentQuestion(Question q)
   {    
        Scanner sc= new Scanner (System.in);
        q.display();
        System.out.print("Enter your answer: ");
        String answer= sc.nextLine();
        if( q.checkAnswer(answer))
        {
            System.out.println("Your answer is true");
        } else 
        {
            System.out.println("Your answer is false");
        }

   }
}

