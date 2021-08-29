package web23.web18.controller;

import web23.web18.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import web23.web18.service.ServiceAuth;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class ControllerAuth {
    ServiceAuth serviceAuth;

    ControllerAuth(ServiceAuth serviceAuth) {
        this.serviceAuth = serviceAuth;
    }
    public static void log(String format, Object... args) {
        System.out.println(String.format(format, args));
    }

    @GetMapping("/login/view")
    public ModelAndView loginView(HttpServletRequest request) {
        User user = this.serviceAuth.currentUser(request);
        String username = user.username;
        String message = String.format("当前登录用户 %s", username);
        ModelAndView modelAndView = new ModelAndView("login_register");
        modelAndView.addObject("message", message);
        return modelAndView;
    }

    @PostMapping("/login")
    public String login(String username, String password, HttpServletResponse response) {

        String sessionId = this.serviceAuth.login(username, password);
        Cookie cookie;
        if (sessionId.length() > 0) {
            cookie = new Cookie("session_id", sessionId);
        } else {
            // cookie = new Cookie("", "");
            return "redirect:/login/view";
        }
        response.addCookie(cookie);
        return "redirect:/weibo/timeline";
    }

    @GetMapping("/register/view")
    public static ModelAndView registerView() {
        ModelAndView modelAndView = new ModelAndView("login_register");
        modelAndView.addObject("message", "请注册");
        return modelAndView;
    }

    @PostMapping("/register")
    public ModelAndView register(String username, String password) {
        String message = "注册失败";
        boolean success = this.serviceAuth.register(username, password);
        if (success) {
            message = String.format("注册成功 %s", username);
        }

        ModelAndView modelAndView = new ModelAndView("login_register");
        modelAndView.addObject("message", message);
        return modelAndView;
    }


}
