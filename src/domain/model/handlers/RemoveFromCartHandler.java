package domain.model.handlers;

import domain.model.RequestHandler;
import domain.service.ShopService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;

public class RemoveFromCartHandler extends RequestHandler {

    public RemoveFromCartHandler(ShopService service){super.setService(service);}

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        HashMap<Integer, Integer> products = (HashMap<Integer, Integer>) session.getAttribute("products");

        int id = Integer.parseInt(request.getParameter("productId"));
        products.remove(id);
        if (products.size() > 0){
            session.setAttribute("products", products);
        }else{
            session.removeAttribute("products");
        }


        return new CartHandler(this.getService()).handleRequest(request, response);
    }
}
