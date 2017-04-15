package database;

import java.sql.Connection;
import java.sql.Statement;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;
import static org.mockito.Mockito.mock;

public class DBConnectionTest{

    @Test
    public void testMockDBConnection() throws Exception {
        DBConnection mockDBConnection = new DBConnection("jdbc:mysql://localhost:3306/library", "root", "1111");
        mock(DBConnection.class);
        Connection connection = mock(Connection.class);
        Statement statement = mock(Statement.class);
        Mockito.when(connection.createStatement()).thenReturn(statement);
        Mockito.when(connection.createStatement().executeUpdate((String) Mockito.any())).thenReturn(1);

        Assert.assertNotNull(mockDBConnection);
        Mockito.verify(connection.createStatement(), Mockito.times(1));
    }
}