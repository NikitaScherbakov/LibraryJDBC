package servlets;



import model.Book;
import utils.BookRepoImpl;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet("/new")
public class AddBookServlet extends HttpServlet {

    /** Called by the server (via the service method) to allow a servlet to handle a GET request. Chooses what function need to do.
     *
     * @param request - object that contains the request the client has made of the servlet
     * @param response - object that contains the response the servlet sends to the client
     * @throws ServletException - if the request for the GET could not be handled
     * @throws IOException - if an input or output error is detected when the servlet handles the GET request
     */

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("bookForm.jsp");
        request.setAttribute("action", "/new");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException{
        Connection connection = (Connection)getServletContext().getAttribute("DBConnection");
        String title = request.getParameter("title");
        String author = request.getParameter("author");
        String genre = request.getParameter("genre");

        Book newBook = new Book(title, author, genre);
        BookRepoImpl bookRepo = new BookRepoImpl();
        try {
            bookRepo.insertBook(connection, newBook);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        response.sendRedirect("list");
    }
}
