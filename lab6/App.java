package lab6;

import java.util.Random;
import java.util.Scanner;

class IdNotFoundException extends Exception{
    
    public IdNotFoundException(String message) {
        super(message);
    }
}
public class App {

    private static final String[] names = {
        "Esra","Osman","Adem","Ismail","Safiye",
        "Mert","Cevahir","Ahmet","Hasan","Engin",
        "Abdullah","Ertan","Ercument","Aslan","Sitki"
    };
    private static final String[] surnames = {
        "Demir","Yilmaz","Kaya","Şahin","Çelik",
        "Yildiz","Yildirim","Özdemir","Öztürk","Aydin",
        "Arslan","Doğan","Koç","Kaplan","Aksoy"
    };
    private static final char[] types = {'A','B','C','D'};
    
    private static final int MAX_NUMBER_OF_USERS=100000;
    private static User[] initialUsers=new User[MAX_NUMBER_OF_USERS];
    private static User[] users=new User[MAX_NUMBER_OF_USERS];

    private static int userCount=0;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Converter.generateInitialRates();

        System.out.println("Welcome to the bank!");
        Converter.displayRates();
        int chosenOption;
        do
        {
            displayMenu();
            chosenOption=sc.nextInt(); sc.nextLine();

            switch(chosenOption)
            {
                case 1://********************************************** */

                generateUsers();

                break;

                case 2://********************************************** */

                list();
                
                break;

                case 3://********************************************** */

                System.out.print("Enter User ID: ");
                String id=sc.next();
                try {
                    displayUser(id);
                } catch (IdNotFoundException a) {
                    System.out.println(a.getMessage());
                }

                break;

                case 4://********************************************** */

                setRates();

                break;

                case 5://********************************************** */

                System.out.println("Choose sort type:\n1. By ID\n2. By Total Amount");
                int type= sc.nextInt();
                System.out.print("Enter sort limit: ");
                int lim = sc.nextInt();
                long begin= System.currentTimeMillis();

                if (type==1) {
                    idSort(users, 0, userCount, lim);
                } 
                else if(type==2) {
                    moneySort(users, 0, userCount, lim);
                }
                System.out.println("Sort duration: "+(System.currentTimeMillis()-begin));

                break;

                case 6://********************************************** */
                reset();
                
                break;

                case 0://********************************************** */
                System.out.println("Bye!");
                break;
            }

        }while (chosenOption!=0);
    }
    public static void setRates() {
        Scanner sc = new Scanner (System.in);
        System.out.print("Set A: ");
        Converter.setRates('A', sc.nextDouble());
        System.out.print("Set B: ");
        Converter.setRates('B', sc.nextDouble());
        System.out.print("Set C: ");
        Converter.setRates('C', sc.nextDouble());
        System.out.print("Set D: ");
        Converter.setRates('D', sc.nextDouble());
    }

    public static void displayMenu() {
        System.out.println("What do you want to do?\n");
        System.out.println("1. Generate random users\n2. List users\n3. Show user with ID\n"+
            "4. Set conversion rates\n5. Sort users\n6. Reset to the original unsorted array\n0. Exit");
        System.out.print("Option: ");
    }

    public static void generateUsers( ) {
        Scanner sc = new Scanner(System.in);
        Random rand = new Random();
        System.out.print("Generate how many?: ");
        int number = sc.nextInt();
        System.out.println("Generating "+ number +" random users");

        for(int i=0; userCount<MAX_NUMBER_OF_USERS && i<number  ; i++) {
            String formedId= "";
            for(int j=0;j<9;j++) {
                formedId+=rand.nextInt(10);
            }
            User formedUser =new User( names[rand.nextInt(15)], surnames[rand.nextInt(15)],formedId);
            int accountCount= 2+rand.nextInt(9);

            for(int a=0; a<accountCount; a++) {
                Account acc= new Account(rand.nextDouble()*1000, types[rand.nextInt(4)]);
                formedUser.addAccount(acc);
            }

            initialUsers[userCount]= formedUser;
            users[userCount]= formedUser;
            userCount++;
        } 
        
    }
    public static void list() {
        for (int i=0;i<userCount;i++) {
            User user = users[i];
            System.out.println(""+user.getId()+" "+ user.getName()+" "+user.getSurname()+
            " Total Amount: "+user.getTotalAmountInCommonCurrency());
        }

    }
    public static void displayUser(String id) throws IdNotFoundException{

        for (int i =0; i<userCount;i++) {
            if (id.equals(users[i].getId())) 
            {
                users[i].displayUserInfo();
                return;
            }
        }
        IdNotFoundException idNotFoundException= new IdNotFoundException("Cannot find the user!");
        throw idNotFoundException;
    }   
    public static void reset() {

        for (int i =0; i<userCount; i++) {
            users[i] =initialUsers[i];
        }
    }


// ************************************* sorting part ***********************************************

    public static void swap (User[] array, int i, int j) {
        User tmp=array[i];

        array[i]=array[j];
        array[j]=tmp;
    }
    public static void moneySort(User[] array, int beginning, int target, int lim) {
        
        if(target-beginning< lim) {
            moneyInsertionSort(array, beginning, target);
        }
        else {
            int pivotIndex=moneyPartition(array, beginning, target);
            moneySort(array,beginning,pivotIndex,lim);   // smaller part  
            moneySort(array,pivotIndex + 1,target,lim);  // larger part
        }
    }
    public static void moneyInsertionSort(User[] array, int beginning, int target) {

        for (int i=beginning+1; i<target; i++) {
            User a=array[i];
            int j=i-1;
            while (j >= beginning && array[j].getTotalAmountInCommonCurrency() > a.getTotalAmountInCommonCurrency()) {
                array[j+1] =array[j];
                j--;
            }
            array[j+1] =a;
        }
    }

    public static int moneyPartition(User[] array, int beginning, int target) {

        double pivot =array[beginning].getTotalAmountInCommonCurrency();
        int left =beginning;
        int right =target -1;

        while (left < right) {
           while (left < target && array[left].getTotalAmountInCommonCurrency() <= pivot) {
                left++;
            }
           while (array[right].getTotalAmountInCommonCurrency() > pivot) {
                right--;
            }
           if (left < right) {
                swap(array, left, right);
            }
        }
        swap(array, beginning, right);
        return right;
    }
    
    public static void idSort(User[] array, int beginning, int target, int lim) {

        if(target-beginning< lim) {
            idInsertionSort(array, beginning, target);
        }
        else {
            int pivotIndex= idPartititon(array, beginning, target);
            idSort(array, beginning, pivotIndex, lim);
            idSort(array, pivotIndex + 1, target, lim);
        }
    }

    public static void idInsertionSort(User[] array, int beginning, int target) {

        for(int i=beginning+1;i<target; i++) {
            User a= array[i];
            int k= i-1;
            while(k>=beginning && array[k].getId().compareTo(a.getId())>0) {
                array[k+1]=array[k];
                k--;
            }
            array[k+1]=a;
        }

    }

    public static int idPartititon(User[] array, int beginning, int target) {

        String pivot =array[beginning].getId();
        int left=beginning;         
        int right=target-1;    

        while (left<right) {
           while (left<target && array[left].getId().compareTo(pivot)<=0) {
                left++;
            }
           while (array[right].getId().compareTo(pivot)>0) {
                right--;
            }
           if (left<right) {
                swap(array, left, right);
            }
        }
        swap(array, beginning, right);
        return right;
    }
    
}
