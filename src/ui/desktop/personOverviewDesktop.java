package ui.desktop;

import domain.db.PersonDbInMemory;
import domain.model.Person;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class personOverviewDesktop {
    public static void main(String[] args){
        Properties properties = new Properties();
        String url = "jdbc:postgresql://databanken.ucll.be:51819/2TX33?currentSchema=r0709610";
        properties.setProperty("user", "local_r0709610");
        properties.setProperty("password", "nVTYO-Ncdé5pH:UF");
        properties.setProperty("ssl", "true");
        properties.setProperty("sslfactory", "org.postgresql.ssl.NonValidatingFactory");
        properties.setProperty("sslmode","prefer");

        try{
            Connection connection = DriverManager.getConnection(url,properties);
            Statement statement = connection.createStatement();

            String input = JOptionPane.showInputDialog("Add new person? (y/n)");
            if (input.equals("y")){
                String useridInput = JOptionPane.showInputDialog("Give a userid:");
                String emailInput = JOptionPane.showInputDialog("Give an email adress:");
                String passwordInput = JOptionPane.showInputDialog("Give a password:");
                String firstInput = JOptionPane.showInputDialog("Give the firstname:");
                String lastInput = JOptionPane.showInputDialog("Give the lastname:");

                try {
                    Person person = new Person(useridInput, emailInput, passwordInput, firstInput, lastInput);
                    String sqlQuerry = "INSERT INTO person VALUES(\'" + useridInput + "\',\'" + emailInput + "\',\'" + passwordInput + "\',\'" + firstInput + "\',\'" + lastInput + "\');";
                    statement.execute(sqlQuerry);
                }
                catch (IllegalArgumentException e) {
                    System.out.println("Error when adding person: " + e.getMessage() + "\n");
                }


            }

            ResultSet result = statement.executeQuery( "SELECT * FROM person" );

            while(result.next()){
                String userid = result.getString("user_id");
                String email = result.getString("email");
                String firstname = result.getString("firstname");
                String lastname = result.getString("lastname");
                String passwd = result.getString("passwd");
                try {
                    Person person = new Person(userid, email, passwd, firstname, lastname);
                    System.out.println(person.toString());
                }
                catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                }
            }
            statement.close();
            connection.close();
        }catch(SQLException s){
            System.out.println(s.getMessage());
        }


    }
}
