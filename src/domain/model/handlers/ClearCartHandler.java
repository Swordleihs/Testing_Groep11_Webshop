package domain.model.handlers;

import domain.model.RequestHandler;
import domain.service.ShopService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ClearCartHandler extends RequestHandler {

    public ClearCartHandler(ShopService s){super.setService(s);}

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        session.removeAttribute("products");

        request.setAttribute("paymentSucces", true);
        return "cart.jsp";
    }
}
