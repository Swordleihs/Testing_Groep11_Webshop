package domain.model.handlers;

import domain.model.RequestHandler;
import domain.service.ShopService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;

public class AddToCartHandler extends RequestHandler {

    public AddToCartHandler(ShopService service){
        super.setService(service);
    }

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        HashMap<Integer, Integer> products;

        products = (HashMap<Integer, Integer>) session.getAttribute("products");

        if(products == null){
            products = new HashMap<>();
        }
        String id = request.getParameter("productId");
        int amount = Integer.parseInt(request.getParameter("amount"));
        if (amount < 1 || amount > 999){
            return new ProductOverviewHandler(this.getService()).handleRequest(request, response);
        }
        try{
            if(products.containsKey(Integer.valueOf(id))){
                amount += products.get(Integer.valueOf(id));
            }
        }catch(Exception e){}

        products.put(Integer.valueOf(id), amount);

        session.setAttribute("products", products);
        return new ProductOverviewHandler(this.getService()).handleRequest(request, response);
    }
}
