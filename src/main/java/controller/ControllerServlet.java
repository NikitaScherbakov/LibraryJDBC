package controller;

import model.Book;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class ControllerServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public void init() {
        String jdbcURL = getServletContext().getInitParameter("jdbcURL");
        String jdbcUsername = getServletContext().getInitParameter("jdbcUsername");
        String jdbcPassword = getServletContext().getInitParameter("jdbcPassword");

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getServletPath();
        //TODO: fix too many if
        try {
            if (action.equals("/new")) {
                showNewForm(request, response);

            } else if (action.equals("/insert")) {
                insertBook(request, response);

            } else if (action.equals("/delete")) {
                deleteBook(request, response);

            } else if (action.equals("/edit")) {
                showEditForm(request, response);

            } else if (action.equals("/update")) {
                updateBook(request, response);

            } else {
                listBook(request, response);

            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    private void listBook(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        List<Book> listBook = new ArrayList<>();
        String sql = "SELECT * FROM book";

        Connection connection = (Connection)getServletContext().getAttribute("DBConnection");
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

        request.setAttribute("listBook", listBook);
        RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
        dispatcher.forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("bookForm.jsp");
        dispatcher.forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Book existingBook = getBook(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("bookForm.jsp");
        request.setAttribute("book", existingBook);
        dispatcher.forward(request, response);

    }

    private void insertBook(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        String title = request.getParameter("title");
        String author = request.getParameter("author");
        String genre = request.getParameter("genre");

        Book newBook = new Book(title, author, genre);

        String sql = "INSERT INTO book (book_title, book_author, book_genre) VALUES (?, ?, ?)";
        Connection connection = (Connection)getServletContext().getAttribute("DBConnection");

        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, newBook.getTitle());
        statement.setString(2, newBook.getAuthor());
        statement.setString(3, newBook.getGenre());

        //TODO: Look and refactor
        /*boolean rowInserted = */statement.executeUpdate() /*> 0*/;
        //if(rowInserted){
            statement.close();
            response.sendRedirect("list");
        //} else response.sendRedirect("Error.jsp");
    }

    private void updateBook(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String title = request.getParameter("title");
        String author = request.getParameter("author");
        String genre = request.getParameter("genre");

        Book book = new Book(id, title, author, genre);

        String sql = "UPDATE book SET book_title = ?, book_author = ?, book_genre = ?";
        sql += " WHERE book_id = ?";
        Connection connection = (Connection)getServletContext().getAttribute("DBConnection");
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, book.getTitle());
        statement.setString(2, book.getAuthor());
        statement.setString(3, book.getGenre());
        statement.setInt(4, book.getId());

        statement.executeUpdate();
        statement.close();
        response.sendRedirect("list");
    }

    private void deleteBook(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));

        Book book = new Book(id);
        Connection connection = (Connection)getServletContext().getAttribute("DBConnection");
        String sql = "DELETE FROM book where book_id = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setInt(1, book.getId());

        statement.executeUpdate();
        statement.close();

        response.sendRedirect("list");
    }

    public Book getBook(int id) throws SQLException {
        Book book = null;
        String sql = "SELECT * FROM book WHERE book_id = ?";

        Connection connection = (Connection)getServletContext().getAttribute("DBConnection");
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
