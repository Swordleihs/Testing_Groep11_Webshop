package domain.model.handlers;

import domain.model.Person;
import domain.model.RequestHandler;
import domain.service.ShopService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DoCheckHandler extends RequestHandler {

    public DoCheckHandler(ShopService service) {
        super.setService(service);
    }

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        this.doCheck(request);
        return "checkPas.jsp";
    }

    private void doCheck(HttpServletRequest request){
        String userId = request.getParameter("userId");
        String passwd = request.getParameter("passwd");

        Person p = this.service.getPerson(userId);
        String response;
        String nature;
        try{
            if (p.isCorrectPassword(passwd)){
                response = "Correct Password";
                nature = "p";

            }else{
                response = "Wrong Password";
                nature = "n";
            }
        }catch(Exception e){
            response = "Password empty";
            nature = "n";
        }

        request.setAttribute("response", response);
        request.setAttribute("nature", nature);
    }

}
