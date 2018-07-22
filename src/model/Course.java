package model;

public class Course {

    private int id;
    private String name;
    private String description;
    private int creatorId;

    public Course(){}

    public Course(int id, String name, String description, int creatorId) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.creatorId = creatorId;
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

    public int getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(int creatorId) {
        this.creatorId = creatorId;
    }
}
