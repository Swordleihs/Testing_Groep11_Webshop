package domain.model.handlers;

import domain.model.Product;
import domain.model.RequestHandler;
import domain.service.ShopService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class UpdateProductPageHandler extends RequestHandler {

    public UpdateProductPageHandler(ShopService service) {
        super.setService(service);
    }

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        Product p = this.service.getProduct(Integer.parseInt(request.getParameter("id")));
        request.setAttribute("previousProductId", p.getProductId());
        request.setAttribute("previousName", p.getName());
        request.setAttribute("previousDescription", p.getDescription());
        request.setAttribute("previousPrice", p.getPrice());
        return "updateProduct.jsp";
    }
}
