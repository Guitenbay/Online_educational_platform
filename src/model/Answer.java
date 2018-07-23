package model;

public class Answer {

    private int id;
    private String Context;
    private int score;
    private int userId;
    private int homeworkId;

    public Answer(){}

    public Answer(int id, String context, int score, int userId, int homeworkId) {
        this.id = id;
        Context = context;
        this.score = score;
        this.userId = userId;
        this.homeworkId = homeworkId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContext() {
        return Context;
    }

    public void setContext(String context) {
        Context = context;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getHomeworkId() {
        return homeworkId;
    }

    public void setHomeworkId(int homeworkId) {
        this.homeworkId = homeworkId;
    }
}
