package Library;

import java.io.Serializable;
import java.util.Comparator;

/**
 * Created by Anthony Perez on 3/10/17.
 */
public class Fine implements Comparator<Fine>, Comparable<Fine>, Serializable {
    private static final Integer INITIALFEE = 10;
    private static final Integer PROGRESSIVEFEE = 2;
    private static Integer balance;
    private static Integer paid;

    public Fine(){
        balance = 0;
        paid = 0;
    }

    // Overriding the compareTo method
    public int compareTo(Fine fine) {
        return balance.compareTo(fine.getBalance());
    }

    // Overriding the compare method to sort the balance
    public int compare(Fine fine1, Fine fine2) {
        return fine1.getBalance() - fine2.getBalance();
    }

    public void incBalance(long weeks){
        if(balance == 0){
            balance += INITIALFEE;
        }
        if((balance >= 10)){
            weeks = (weeks > 10 ? 10 : weeks);
            for(int i=0; i<weeks; i++) {
                balance += PROGRESSIVEFEE;
            }
        }
    }

    public void pay(Integer amount){
        paid += amount;
        balance -= amount;
    }

    public Integer getPaid(){
        return paid;
    }
    public Integer getBalance(){
        return balance;
    }
}