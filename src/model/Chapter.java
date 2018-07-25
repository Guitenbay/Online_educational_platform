package model;

public class Chapter {

    private int id;
    private String name;
    private int courseId;

    public Chapter(){}

    public Chapter(int id, String name, int courseId) {
        this.id = id;
        this.name = name;
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

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }
}
