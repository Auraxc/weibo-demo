package web23.web18.controller;

import web23.web18.model.MapperAuth;
import web23.web18.model.Todo;
import web23.web18.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import web23.web18.service.ServiceTodo;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;

@Controller
public class ControllerTodo {
    private ServiceTodo serviceTodo;
    private MapperAuth mapperAuth;
    ControllerTodo(ServiceTodo serviceTodo, MapperAuth mapperAuth) {
        this.serviceTodo = serviceTodo;
        this.mapperAuth = mapperAuth;

    }
    @GetMapping("/todo/all")
    public ModelAndView all(HttpServletRequest request) {
        // 显示所有 todo
        User currentUser = ControllerHelper.currentUser(request, this.mapperAuth);

        ArrayList<Todo> todos = this.serviceTodo.currentUserTodos(currentUser);
        // 读出 模板 文件并把数据放进 html 文件
        ModelAndView modelAndView = new ModelAndView("todo_all");
        modelAndView.addObject("todos", todos);
        return modelAndView;
    }

    @GetMapping("/todo/add")
    public String add(String content, HttpServletRequest request) {
            User currentUser = ControllerHelper.currentUser(request, this.mapperAuth);
            // 处理数据
        this.serviceTodo.add(content, currentUser);
        // 返回数据
        return "redirect:/todo/all";
    }
    @GetMapping("/todo/delete")
    public String delete(int id, HttpServletRequest request) {
        // 拿数据
        User user = ControllerHelper.currentUser(request,this.mapperAuth);
        // 处理数据
        if (this.serviceTodo.currentUserTodo(id, user.id)) {
            this.serviceTodo.delete(id);
        }
        // 返回数据
        return "redirect:/todo/all";
    }

    @GetMapping("/todo/edit")
    public Object edit(int id, HttpServletRequest request) {
        // 拿到请求 id
        User user = ControllerHelper.currentUser(request,this.mapperAuth);
        if (this.serviceTodo.currentUserTodo(id, user.id)) {
            ModelAndView modelAndView = new ModelAndView("todo_edit");
            modelAndView.addObject("todo_id", id);
            return modelAndView;
        } else {
            return "redirect:/todo/all";
        }

    }
    @GetMapping("/todo/update")
    public String update(int id, String content, HttpServletRequest request) {

        User user = ControllerHelper.currentUser(request,this.mapperAuth);
        if (this.serviceTodo.currentUserTodo(id, user.id)) {
            // 处理数据
            this.serviceTodo.update(id, content);
        }
        return "redirect:/todo/all";
    }
}
