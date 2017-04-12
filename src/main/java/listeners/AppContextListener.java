package listeners;

import database.DBConnection;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.sql.Connection;
import java.sql.SQLException;

@WebListener
public class AppContextListener implements ServletContextListener {
    public void contextInitialized(ServletContextEvent servletContextEvent){
        ServletContext servletContext = servletContextEvent.getServletContext();

        String dbURL = servletContext.getInitParameter("jdbcURL");
        String user = servletContext.getInitParameter("jdbcUsername");
        String password = servletContext.getInitParameter("jdbcPassword");

        try{
            DBConnection connection = new DBConnection(dbURL, user, password);
            servletContext.setAttribute("DBConnection", connection.getConnection());
            System.out.println("DB Connection initialized successfully");
        } catch (ClassNotFoundException e){
            e.printStackTrace();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        Connection connection = (Connection) servletContextEvent.getServletContext().getAttribute("DBConnection");
        try{
            connection.close();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }
}
