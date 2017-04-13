package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private Connection connection;

    /** Constructor with parameters for database connection with given URL, username, password
     *
     * @param dbURL - URL of the database
     * @param user - username for mysql database
     * @param password - password for mysql database
     * @throws ClassNotFoundException - if the com.mysql.jdbc.Driver class not found
     * @throws SQLException - if connection to database is failed.
     */
    public DBConnection(String dbURL, String user, String password)
        throws ClassNotFoundException, SQLException{
        Class.forName("com.mysql.jdbc.Driver");
        this.connection = DriverManager.getConnection(dbURL, user, password);
    }

    /** Establishes a database connection
     *
     * @return - the database connection
     */
    public Connection getConnection(){
        return this.connection;
    }
}
