package util;

import javax.sql.DataSource;
import org.apache.commons.dbcp2.BasicDataSource;
import java.sql.*;

public class Conexion {

    private static final String JDBC_URL = "jdbc:postgresql://ec2-52-202-66-191.compute-1.amazonaws.com:5432/d7lglc35csn7ko";
    private static final String JDBC_USER = "ckjqpoqkfzcjbu";
    private static final String JDBC_PASS = "0a1a80f5ec8f41d375bbdf011068f3ec9b6b0892ab8ae474e150fb6bc717b1cf";
    private static BasicDataSource ds;

    private static DataSource getDataSource() {
        if (ds == null) {
            ds = new BasicDataSource();
            ds.setUrl(JDBC_URL);
            ds.setUsername(JDBC_USER);
            ds.setPassword(JDBC_PASS);
            ds.setInitialSize(1);
            ds.setMinIdle(2);
            ds.setMaxIdle(2);
            ds.setMaxTotal(3);
            ds.setMaxWaitMillis(5000);
        }
        return ds;
    }

    public static Connection getConnection() throws SQLException {
        Connection conn = getDataSource().getConnection();
        if (conn.getAutoCommit()) {
            conn.setAutoCommit(false);
        }
        return conn;
    }

    public static void close(Connection conn) {
        try {
            conn.close();
        } catch (SQLException | NullPointerException ex) {
            ex.printStackTrace(System.out);
        }
    }

    public static void close(PreparedStatement stmt) {
        try {
            stmt.close();
        } catch (SQLException | NullPointerException ex) {
            ex.printStackTrace(System.out);
        }
    }

    public static void close(ResultSet rs) {
        try {
            rs.close();
        } catch (SQLException | NullPointerException ex) {
            ex.printStackTrace(System.out);
        }
    }

}
