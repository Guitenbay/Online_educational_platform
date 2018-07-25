package dao;

import model.Answer;

import java.util.List;

public interface AnswerDao {

    List<Answer> getAnswerListByHomeworkId(int homeworkId);

    List<Answer> getAnswerListByUserId(int userId);

    Answer getAnswerById(int id);

    void save(Answer answer);

    void update(Answer answer);

    void deleteAnswerById(int id);

}
