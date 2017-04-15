package controller;

import database.DBConnection;
import listeners.AppContextListener;
import model.Book;
import org.junit.Before;
import org.junit.Test;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.sql.Connection;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ControllerServletTest {

    private HttpServletRequest request;
    private HttpServletResponse response;
    private RequestDispatcher requestDispatcher;
    private ServletContext servletContext;
    private Book book;
    private DBConnection dbConnection;
    private ControllerServlet controllerServlet;

    @Before
    public void setUp() throws Exception {
        this.request = mock(HttpServletRequest.class);
        this.response = mock(HttpServletResponse.class);
        this.requestDispatcher = mock(RequestDispatcher.class);
        this.servletContext = mock(ServletContext.class);
        this.dbConnection = new DBConnection("jdbc:mysql://localhost:3306/library", "root", "1111");
        book = new Book(1, "Title", "Author", "Genre");
        when(request.getParameter("id")).thenReturn("1");
        when(request.getParameter("title")).thenReturn("Title");
        when(request.getParameter("author")).thenReturn("Author");
        when(request.getParameter("genre")).thenReturn("Genre");
    }

    @Test
    public void doGet() throws Exception {

    }

    @Test
    public void doPost() throws Exception {

    }

    @Test
    public void getBook() throws Exception {

    }
}