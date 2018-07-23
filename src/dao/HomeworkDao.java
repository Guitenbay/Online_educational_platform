package dao;

import model.Homework;

import java.util.List;

public interface HomeworkDao {

    List<Homework> getHomeworkListByCourseId(int courseId);

    Homework getHomeworkById(int id);

    void save(Homework homework);

    void update(Homework homework);

    void deleteHomeworkById(int id);

}
