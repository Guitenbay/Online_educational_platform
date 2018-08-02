package dao.impl;

import dao.CourseDao;
import dao.Dao;
import model.Course;

import java.util.List;

public class CourseDaoImpl extends Dao<Course> implements CourseDao {
    @Override
    public List<Course> getCourseAllByDesc() {
        String sql = "SELECT id, name, description, creatorId " +
                "FROM courses ORDER BY id DESC";
        return getForList(sql);
    }

    @Override
    public List<Course> getMaxNumberCourseAll() {
        String sql = "SELECT id, name, description, creatorId " +
                "FROM courses ORDER BY number DESC";
        return getForList(sql);
    }

    @Override
    public List<Course> getMaxNumberCourseList(int from, int to) {
        String sql = "SELECT id, name, description, creatorId " +
               "FROM courses ORDER BY number DESC LIMIT " + from + "," + to;
        return getForList(sql);
    }

    @Override
    public List<Course> getCourseAll() {

        String sql = "SELECT id, name, description, creatorId FROM courses";
        return getForList(sql);
    }

    @Override
    public List<Course> getCourseListByUserId(int userId) {
        String sql = "SELECT id, name, description, creatorId " +
                "FROM courses WHERE creatorId=?";
        return getForList(sql, userId);
    }

    @Override
    public Course getCourseById(int id) {
        String sql = "SELECT id, name, description, creatorId" +
                " FROM courses WHERE id=?";
        return get(sql, id);
    }

    @Override
    public int getCourseIdOfMostNew() {
        String sql = "SELECT id FROM courses ORDER BY id DESC LIMIT 1";
        return getForValue(sql);
    }

    @Override
    public void save(Course course) {
        String sql = "INSERT INTO courses (name, description, creatorId, number) " +
                "VALUES (?, ?, ?, ?)";
        update(sql, course.getName(), course.getDescription(), course.getCreatorId(), course.getNumber());
    }

    @Override
    public void update(Course course) {
        String sql = "UPDATE courses SET name=?, description=?, creatorId=? number=? WHERE id=?";
        update(sql, course.getName(), course.getDescription(),
                course.getCreatorId(), course.getNumber(), course.getId());
    }

    @Override
    public void deleteCourseById(int id) {
        String sql = "DELETE FROM courses WHERE id=?";
        update(sql, id);
    }
}
