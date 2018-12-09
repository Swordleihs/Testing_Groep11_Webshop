package domain.model.handlers;

import domain.model.Person;
import domain.model.RequestHandler;
import domain.model.sorters.PersonSorter;
import domain.service.ShopService;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;


public class PersonOverviewHandler extends RequestHandler {

    public PersonOverviewHandler(ShopService service) {
        super.setService(service);
    }

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
        List<Person> persons = this.service.getPersons();
        PersonSorter sorter = new PersonSorter();
        if (request.getCookies() != null) {
            Cookie[] cookies = request.getCookies();
            for (Cookie c : cookies) {
                if (c.getName().equals("sort")) {
                    if (c.getValue().equals("Email")) {
                        List<Person> personsSorted = sorter.sortByEmail(persons);
                        request.setAttribute("sortingType", "email");
                        request.setAttribute("personen", personsSorted);
                    }
                    else if(c.getValue().equals("FirstName")){
                        List<Person> personsSorted = sorter.sortByFirstName(persons);
                        request.setAttribute("sortingType", "firstname");
                        request.setAttribute("personen", personsSorted);
                    }
                    else {
                        List<Person> personsSorted = sorter.sortByLastName(persons);
                        request.setAttribute("sortingType", "lastname");
                        request.setAttribute("personen", personsSorted);
                    }
                }
            }
        }else if(request.getAttribute("sortingType") != null){
            String attr = (String) request.getAttribute("sortingType");
            if (attr.equals("Email")) {
                List<Person> personsSorted = sorter.sortByEmail(persons);
                request.setAttribute("sortingType", "email");
                request.setAttribute("personen", personsSorted);
            }
            else if(attr.equals("FirstName")){
                List<Person> personsSorted = sorter.sortByFirstName(persons);
                request.setAttribute("sortingType", "firstname");
                request.setAttribute("personen", personsSorted);
            }
            else {
                List<Person> personsSorted = sorter.sortByLastName(persons);
                request.setAttribute("sortingType", "lastname");
                request.setAttribute("personen", personsSorted);
            }
        }
        request.setAttribute("personen", persons);
        return "personoverview.jsp";
    }
}
