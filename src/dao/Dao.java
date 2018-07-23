package dao;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import util.JDBCUtil;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class Dao<T> {

    private QueryRunner queryRunner = new QueryRunner();

    private Class<T> clazz;

    public Dao(){
        Type superClass = getClass().getGenericSuperclass();

        if (superClass instanceof ParameterizedType) {
            ParameterizedType parameterizedType = (ParameterizedType) superClass;

            Type[] types = parameterizedType.getActualTypeArguments();
            if (types != null && types.length > 0
                    && types[0] instanceof Class){
                clazz = (Class<T>) types[0];
            }
        }
    }

    /**
     * return one object of T by using SELECT
     * @param sql
     * @param params
     * @return T
     */
    public T get(String sql, Object... params) {
        Connection connection = null;
        try {
            connection = JDBCUtil.getConnection();
            return queryRunner.query(connection, sql, new BeanHandler<>(clazz), params);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            assert connection != null;
            JDBCUtil.releaseConnection(connection);
        }
        return null;
    }

    /**
     * return a list of object of T by using SELECT
     * @param sql
     * @param params
     * @return List<T>
     */
    public List<T> getForList(String sql, Object... params) {
        Connection connection = null;
        try {
            connection = JDBCUtil.getConnection();
            return queryRunner.query(connection, sql, new BeanListHandler<>(clazz), params);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            assert connection != null;
            JDBCUtil.releaseConnection(connection);
        }
        return null;
    }

    /**
     * return a list of key-value
     * @param sql
     * @param params
     * @return
     */
    public List<Map<String, Object>> getMapForList(String sql, Object... params){
        Connection connection = null;
        try {
            connection = JDBCUtil.getConnection();
            return queryRunner.query(connection, sql, new MapListHandler(), params);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            assert connection != null;
            JDBCUtil.releaseConnection(connection);
        }
        return null;
    }

    /**
     * return value of T
     * @param sql
     * @param params
     * @param <E>
     * @return E
     */
    public <E> E getForValue(String sql, Object... params){
        Connection connection = null;
        try {
            connection = JDBCUtil.getConnection();
            return queryRunner.query(connection, sql, new ScalarHandler<>(), params);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            assert connection != null;
            JDBCUtil.releaseConnection(connection);
        }
        return null;
    }

    /**
     * update database by using INSERT, UPDATE or DELETE
     * @param sql
     * @param params
     */
    public void update(String sql, Object... params){
        Connection connection = null;
        try {
            connection = JDBCUtil.getConnection();
            queryRunner.update(connection, sql, params);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            assert connection != null;
            JDBCUtil.releaseConnection(connection);
        }
    }


}
