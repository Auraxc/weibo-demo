package web23.web18.service;

import org.springframework.stereotype.Component;
import web23.web18.model.MapperWeibo;
import web23.web18.model.*;

import java.util.ArrayList;

import static web23.web18.controller.ControllerPublic.log;

@Component
public class ServiceWeibo {

    private MapperWeibo mapperWeibo;

    ServiceWeibo(MapperWeibo mapperWeibo) {
        this.mapperWeibo = mapperWeibo;
    }


    public ArrayList<WeiboWithComments> currentUserWeibos(User currentUser) {
        log("start get weibo with comments");
        ArrayList<WeiboWithComments> weibos = this.mapperWeibo.currentUserWeibosWithComments(currentUser.id);
        log("weibo with comments %s", weibos);
        return weibos;
    }

    public ArrayList<WeiboWithComments> timelineWeibos(User currentUser) {
        ArrayList<WeiboWithComments> weibos = this.mapperWeibo.timelineWeibos(currentUser.id);
        return weibos;
    }

    public void add(String content, User currentUser) {
        this.mapperWeibo.add(content, currentUser.id);
    }

    public void commentAdd(String content, User currentUser, int weiboId) {
        this.mapperWeibo.commentAdd(content, currentUser.id, weiboId);
    }

    public void delete(int id) {
        this.mapperWeibo.delete(id);
    }

    public void update(int id, String content) {
        this.mapperWeibo.update(id, content);
    }

    public void addFollow(int followerId, int followeeId){
        this.mapperWeibo.addFollow(followerId, followeeId);
    }
}
