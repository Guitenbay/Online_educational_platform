package dao.impl;

import dao.CourseDao;
import dao.Dao;
import model.Course;

import java.util.List;

public class CourseDaoImpl extends Dao<Course> implements CourseDao {
    @Override
    public List<Course> getCourseAllByDesc() {
        String sql = "SELECT id, name, description, creator_id creatorId " +
                "FROM courses ORDER BY id DESC";
        return getForList(sql);
    }

    @Override
    public List<Course> getCourseListByUserId(int userId) {
        String sql = "SELECT id, name, description, creator_id creatorId " +
                "FROM courses WHERE creator_id=?";
        return getForList(sql, userId);
    }

    @Override
    public Course getCourseById(int id) {
        String sql = "SELECT id, name, description, creator_id creatorId " +
                "FROM courses WHERE id=?";
        return get(sql, id);
    }

    @Override
    public void save(Course course) {
        String sql = "INSERT INTO courses (name, description, creator_id) " +
                "VALUES (?, ?, ?)";
        update(sql, course.getName(), course.getDescription(), course.getCreatorId());
    }

    @Override
    public void update(Course course) {
        String sql = "UPDATE courses SET name=?, description=?, creator_id=? WHERE id=?";
        update(sql, course.getName(), course.getDescription(),
                course.getCreatorId(), course.getId());
    }

    @Override
    public void deleteCourseById(int id) {
        String sql = "DELETE FROM courses WHERE id=?";
        update(sql, id);
    }
}
