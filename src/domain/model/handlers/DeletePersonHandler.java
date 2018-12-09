package domain.model.handlers;

import domain.model.RequestHandler;
import domain.service.ShopService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class DeletePersonHandler extends RequestHandler {

    public DeletePersonHandler(ShopService service) {
        super.setService(service);
    }

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        this.service.deletePerson(request.getParameter("userid"));
        HttpSession session = request.getSession();
        session.invalidate();
        return "index.jsp";
    }
}
