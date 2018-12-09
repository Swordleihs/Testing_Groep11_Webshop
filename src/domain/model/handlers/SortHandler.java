package domain.model.handlers;

import domain.model.RequestHandler;
import domain.service.ShopService;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SortHandler extends RequestHandler {

    public SortHandler(ShopService service){
        super.setService(service);
    }

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        Cookie cookie = new Cookie("sort", request.getParameter("sort"));
        response.addCookie(cookie);

        if (request.getParameter("sort").equals("Email")){ request.setAttribute("sortingType", "Email");}
        else if (request.getParameter("sort").equals("FirstName")){ request.setAttribute("sortingType", "FirstName");}
        else if(request.getParameter("sort").equals("Name")){request.setAttribute("sortingType", "Name");}

        RequestHandler handler = new PersonOverviewHandler(this.getService());
        return handler.handleRequest(request, response);
    }
}
