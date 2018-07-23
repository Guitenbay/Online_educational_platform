package dao;

import model.Chapter;

import java.util.List;

public interface ChapterDao {

    List<Chapter> getChapterListByCourseId(int courseId);

    Chapter getChapterById(int id);

    void save(Chapter chapter);

    void update(Chapter chapter);

    void deleteChapterById(int id);

}
