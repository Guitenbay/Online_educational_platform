package dao.impl;

import dao.AnswerDao;
import dao.Dao;
import model.Answer;

import java.util.List;

public class AnswerDaoImpl extends Dao<Answer> implements AnswerDao {
    @Override
    public List<Answer> getAnswerListByHomeworkId(int homeworkId) {
        String sql = "SELECT id, context, score, userId, homeworkId, " +
                "updateTime FROM answers WHERE homeworkId=?";
        return getForList(sql, homeworkId);
    }

    @Override
    public List<Answer> getAnswerListByUserId(int userId) {
        String sql = "SELECT id, context, score, userId, homeworkId, " +
                "updateTime FROM answers WHERE userId=?";
        return getForList(sql, userId);
    }

    @Override
    public Answer getAnswerByUserIdAndHomeworkId(int userId,int homeworkId) {
        String sql = "SELECT id, context, score, userId, homeworkId, " +
                "updateTime FROM answers WHERE userId=? and homeworkId=?";
        return get(sql, userId,homeworkId);
    }

    @Override
    public Answer getAnswerById(int id) {
        String sql = "SELECT id, context, score, userId, homeworkId, " +
                "updateTime updateTime FROM answers WHERE id=?";
        return get(sql, id);
    }

    @Override
    public void save(Answer answer) {
        String sql = "INSERT INTO answers (context, score, userId, homeworkId, updateTime) " +
                "VALUES (?, ?, ?, ?, ?)";
        update(sql, answer.getContext(), answer.getScore(),
                answer.getUserId(), answer.getHomeworkId(), answer.getUpdateTime());
    }

    @Override
    public void update(Answer answer) {
        String sql = "UPDATE answers SET context=?, score=?, updateTime=? WHERE id=?";
        update(sql, answer.getContext(), answer.getScore(), answer.getUpdateTime(),
                answer.getId());
    }
    @Override
    public void updateScore(Answer answer) {
        String sql = "UPDATE answers SET score=? WHERE id=?";
        update(sql, answer.getScore(), answer.getId());
    }

    @Override
    public void deleteAnswerById(int id) {
        String sql = "DELETE FROM answers WHERE id=?";
        update(sql, id);
    }
}
