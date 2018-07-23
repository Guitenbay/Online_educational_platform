package model;

import java.util.Date;

public class Barrage {

    private int id;
    private String context;
    private int sectionId;
    private Date showTime;

    public Barrage(){}

    public Barrage(int id, String context, int sectionId, Date showTime) {
        this.id = id;
        this.context = context;
        this.sectionId = sectionId;
        this.showTime = showTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public int getSectionId() {
        return sectionId;
    }

    public void setSectionId(int sectionId) {
        this.sectionId = sectionId;
    }

    public Date getShowTime() {
        return showTime;
    }

    public void setShowTime(Date showTime) {
        this.showTime = showTime;
    }
}
