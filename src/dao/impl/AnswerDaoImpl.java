package dao.impl;

import dao.AnswerDao;
import dao.Dao;
import model.Answer;

import java.util.List;

public class AnswerDaoImpl extends Dao<Answer> implements AnswerDao {
    @Override
    public List<Answer> getAnswerListByHomeworkId(int homeworkId) {
        return null;
    }

    @Override
    public List<Answer> getAnswerListByUserId(int userId) {
        return null;
    }

    @Override
    public Answer getAnswerById(int id) {
        return null;
    }

    @Override
    public void save(Answer answer) {

    }

    @Override
    public void update(Answer answer) {

    }

    @Override
    public void deleteAnswerById(int id) {

    }
}
