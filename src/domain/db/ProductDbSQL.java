package domain.db;

import domain.model.Person;
import domain.model.Product;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class ProductDbSQL implements ProductDb {

    private Properties properties;
    private String url;

    public ProductDbSQL(Properties properties){
        try{
            Class.forName("org.postgresql.Driver");
            this.properties = properties;
            this.url = properties.getProperty("url");
        }catch(Exception e){
            throw new DbException(e.getMessage(), e);
        }
    }

    @Override
    public Product get(int id) {
        try(Connection connection = DriverManager.getConnection(url,properties);
            Statement statement = connection.createStatement();){
            String query = "SELECT * FROM product WHERE product_id = \'" + id + "\'";
            ResultSet result = statement.executeQuery(query);
            Product product = new Product();
            while (result.next()){
                int productId = result.getInt("product_id");
                String name = result.getString("name");
                String description = result.getString("description");
                double price = result.getDouble("price");

                product.setProductId(productId);
                product.setName(name);
                product.setDescription(description);
                product.setPrice(price);
            }
            return product;
        }catch(Exception e){
            throw new DbException(e.getMessage(), e);
        }
    }

    @Override
    public List<Product> getAll() {
        try(Connection connection = DriverManager.getConnection(url,properties);
            Statement statement = connection.createStatement();){
            String query = "SELECT * FROM product";
            ResultSet result = statement.executeQuery(query);
            List<Product> products = new ArrayList<>();

            while(result.next()){
                int productId = result.getInt("product_id");
                String name = result.getString("name");
                String description = result.getString("description");
                double price = result.getDouble("price");

                Product product = new Product(productId, name, description, price);
                products.add(product);
            }
            return products;
        }catch(Exception e){
            throw new DbException(e.getMessage(), e);
        }    }

    @Override
    public void add(Product product) {
        try(Connection connection = DriverManager.getConnection(url,properties);
            Statement statement = connection.createStatement();){
            String query = "INSERT INTO Product VALUES(\'" + product.getProductId() + "\',\'" + product.getName() + "\',\'" + product.getDescription() + "\',\'" + product.getPrice() + "\')";
            statement.execute(query);
        }catch(Exception e){
            throw new DbException(e.getMessage(), e);
        }
    }

    @Override
    public void update(Product product) {
        try(Connection connection = DriverManager.getConnection(url,properties);
            Statement statement = connection.createStatement();){
            String query = "UPDATE product SET name = \'" + product.getName() + "\' WHERE product_id = " + product.getProductId();
            statement.execute(query);

            String query0 = "UPDATE product SET description = \'" + product.getDescription() + "\' WHERE product_id = " + product.getProductId();
            statement.execute(query0);

            String query1 = "UPDATE product SET price = " + product.getPrice() + " WHERE product_id = " + product.getProductId();
            statement.execute(query1);
        }catch(Exception e){
            throw new DbException(e.getMessage(), e);
        }
    }

    @Override
    public void delete(int id) {
        try(Connection connection = DriverManager.getConnection(url,properties);
            Statement statement = connection.createStatement();){
            String query = "DELETE FROM product WHERE product_id = " + id;
            statement.execute(query);
        }catch(Exception e){
            throw new DbException(e.getMessage(), e);
        }
    }

    @Override
    public int getNumbeOfProducts() {
        try(Connection connection = DriverManager.getConnection(url,properties);
            Statement statement = connection.createStatement();){
            String query = "SELECT count(product_id) FROM product";
            ResultSet result = statement.executeQuery(query);
            int count = result.getInt("count");
            return count;
        }catch(Exception e){
            throw new DbException(e.getMessage(), e);
        }
    }

}
