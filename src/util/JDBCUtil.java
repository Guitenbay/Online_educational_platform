package util;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class JDBCUtil {

    private static DataSource dataSource = null;

    static{
        dataSource = new ComboPooledDataSource("online-course-c3p0");
    }

    public static Connection getConnection() throws SQLException {
//        try {
//            Connection connection=dataSource.getConnection();
//        }
        return dataSource.getConnection();
    }

    public static void releaseConnection(Connection connection) {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
