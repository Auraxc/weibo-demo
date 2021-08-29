package web23.web18.model;

public class Message {
    public int id;
    public String content;
    private int userId;

    public Message(int id, String content, int userId) {
        this.id = id;
        this.content = content;
        this.userId = userId;
    }

    @Override
    public String toString() {
        String s = String.format(
                "(内容 content: %s, userId: %s)",
                this.content,
                this.userId
        );
        return s;
    }


    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getUserId() {
        return userId;
    }

    public int getId() {
        return id;
    }
}
