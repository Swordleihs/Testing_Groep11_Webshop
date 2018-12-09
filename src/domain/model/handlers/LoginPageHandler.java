package domain.model.handlers;

import domain.model.RequestHandler;
import domain.service.ShopService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginPageHandler extends RequestHandler {

    public LoginPageHandler(ShopService service){ super.setService(service);}

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        return "login.jsp";
    }
}
