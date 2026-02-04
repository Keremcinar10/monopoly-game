package lab6;

import java.util.ArrayList;

public class User {
    
    private String name;
    private String surname;
    private String id;
    private ArrayList<Account> accounts;

    public User(String name, String surname, String id) {
        this.name=name;
        this.surname=surname;
        this.id=id;
        this.accounts=new ArrayList<Account>();
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getId() {
        return id;
    }

    public void addAccount(Account account) {
        accounts.add(account);
    }

    public double getTotalAmountInCommonCurrency() {

        double total1=0;
        for(int i=0;i<accounts.size();i++)
        {
            total1+=accounts.get(i).getMoneyInCommon();
        }
        return total1;
    }

    public void displayUserInfo() {
        System.out.println(this.id+" "+this.name+" "+this.surname+" Total Amount: "+
            this.getTotalAmountInCommonCurrency());

        System.out.println("Accounts: ");

        for(int i=0;i<accounts.size();i++){

            System.out.println(i+1+". Type: "+accounts.get(i).getType()+" Amount: "+
                 accounts.get(i).getMoney()+" Common: "+ accounts.get(i).getMoneyInCommon());
        }
    }
}
