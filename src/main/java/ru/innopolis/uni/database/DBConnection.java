package ru.innopolis.uni.database;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *Created by Igor Ryabtsev on 28.12.2016.
 * Класс для получения соединения с базой данных
 */
public class DBConnection {
    private static Logger log = LoggerFactory.getLogger(DBConnection.class);
    public static final String DATABASE_URL = "jdbc:mysql://localhost:3306/online_shop?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private static Connection conn;

    private DBConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection(
                    DATABASE_URL, "root",
                    "Sparta1991");
        } catch (ClassNotFoundException e) {
            log.warn(e.getMessage());
        } catch (SQLException e) {
            log.warn(e.getMessage());
        }
    }

    public static Connection getConnecton() {
        if (conn != null) {
            return conn;
        } else {
            return new DBConnection().conn;
        }
    }

    public void close() throws SQLException {
        if (conn != null && !conn.isClosed()) {
            conn.close();
            conn = null;
        }
    }
}
