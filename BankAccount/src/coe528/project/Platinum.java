
package coe528.project;

/**
 *
 * @author antho
 */
public class Platinum extends Level{
    
    @Override
    public void doOnlinePurchase(Customer c, double amount){
        if(amount >= 50){
            c.withdrawMoney(amount);
        }
        if(c.getBalance() < 10000){
            c.replaceValue(c.getBalance(), "Silver");
            c.setLevel(new Silver());
        }
        else if (c.getBalance() >= 10000 && c.getBalance() < 20000){
            c.replaceValue(c.getBalance(), "Gold");
            c.setLevel(new Gold());
        }
    }
    
    @Override
    public String toString(){
        return "Platinum";
    }
}
