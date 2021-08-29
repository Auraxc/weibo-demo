package web23.web18.service;


import org.springframework.stereotype.Component;
import web23.web18.model.MapperAuth;
import web23.web18.model.User;
import web23.web18.model.UserRole;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.UUID;


@Component
public class ServiceAuth {
    private MapperAuth mapperAuth;

    ServiceAuth(MapperAuth mapperAuth) {
        this.mapperAuth = mapperAuth;
    }

    public static void log(String format, Object... args) {
        System.out.println(String.format(format, args));
    }


    public static String hexFromBytes(byte[] bytes) {
        StringBuilder result = new StringBuilder();
        for (int i = 0, bytesLength = bytes.length; i < bytesLength; i++) {
            byte currentByte = bytes[i];
            // 02 代表不足两位补足两位 x代表用16进制表示
            // String.format("%02x", 0) = "00"
            result.append(String.format("%02x", currentByte));
        }
        return result.toString();
    }

    public static String SaltedPasswordHash(String password, String salt) {
        MessageDigest md;
        try {
            md = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        String salted = salt + password;
        md.update(salted.getBytes(StandardCharsets.UTF_8));
        byte[] result = md.digest();
        return hexFromBytes(result);
    }

    public static User guest() {
        User user = new User(0, "游客", "dadasds", UserRole.guest, "dasdas", "");
        return user;
    }

    public String login(String username, String password) {
        log("login user");
        User user = this.mapperAuth.userFromUsername(username);
        log("user from mysql is %s", user);
        if (user == null) {
            return "";
        }
        if (!user.role.equals(UserRole.guest)) {
            String salt = user.salt;
            String saltedPassword = ServiceAuth.SaltedPasswordHash(password, salt);
            if (user.password.equals(saltedPassword)) {
                String sessionId = UUID.randomUUID().toString();
                insertSession(user.id, sessionId);
                log("login session id is %s", sessionId);
                return sessionId;
            }
        }
        return "";
    }

    public User userFromUsername(String username) {
        User user = this.mapperAuth.userFromUsername(username);
        if (user != null) {
            return user;
        } else {
            return guest();
        }
    }

    public boolean register(String username, String password) {
        User user = userFromUsername(username);
        try {
            if (user.role.equals(UserRole.guest)) {
                log("register start");
                String salt = UUID.randomUUID().toString();
                String saltedPassword = ServiceAuth.SaltedPasswordHash(password, salt);

                String role = String.valueOf(UserRole.normal);
                this.mapperAuth.register(username, saltedPassword, role, salt);
                return true;
            }
        }catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
        return false;

    }

    public User userFromSessionId(String sessionId) {
        User user = this.mapperAuth.userFromSessionId(sessionId);
        if (user != null) {
            return user;
        }
        return ServiceAuth.guest();
    }

    public void insertSession(int userId, String session) {
        this.mapperAuth.insertSession(userId, session);
    }

    public User currentUser(HttpServletRequest request) {
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
            user = this.mapperAuth.userFromSessionId(session_id);
        } else {
            user = ServiceAuth.guest();
        }
        log("user is %s", user);
        return user;
    }
}
