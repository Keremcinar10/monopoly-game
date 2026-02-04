package lab03;

import java.util.Scanner;

public class Lab03_Q3 {
    public static void main(String[] args) {
        Scanner sc= new Scanner (System.in);

        String adminInfo="Violet#Cupcake";
        String userInfo="Jinx#Jinx,Jayce#Jayce,Caitlyn#Caitlyn";
        String movies="Joker 2,Gladiator 2,Fear,Inception,Cape Fear,";
        String reviews="Joker 2 (Jinx): Waste of time\nJoker 2 (Jayce): It is trash. Don't watch\nJoker 2 (Caitlyn): I like Lady Gaga\nGladiator 2 ( Jayce ): I really luved it *_*\n";
        
        System.out.print("Enter your username: ");
        String inputUserName= sc.next();
        System.out.print("Enter password: ");
        String inputPassword=sc.next();

        int userFirstCommaIndex=userInfo.indexOf(",");
        int userSecondCommaIndex= userInfo.indexOf(",", userFirstCommaIndex+1);

        int moviesFirstCommaIndex= movies.indexOf(",");
        int moviesSecondCommaIndex= movies.indexOf(",", moviesFirstCommaIndex+1);
        int moviesThirdCommaIndex= movies.indexOf(",", moviesSecondCommaIndex+1);
        int moviesFourthCommaIndex= movies.indexOf(",", moviesThirdCommaIndex+1);

        String firstMovie= movies.substring(0, moviesFirstCommaIndex).replace(" ", "");
        String secondMovie= movies.substring(moviesFirstCommaIndex+1, moviesSecondCommaIndex).replace(" ", "");
        String thirdMovie= movies.substring(moviesSecondCommaIndex+1, moviesThirdCommaIndex).replace(" ", "");
        String fourthMovie= movies.substring(moviesThirdCommaIndex+1, moviesFourthCommaIndex).replace(" ", "");
        String fifthmovie= movies.substring(moviesFourthCommaIndex+1).replace(" ", "");

        String user1UserName = userInfo.substring(0,userInfo.indexOf("#")).replace(" ", "");
        String user1Password= user1UserName;
        String user2UserName=userInfo.substring(userInfo.indexOf(",")+1,userInfo.indexOf("#", userInfo.indexOf(",")+1)).replace(" ", "");
        String user2Password= user2UserName;
        String user3UserName= userInfo.substring(userSecondCommaIndex+1, userInfo.indexOf("#", userSecondCommaIndex+1)).replace(" ", "");
        String user3Password= user3UserName;

        String adminUserName=adminInfo.substring(0,adminInfo.indexOf("#")).replace(" ", "");
        String adminPassword= adminInfo.substring(adminInfo.indexOf("#")+1).replace(" ", "");

       
        boolean isAdmin=inputUserName.equals(adminUserName) && inputPassword.equals(adminPassword);
        boolean isUser=(inputUserName.equals(user1UserName)&& inputPassword.equals(user1Password)) || (inputUserName.equals(user2UserName)&& inputPassword.equals(user2Password)) || (inputUserName.equals(user3UserName)&& inputPassword.equals(user3Password));


       if(!isAdmin && !isUser)
       {
        System.out.println("You shall not pass!");
       }
       else if(isAdmin)
       {
        System.out.println("Welcome! Your role is Admin.");
        System.out.println("1- Add Movie\n2- Delete Movie\n3- Logout");
        System.out.print("Select an operation: ");
        int selected= sc.nextInt(); sc.nextLine();

            if(selected==1)
            {
                System.out.println(movies);
                System.out.print("Enter movie name to add: ");
                String movieToAdd= sc.nextLine().replace(" ","");
                if(!firstMovie.equals(movieToAdd) && !secondMovie.equals(movieToAdd)&& !thirdMovie.equals(movieToAdd) && !fourthMovie.equals(movieToAdd) && !fifthmovie.equals(movieToAdd))
                {
                    System.out.println("Movie added!");
                    movies+=", "+movieToAdd;
                    System.out.println(movies);
                }
                else
                {
                    System.out.println("This movie is already on your list");
                    
                }
                
            }
            else if(selected==2)
            {
                System.out.print("Enter movie name to delete: ");
                String oldMovieTodelete=sc.nextLine();
                String movieToDelete= oldMovieTodelete.replace(" ", "");
                
                if(firstMovie.equals(movieToDelete) || secondMovie.equals(movieToDelete)|| thirdMovie.equals(movieToDelete) || fourthMovie.equals(movieToDelete) || fifthmovie.equals(movieToDelete))
                {
                    System.out.println("Movie deleted!");
                    movies= movies.replace(oldMovieTodelete+",", "");
                    System.out.println(movies);
                }
                else 
                {
                    System.out.println("Movie is not in the list!");
                }
            }
            else if (selected==3)
            {
                System.out.println("Logged out successfully");
            }
       }

       else if(isUser)
       {
        System.out.println("Welcome! Your role is User.");
        System.out.println("1 - List Movies and Reviews\n2 - Submit a Review\n3 - Logout");
        System.out.print("Select an operation: ");
        int selectedOp= sc.nextInt(); sc.nextLine();

            if(selectedOp==1)
            {
                System.out.println(reviews);
            }
            else if(selectedOp==2)
            {
                System.out.print("Enter movie name to review: ");
                String oldMovieToReview= sc.nextLine();
                String movieToReview= oldMovieToReview.replace(" ", "");
                if(firstMovie.equals(movieToReview) || secondMovie.equals(movieToReview)|| thirdMovie.equals(movieToReview) || fourthMovie.equals(movieToReview) || fifthmovie.equals(movieToReview))
                {
                    System.out.print("Enter review: ");
                    String review= sc.next();
                    reviews+=oldMovieToReview+ " ("+inputPassword+"): "+review;
                    System.out.println(reviews);
                }
                else 
                {
                    System.out.println("Movie not found");
                }
            }
            else if( selectedOp==3)
            {
                System.out.println("Logged out successfully");
            }
       }
    }
}
