package web23.web18.controller;

import web23.web18.model.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ControllerPublic {
    // MapperUser mapperUser;
    // ControllerPublic(MapperUser mapperUser) {
    //     this.mapperUser = mapperUser;
    // }
    private MapperAuth mapperAuth;

    ControllerPublic(MapperAuth mapperAuth) {
        this.mapperAuth = mapperAuth;
    }

    public static void log(String format, Object... args) {
        System.out.println(String.format(format, args));
    }


    @GetMapping("/index")
    public ModelAndView index(HttpServletRequest request) {
        String message;
        User user = ControllerHelper.currentUser(request, this.mapperAuth);
        String username = user.username;
        message = String.format("当前登录用户 %s", username);
        ModelAndView modelAndView = new ModelAndView("index_old");
        modelAndView.addObject("message", message);
        return modelAndView;
    }

    @GetMapping("/admin")
    public ModelAndView admin(HttpServletRequest request) {
        String message;
        User user = ControllerHelper.currentUser(request, this.mapperAuth);
        String username = user.username;
        // if (user.role.equals("admin")) {
        if (user.role == UserRole.admin) {
            message = String.format("当前登录用户 %s", username);
        } else {
            message = "没有权限访问";
        }
        ModelAndView modelAndView = new ModelAndView("index_old");
        modelAndView.addObject("message", message);
        return modelAndView;
    }


}
