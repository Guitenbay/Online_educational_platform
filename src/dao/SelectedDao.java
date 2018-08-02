package dao;

import java.util.List;
import java.util.Map;

public interface SelectedDao {

    int getUserNumberByCourse(int courseId);

    List<Map<String, Object>> getSelectedListByCourseId(int courseId);

    List<Map<String, Object>> getSelectedListByUserId(int userId);

    Map<String, Object> getSelectedByUserIdAndCourseId(int userId, int courseId);

    void save(int userId, int courseId);

    void delete(int userId, int courseId);

}
