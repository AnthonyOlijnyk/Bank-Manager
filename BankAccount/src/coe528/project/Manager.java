
package coe528.project;
import java.util.ArrayList;
/**
 *
 * @author antho
 */
public class Manager {
    /**
    //Overview: the Manager class is a mutable class that is in charge of keeping track of the
    //customers that are available in their arraylist. This class
    //uses the main application as an admin and can add or remove any customers they want.
    
    //The abstraction function is:
    //for a java Manager object C
    //AF(C) = a manager account m who is in charge of keeping track of the customers that use
    // the application and where:
    //m.username = the username of the manager (admin) or C.username
    //m.password = the password of the manager (admin) or C.password
    //m.title = the job title of the person using the account (manager) or C.role
    //m.customerList = the list of customers that the manager takes care of or C.customers
    
    //The rep invariant is:
    //for a java Manager object C
    //RF(C) = true if C.username.equals("admin") &&
    //        C.password.equals("admin") &&
    //        C.role.equals("manager") &&
    //        C.customers != null &&
    //        C.customers cannot be empty &&
    //        C.customers(i) != null &&
    //        C.customers must have unique usernames
    * */
    
    private String username = "admin";
    private String password = "admin";
    private String role = "manager";
    private ArrayList<Customer> customers;
    
    
    /**
    //Requires: none
    //Modifies: customers ArrayList
    //Effects: creates a new customers array when a Manager object is created
    * */
    public Manager(){
        this.customers = new ArrayList<Customer>();
    }
    
    /**
    //Requires: none
    //Modifies: none
    //Effects: Prints who logged in to the terminal
    * */
    public void logIn(){
        System.out.println("Logged in as " + this.username);
    }
    
    /**
    //Requires: none
    //Modifies: none
    //Effects: prints who logged out to the terminal
    * */
    public void logOut(){
        System.out.println("Logged out from " + this.username);
    }
    
    /**
    //Requires: none
    //Modifies: none
    //Effects: returns the username value set for the class
    * */
    public String getUsername(){
        return this.username;
    }
    
    /**
    //Requires: none
    //Modifies: none
    //Effects: returns the password value set for the class
    * */
    public String getPassword(){
        return this.password;
    }
    
    /**
    //Requires: none
    //Modifies: none
    //Effects: returns the role value set for the class
    * */
    public String getRole(){
        return this.role;
    }
    
    /**
    //Requires: none
    //Modifies: customers ArrayList
    //Effects: adds a specified Customer object to the customers ArrayList
    * */
    public void addCustomer(Customer c){
        customers.add(c);
    }
    
    /**
    //Requires: none
    //Modifies: customers ArrayList
    //Effects: removes a Customer object from the customers ArrayList
    * */
    public void deleteCustomer(Customer c){
        customers.remove(c);
        c = null;
    }
    
    /**
    //Requires: none
    //Modifies: none
    //Effects: implements the abstraction function and prints out
    //         information pertaining to the manager class.
    //         in specific, it prints out the username,
    //         password, role, and the customers under management
    * */
    @Override
    public String toString(){
        String temp = "A manager account where:\n"
                + "the username is: " + username
                + "\n" + "the password is: " + password
                + "\n" + "the role is: " + role
                + "\n" + "customers under supervision are: ";
        for(int i = 0; i < customers.size(); i++){
            temp = temp + customers.get(i).getUsername() + "\n";
        }
        return temp;
    }
    
    /**
    //Requires: none
    //Modifies: none
    //Effects: returns true only if the username of the class is admin,
    //         the password is admin, the role is manager and the customers
    //         ArrayList is not empty and does not contain duplicates
    * */
    public boolean repOk(){
        for (int i = 0; i < customers.size(); i++){
            for (int j = i + 1; j < customers.size(); i++){
                if (customers.get(i).getUsername().equals(customers.get(j).getUsername())){
                    return false;
                }
            }
        }
        if(username.equals("admin") && password.equals("admin")
                && role.equals("manager") && customers.isEmpty() != true)
            return true;
        return false;
    }
    
    /**
    //Requires: none
    //Modifies: none
    //Effects: searches the customers Arraylist to see if there is a
    //         customer present in there that has the same username
    //         and password that are specified in the parameters
    * */
    public Customer search(String u, String p){
        for (Customer c: customers){
            if(c.getUsername().equals(u) && c.getPassword().equals(p)){
                return c;
            }
        }
        return null;
    }
}
