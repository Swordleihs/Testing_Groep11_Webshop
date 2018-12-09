package domain.model.handlers;

import domain.model.Person;
import domain.model.RequestHandler;
import domain.service.ShopService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginHandler extends RequestHandler {

    public LoginHandler(ShopService service){
        super.setService(service);
    }

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        if(this.login(request)){

            HttpSession session = request.getSession();
            Person person = this.getService().getUserIfAuthenticated(request.getParameter("userId"), request.getParameter("passwd"));

            session.setAttribute("loggedIn", person);
            return new IndexHandler(this.getService()).handleRequest(request, response);
        }else{
            request.setAttribute("loginError", "Login failed.");
        }

        return "index.jsp";
    }

    private boolean login(HttpServletRequest request){

        return this.getService().getUserIfAuthenticated(request.getParameter("userId"), request.getParameter("passwd")) != null;
    }

}
