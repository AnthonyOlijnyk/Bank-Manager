
package coe528.project;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.GridPane;
import javafx.geometry.Insets;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

public class MainApplication extends Application implements EventHandler<ActionEvent>{
    private Customer c = new Customer (null,null,0,null);
    private GridPane grid = new GridPane();
    private GridPane grid2 = new GridPane();
    private GridPane grid3 = new GridPane();
    
    private Label balance = new Label("100.0");
    
    private Stage window;
    private Scene scene1, scene2, scene3;
    private Button button1 = new Button("Login");
    private Button button2 = new Button("Create");
    private Button button3 = new Button("Logout");
    private Button button4 = new Button("Delete");
    private Button button5 = new Button("Logout");
    private Button button6 = new Button("Get Balance");
    private Button button7 = new Button("Go");
    private Button button8 = new Button("Go");
    private Button button9 = new Button("Go");
    private Manager m = new Manager();
    private TextField username = new TextField();
    private TextField password = new TextField();
    private TextField role = new TextField();
    private TextField usernameSet = new TextField();
    private TextField passwordSet = new TextField();
    private TextField deposit = new TextField();
    private TextField withdraw = new TextField();
    private TextField purchase = new TextField();
    
    @Override public void start(Stage primaryStage) throws Exception{
        window = primaryStage;
        primaryStage.setTitle("Account");
       
        
        //Scene1 (Login)
        grid.setPadding(new Insets(100, 100, 100, 100));
        grid.setVgap(8);
        grid.setHgap(10);
        
        Label pageLabel = new Label("Login Page");
        grid.setConstraints(pageLabel, 0, 0);
        
        Label nameLabel = new Label("Username:");
        grid.setConstraints(nameLabel, 0, 1);
        grid.setConstraints(username, 1, 1);
        
        Label passLabel = new Label("Password:");
        grid.setConstraints(passLabel, 0, 2);
        grid.setConstraints(password, 1, 2);
        
        Label roleLabel = new Label("Role:");
        grid.setConstraints(roleLabel, 0, 3);
        grid.setConstraints(role, 1, 3);
        
      

        GridPane.setConstraints(button1, 1, 4);
        button1.setOnAction(this);//no need for handle with lambda
        
        grid.getChildren().addAll(nameLabel, username, passLabel, password, button1, roleLabel, role, pageLabel);
        
        scene1 = new Scene(grid, 500, 500);
        
        
        //Scene2 (Manager working)
        grid2.setPadding(new Insets(100, 100, 100, 100));
        grid2.setVgap(8);
        grid2.setHgap(8);
        
        Label pageLabel2 = new Label("Logged in as Manager");
        grid2.setConstraints(pageLabel2, 0, 0);
        
        Label nameSetLabel = new Label("Customer Username:");
        grid2.setConstraints(nameSetLabel, 0, 1);
        grid2.setConstraints(usernameSet, 1, 1);
        
        Label passSetLabel = new Label("Customer Password:");
        grid2.setConstraints(passSetLabel, 0, 2);
        grid2.setConstraints(passwordSet, 1, 2);
        
        button2.setOnAction(this);
        grid2.setConstraints(button2, 1, 3);
        
        button3.setOnAction(this);
        grid2.setConstraints(button3, 1, 4);
        
        button4.setOnAction(this);
        grid2.setConstraints(button4, 1, 5);
       
        grid2.getChildren().addAll(nameSetLabel, usernameSet, passSetLabel, passwordSet, button2, button3, pageLabel2, button4);
        scene2 = new Scene(grid2, 500, 500);
        
        
        //Scene3 (Customer working)
        grid3.setPadding(new Insets(80, 80, 80, 80));
        grid3.setVgap(8);
        grid3.setHgap(8);
        
        Label pageLabel3 = new Label("Logged as Customer");
        grid3.setConstraints(pageLabel3, 0, 0);
        
        Label depositLabel = new Label("Deposit Amount:");
        grid3.setConstraints(depositLabel, 0, 1);
        grid3.setConstraints(deposit, 1, 1);
        
        Label withdrawLabel = new Label("Withdraw Amount:");
        grid3.setConstraints(withdrawLabel, 0, 2);
        grid3.setConstraints(withdraw, 1, 2);
        
        Label purchaseLabel = new Label("Purchase Amount:");
        grid3.setConstraints(purchaseLabel, 0, 3);
        grid3.setConstraints(purchase, 1, 3);
        
        button5.setOnAction(this);
        grid3.setConstraints(button5, 1, 4);
        
        button6.setOnAction(this);
        grid3.setConstraints(button6, 1, 5);
        
        button7.setOnAction(this);
        grid3.setConstraints(button7, 2, 1);
        
        button8.setOnAction(this);
        grid3.setConstraints(button8, 2, 2);
        
        button9.setOnAction(this);
        grid3.setConstraints(button9, 2, 3);
        
        grid3.setConstraints(balance, 0, 5);
        
        grid3.getChildren().addAll(button5, purchase, purchaseLabel, withdraw, withdrawLabel, deposit, depositLabel, pageLabel3, button6, button7, button8, button9, balance);
        scene3 = new Scene(grid3, 500, 500);
        
        window.setScene(scene1);
        window.show();
    }
    
    @Override
    public void handle(ActionEvent event){
        if(event.getSource() == button1 && username.getText().equals(m.getUsername())
                && password.getText().equals(m.getPassword())
                && role.getText().equals(m.getRole())){
            m.logIn();
            window.setScene(scene2);
        }
        
        else if (event.getSource() == button3){
            m.logOut();
            window.setScene(scene1);
        }

        else if(event.getSource() == button2){
            String temp = "" + usernameSet.getText() + ".txt";
            System.out.println(temp);
            File f = new File(temp);
            c = new Customer(usernameSet.getText(), passwordSet.getText(), 100, new Silver());
            m.addCustomer(c);
        }
        
        else if (event.getSource() == button1 && !(username.getText().equals("admin")) &&
                !(password.getText().equals("admin"))){
            File f = new File(username.getText() + ".txt");
            System.out.println(f.exists());
            if(f.exists()){
                try{
                    char[] contents = new char[1000];
                    FileReader fr = new FileReader(f);
                    fr.read(contents);
                    String temp = new String(contents);
                    Level level = new Silver();
                    if(temp.contains("Silver")){
                        level = new Silver();
                    }
                    if(temp.contains("Gold")){
                        level = new Gold();
                    }
                    if(temp.contains("Platinum")){
                        level = new Platinum();
                    }
                    String line3 = Files.readAllLines(Paths.get(username.getText() + ".txt")).get(2);
                    String line2 = Files.readAllLines(Paths.get(username.getText() + ".txt")).get(1);
                    String[] passwordBreak = line2.split(" ");
                    String passwordB = passwordBreak[1];
                    String[] balanceBreak = line3.split(" ");
                    double balanceB = Double.parseDouble(balanceBreak[1]);
                    System.out.println(balanceB);
                    if(password.getText().equals(passwordB)){
                        c = new Customer(username.getText(), password.getText(), balanceB, level);
                        m.addCustomer(c);
                        System.out.println(c.getUsername());
                        System.out.println(c.getPassword());
                        System.out.println(c.getLevel());
                        System.out.println(c.getBalance());
                        System.out.println(passwordB);
                        balance.setText(Double.toString(c.getBalance()));
                        window.setScene(scene3);  
                    }
                } catch (IOException e){
                    System.out.println("Uh oh lmao");
                }
            }
            
            
            if(m.search(username.getText(), password.getText()) != null){
                c = m.search(username.getText(), password.getText());
                c.logIn();
                window.setScene(scene3);
            }
        }
        
        else if (event.getSource() == button4 && !(usernameSet.getText().equals("admin")) &&
                !(passwordSet.getText().equals("admin"))){
            System.gc();
            if(m.search(usernameSet.getText(), passwordSet.getText()) != null){
                m.deleteCustomer(m.search(usernameSet.getText(), passwordSet.getText()));
                c = null;
                System.gc();
            }
                File f2 = new File(usernameSet.getText() + ".txt");
                System.out.println(f2.exists());
                System.gc();
                if(f2.delete()){
                    System.out.println("deleted");
                } else {
                    System.out.println("Why");
                }
            }
        
        else if (event.getSource() == button5){
            c.logOut();
            window.setScene(scene1);
        }
        
        else if(event.getSource() == button6){
            balance.setText(Double.toString(c.getBalance()));
        }
        
        else if (event.getSource() == button7){
            c.depositMoney(Double.parseDouble(deposit.getText()));
        }
        
        else if (event.getSource() == button8){
            c.withdrawMoney(Double.parseDouble(withdraw.getText()));
        }
        
        else if (event.getSource() == button9){
            c.doOnlinePurchase(Double.parseDouble(purchase.getText()));
        }
    }
    
    
    public static void main(String[] args){
        launch(args);
    }
}
