package main.com.bsuir.autoservice.controller.bean;

import com.google.inject.Inject;
import main.com.bsuir.autoservice.binding.annotation.action.map.BeanDependencyEditActionMap;
import main.com.bsuir.autoservice.controller.AbstractPageController;
import main.com.bsuir.autoservice.controller.action.Action;

import java.util.Map;

public class BeanDependencyEditController extends AbstractPageController{
    @Inject
    private BeanDependencyEditController(@BeanDependencyEditActionMap Map<String, Action> actionMap){
        super(actionMap);
    }

    @Override
    protected String getJspName() {
        return "/bean/editForm.jsp";
    }
}