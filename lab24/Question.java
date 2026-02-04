package lab24;


import java.util.ArrayList;
import java.util.Scanner;

class Question {
    
    
        private String text;
        private String answer;

        public Question()
        {
            this.text="";
            this.answer="";
        }

        public void setText(String txt)
        {
            this.text=txt;
        }

        public void setAnswer(String answer)
        {
            this.answer=answer;
        }

        public boolean checkAnswer(String answer)
        {
            return this.answer.equals(answer);
        }

        public void disPlay()
        {
            System.out.println(this.text);
        }
}
 
class ChoiceQuestion extends Question
{
    private ArrayList<String>choices;

    public ChoiceQuestion()
    {
        this.choices=new ArrayList<String>();
    }

    public void addChoice(String choice, boolean correct)
    {
        choices.add(choice);
        if(correct)
        {
            String choiceString = ""+choices.size();
            this.setAnswer(choiceString);   
        }
    }

    public void disPlay()
    {
        super.disPlay();
        for(int i=0;i<choices.size();i++)
        {
            System.out.println(i+1+":" +choices.get(i));
        }
    }
}
class Demo
{
    public static void main(String[] args) {
    Question first = new Question();
    first.setText("what color is the sky?");
    first.setAnswer("blue");
    presentQuestion(first);

    ChoiceQuestion second= new ChoiceQuestion();
    second.setText("what is your name?");
    second.addChoice("kerem", true);
    second.addChoice("ahmet", false);
    second.addChoice("veli", false);
    presentQuestion(second);
    

}
public static void presentQuestion(Question aQuestion)
{
    Scanner sc= new Scanner(System.in);
    aQuestion.disPlay();
    System.out.print("Your answer: ");
    String answer= sc.next();
    System.out.println("The answer is "+ aQuestion.checkAnswer(answer));
}


}
