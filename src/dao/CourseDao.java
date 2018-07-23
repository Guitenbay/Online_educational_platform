package dao;

import model.Course;

import java.util.List;

public interface CourseDao {

    List<Course> getCourseAllByDesc();

    List<Course> getCourseListByUserId(int userId);

    Course getCourseById(int id);

    void save(Course course);

    void update(Course course);

    void deleteCourseById(int id);

}
