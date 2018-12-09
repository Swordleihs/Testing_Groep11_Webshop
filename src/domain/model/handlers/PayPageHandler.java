package domain.model.handlers;

import domain.model.RequestHandler;
import domain.service.ShopService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PayPageHandler extends RequestHandler {

    public PayPageHandler(ShopService service){super.setService(service);}

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        request.setAttribute("amount", request.getParameter("amount"));
        return "pay.jsp";
    }
}
