package dao.impl;

import dao.ChapterDao;
import dao.Dao;
import model.Chapter;

import java.util.List;

public class ChapterDaoImpl extends Dao<Chapter> implements ChapterDao {
    @Override
    public List<Chapter> getChapterListByCourseId(int courseId) {
        String sql = "SELECT id, name, courseId FROM chapters WHERE courseId=? ORDER BY id ASC";
        return getForList(sql, courseId);
    }

    @Override
    public Chapter getChapterById(int id) {
        String sql = "SELECT id, name, courseId FROM chapters WHERE id=?";
        return get(sql, id);
    }

    @Override
    public void save(Chapter chapter) {
        String sql = "INSERT INTO chapters (name, courseId) VALUES (?, ?)";
        update(sql, chapter.getName(), chapter.getCourseId());
    }

    @Override
    public void update(Chapter chapter) {
        String sql = "UPDATE chapters SET name=?, courseId=? WHERE id=?";
        update(sql, chapter.getName(), chapter.getCourseId(), chapter.getId());
    }

    @Override
    public void deleteChapterById(int id) {
        String sql = "DELETE FROM chapters WHERE id=?";
        update(sql, id);
    }
}
