package domain.model.handlers;

import domain.model.RequestHandler;
import domain.service.ShopService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class DeleteProductHandler extends RequestHandler {

    public DeleteProductHandler(ShopService service) {
        super.setService(service);
    }

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        this.service.deleteProduct(Integer.parseInt(request.getParameter("productId")));
        request.setAttribute("producten", this.service.getProducts());
        return "productoverview.jsp";
    }
}
