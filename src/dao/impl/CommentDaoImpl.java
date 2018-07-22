package dao.impl;

import dao.CommentDao;
import dao.Dao;
import model.Comment;

import java.util.List;

public class CommentDaoImpl extends Dao<Comment> implements CommentDao {
    @Override
    public List<Comment> getCommentListByCourseId(int courseId) {
        return null;
    }

    @Override
    public List<Comment> getCommentListByUserId(int userId) {
        return null;
    }

    @Override
    public Comment getCommentById(int id) {
        return null;
    }

    @Override
    public void save(Comment comment) {

    }

    @Override
    public void update(Comment comment) {

    }

    @Override
    public void deleteCommentById(int id) {

    }
}
