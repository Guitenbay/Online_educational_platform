package model;

import config.FileConfig;
import util.FileUtil;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Course implements Serializable {

    private int id;
    private String name;
    private String description;
    private int creatorId;
    private int number;

    public Course(){}

    public Course(int id, String name, String description, int creatorId, int number) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.creatorId = creatorId;
        this.number = number;

    }

    public String getImagePath(){
        String relativePath = "/" + FileConfig.COURSE_UPLOAD_DIRECTORY + "/" + id + "/" + id;
        String path = FileConfig.GLOBAL_PATH + relativePath;
        if(!(new File(path).exists()))
            return FileConfig.DEFAULT_COURSE_IMAGE_PATH;
        else
            return "/assets" + relativePath;
    }

    public List<Map<String, String>> getResRelativePaths(){
        String relativePath = "/" + FileConfig.COURSE_UPLOAD_DIRECTORY + "/" + id + "/" + FileConfig.RESOURCE_UPLOAD_DIRECTORY;
        String path = FileConfig.GLOBAL_PATH + relativePath;
        List<String> fileNames = FileUtil.walkThroughFolder(path);
        List<Map<String, String>> files = new ArrayList<>();
        for (String name : fileNames) {
            Map<String, String> entry = new HashMap<>();
            entry.put("name", name);
            entry.put("resPath", relativePath + "/" + name);
            files.add(entry);
        }
        return files;
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


    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }
}
