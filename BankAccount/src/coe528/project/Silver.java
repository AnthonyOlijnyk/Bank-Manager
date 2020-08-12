
package coe528.project;

/**
 *
 * @author antho
 */
public class Silver extends Level{
    
    @Override
    public void doOnlinePurchase(Customer c, double amount){
        if(amount >= 50){
            c.withdrawMoney(amount + 20);
        }
        if(c.getBalance() >= 10000 && c.getBalance() < 20000){
            c.replaceValue(c.getBalance(), "Gold");
            c.setLevel(new Gold());
        }
        else if(c.getBalance() >= 20000){
            c.replaceValue(c.getBalance(), "Platinum");
            c.setLevel(new Platinum());
        }
    }
    
    @Override
    public String toString(){
        return "Silver";
    }
}
