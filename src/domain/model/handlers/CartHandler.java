package domain.model.handlers;

import domain.model.RequestHandler;
import domain.service.ShopService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CartHandler extends RequestHandler {

    public CartHandler(ShopService service){
        super.setService(service);
    }

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        request.setAttribute("service", this.service);
        return "cart.jsp";
    }
}
