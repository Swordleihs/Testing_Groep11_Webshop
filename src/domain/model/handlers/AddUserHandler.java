package domain.model.handlers;

import domain.model.Person;
import domain.model.RequestHandler;
import domain.service.ShopService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;


public class AddUserHandler extends RequestHandler {

    public AddUserHandler(ShopService service) {
        super.setService(service);
    }

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        ArrayList<String> errors = new ArrayList<>();
        Person p = new Person();
        this.setUserId(p, request, errors);
        this.setFirstName(p, request, errors);
        this.setLastName(p, request, errors);
        this.setEmail(p, request, errors);
        this.setPassword(p, request, errors);
        if (errors.size() < 1){
            try {
                this.service.addPerson(p);
                request.setAttribute("addAdminSucces", true);
                return "adminOptions.jsp";
            }catch(Exception e){
                errors.add(e.getMessage());
                request.setAttribute("errors", errors);
                return "signUp.jsp";
            }
        }else {
            request.setAttribute("errors", errors);
            return "signUp.jsp";
        }
    }


    private void setUserId(Person p, HttpServletRequest request, ArrayList<String> errors){
        String userid = request.getParameter("userid");
        try{
            p.setUserid(userid);
            request.setAttribute("previousUserId", userid);
        }catch(Exception e){
            errors.add(e.getMessage());
        }
    }

    private void setEmail(Person p, HttpServletRequest request, ArrayList<String> errors){
        String email = request.getParameter("email");
        try{
            p.setEmail(email);
            request.setAttribute("previousEmail", email);
        }catch(Exception e){
            errors.add(e.getMessage());
        }
    }

    private void setPassword(Person p, HttpServletRequest request, ArrayList<String> errors){
        String password = request.getParameter("password");
        try{
            p.setPassword(password);
            p.setPasswordHashed(password);
            request.setAttribute("previousPassword", password);
        }catch(Exception e){
            errors.add(e.getMessage());
        }
    }

    private void setFirstName(Person p, HttpServletRequest request, ArrayList<String> errors){
        String firstName = request.getParameter("firstName");
        try{
            p.setFirstName(firstName);
            request.setAttribute("previousFirstName", firstName);
        }catch(Exception e){
            errors.add(e.getMessage());
        }
    }

    private void setLastName(Person p, HttpServletRequest request, ArrayList<String> errors){
        String lastName = request.getParameter("lastName");
        try{
            p.setLastName(lastName);
            request.setAttribute("previousLastName", lastName);
        }catch(Exception e){
            errors.add(e.getMessage());
        }
    }

}
