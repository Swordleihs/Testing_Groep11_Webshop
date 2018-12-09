package domain.model.handlers;

import domain.model.RequestHandler;
import domain.service.ShopService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteProductPageHandler extends RequestHandler {

    public DeleteProductPageHandler(ShopService service) {
        super.setService(service);
    }

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        int id = Integer.parseInt(request.getParameter("productId"));
        request.setAttribute("productId", id);
        return "deleteProduct.jsp";
    }
}
