package dao.impl;

import dao.Dao;
import dao.UserDao;
import model.User;

import java.util.List;

public class UserDaoImpl extends Dao<User> implements UserDao {
    @Override
    public List<User> getAll() {
        String sql = "SELECT id, username, password FROM users";
        return getForList(sql);
    }

    @Override
    public User getUserById(int id) {
        String sql = "SELECT id, username, password FROM users WHERE id=?";
        return get(sql, id);
    }

    @Override
    public User getUserByName(String username) {
        String sql = "SELECT id, username, password FROM users WHERE username=?";
        return get(sql, username);
    }

    @Override
    public void update(User user) {
        String sql = "UPDATE users SET username=?, password=? WHERE id=?";
        update(sql, user.getUsername(), user.getPassword(), user.getId());
    }

    @Override
    public void save(User user) {
        String sql = "INSERT INTO users (username, password)" +
                "VALUES (?, ?)";
        update(sql, user.getUsername(), user.getPassword());
    }

    @Override
    public void deleteUserById(int id) {
        String sql = "DELETE FROM users WHERE id=?";
        update(sql, id);
    }
}
