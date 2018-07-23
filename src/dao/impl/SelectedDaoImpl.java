package dao.impl;

import dao.Dao;
import dao.SelectedDao;

import java.util.List;
import java.util.Map;

public class SelectedDaoImpl extends Dao implements SelectedDao {
    @Override
    public int getUserNumberByCourse(int courseId) {
        String sql = "SELECT COUNT(user_id) AS user_number FROM selected " +
                "WHERE course_id=?";
        return ((Long) getForValue(sql, courseId)).intValue();
    }

    @Override
    public List<Map<String, Object>> getSelectedListByCourseId(int courseId) {
        String sql = "SELECT id, user_id userId, course_id courseId " +
                "FROM selected WHERE course_id=?";
        return getMapForList(sql, courseId);
    }

    @Override
    public List<Map<String, Object>> getSelectedListByUserId(int userId) {
        String sql = "SELECT id, user_id userId, course_id courseId " +
                "FROM selected WHERE user_id=?";
        return getMapForList(sql, userId);
    }


}
