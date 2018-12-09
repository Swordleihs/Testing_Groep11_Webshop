package ui.controller;

import domain.model.HandlerFactory;
import domain.model.Person;
import domain.model.RequestHandler;
import domain.service.ShopService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;
import java.util.Properties;

@WebServlet("/Controller")
public class Controller extends HttpServlet {

    private ShopService service;
    private HandlerFactory handlerFactory;

    public void init() throws ServletException{
        super.init();
        ServletContext context = getServletContext();

        Properties properties = new Properties();

        Enumeration<String> parameterNames =context.getInitParameterNames();
        while(parameterNames.hasMoreElements()){
            String propertyName = parameterNames.nextElement();
            properties.setProperty(propertyName, context.getInitParameter(propertyName));
        }
        service = new ShopService(properties);
        this.handlerFactory = new HandlerFactory(this.service);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException{
        try{
            String action = request.getParameter("action");
            RequestHandler handler = this.handlerFactory.getHandler(action);
            String destination = handler.handleRequest(request, response);
            RequestDispatcher view = request.getRequestDispatcher(destination);
            view.forward(request, response);
        }catch(Exception e){
            System.out.println(e.getMessage());
            throw new ServletException(e.getMessage(), e);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.processRequest(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.processRequest(request, response);
    }
}
