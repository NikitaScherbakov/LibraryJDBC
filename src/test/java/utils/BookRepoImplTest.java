package utils;

import database.DBConnection;
import model.Book;
import org.junit.*;
import java.sql.Connection;
import java.util.List;

import static org.junit.Assert.*;

public class BookRepoImplTest {

    private Connection connection;
    private BookRepo bookRepo;
    private Book book1;

    @Before
    public void setUp() throws Exception{
        DBConnection dbConnection = new DBConnection("jdbc:mysql://localhost:3306/library", "root", "1111");
        connection = dbConnection.getConnection();
        connection.setAutoCommit(false);
        bookRepo = new BookRepoImpl();
        book1 = new Book();
        book1.setTitle("title1");
        book1.setAuthor("author1");
        book1.setGenre("genre1");
    }

    @Test
    public void testAdd() throws Exception{
        bookRepo.insertBook(connection, book1);
        List<Book> books = bookRepo.listAllBooks(connection);
        assertEquals(book1.getTitle(), books.get(3).getTitle());
        connection.rollback();
        connection.close();
    }

    @Test
    public void testGetAll() throws Exception{
        List<Book> books = bookRepo.listAllBooks(connection);
        assertEquals(3, books.size());
    }

    @Test
    public void getBook() throws Exception{
        bookRepo.insertBook(connection, book1);
        List<Book> books = bookRepo.listAllBooks(connection);
        assertEquals(4, books.size());
        int id = books.get(3).getId();
        book1 = bookRepo.getBook(connection, id);
        assertEquals(book1.getTitle(), books.get(3).getTitle());
        connection.rollback();
        connection.close();
    }

    @Test
    public void testDelete() throws Exception{
        bookRepo.insertBook(connection, book1);
        List<Book> books = bookRepo.listAllBooks(connection);
        assertEquals(4, books.size());
        int id = books.get(3).getId();
        book1 = bookRepo.getBook(connection, id);
        assertNotNull(book1);
        bookRepo.deleteBook(connection, book1);
        assertNull(bookRepo.getBook(connection, id));
        connection.close();
    }

    @Test
    public void testUpdate() throws Exception{
        bookRepo.insertBook(connection, book1);
        List<Book> books = bookRepo.listAllBooks(connection);
        int id = books.get(3).getId();
        book1 = bookRepo.getBook(connection, id);
        book1.setTitle("title2");
        bookRepo.updateBook(connection, book1);
        book1 = bookRepo.getBook(connection, id);
        assertEquals(book1.getTitle(), "title2");
        connection.rollback();
        connection.close();
    }
}
