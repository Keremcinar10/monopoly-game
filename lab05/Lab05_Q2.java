package lab05;

import java.util.Random;

public class Lab05_Q2 {
    public static void main(String[] args) {
        
        final int DECK_LENGTH= 44;
        Random random = new Random();

        String initialNumbers="0123456789012345678901234567890123456789CCCC";
        String initialColors= "RRRRRRRRRRGGGGGGGGGGPPPPPPPPPPYYYYYYYYYYBBBB";
        String numbers="";
        String colors="";

        String player1Numbers="";
        String player1Colors="";
        String player2Numbers="";
        String player2Colors="";

        System.out.println("Starting the game!\n\nThe initial deck:");
        System.out.print("numbers: "+initialNumbers);
        System.out.println();
        System.out.print("colors:  "+initialColors);
        System.out.println();

        for(int i=0;i<DECK_LENGTH;i++)
        {
            int randomInt= random.nextInt(initialColors.length());
            numbers += initialNumbers.charAt(randomInt);
            colors += initialColors.charAt(randomInt);
            if(randomInt==0)
            {
                initialNumbers=initialNumbers.substring(1);
                initialColors= initialColors.substring(1);
            }
            else if(randomInt==initialColors.length()-1)
            {
                initialNumbers=initialNumbers.substring(0,initialNumbers.length()-1 );
                initialColors= initialColors.substring(0,initialColors.length()-1);
            }
            else
            {
                initialNumbers=initialNumbers.substring(0,randomInt)+ initialNumbers.substring(randomInt+1);
                initialColors=initialColors.substring(0,randomInt)+ initialColors.substring(randomInt+1);
            }
        }
        System.out.println("Shuffled deck:");
        System.out.print("numbers: "+numbers);
        System.out.println();
        System.out.print("colors:  "+colors);
        System.out.println();

        player1Numbers=numbers.substring(0,7);
        player1Colors=colors.substring(0,7);
        player2Numbers=numbers.substring(7,14);
        player2Colors=colors.substring(7,14);
        numbers= numbers.substring(14);
        colors= colors.substring(14);

        System.out.println("Dealing the initial cards:\n");
        System.out.print("Player1 numbers: "+player1Numbers);
        System.out.println();
        System.err.print("Player1 colors : "+player1Colors);
        System.out.println();
        System.out.print("Player2 numbers: "+player2Numbers);
        System.out.println();
        System.err.print("Player2 colors : "+player2Colors);
        System.out.println();
        System.out.println();
        System.out.println("Deck after dealing player cards:");
        
        while(numbers.charAt(0)=='C')
        {
            numbers= numbers.substring(1)+"C";
            colors=colors.substring(1)+"B";
        }
        System.out.print("numbers: "+numbers);
        System.out.println();
        System.out.print("colors:  "+colors);
        System.out.println();
        System.out.println("Top Card: "+colors.charAt(0)+numbers.charAt(0));
        System.out.println();

        char topCardColor=colors.charAt(0);
        char topCardNumber=numbers.charAt(0);

        numbers=numbers.substring(1);
        colors= colors.substring(1);

        do
        {
            //****************************************************** Player1 ************************************************************ */
            
            System.out.println("Player 1's turn");
            System.out.print("Player1 numbers: "+player1Numbers);
            System.out.println();
            System.err.print("Player1 colors : "+player1Colors);
            System.out.println();
            System.out.print("Player2 numbers: "+player2Numbers);
            System.out.println();
            System.err.print("Player2 colors : "+player2Colors);
            System.out.println();

            boolean hasPlayableCard=false;
            for(int i=0;i<player1Numbers.length() && !hasPlayableCard ;i++)
            {
                if(player1Numbers.charAt(i)==topCardNumber && player1Numbers.charAt(i)!='C')
                {
                    hasPlayableCard=true;
                    topCardColor=player1Colors.charAt(i);
                    if(i==0)
                    {
                        player1Numbers=player1Numbers.substring(1);
                        player1Colors=player1Colors.substring(1);
                    }
                    else if(i==player1Colors.length()-1)
                    {
                        player1Numbers=player1Numbers.substring(0,player1Numbers.length()-1);
                        player1Colors=player1Colors.substring(0,player1Colors.length()-1);
                    }
                    else
                    {
                        player1Colors=player1Colors.substring(0,i)+player1Colors.substring(i+1);
                        player1Numbers=player1Numbers.substring(0,i)+player1Numbers.substring(i+1);
                    }
                    System.out.println("Deck after this round:");
                    System.out.print("numbers: "+numbers);
                    System.out.println();
                    System.out.println("colors:  "+colors);
                    System.out.println();
                    System.out.println("Top card: "+topCardColor+""+topCardNumber);
                }
                else if(player1Colors.charAt(i)==topCardColor && player1Colors.charAt(i)!='B')
                {
                    hasPlayableCard=true;
                    topCardNumber=player1Numbers.charAt(i);
                    if(i==0)
                    {
                        player1Numbers=player1Numbers.substring(1);
                        player1Colors=player1Colors.substring(1);
                    }
                    else if(i==player1Colors.length()-1)
                    {
                        player1Numbers=player1Numbers.substring(0,player1Numbers.length()-1);
                        player1Colors=player1Colors.substring(0,player1Colors.length()-1);
                    }
                    else
                    {
                        player1Colors=player1Colors.substring(0,i)+player1Colors.substring(i+1);
                        player1Numbers=player1Numbers.substring(0,i)+player1Numbers.substring(i+1);
                    }
                    System.out.println("Deck after this round:");
                    System.out.print("numbers: "+numbers);
                    System.out.println();
                    System.out.println("colors:  "+colors);
                    System.out.println();
                    System.out.println("Top card: "+topCardColor+""+topCardNumber);
                }
                else if(player1Numbers.charAt(i)=='C')
                {
                    boolean abc= false;
                    for(int d=0;d<player1Colors.length() && !abc ;d++)
                    {
                        if(player1Colors.charAt(d)!='B')
                        {
                            topCardColor=player1Colors.charAt(d);
                            topCardNumber='C';
                            abc=true;
                            hasPlayableCard=true;
                        }
                    }
                    if(hasPlayableCard)
                    {
                        if(i==0)
                        {
                            player1Numbers=player1Numbers.substring(1);
                            player1Colors=player1Colors.substring(1);
                        }
                        else if(i==player1Colors.length()-1)
                        {
                            player1Numbers=player1Numbers.substring(0,player1Numbers.length()-1);
                            player1Colors=player1Colors.substring(0,player1Colors.length()-1);
                        }
                        else
                        {
                            player1Colors=player1Colors.substring(0,i)+player1Colors.substring(i+1);
                            player1Numbers=player1Numbers.substring(0,i)+player1Numbers.substring(i+1);
                        }
                        System.out.println("Deck after this round:");
                        System.out.print("numbers: "+numbers);
                        System.out.println();
                        System.out.println("colors:  "+colors);
                        System.out.println();
                        System.out.println("Top card: "+topCardColor+""+topCardNumber);
                    }
                    
                }
               
            }
            if(!hasPlayableCard)
            {
                System.out.println("Current player has no card to play, drawing one from the deck.");
                char colorOfDrawnCard= colors.charAt(0);
                char numberOfDrawnCard= numbers.charAt(0);
                if(numberOfDrawnCard== topCardNumber && numberOfDrawnCard!='C')
                {
                    topCardColor=colorOfDrawnCard;
                }
                else if(colorOfDrawnCard==topCardColor && colorOfDrawnCard!='B')
                {
                    topCardNumber=numberOfDrawnCard;
                }
                else if(numberOfDrawnCard=='C')
                {
                    boolean def=false;
                    for(int d=0;d<player1Colors.length() && !def ;d++)
                    {
                        if(player1Colors.charAt(d)!='B')
                        {
                            topCardColor=player1Colors.charAt(d);
                            topCardNumber='C';
                            def=true;
                        }
                    }
                    if(!def)
                    {
                        player1Numbers+='C';
                        player1Colors+='B';
                    }
                }
                else 
                {
                    player1Colors+=colors.charAt(0);
                    player1Numbers+=numbers.charAt(0);
                }
                colors=colors.substring(1);
                numbers=numbers.substring(1);

                System.out.println("Deck after this round:");
                System.out.print("numbers: "+numbers);
                System.out.println();
                System.out.println("colors:  "+colors);
                System.out.println();
                System.out.println("Top card: "+topCardColor+""+topCardNumber);
            }

            //***************************************************** Player2 *********************************************************** */

            System.out.println("Player 2's turn");
            System.out.print("Player1 numbers: "+player1Numbers);
            System.out.println();
            System.err.print("Player1 colors : "+player1Colors);
            System.out.println();
            System.out.print("Player2 numbers: "+player2Numbers);
            System.out.println();
            System.err.print("Player2 colors : "+player2Colors);
            System.out.println();
            boolean hasPlayableCard2=false;
            for(int i=0;i<player2Numbers.length() && !hasPlayableCard2 ;i++)
            {
                if(player2Numbers.charAt(i)==topCardNumber && player2Numbers.charAt(i)!='C')
                {
                    hasPlayableCard2=true;
                    topCardColor=player2Colors.charAt(i);
                    if(i==0)
                    {
                        player2Numbers=player2Numbers.substring(1);
                        player2Colors=player2Colors.substring(1);
                    }
                    else if(i==player2Colors.length()-1)
                    {
                        player2Numbers=player2Numbers.substring(0,player2Numbers.length()-1);
                        player2Colors=player2Colors.substring(0,player2Colors.length()-1);
                    }
                    else
                    {
                        player2Colors=player2Colors.substring(0,i)+player2Colors.substring(i+1);
                        player2Numbers=player2Numbers.substring(0,i)+player2Numbers.substring(i+1);
                    }
                    System.out.println("Deck after this round:");
                    System.out.print("numbers: "+numbers);
                    System.out.println();
                    System.out.println("colors:  "+colors);
                    System.out.println();
                    System.out.println("Top card: "+topCardColor+""+topCardNumber);
                }
                else if(player2Colors.charAt(i)==topCardColor && player2Colors.charAt(i)!='B')
                {
                    hasPlayableCard2=true;
                    topCardNumber=player2Numbers.charAt(i);
                    if(i==0)
                    {
                        player2Numbers=player2Numbers.substring(1);
                        player2Colors=player2Colors.substring(1);
                    }
                    else if(i==player2Colors.length()-1)
                    {
                        player2Numbers=player2Numbers.substring(0,player2Numbers.length()-1);
                        player2Colors=player2Colors.substring(0,player2Colors.length()-1);
                    }
                    else
                    {
                        player2Colors=player2Colors.substring(0,i)+player2Colors.substring(i+1);
                        player2Numbers=player2Numbers.substring(0,i)+player2Numbers.substring(i+1);
                    }
                    System.out.println("Deck after this round:");
                    System.out.print("numbers: "+numbers);
                    System.out.println();
                    System.out.println("colors:  "+colors);
                    System.out.println();
                    System.out.println("Top card: "+topCardColor+""+topCardNumber);
                }
                else if(player2Numbers.charAt(i)=='C')
                {
                    boolean abc2= false;
                    for(int r=0;r<player2Colors.length() && !abc2;r++)
                    {
                        if(player2Colors.charAt(r)!='B')
                        {
                            topCardColor=player2Colors.charAt(r);
                            topCardNumber='C';
                            abc2=true;
                            hasPlayableCard2=true;
                        }
                    }
                    if(hasPlayableCard2)
                    {
                        if(i==0)
                        {
                            player2Numbers=player2Numbers.substring(1);
                            player2Colors=player2Colors.substring(1);
                        }
                        else if(i==player2Colors.length()-1)
                        {
                            player2Numbers=player2Numbers.substring(0,player2Numbers.length()-1);
                            player2Colors=player2Colors.substring(0,player2Colors.length()-1);
                        }
                        else
                        {
                            player2Colors=player2Colors.substring(0,i)+player2Colors.substring(i+1);
                            player2Numbers=player2Numbers.substring(0,i)+player2Numbers.substring(i+1);
                        }
                        System.out.println("Deck after this round:");
                        System.out.print("numbers: "+numbers);
                        System.out.println();
                        System.out.println("colors:  "+colors);
                        System.out.println();
                        System.out.println("Top card: "+topCardColor+""+topCardNumber);
                    }
                    
                }
               
            }
            if(!hasPlayableCard2)
            {
                System.out.println("Current player has no card to play, drawing one from the deck.");
                char colorOfDrawnCard= colors.charAt(0);
                char numberOfDrawnCard= numbers.charAt(0);
                if(numberOfDrawnCard== topCardNumber && numberOfDrawnCard!='C')
                {
                    topCardColor=colorOfDrawnCard;
                }
                else if(colorOfDrawnCard==topCardColor && colorOfDrawnCard!='B')
                {
                    topCardNumber=numberOfDrawnCard;
                }
                else if(numberOfDrawnCard=='C')
                {
                    boolean def2=false;
                    for(int d=0;d<player2Colors.length() && !def2;d++)
                    {
                        if(player2Colors.charAt(d)!='B')
                        {
                            topCardColor=player2Colors.charAt(d);
                            topCardNumber='C';
                            def2=true;
                        }
                    }
                    if(!def2)
                    {
                        player2Colors+='B';
                        player2Numbers+='C';
                    }
                }
                else 
                {
                    player2Colors+=colors.charAt(0);
                    player2Numbers+=numbers.charAt(0);
                }
                colors=colors.substring(1);
                numbers=numbers.substring(1);

                System.out.println("Deck after this round:");
                System.out.print("numbers: "+numbers);
                System.out.println();
                System.out.println("colors:  "+colors);
                System.out.println();
                System.out.println("Top card: "+topCardColor+""+topCardNumber);
            }

        }while(player1Numbers.length()!=0 && player2Numbers.length()!=0 && numbers.length()>0);

        if(player1Numbers.length()!=0 && player2Numbers.length()!=0)
        {
           int player1Count=0;
           int player2Count=0;
            
           for(int h=0;h<player1Numbers.length();h++)
           {
            if(player1Numbers.charAt(h)!='C')
            {
                player1Count+=Integer.parseInt(player1Numbers.substring(h, h+1));
            }
           }
           for(int p=0;p<player2Numbers.length();p++)
           {
            if(player2Numbers.charAt(p)!='C')
            {
                player2Count+=Integer.parseInt(player2Numbers.substring(p, p+1));
            }
           }
           if(player1Count<player2Count)
           {
            System.out.println("Player 1 wins!");
           }
           else if(player2Count<player1Count)
           {
            System.out.println("Player 2 wins!");
           }
           else
           {
            System.out.println("Game ended in a draw!");
           }
        }
        else if(player1Numbers.length()==0)
        {
            System.out.println("Player 1 wins!");
        }
        else if(player2Numbers.length()==0)
        {
            System.out.println("Player 2 wins!");
        }
    }
}
