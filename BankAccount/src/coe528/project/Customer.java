
package coe528.project;
import java.io.*;

/**
 *
 * @author antho
 */
public class Customer {
    
    private File f;
    private FileWriter fw;
    private FileReader fr;
    private String username;
    private String password;
    private String role = "customer";
    private double balance;
    private Level level;
    
    public Customer(String u, String p, double b, Level l){
        if(u != null){
            this.username = u;
            this.password = p;
            this.balance = b;
            this.level = l;
            this.f = new File(username + ".txt");
            try{
                this.fw = new FileWriter(f, false);
                this.fr = new FileReader(f);  
                fw.write("Username: " + username + "\nPassword: " + password + 
                            "\nBalance: " + balance + "\nLevel: " + level );
                fw.flush();
                } catch (IOException e){
                    System.out.println("Something bad Happened");
                } 
            }
        }
    
    public void logIn(){
        System.out.println("Logged in as " + this.username);
    }
    
    public void logOut(){
        System.out.println("Logged out from " + this.username);
    }
    
    public void depositMoney(double num){
        double temp = this.balance + num;
        replaceValue(temp, this.level.toString());
        this.balance = this.balance + num;
    }
    
    public void withdrawMoney(double num){
        double temp = this.balance - num;
        replaceValue(temp, this.level.toString());
        this.balance = this.balance - num;
    }
    
    public double getBalance(){
        if(this.balance < 10000){
            replaceValue(this.balance, "Silver");
            this.level = new Silver();
        }
        if(this.balance >= 10000 && this.balance < 20000){
            replaceValue(this.balance, "Gold");
            this.level = new Gold();
        }
        if(this.balance >= 20000){
            replaceValue(this.balance, "Platinum");
            this.level = new Platinum();
        }
        return this.balance;
    }
    
    public void doOnlinePurchase(double amount){
        if(this.balance < 10000){
            replaceValue(this.balance, "Silver");
            this.level = new Silver();
        }
        if(this.balance >= 10000 && this.balance < 20000){
            replaceValue(this.balance, "Gold");
            this.level = new Gold();
        }
        if(this.balance >= 20000){
            replaceValue(this.balance, "Platinum");
            this.level = new Platinum();
        }
        this.level.doOnlinePurchase(this, amount);
    }
    
    public void setLevel(Level l){
        this.level = l;
    }
    
    public Level getLevel(){
        return this.level;
    }
    
    public String getUsername(){
        return this.username;
    }
    
    public void write(String s){
        try{
            fw.write(s);
            fw.flush();
        } catch (IOException e){
            System.out.println("Something wrong with writing");
        }
    }
    
    public void replaceValue(double val, String lev){
        try{
            char contents[] = new char[10000];
            fr.read(contents);
            String temp = new String(contents);
            temp = temp.replaceAll(Double.toString(balance), Double.toString(val));
            temp = temp.replaceAll(this.level.toString(), lev);
            this.f = new File(this.username + ".txt");
            this.fw = new FileWriter(f, false);
            this.fr = new FileReader(f);
            fw.write(temp);
            fw.flush();
        } catch (IOException e){
            System.out.println("Something wrong with the reading");
        }
    }
    
    public String getPassword(){
        return this.password;
    }
    
    public File getFile(){
        return f;
    }
    
}
