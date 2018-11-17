package dao.impl;

import dao.Dao;
import dao.SelectedDao;

import java.util.List;
import java.util.Map;

public class SelectedDaoImpl extends Dao implements SelectedDao {
    @Override
    public int getUserNumberByCourse(int courseId) {
        String sql = "SELECT COUNT(userId) AS user_number FROM selected " +
                "WHERE courseId=?";
        return ((Long) getForValue(sql, courseId)).intValue();
    }

    @Override
    public List<Map<String, Object>> getSelectedListByCourseId(int courseId) {
        String sql = "SELECT id, userId, courseId " +
                "FROM selected WHERE courseId=?";
        return getMapForList(sql, courseId);
    }

    @Override
    public List<Map<String, Object>> getSelectedListByUserId(int userId) {
        String sql = "SELECT id, userId, courseId " +
                "FROM selected WHERE userId=?";
        return getMapForList(sql, userId);
    }

    @Override
    public Map<String, Object> getSelectedByUserIdAndCourseId(int userId, int courseId) {
        String sql = "SELECT id, userId, courseId " +
                "FROM selected WHERE userId=? AND courseId=?";
        return getMap(sql, userId, courseId);
    }

    @Override
    public void save(int userId, int courseId) {
        String sql = "INSERT INTO selected (userId, courseId)" +
                "VALUES (?, ?)";
        update(sql, userId, courseId);
    }

    @Override
    public void delete(int userId, int courseId) {
        String sql = "DELETE FROM selected WHERE userId=? AND courseId=?";
        update(sql, userId, courseId);
    }

}
