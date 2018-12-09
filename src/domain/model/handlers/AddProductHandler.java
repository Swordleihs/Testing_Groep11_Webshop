package domain.model.handlers;

import domain.model.Product;
import domain.model.RequestHandler;
import domain.service.ShopService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;

public class AddProductHandler extends RequestHandler {

    public AddProductHandler(ShopService service) {
        super.setService(service);
    }

    public String handleRequest(HttpServletRequest request, HttpServletResponse response){
        ArrayList<String> errors = new ArrayList<>();
        Product p = new Product();
        this.setProductId(p, request, errors);
        this.setName(p, request, errors);
        this.setDescription(p, request, errors);
        this.setPrice(p, request, errors);
        if (errors.size() < 1){
            try {
                this.service.addProduct(p);
                request.setAttribute("producten", this.service.getProducts());
                return new ProductOverviewHandler(this.getService()).handleRequest(request, response);
            }catch(Exception e){
                errors.add(e.getMessage());
                request.setAttribute("errors", errors);
                return "addProduct.jsp";
            }

        }else {
            request.setAttribute("errors", errors);
            return "addProduct.jsp";
        }
    }

    private void setProductId(Product p, HttpServletRequest r, ArrayList<String> errors){
        String prodidS = r.getParameter("prodid");
        if (!prodidS.trim().isEmpty()){
            int prodid = Integer.parseInt(prodidS);
            try{
                p.setProductId(prodid);
                r.setAttribute("previousProductId", prodid);
            }catch(Exception e){
                errors.add(e.getMessage());
            }
        }
        else {
            errors.add("No product ID given");
        }
    }

    private void setName(Product p, HttpServletRequest r, ArrayList<String> errors){
        String name = r.getParameter("name");
        try{
            p.setName(name);
            r.setAttribute("previousName", name);
        }catch(Exception e){
            errors.add(e.getMessage());
        }
    }

    private void setDescription(Product p, HttpServletRequest r, ArrayList<String> errors){
        String desc = r.getParameter("desc");
        try{
            p.setDescription(desc);
            r.setAttribute("previousDescription", desc);
        }catch(Exception e){
            errors.add(e.getMessage());
        }
    }

    private void setPrice(Product p, HttpServletRequest r, ArrayList<String> errors){
        String priceS = r.getParameter("price");
        if(!priceS.trim().isEmpty()){
            Double price = Double.parseDouble(priceS);
            try{
                p.setPrice(price);
                r.setAttribute("previousPrice", price);
            }catch(Exception e){
                errors.add(e.getMessage());
            }
        }
        else{
            errors.add("No price given");
        }
    }
}
