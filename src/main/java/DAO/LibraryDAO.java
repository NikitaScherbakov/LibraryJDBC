package DAO;

import model.Library;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LibraryDAO {
    private String jdbcURL;
    private String jdbcUsername;
    private String jdbcPassword;
    private Connection jdbcConnection;

    public LibraryDAO(String jdbcURL, String jdbcUsername, String jdbcPassword) {
        this.jdbcURL = jdbcURL;
        this.jdbcUsername = jdbcUsername;
        this.jdbcPassword = jdbcPassword;
    }

    private void connect() throws SQLException{
        if(jdbcConnection == null || jdbcConnection.isClosed()){
            try{
                Class.forName("com.mysql.jdbc.Driver");
            } catch (ClassNotFoundException e){
                throw new SQLException(e);
            }
            jdbcConnection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        }
    }
    private void disconnect() throws SQLException{
        if(jdbcConnection != null && !jdbcConnection.isClosed()){
            jdbcConnection.close();
        }
    }

    public boolean insertBook(Library library) throws SQLException{
        String sql = "INSERT INTO library (title, author, in_store) VALUES (?, ?, ?)";
        connect();

        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setString(1, library.getTitle());
        statement.setString(2,library.getAuthor());
        statement.setString(3,library.getInStore());
        boolean rowInserted = statement.executeUpdate() > 0;
        statement.close();
        return rowInserted;
    }

    public List<Library> listAllBooks() throws SQLException{
        List<Library> listBooks = new ArrayList<Library>();
        String sql = "SELECT * FROM library";
        connect();

        Statement statement = jdbcConnection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);

        while(resultSet.next()){
            int id = resultSet.getInt("id");
            String title = resultSet.getString("title");
            String author = resultSet.getString("author");
            String inStore = resultSet.getString("in_store");

            Library library = new Library(id, title, author, inStore);
            listBooks.add(library);
        }

        resultSet.close();
        statement.close();

        disconnect();
        return listBooks;
    }

    public boolean deleteBook(Library library) throws SQLException{
        String sql = "DELETE FROM library where id = ?";
        connect();

        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setInt(1, library.getId());

        boolean rowDeleted = statement.executeUpdate() > 0;
        statement.close();
        disconnect();
        return rowDeleted;
    }

    public boolean updateBook(Library library) throws SQLException{
        String sql = "UPDATE book SET title = ?, author = ?, in_store = ?";
        sql += "WHERE id = ?";
        connect();

        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setString(1, library.getTitle());
        statement.setString(2,library.getAuthor());
        statement.setString(3,library.getInStore());
        statement.setInt(4,library.getId());

        boolean rowUpdated = statement.executeUpdate() > 0;
        statement.close();
        disconnect();
        return rowUpdated;
    }

    public Library getLibrary(int id) throws SQLException{
        Library library = null;
        String sql = "SELECT * FROM library WHERE id = ?";
        connect();

        PreparedStatement statement = jdbcConnection.prepareStatement(sql);
        statement.setInt(1, id);

        ResultSet resultSet = statement.executeQuery();

        if(resultSet.next()){
            String title = resultSet.getString("title");
            String author = resultSet.getString("author");
            String inStore = resultSet.getString("in_store");

            library = new Library(id, title, author, inStore);
        }

        resultSet.close();
        statement.close();

        return library;
    }
}
