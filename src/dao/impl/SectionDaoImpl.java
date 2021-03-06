package dao.impl;

import dao.Dao;
import dao.SectionDao;
import model.Section;

import java.util.List;

public class SectionDaoImpl extends Dao<Section> implements SectionDao {
    @Override
    public List<Section> getSectionListByChapterId(int chapterId) {
        String sql = "SELECT id, name, chapterId FROM sections WHERE chapterId=? ORDER BY id ASC";
        return getForList(sql, chapterId);
    }

    @Override
    public Section getSectionById(int id) {
        String sql = "SELECT id, name, chapterId FROM sections WHERE id=?";
        return get(sql, id);
    }

    @Override
    public void save(Section section) {
        String sql = "INSERT INTO sections (chapterId, name)" +
                "VALUES (?, ?)";
        update(sql, section.getChapterId(), section.getName());
    }

    @Override
    public void update(Section section) {
        String sql = "UPDATE sections SET name=?, chapterId=? WHERE id=?";
        update(sql, section.getName(), section.getChapterId(), section.getId());
    }

    @Override
    public void deleteSectionById(int id) {
        String sql = "DELETE FROM sections WHERE id=?";
        update(sql, id);
    }
}
