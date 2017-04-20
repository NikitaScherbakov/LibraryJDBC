package utils;

import model.Book;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface BookRepo {
    /**
     * Perform the transaction with database to insert new book in it
     * @param connection - connection to database
     * @param book - inserted book
     * @throws SQLException - if an input or output error is detected
     */
    void insertBook(Connection connection, Book book) throws SQLException;

    /**
     * Perform the transaction with database to show all books in it
     * @param connection - connection to database
     * @return - list of books
     * @throws SQLException - if an input or output error is detected
     */
    List<Book> listAllBooks(Connection connection) throws SQLException;

    /**
     * Perform the transaction with database to delete book in it by id
     * @param connection - connection to database
     * @param book - deleted book
     * @throws SQLException - if an input or output error is detected
     */
    void deleteBook(Connection connection, Book book) throws SQLException;

    /**
     * Perform the transaction with database to update information about book in it
     * @param connection - connection to database
     * @param book - updated book
     * @throws SQLException - if an input or output error is detected
     */
    void updateBook(Connection connection, Book book) throws SQLException;

    /**
     * Perform the transaction with database to get the information about the definite book from it
     * @param connection - connection to database
     * @param id - id of got book
     * @return - got book
     * @throws SQLException - if an input or output error is detected
     */
    Book getBook(Connection connection, int id) throws SQLException;
}
