package lab01;
public class lab01_Q1 
{
	public static void main(String[] args) 
	{
		System.out.println("Movie Stream Pirated Movies:");
		System.out.println("Movie Stream offers 1323 movies for streaming");
		
		int noOfPiratedMovies = 1383;
		double moviePrice = 100.0;
		
		// calculate the revenue amassed
		double totalRevenueGained = noOfPiratedMovies * moviePrice;
		
		System.out.println("Movie Stream earns "+  moviePrice+  " TL per movie (total of " + totalRevenueGained + " TL).");
		
		double fine = 0.3;
		double penaltyPerMovie = 3000.0;
		
		// calculate his total debt and and fine
		double totaldebt = noOfPiratedMovies * penaltyPerMovie;
		double totalFine = totalRevenueGained * fine;
		
		System.out.println(
							"Recently Movie Rights Protection Agency (MRPA) started to crackdown.");
		System.out.println("MRPA sued them " + penaltyPerMovie + " for every movie they streamed and claimed " + fine * 100 + "% of their income.");
		System.out.println("Now they owe a total of " + totaldebt + " TLs and " + totalFine + " TL worth fine for their actions.");
		
		// now calculate debt if they choose to repay with suspension
		int hoursOfPrisonTimePerMovie = 900;
		System.out.println("They can either choose to pay this or the company license will be suspended " + hoursOfPrisonTimePerMovie + " hours for every movie pirated.");
		
		double totalHours = hoursOfPrisonTimePerMovie * noOfPiratedMovies;
		
		// convert hours to year:day:hour format*/
		int  hours = (int)totalHours % 24;
		int days = (int)totalHours / 24;
		int years = (int) days / 365;
		days = days % 365;
		
		// Now print what is calculated and be done with this question
		System.out.println("Which is equal to " + years + " years " + days + " days and " + hours + " hours of prison time.");
	}

}
