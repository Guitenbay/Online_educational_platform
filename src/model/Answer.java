package model;

import java.util.Date;

public class Answer  {

    private int id;
    private String Context;
    private Integer score;
    private Date updateTime;
    private int userId;
    private int homeworkId;

    public Answer(){}

    public Answer(int id, String context, Integer score, Date updateTime, int userId, int homeworkId) {
        this.id = id;
        Context = context;
        this.score = score;
        this.updateTime = updateTime;
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

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
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
