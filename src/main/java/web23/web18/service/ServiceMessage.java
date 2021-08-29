package web23.web18.service;



import org.springframework.stereotype.Component;
import web23.web18.model.MapperMessage;
import web23.web18.model.Message;
import web23.web18.model.User;

import java.util.ArrayList;

@Component
public class ServiceMessage {
    private MapperMessage mapperMessage;

    ServiceMessage(MapperMessage mapperMessage) {
        this.mapperMessage = mapperMessage;
    }

    public ArrayList<Message> currentUserMessagess(int userId) {
        ArrayList<Message> messages = this.mapperMessage.currentUserMessagess(userId);
        return messages;

    }

    public void add(String content, int userId) {
        this.mapperMessage.add(content, userId);
    }

}
