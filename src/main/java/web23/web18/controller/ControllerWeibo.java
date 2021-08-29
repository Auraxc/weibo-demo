package web23.web18.controller;

import web23.web18.model.MapperAuth;
import web23.web18.model.User;
import web23.web18.model.Weibo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import web23.web18.model.WeiboWithComments;
import web23.web18.service.ServiceTodo;
import web23.web18.service.ServiceWeibo;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

import static web23.web18.controller.ControllerPublic.log;

@Controller
public class ControllerWeibo {
    // private ServiceWeibo serviceWeibo ;
    MapperAuth mapperAuth;
    ServiceWeibo serviceWeibo;

    ControllerWeibo(MapperAuth mapperAuth, ServiceWeibo serviceWeibo) {
        this.mapperAuth = mapperAuth;
        this.serviceWeibo = serviceWeibo;
    }

    @GetMapping("/weibo/timeline")
    public ModelAndView timeline(HttpServletRequest request) {
        User currentUser = ControllerHelper.currentUser(request, this.mapperAuth);
        String username = currentUser.username;
        ArrayList<WeiboWithComments> weibos = this.serviceWeibo.timelineWeibos(currentUser);
        // 读出 模板 文件并把数据放进 html 文件
        log("weibos %s", weibos);
        ModelAndView modelAndView = new ModelAndView("weibo_timeline");
        modelAndView.addObject("weibos", weibos);
        log("avator %s", currentUser.avator);
        modelAndView.addObject("user", currentUser);
        // modelAndView.addObject("user", currentUser);
        return modelAndView;
    }

    @GetMapping("/weibo/all")
    public ModelAndView all(HttpServletRequest request) {
        // 显示所有 weibo
        User currentUser = ControllerHelper.currentUser(request, this.mapperAuth);

        ArrayList<WeiboWithComments> weibos = this.serviceWeibo.currentUserWeibos(currentUser);
        log("all weibos %s", weibos.toString());
        ModelAndView modelAndView = new ModelAndView("weibo_all");
        modelAndView.addObject("weibos", weibos);
        // modelAndView.addObject("user_id", user_id);
        return modelAndView;
    }

    @GetMapping("/weibo/add")
    public String add(String content, HttpServletRequest request) {
        User currentUser = ControllerHelper.currentUser(request, this.mapperAuth);
        // 处理数据
        this.serviceWeibo.add(content, currentUser);

        // 返回数据
        return "redirect:/weibo/timeline";
    }

    @GetMapping("/comment/new")
    public static ModelAndView commentNew(int id) {
        ModelAndView modelAndView = new ModelAndView("comment_new");
        modelAndView.addObject("weibo_id", id);
        return modelAndView;

    }

    @GetMapping("/comment/add")
    public String commentAdd(int id, String content, HttpServletRequest request) {
        User currentUser = ControllerHelper.currentUser(request, this.mapperAuth);
        // 处理数据
        this.serviceWeibo.commentAdd(content, currentUser, id);

        // 返回数据
        return "redirect:/weibo/timeline";
    }

    @GetMapping("/weibo/delete")
    public String delete(int id, HttpServletRequest request) {
        // 拿数据
        User user = ControllerHelper.currentUser(request, this.mapperAuth);
        // 处理数据
        // if (ServiceWeibo.currentUserWeibos(id, user.id)) {
        this.serviceWeibo.delete(id);
        // }
        // 返回数据
        return "redirect:/weibo/timeline";
    }

    @GetMapping("/weibo/follow")
    public String follow(int user_id, HttpServletRequest request) {
        // 显示所有 weibo
        User currentUser = ControllerHelper.currentUser(request, this.mapperAuth);
        if (currentUser.id != user_id) {
            this.serviceWeibo.addFollow(currentUser.id, user_id);
        }
        return "redirect:/weibo/timeline";
    }
}
