package web23.web18.controller;

import web23.web18.model.MapperAuth;
import web23.web18.model.User;
import web23.web18.service.ServiceAuth;
import web23.web18.service.ServiceMessage;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

public class ControllerHelper {


    public static User currentUser(HttpServletRequest request, MapperAuth mapperAuth) {
        String username;
        Cookie[] cookies = request.getCookies();
        boolean found = false;
        String session_id = "";
        if (cookies != null) {
            for (int i = 0; i < cookies.length; i++) {
                Cookie cookie = cookies[i];
                if (cookie.getName().equals("session_id")) {
                    session_id = cookie.getValue();
                    found = true;
                }
            }
        }
        User user;
        if (found) {
            user = mapperAuth.userFromSessionId(session_id);
        } else {
            user = ServiceAuth.guest();
        }
        return user;
    }
}
