package model;

import java.util.Date;

public class Homework {

    private int id;
    private String name;
    private String description;
    private Date deadLine;
    private int courseId;

    public Homework(){}

    public Homework(int id, String name, String description, Date deadLine, int courseId) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.deadLine = deadLine;
        this.courseId = courseId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDeadLine() {
        return deadLine;
    }

    public void setDeadLine(Date deadLine) {
        this.deadLine = deadLine;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }
}
