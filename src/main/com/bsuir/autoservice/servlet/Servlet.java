package main.com.bsuir.autoservice.servlet;

import com.google.inject.Guice;
import com.google.inject.Injector;
import main.com.bsuir.autoservice.binding.AutoServiceShopModule;
import main.com.bsuir.autoservice.command.exception.CommandException;
import main.com.bsuir.autoservice.controller.IController;
import main.com.bsuir.autoservice.controller.bean.BeanViewController;
import main.com.bsuir.autoservice.controller.exception.ControllerException;
import main.com.bsuir.autoservice.controller.provider.ControllerProvider;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Enumeration;

public class Servlet extends HttpServlet {
    static {
        try {
            Injector injector = Guice.createInjector(new AutoServiceShopModule());
            injector.getInstance(BeanViewController.class);
            controllerProvider =  injector.getInstance(ControllerProvider.class);
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        try {
            executeRequest(request,response);
        }catch (Exception e){
            throw  new ServletException(e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException {
        try {
            executeRequest(request, response);
        }catch (Exception e){
            throw  new ServletException(e);
        }
    }

    private void executeRequest(HttpServletRequest request, HttpServletResponse response)
            throws ControllerException, CommandException {
        String url = getUrl(request.getRequestURI());
        Enumeration en = request.getParameterNames();
        IController controller = controllerProvider.getController(url);
        Object resultData = controller.invokeCommand(request.getParameterMap());
        controller.returnResult(request, response, resultData);
    }

    private static final ControllerProvider controllerProvider;
    private String getUrl(String requestURL) {
        return requestURL.replace(".ass","");
    }
}
