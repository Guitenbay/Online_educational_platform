package model;

public class Course {

    private int id;
    private String name;
    private String description;
    private int creator_id;

    public Course(){}

    public Course(int id, String name, String description, int creator_id) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.creator_id = creator_id;
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

    public int getCreator_id() {
        return creator_id;
    }

    public void setCreator_id(int creator_id) {
        this.creator_id = creator_id;
    }
}
