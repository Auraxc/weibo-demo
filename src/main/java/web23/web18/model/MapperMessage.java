package web23.web18.model;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;


import java.util.ArrayList;


// 这个是 mybaits
@Mapper
@Repository
public interface MapperMessage {

    ArrayList<Message> currentUserMessagess(int userId);

    void add(String content, int userId);

}
