package dao;

import model.Course;

import java.util.List;

public interface CourseDao {

    List<Course> getCourseAllByDesc();

    List<Course> getMaxNumberCourseAll();

    List<Course> getMaxNumberCourseList(int from, int to);

    List<Course> getCourseAll();

    List<Course> getCourseListByUserId(int userId);

    Course getCourseById(int id);

    int getCourseIdOfMostNew();

    void save(Course course);

    void update(Course course);

    void deleteCourseById(int id);

}
