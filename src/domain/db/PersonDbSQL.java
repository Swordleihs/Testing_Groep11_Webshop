package domain.db;

import domain.model.Person;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class PersonDbSQL implements PersonDb {

    private Properties properties;
    private String url;

    public PersonDbSQL(Properties properties){
        try{
            Class.forName("org.postgresql.Driver");
            this.properties = properties;
            this.url = properties.getProperty("url");
        }catch(Exception e){
            throw new DbException(e.getMessage(), e);
        }
    }

    @Override
    public Person get(String personId) {
        try(Connection connection = DriverManager.getConnection(url,properties);
            Statement statement = connection.createStatement();){
            String query = "SELECT * FROM person WHERE user_id = \'" + personId + "\'";
            ResultSet result = statement.executeQuery(query);
            Person person = new Person();
            while (result.next()){
                String userId = result.getString("user_id");
                String email = result.getString("email");
                String passwd = result.getString("passwd");
                String firstName = result.getString("firstname");
                String lastName = result.getString("lastname");
                person.setUserid(userId);
                person.setEmail(email);
                person.setPassword(passwd);
                person.setFirstName(firstName);
                person.setLastName(lastName);
                person.setPasswordHashed(passwd);
            }
            return person;
        }catch(Exception e){
            throw new DbException(e.getMessage(), e);
        }
    }

    @Override
    public List<Person> getAll() {
        try(Connection connection = DriverManager.getConnection(url,properties);
            Statement statement = connection.createStatement();){
            String query = "SELECT * FROM person";
            ResultSet result = statement.executeQuery(query);
            List<Person> persons = new ArrayList<>();

            while(result.next()){
                String userId = result.getString("user_id");
                String email = result.getString("email");
                String passwd = result.getString("passwd");
                String firstName = result.getString("firstname");
                String lastName = result.getString("lastname");

                Person person = new Person(userId, email, passwd, firstName, lastName);
                persons.add(person);
            }
            return persons;
        }catch(Exception e){
            throw new DbException(e.getMessage(), e);
        }
    }

    @Override
    public void add(Person person) {
        try(Connection connection = DriverManager.getConnection(url,properties);
            Statement statement = connection.createStatement();){
            String sqlQuerry = "INSERT INTO person VALUES(\'" + person.getUserid() + "\',\'" + person.getEmail() + "\',\'" + person.getPassword() + "\',\'" + person.getFirstName() + "\',\'" + person.getLastName() + "\',\'" + person.getPasswordHashed() + "\');";
            statement.execute(sqlQuerry);
        }catch(Exception e){
            throw new DbException(e.getMessage(), e);
        }
    }

    @Override
    public void update(Person person) {
        try(Connection connection = DriverManager.getConnection(url,properties);
            Statement statement = connection.createStatement();){
            String query = "UPDATE person SET email = \'" + person.getEmail() + "\' WHERE user_id = \'" + person.getUserid() + "\'";
            statement.execute(query);

            String query0 = "UPDATE person SET passwd = \'" + person.getPassword() + "\' WHERE user_id = \'" + person.getUserid() + "\'";
            statement.execute(query0);

            String query01 = "UPDATE person SET passwdHashed = \'" + person.getPasswordHashed() + "\' WHERE user_id = \'" + person.getUserid() + "\'";
            statement.execute(query01);

            String query1 = "UPDATE person SET firstname = \'" + person.getFirstName() + "\' WHERE user_id = \'" + person.getUserid() + "\'";
            statement.execute(query1);

            String query2 = "UPDATE person SET lastname = \'" + person.getLastName() + "\' WHERE user_id = \'" + person.getUserid() + "\'";
            statement.execute(query2);

        }catch(Exception e){
            throw new DbException(e.getMessage(), e);
        }
    }

    @Override
    public void delete(String personId) {
        try(Connection connection = DriverManager.getConnection(url,properties);
            Statement statement = connection.createStatement();){
            String query = "DELETE FROM person WHERE user_id = \'" + personId + "\'";
            statement.execute(query);
        }catch(Exception e){
            throw new DbException(e.getMessage(), e);
        }
    }

    @Override
    public int getNumberOfPersons() {
        try(Connection connection = DriverManager.getConnection(url,properties);
            Statement statement = connection.createStatement();){
            String query = "SELECT count(user_id) FROM person";
            ResultSet result = statement.executeQuery(query);
            int count = result.getInt("count");
            return count;
        }catch(Exception e){
            throw new DbException(e.getMessage(), e);
        }
    }

}