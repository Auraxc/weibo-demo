package web23.web18.service;

import org.springframework.stereotype.Component;
import web23.web18.model.MapperTodo;
import web23.web18.model.MySQLStorage;
import web23.web18.model.Todo;
import web23.web18.model.User;

import java.util.ArrayList;

@Component
public class ServiceTodo {
    private MapperTodo mapperTodo;

    ServiceTodo(MapperTodo mapperTodo) {
        this.mapperTodo = mapperTodo;
    }
    public boolean currentUserTodo(int todoId, int currentUserId) {
        ArrayList<Todo> todos = this.mapperTodo.currentUserTodo(todoId, currentUserId);
        boolean result = todos.size() > 0;
        return result;
    }

    public ArrayList<Todo> currentUserTodos(User currentUser) {
        ArrayList<Todo> todos = this.mapperTodo.currentUserTodos(currentUser.id);
        return todos;
    }

    public void add(String content, User currentUser) {
        this.mapperTodo.add(content, currentUser.id);
    }

    public void delete(int id) {
        this.mapperTodo.delete(id);
    }

    public void update(int id, String content) {
        this.mapperTodo.update(id, content);
    }
}