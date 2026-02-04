package lab6;

public class Account {
    
    private double money;
    private char type;

    public Account(double money, char type) {
        this.money=money;
        this.type=type;
    }

    public double getMoney() {
        return money;
    }

    public char getType() {
        return type;
    }
    
    public double getMoneyInCommon() {
        return Converter.convert(money,type);
    }

    
}
