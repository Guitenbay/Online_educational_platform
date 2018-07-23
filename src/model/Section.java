package model;

public class Section {

    private int id;
    private String name;
    private int chapterId;

    public Section(){}

    public Section(int id, String name, int chapterId) {
        this.id = id;
        this.name = name;
        this.chapterId = chapterId;
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

    public int getChapterId() {
        return chapterId;
    }

    public void setChapterId(int chapterId) {
        this.chapterId = chapterId;
    }
}
