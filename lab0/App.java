package lab0;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;



public class App {
    private boolean running = true; // Indicator for program's running state

    private int[] array = null; // TODO: methods should manipulate this array

    public static void main(String[] args) throws Exception {
        new App().appHandler(args);
    }

    private void printMenu(ArrayList<MenuOption> optionList) {
        for (int optionId = 0; optionId < optionList.size(); optionId++) {
            MenuOption option = optionList.get(optionId);
            System.out.printf("%d. %s\n", optionId + 1, option.getDescription());
        }
    }

    /**
    * Creats a new array with elements at a given size
    * Fills the array it creats with numbers between 0 and 100 included
    */
    private void createArrayEvent() {
        Scanner scanner = new Scanner (System.in);
        Random random = new Random();
        int size;

        System.out.println("Enter the size of the array: ");
        size = arraySizeValidate(scanner);
        while (size <= 0) {
            if (size == -1) {
                System.out.println ("Size must be a numeric value!");
            } else {
                System.out.println ("Size must be a positive integer!");
            }
            System.out.println("Enter the size of the array: ");
            size = arraySizeValidate(scanner);
        }

        // initialize the array;
        array = new int[size];

        // fill the array with numbers between 0 and 100 included
        for (int i = 0; i < size; i++) {
            array [i] = random.nextInt(101);
        }

        System.out.println("The array created is : ");
        printIntegerArray(array);
        
    }

    private static int arraySizeValidate (Scanner scanner) {
        int result = -1;
        if (scanner.hasNextInt()) {
            result = scanner.nextInt();
            if (result <= 0) {
                result = -2;
            }
        }
        return result;
    }

    private static void printIntegerArray (int [] arr) {
        System.out.print("( ");
        for (int i = 0; i < arr.length - 1; i++) {
            System.out.print(arr[i] + ", ");
        }
        System.out.println(arr[arr.length - 1] + " )");
    }

    // Print min and max of array(aka c.)
    private void minMaxOfArrayEvent() {
        
        System.out.println("The element with the maximum value is: "+FindMax(array));
        System.out.println("The element with the minium value is: "+FindMin(array));
    }

    // Find the average of array and print the distances of all elements to (aka d.)
    private void findAverageAndDistancesEvent() {


    }

    // Find sums of even and odd indexed elements (aka e.)
    private void sumsOddEvenEvent() {

    }

    // Handle the exit condition
    private void handleExitEvent() {
        System.err.printf("Exiting the app.\n");
        running = false;
    }

    private void handleOption(int optionId) {
        switch (optionId) {
            case 0:
                createArrayEvent();
                break;
            case 1:
                minMaxOfArrayEvent();
                break;
            case 2:
                findAverageAndDistancesEvent();
                break;
            case 3:
                sumsOddEvenEvent();
                break;
            case 4:
                handleExitEvent();
                break;
        }
    }

    // Handler for the main event loop
    private void appHandler(String[] args) {
        Scanner in = new Scanner(System.in);
        ArrayList<MenuOption> optionList = new ArrayList<>();
        optionList.add(new MenuOption("Create new array with inputted size"));
        optionList.add(new MenuOption("Find the array's minimum and maximum"));
        optionList.add(new MenuOption("Find the average of the array and the distances of elements to the average"));
        optionList
                .add(new MenuOption("Find the sums of odd and even indexed elements respectively"));
        optionList.add(new MenuOption("Exit the app"));

        while (running) {
            printMenu(optionList);
            System.out.printf("Please select an option: ");
            while (!in.hasNextInt()) {
                in.next();
            }
            int selectedId = in.nextInt() - 1;
            if (selectedId < 0 || selectedId >= optionList.size()) {
                System.err.printf("[ERROR] Please input a valid option\n");
                continue;
            }
            handleOption(selectedId);
        }
        in.close();
    }

    private static int FindMax(int []array)
    {
        int max=0;
        for(int i=0;i<array.length;i++)
        {
            if(max<array[i])
            {
                max=array[i];
            }
        }
        return max;
    }

    private static int FindMin(int []array)
    {
        int min=100;
        for(int i=0;i<array.length;i++)
        {
            if(min>array[i])
            {
                min=array[i];
            }
        }
        return min;
    }


}