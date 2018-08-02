package dao;

import model.User;

import java.util.List;

public interface UserDao {

    /**
     * get all users
     * @return
     */
    List<User> getAll();

    /**
     * get user by using id
     * @param id
     * @return
     */
    User getUserById(int id);

    /**
     * get user by using username
     * @param username
     * @return
     */
    User getUserByName(String username);

    /**
     * get userId by username
     * @param username
     * @return
     */
    int getUserIdByName(String username);

    /**
     * update user
     * @param user
     */
    void update(User user);

    /**
     * save new User
     * @param user
     */
    void save(User user);

    /**
     * delete user by using id
     * @param id
     */
    void deleteUserById(int id);

}
