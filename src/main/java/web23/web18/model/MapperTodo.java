package web23.web18.model;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;


// 这个是 mybaits
@Mapper
@Repository
public interface MapperTodo {
    ArrayList<Todo> currentUserTodo(@Param("todoId") int todoId, @Param("currentUserId") int currentUserId);

    ArrayList<Todo> currentUserTodos(int userId);

    void add(String content, int userId);

    void delete(int id);

    void update(int id, String content);

}
