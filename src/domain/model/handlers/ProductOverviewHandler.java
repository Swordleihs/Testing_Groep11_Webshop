package domain.model.handlers;

import domain.model.Product;
import domain.model.RequestHandler;
import domain.model.sorters.ProductSorter;
import domain.service.ShopService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;


public class ProductOverviewHandler extends RequestHandler {

    public ProductOverviewHandler(ShopService service) {
        super.setService(service);
    }

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        List<Product> products = this.service.getProducts();
        ProductSorter sorter = new ProductSorter();
        request.setAttribute("producten", sorter.sortByName(products));
        return "productoverview.jsp";
    }
}
