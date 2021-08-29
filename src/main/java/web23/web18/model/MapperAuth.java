package web23.web18.model;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;


import java.util.ArrayList;


// 这个是 mybaits
@Mapper
@Repository
public interface MapperAuth {

    User userFromUsername(String username);

    void register(String username, String password, String user_role, String salt);

    User userFromSessionId(String sessionId);

    void insertSession(int userId, String session);
}
