
package coe528.project;

/**
 *
 * @author antho
 */
public class Gold extends Level{
    
    @Override
    public void doOnlinePurchase(Customer c, double amount){
        if(amount >= 50){
            c.withdrawMoney(amount + 10);
        }
        if(c.getBalance() < 10000){
            c.replaceValue(c.getBalance(), "Silver");
            c.setLevel(new Silver());
        }
        else if (c.getBalance() >= 20000){
            c.replaceValue(c.getBalance(), "Platinum");
            c.setLevel(new Platinum());
        }
    }
    
    @Override
    public String toString(){
        return "Gold";
    }
}
