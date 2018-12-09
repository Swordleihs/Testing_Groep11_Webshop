package domain.service;

import domain.db.*;
import domain.model.Person;
import domain.model.Product;
import net.bytebuddy.pool.TypePool;

import java.util.List;
import java.util.Properties;

public class ShopService {

    private PersonDb personDb;
    private ProductDb productDb;

    public ShopService(Properties properties) {
        this.personDb = new PersonDbSQL(properties);
        this.productDb = new ProductDbSQL(properties);

    }

    public Person getPerson(String personId) {
        boolean personExists = false;
        for (Person p : this.getPersons()){
            if (p.getUserid().equals(personId)){
                personExists = true;
            }
        }
        if (personExists){
            return getPersonDb().get(personId);
        }else{
            return null;
        }
    }

    public List<Person> getPersons() {
        return getPersonDb().getAll();
    }

    public void addPerson(Person person) {
        String id = person.getUserid();
        for (Person p : this.getPersons()){
            if (p.getUserid().equals(id)){
                throw new IllegalArgumentException("This id is already in use.");
            }
        }
        getPersonDb().add(person);
    }

    public void updatePersons(Person person) {
        getPersonDb().update(person);
    }

    public void deletePerson(String id) {
        getPersonDb().delete(id);
    }

    private PersonDb getPersonDb() {
        return this.personDb;
    }


    public Product getProduct(int productId) {
        return getProductDb().get(productId);
    }

    public List<Product> getProducts() {
        return getProductDb().getAll();
    }

    public void addProduct(Product product) {
        getProductDb().add(product);
    }

    public void updateProducts(Product product) {
        getProductDb().update(product);
    }

    public void deleteProduct(int id) {
        getProductDb().delete(id);
    }

    private ProductDb getProductDb() {
        return this.productDb;
    }

    public Person getUserIfAuthenticated(String userId, String passwd){
        Person person = this.getPerson(userId);
        if (person != null){
            if (person.isCorrectPassword(passwd)){
                return person;
            }else{
                return null;
            }
        }else{
            return null;
        }
    }

}
