package web23.web18.model;

public class User {
    public int id;
    public String username;
    public String password;
    public UserRole role;
    public String salt;
    public String avator;

    public User(int id, String username, String password, UserRole role, String salt, String avator) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.role = role;
        this.salt = salt;
        this.avator = avator;
    }

    @Override
    public String toString() {
        String s = String.format(
                "(用户名: %s, 密码: %s, 角色 %s 盐 %s 头像 %s)",
                this.username,
                this.password,
                this.role,
                this.salt,
                this.avator
        );
        return s;
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public UserRole getRole() {
        return role;
    }

    public String getAvator() {
        return avator;
    }
}
