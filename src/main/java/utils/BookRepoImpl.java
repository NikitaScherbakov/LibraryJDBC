package utils;

import model.Book;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BookRepoImpl implements BookRepo {

    public void insertBook(Connection connection, Book book) throws SQLException {
        String sql = "INSERT INTO book (book_title, book_author, book_genre) VALUES (?, ?, ?)";

        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, book.getTitle());
        statement.setString(2, book.getAuthor());
        statement.setString(3, book.getGenre());

        statement.executeUpdate();
        statement.close();
    }

    public List<Book> listAllBooks(Connection connection) throws SQLException {
        List<Book> listBook = new ArrayList<>();

        String sql = "SELECT * FROM book";



        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);

        while (resultSet.next()) {
            int id = resultSet.getInt("book_id");
            String title = resultSet.getString("book_title");
            String author = resultSet.getString("book_author");
            String genre = resultSet.getString("book_genre");

            Book book = new Book(id, title, author, genre);
            listBook.add(book);
        }

        resultSet.close();
        statement.close();

        return listBook;
    }

    public void deleteBook(Connection connection, Book book) throws SQLException {
        String sql = "DELETE FROM book where book_id = ?";


        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, book.getId());

        statement.executeUpdate();
        statement.close();

    }

    public void updateBook(Connection connection, Book book) throws SQLException {
        String sql = "UPDATE book SET book_title = ?, book_author = ?, book_genre = ?";
        sql += " WHERE book_id = ?";

        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, book.getTitle());
        statement.setString(2, book.getAuthor());
        statement.setString(3, book.getGenre());
        statement.setInt(4, book.getId());

        statement.executeUpdate();
        statement.close();

    }

    public Book getBook(Connection connection, int id) throws SQLException {
        Book book = null;
        String sql = "SELECT * FROM book WHERE book_id = ?";

        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, id);

        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            String title = resultSet.getString("book_title");
            String author = resultSet.getString("book_author");
            String genre = resultSet.getString("book_genre");

            book = new Book(id, title, author, genre);
        }

        resultSet.close();
        statement.close();

        return book;
    }
}
