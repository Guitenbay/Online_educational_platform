package dao.impl;

import dao.Dao;
import dao.HomeworkDao;
import model.Homework;

import java.util.List;

public class HomeworkDaoImpl extends Dao<Homework> implements HomeworkDao {

    @Override
    public List<Homework> getHomeworkListByCourseId(int courseId) {
        String sql = "SELECT id, name, description, courseId, deadline" +
                " FROM homework WHERE courseId=?";
        return getForList(sql, courseId);
    }

    @Override
    public Homework getHomeworkById(int id) {
        String sql = "SELECT id, name, description, courseId, deadline" +
                " FROM homework WHERE id=?";
        return get(sql, id);
    }

    @Override
    public void save(Homework homework) {
        String sql = "INSERT INTO homework (name, description, courseId, deadline)" +
                "VALUES (?, ?, ?, ?)";
        update(sql, homework.getName(), homework.getDescription(),
                homework.getCourseId(), homework.getDeadLine());
    }

    @Override
    public void update(Homework homework) {
        String sql = "UPDATE homework SET name=?, description=?, courseId=?, deadline=? " +
                "WHERE id=?";
        update(sql, homework.getName(), homework.getDescription(),
                homework.getCourseId(), homework.getDeadLine(), homework.getId());
    }

    @Override
    public void deleteHomeworkById(int id) {
        String sql = "DELETE FROM homework WHERE id=?";
        update(sql, id);
    }
}
