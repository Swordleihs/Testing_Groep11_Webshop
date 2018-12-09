package domain.model.handlers;

import domain.model.RequestHandler;
import domain.service.ShopService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AdminOptionsHandler extends RequestHandler {

    public AdminOptionsHandler(ShopService service) {
        super.setService(service);
    }

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        return "adminOptions.jsp";
    }
}
