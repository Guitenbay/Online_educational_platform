package dao.impl;

import dao.ChapterDao;
import dao.Dao;
import model.Chapter;

import java.util.List;

public class ChapterDaoImpl extends Dao<Chapter> implements ChapterDao {
    @Override
    public List<Chapter> getChapterListByCourseId(int courseId) {
        return null;
    }

    @Override
    public Chapter getChapterById(int id) {
        return null;
    }

    @Override
    public void save(Chapter chapter) {

    }

    @Override
    public void update(Chapter chapter) {

    }

    @Override
    public void deleteChapterById(int id) {

    }
}
