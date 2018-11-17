package dao;

import model.Answer;

import java.util.List;

public interface AnswerDao {

    List<Answer> getAnswerListByHomeworkId(int homeworkId);

    Answer getAnswerByUserIdAndHomeworkId(int userId, int homeworkId);

    List<Answer> getAnswerListByUserId(int userId);

    Answer getAnswerById(int id);

    void save(Answer answer);

    void update(Answer answer);
    void updateScore(Answer answer);

    void deleteAnswerById(int id);

}
