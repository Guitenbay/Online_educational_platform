package dao.impl;

import dao.AnswerDao;
import dao.Dao;
import model.Answer;

import java.util.List;

public class AnswerDaoImpl extends Dao<Answer> implements AnswerDao {
    @Override
    public List<Answer> getAnswerListByHomeworkId(int homeworkId) {
        String sql = "SELECT id, context, score, user_id userId, homework_id homeworkId, " +
                "update_time updateTime FROM answers WHERE homework_id=?";
        return getForList(sql, homeworkId);
    }

    @Override
    public List<Answer> getAnswerListByUserId(int userId) {
        String sql = "SELECT id, context, score, user_id userId, homework_id homeworkId, " +
                "update_time updateTime FROM answers WHERE user_id=?";
        return getForList(sql, userId);
    }

    @Override
    public Answer getAnswerById(int id) {
        String sql = "SELECT id, context, score, user_id userId, homework_id homeworkId, " +
                "update_time updateTime FROM answers WHERE id=?";
        return get(sql, id);
    }

    @Override
    public void save(Answer answer) {
        String sql = "INSERT INTO answers (context, score, user_id, homework_id, update_time) " +
                "VALUES (?, ?, ?, ?, ?)";
        update(sql, answer.getContext(), answer.getScore(),
                answer.getUserId(), answer.getHomeworkId(), answer.getUpdateTime());
    }

    @Override
    public void update(Answer answer) {
        String sql = "UPDATE answers SET context=?, score=?, update_time=? WHERE id=?";
        update(sql, answer.getContext(), answer.getScore(), answer.getUpdateTime(),
                answer.getId());
    }

    @Override
    public void deleteAnswerById(int id) {
        String sql = "DELETE FROM answers WHERE id=?";
        update(sql, id);
    }
}
