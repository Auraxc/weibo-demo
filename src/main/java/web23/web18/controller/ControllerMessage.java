package web23.web18.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import web23.web18.model.MapperAuth;
import web23.web18.model.Message;
import web23.web18.model.User;
import web23.web18.service.ServiceMessage;


import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

@Controller
public class ControllerMessage {
    ServiceMessage serviceMessage;
    private MapperAuth mapperAuth;
    ControllerMessage(ServiceMessage serviceMessage, MapperAuth mapperAuth) {
        this.serviceMessage = serviceMessage;
        this.mapperAuth = mapperAuth;
    }

    @GetMapping("/message/all")
    public ModelAndView message(HttpServletRequest request) {
        User user = ControllerHelper.currentUser(request, this.mapperAuth);
        ArrayList<Message> message = this.serviceMessage.currentUserMessagess(user.id);
        ModelAndView modelAndView = new ModelAndView("message");
        modelAndView.addObject("messages", message);
        return modelAndView;
    }

    @GetMapping("/message/add")
    public ModelAndView add(String content, HttpServletRequest request) {
        User user = ControllerHelper.currentUser(request, this.mapperAuth);
        this.serviceMessage.add(content, user.id);
        return new ModelAndView("redirect:/message/all");
    }
}
