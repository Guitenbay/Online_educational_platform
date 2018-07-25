package dao.impl;

import dao.Dao;
import dao.HomeworkDao;
import model.Homework;

import java.util.List;

public class HomeworkDaoImpl extends Dao<Homework> implements HomeworkDao {
    @Override
    public List<Homework> getHomeworkListByCourseId(int courseId) {
        String sql = "SELECT id, name, description, course_id courseId, dead_line deadLine " +
                "FROM homework WHERE course_id=?";
        return getForList(sql, courseId);
    }

    @Override
    public Homework getHomeworkById(int id) {
        String sql = "SELECT id, name, description, course_id courseId, dead_line deadLine " +
                "FROM homework WHERE id=?";
        return get(sql, id);
    }

    @Override
    public void save(Homework homework) {
        String sql = "INSERT INTO homework (name, description, course_id, dead_line)" +
                "VALUES (?, ?, ?, ?)";
        update(sql, homework.getName(), homework.getDescription(),
                homework.getCourseId(), homework.getDeadLine());
    }

    @Override
    public void update(Homework homework) {
        String sql = "UPDATE homework SET name=?, description=?, course_id=?, dead_line=? " +
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
