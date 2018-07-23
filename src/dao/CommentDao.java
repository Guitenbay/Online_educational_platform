package dao;

import model.Comment;

import java.util.List;

public interface CommentDao {

    List<Comment> getCommentListByCourseId(int courseId);

    List<Comment> getCommentListByUserId(int userId);

    Comment getCommentById(int id);

    void save(Comment comment);

    void update(Comment comment);

    void deleteCommentById(int id);

}
