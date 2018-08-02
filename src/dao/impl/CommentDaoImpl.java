package dao.impl;

import dao.CommentDao;
import dao.Dao;
import model.Comment;

import java.util.List;

public class CommentDaoImpl extends Dao<Comment> implements CommentDao {
    @Override
    public List<Comment> getCommentListByCourseId(int courseId) {
        String sql = "SELECT id, title, context, userId, courseId " +
                "FROM comments WHERE courseId=?";
        return getForList(sql, courseId);
    }

    @Override
    public List<Comment> getCommentListByUserId(int userId) {
        String sql = "SELECT id, title, context, userId, courseId " +
                "FROM comments WHERE userId=?";
        return getForList(sql, userId);
    }

    @Override
    public Comment getCommentById(int id) {
        String sql = "SELECT id, title, context, userId, courseId " +
                "FROM comments WHERE id=?";
        return get(sql, id);
    }

    @Override
    public void save(Comment comment) {
        String sql = "INSERT INTO comments (title, context, userId, courseId)" +
                " VALUES (?, ?, ?, ?)";
        update(sql, comment.getTitle(), comment.getContext(), comment.getUserId(),
                comment.getCourseId());
    }

    @Override
    public void update(Comment comment) {
        String sql = "UPDATE comments SET title=?, context=?, user_id=?, courseId=? " +
                "WHERE id=?";
        update(sql, comment.getTitle(), comment.getContext(), comment.getUserId(),
                comment.getCourseId(), comment.getId());
    }

    @Override
    public void deleteCommentById(int id) {
        String sql = "DELETE FROM comments WHERE id=?";
        update(sql, id);
    }
}
