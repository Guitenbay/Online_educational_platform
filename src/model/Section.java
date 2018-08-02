package model;

import config.FileConfig;
import util.FileUtil;

import java.util.ArrayList;
import java.util.List;

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

    public List<String> getVideoPaths(int courseId){
        String chapterDir = FileConfig.CHAPTER_UPLOAD_DIRECTORY + "-" + chapterId;
        String relativePath = "/" + FileConfig.COURSE_UPLOAD_DIRECTORY
                + "/" + courseId + "/" + chapterDir + "/" + id + "/" + FileConfig.VIDEO_UPLOAD_DIRECTORY;
        String path = FileConfig.GLOBAL_PATH + relativePath;
        List<String> fileNames = FileUtil.walkThroughFolder(path);
        List<String> filePaths = new ArrayList<>();
        for (String name : fileNames) {
            filePaths.add("/assets" + relativePath + "/" + name);
        }
        return filePaths;
    }

    public List<String> getPPTPaths(int courseId){
        String chapterDir = FileConfig.CHAPTER_UPLOAD_DIRECTORY + "-" + chapterId;
        String relativePath = "/" + FileConfig.COURSE_UPLOAD_DIRECTORY
                + "/" + courseId + "/" + chapterDir + "/" + id + "/" + FileConfig.PPT_UPLOAD_DIRECTORY;
        String path = FileConfig.GLOBAL_PATH + relativePath;
        List<String> fileNames = FileUtil.walkThroughFolder(path);
        List<String> filePaths = new ArrayList<>();
        for (String name : fileNames) {
            filePaths.add("/assets" + relativePath + "/" + name);
        }
        return filePaths;
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
