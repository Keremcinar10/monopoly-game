package lab06;

import java.util.Random;
import java.util.Scanner;

public class Lab06_Q2 {

    static int snakeX=5;
    static int snakeY=5;
    static int foodX=-1;
    static int foodY=-1;
    public static void main(String[] args) {
    playGame();        
    }

    public static void moveSnake(String direction)
    {
        if(direction.equals("W"))
        {
            snakeY-=1;
        }
        if(direction.equals("S"))
        {
            snakeY+=1;
        }
        if(direction.equals("D"))
        {
            snakeX+=1;
        }
        if(direction.equals("A"))
        {
            snakeX-=1;
        }
    }

    public static void checkFood()
    {
        if(foodX==snakeX && foodY == snakeY)
        {
            System.out.println("Yum! Snake ate the food!");
            generateFood();
        }
    }

    public static boolean isGameOver()
    {
        return (snakeX==10 || snakeY==10 || snakeX==-1 || snakeY==-1);
    } 

    public static void generateFood()
    {
        Random random = new Random();
        int randomX= random.nextInt(10);
        int randomY= random.nextInt(10);
        foodX=randomX;
        foodY= randomY;
    }

    public static void printGrid()
    {
        for (int i=0;i<10;i++)
        {
            for(int j=0;j<10;j++)
            {
                if(j==snakeX && i== snakeY)
                {
                    System.out.print("S");
                }
                else if(j==foodX && i== foodY)
                {
                    System.out.print("O");
                }
                else
                {
                    System.out.print(".");
                }
            }
            System.out.println();
        }
    }
    public static void playGame()
    {
        Scanner sc= new Scanner (System.in);
        System.out.println("Welcome to Hungary Snake!");
        System.out.println("Use W (up), A (left), S (down), D (right) to move.");
        while (!isGameOver())
        {
           if(foodX<0 && foodY<0)
            {
                generateFood();
            }
            printGrid();
            System.out.print("Move: ");
            moveSnake(sc.next());
            checkFood();
        }
        System.out.println("Game over! Snake hit the wall.");
    }
}