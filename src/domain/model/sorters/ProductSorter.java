package domain.model.sorters;

import domain.model.Product;
import java.util.Comparator;
import java.util.List;

public class ProductSorter {

    public ProductSorter(){}

    public List<Product> sortByName(List<Product> p){
        List<Product> products = p;
        products.sort(Comparator.comparing(Product::getName));
        return products;
    }
}
