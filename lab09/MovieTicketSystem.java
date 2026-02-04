package lab09;
import java.util.ArrayList;
import java.util.Scanner;

public class MovieTicketSystem {
    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);

        // Initial mock data
        // Add movies
        ArrayList<Movie> movies = new ArrayList<>();
        movies.add(new Movie("Toyz Goin' Wild", "Family", 1, 1.23));
        movies.add(new Movie("Car's Life", "Family", 2, 1.00));
        movies.add(new Movie("The Amazing Bulk", "Action", 1, 2.10));
        movies.add(new Movie("The Rise of Black Bat", "Action", 2, 1.90));

        // Add auditoriums
        ArrayList<Auditorium> auditoriums = new ArrayList<>();
        auditoriums.add(new Auditorium("Screen 1", 5, 4, 1.0));
        auditoriums.add(new Auditorium("Screen 2", 5, 4, 1.0));
        auditoriums.add(new Auditorium("IMAX", 3, 5, 1.5));

        // Add initial screenings
        ArrayList<Screening> screenings = new ArrayList<>();
        screenings.add(new Screening(movies.get(1), auditoriums.get(0), 1));
        screenings.add(new Screening(movies.get(2), auditoriums.get(0), 0));
        screenings.add(new Screening(movies.get(2), auditoriums.get(0), 4));
        screenings.add(new Screening(movies.get(0), auditoriums.get(1), 0));
        screenings.add(new Screening(movies.get(2), auditoriums.get(2), 4));
        screenings.add(new Screening(movies.get(3), auditoriums.get(2), 6));
        int countForScreening=6;

        // Buy some tickets
        screenings.get(0).buyTicketForRange(2, 2, 4);
        screenings.get(1).buyTicketForRange(2, 1, 2);
        screenings.get(2).buyTicketForRange(1, 1, 4);
        screenings.get(2).buyTicketForRange(3, 2, 4);
        screenings.get(3).buyTicketForRange(5, 1, 3);
        screenings.get(3).buyTicketForRange(3, 3, 4);
        screenings.get(5).buyTicketForRange(3, 1, 5);

        // You can implement the functionalities described in the lab document here.
        // Feel free to add additional private static helper methods as needed.

        int choice;
        do {
            displayMenu(movies);
            choice = scanner.nextInt();
            if (choice==1) 
             {
                System.out.println("Available Movies: ");
                for (int i = 0; i < movies.size(); i++) {
                    System.out.println( " " + movies.get(i).toString());
                }
                System.out.println();
            }
            else if (choice==2) 
            {
                for (int i = 0; i < screenings.size(); i++) {
                    System.out.println(  " " + screenings.get(i).toString());
                }
                System.out.println();
            }
            else if (choice==3) 
            {
                addScreening( movies, auditoriums, screenings);
            }
            else if (choice==4) 
            {
                buyTicket( movies, screenings);
            }
            else if (choice==5) {
                double count =0;
                for (int i = 0; i < screenings.size(); i++) {
                    count += screenings.get(i).getRevenue();
                }
                System.out.printf("Total revenue: $%.2f\n", count);
            } 
            else if (choice==6) {
                System.out.println("Bye!");
            }
            else {
                System.out.println("Invalid option. Please try again.");
            }
        
        }while(choice!=6);
    
    }

    
    private static void displayMenu (ArrayList<Movie> movies) {
        System.out.println("1. List Movies\n2. List Screenings\n3. Add Screening\n"+
            "4. Buy Ticket\n5. Calculate Revenue\n6. Exit\n");
        System.out.print("Enter your option: ");
    }

    private static void addScreening(ArrayList<Movie> movies,ArrayList<Auditorium> auditoriums2,ArrayList<Screening> screenings2) {
    Scanner sc= new Scanner(System.in);
    int movieSelected;
    do 
    {
        System.out.println("Select a movie:");
        for (int i = 0; i < movies.size(); i++) {
            System.out.println((i + 1) + ": " + movies.get(i));
        }
        movieSelected = sc.nextInt() - 1; sc.nextLine();
        if (movieSelected < 0 || movieSelected >= movies.size()) {
            System.out.println("Invalid selection. Please try again.");
        }
    } while (movieSelected < 0 || movieSelected >= movies.size());

    Movie m = movies.get(movieSelected);

   
    int selectedAuditorium;
    do {
        System.out.println("Select an auditorium:");
        for (int i = 0; i < auditoriums2.size(); i++) {
            System.out.println((i + 1) + ": " + auditoriums2.get(i));
        }
        selectedAuditorium = sc.nextInt() - 1; sc.nextLine();
        if (selectedAuditorium < 0 || selectedAuditorium >= auditoriums2.size()) {
            System.out.println("Invalid selection. Please try again.");
        }
    } while (selectedAuditorium < 0 || selectedAuditorium >= auditoriums2.size());

    Auditorium a = auditoriums2.get(selectedAuditorium);

 
    System.out.println();
    for (Screening s : screenings2) {
        if (s.getAuditorium().equals(a)) {
            System.out.println(s);
        }
    }

    System.out.print("Enter a start time: ");
    int startTime = sc.nextInt(); sc.nextLine();

    boolean notAvailable = false;
    for (Screening s : screenings2) {
        if (s.getAuditorium().equals(a) && startTime < s.getEndTime()
            && (startTime + m.getDuration()) > s.getStartTime()) {
            notAvailable = true;
        }
    }

    if (notAvailable) {
        System.out.println("The auditorium is not available at the given time.");
    } 
    else {
        Screening newScr = new Screening(m, a, startTime);
        screenings2.add(newScr);
        System.out.println("Screening added: " + newScr);
    }
}


private static void buyTicket(ArrayList<Movie> movies,ArrayList<Screening> screenings2) {
   Scanner sc= new Scanner(System.in);
    int selectedMovie;
    do {
        System.out.println("Select a movie:");
        for (int i = 0; i < movies.size(); i++) {
            System.out.println((i + 1) + ": " + movies.get(i));
        }
        selectedMovie = sc.nextInt() - 1; sc.nextLine();
        if (selectedMovie < 0 || selectedMovie >= movies.size()) {
            System.out.println("Invalid selection. Please try again.");
        }
    } while (selectedMovie < 0 || selectedMovie >= movies.size());

    Movie m = movies.get(selectedMovie);

    ArrayList<Screening> choices = new ArrayList<>();
    for (Screening s : screenings2) {
        if (s.getMovie().equals(m)) {
            choices.add(s);
        }
    }

   int selectedScreening;
    do {
        System.out.println("Select a screening for '" + m.getTitle() + "':");
        for (int i = 0; i < choices.size(); i++) {
            System.out.println((i + 1) + ": " + choices.get(i));
        }
        selectedScreening = sc.nextInt() - 1; sc.nextLine();
        if (selectedScreening < 0 || selectedScreening >= choices.size()) {
            System.out.println("Invalid selection. Please try again.");
        }
    } while (selectedScreening < 0 || selectedScreening >= choices.size());

    Screening s = choices.get(selectedScreening);

    System.out.println("Seating layout for " + s + ":");
    System.out.print(s.getlayout());

    
    System.out.print("Select row: ");
    int row = Integer.parseInt(sc.nextLine());

  
    System.out.print("Select column or range (e.g., 2 or 2-4): ");
    String col = sc.nextLine();

   
    Ticket ticket;
    if (col.contains("-")) {
       
        int LineIndex= col.indexOf('-');
        int c1 = Integer.parseInt(col.substring(0, LineIndex));
        int c2 = Integer.parseInt(col.substring(LineIndex+1));
        ticket = s.buyTicketForRange(row, c1, c2);
    } 
    else {
        int c = Integer.parseInt(col);
        ticket = s.buyTicketForRange (row, c, c);
    }

   
    if (ticket == null) {
        System.out.println("Ticket purchase failed: one or more selected seats are unavailable.");
    } 
    else {
        System.out.println("Ticket purchased:");
        System.out.println(ticket);

        System.out.printf("%nSeating layout for %s:%n", s);
        System.out.print(s.getlayout());
    }
    
}
}
