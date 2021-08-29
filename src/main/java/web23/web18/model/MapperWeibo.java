package web23.web18.model;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Mapper
@Repository
public interface MapperWeibo {

    ArrayList<WeiboWithComments> timelineWeibos(int userId);

    ArrayList<WeiboWithComments> currentUserWeibosWithComments(int userId);

    ArrayList<Comment> commentsFromWeibo(int weiboId);

    void add(String content, int userId);

    void commentAdd(String content, int userId, int weiboId);

    void delete(int id);

    void update(int id, String content);
    void addFollow(int followerId, int followeeId);

}
