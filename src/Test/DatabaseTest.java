package Test;

import DB.Database;
import org.junit.jupiter.api.*;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DatabaseTest {

    private static final String MOCK_DB_NAME = "testDB";
    private static final String MOCK_DB_USERNAME = "testUser";
    private static final String MOCK_DB_PASSWORD = "testPassword";

    @Test
    void testDatabase() {
        try (MockedStatic<Class> classMock = Mockito.mockStatic(Class.class);
             MockedStatic<DriverManager> driverManagerMock = Mockito.mockStatic(DriverManager.class)) {

            Connection mockConnection = mock(Connection.class);
            Statement mockStatement = mock(Statement.class);

            driverManagerMock.when(() -> DriverManager.getConnection(
                            "jdbc:mysql://localhost:3306/" + MOCK_DB_NAME + "?useSSL=false&serverTimezone=UTC",
                            MOCK_DB_USERNAME, MOCK_DB_PASSWORD))
                    .thenReturn(mockConnection);

            when(mockConnection.createStatement()).thenReturn(mockStatement);

            Database db = new Database();

            classMock.verify(() -> Class.forName("com.mysql.cj.jdbc.Driver"), times(1));
            driverManagerMock.verify(() -> DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/" + MOCK_DB_NAME + "?useSSL=false&serverTimezone=UTC",
                    MOCK_DB_USERNAME, MOCK_DB_PASSWORD), times(1));

            assertNotNull(db);
            assertNotNull(db.getStatement());
            System.out.println("Test case: Successful connection - Passed!");

        } catch (Exception e) {
            fail("Exception during successful connection test: " + e.getMessage());
        }
    }

    @Test
    void testSQLException() {
        try (MockedStatic<Class> classMock = Mockito.mockStatic(Class.class);
             MockedStatic<DriverManager> driverManagerMock = Mockito.mockStatic(DriverManager.class)) {

            driverManagerMock.when(() -> DriverManager.getConnection(anyString(), anyString(), anyString()))
                    .thenThrow(new SQLException("Mock SQL Exception"));

            Database db = new Database();

            classMock.verify(() -> Class.forName("com.mysql.cj.jdbc.Driver"), times(1));
            driverManagerMock.verify(() -> DriverManager.getConnection(anyString(), anyString(), anyString()), times(1));

            assertNull(db.getStatement());
            System.out.println("Test case: SQLException - Passed!");

        } catch (Exception e) {
            fail("Exception during SQLException test: " + e.getMessage());
        }
    }

    @Test
    void testClassNotFoundException() {
        try (MockedStatic<Class> classMock = Mockito.mockStatic(Class.class)) {

            classMock.when(() -> Class.forName("com.mysql.cj.jdbc.Driver"))
                    .thenThrow(new ClassNotFoundException("Mock ClassNotFoundException"));

            Database db = new Database();

            classMock.verify(() -> Class.forName("com.mysql.cj.jdbc.Driver"), times(1));

            assertNull(db.getStatement());
            System.out.println("Test case: ClassNotFoundException - Passed!");

        } catch (Exception e) {
            fail("Exception during ClassNotFoundException test: " + e.getMessage());
        }
    }

    @Test
    void testNullConnection() {
        try (MockedStatic<Class> classMock = Mockito.mockStatic(Class.class);
             MockedStatic<DriverManager> driverManagerMock = Mockito.mockStatic(DriverManager.class)) {

            driverManagerMock.when(() -> DriverManager.getConnection(anyString(), anyString(), anyString()))
                    .thenReturn(null);

            Database db = new Database();

            classMock.verify(() -> Class.forName("com.mysql.cj.jdbc.Driver"), times(1));
            driverManagerMock.verify(() -> DriverManager.getConnection(anyString(), anyString(), anyString()), times(1));

            assertNull(db.getStatement());
            System.out.println("Test case: Null connection - Passed!");

        } catch (Exception e) {
            fail("Exception during null connection test: " + e.getMessage());
        }
    }

    //Insert METHOD

    @Test
    void testEmptyMap() {
        Map<String, String> data = new HashMap<>();
        Database db = new Database();
        assertThrows(StringIndexOutOfBoundsException.class, () -> db.insert(data));
    }
    @Test
    void testSingleKeyValuePair() {
        Map<String, String> data = new HashMap<>();
        data.put("name", "John");
        Database db = new Database();
        db.insert(data);
        String expectedQuery = "INSERT INTO`" + db.getTableName() + "`(`name`) VALUES ('John')";
        assertEquals(expectedQuery, db.getLastQuery());
    }

    @Test
    void testMultipleKeyValuePairs() {
        Map<String, String> data = new HashMap<>();
        data.put("name", "John");
        data.put("age", "30");
        Database db = new Database();

        db.insert(data);

        String expectedQuery = "INSERT INTO `" + db.getTableName() + "`(`name`,`age`) VALUES ('John','30')";
        assertEquals(expectedQuery, db.getLastQuery());
    }

    @Test
    void testSpecialCharactersInValues() {
        Map<String, String> data = new HashMap<>();
        data.put("name", "O'Reilly");
        data.put("path", "C:\\Users\\Test");
        Database db = new Database();

        db.insert(data);

        String expectedQuery = "INSERT INTO `" + db.getTableName() + "`(`path`,`name`) VALUES ('C:UsersTest','O''Reilly')";
        assertEquals(expectedQuery, db.getLastQuery());
    }

    @Test
    void testNullInput() {
        Database db = new Database();
        assertThrows(NullPointerException.class, () -> db.insert(null));
    }

    //Update METHOD

    @Test
    void testValidLowerBoundaryId() {
        Map<String, String> data = new HashMap<>();
        data.put("name", "John");
        Database db = new Database();

        db.update(data, 1);

        String expectedQuery = "UPDATE `" + db.getTableName() + "` SET `name` = 'John' WHERE `id` = 1;";
        assertEquals(expectedQuery, db.getLastQuery());
    }

    @Test
    void testValidUpperBoundaryId() {
        Map<String, String> data = new HashMap<>();
        data.put("name", "John");
        Database db = new Database();

        db.update(data, Integer.MAX_VALUE);

        String expectedQuery = "UPDATE `" + db.getTableName() + "` SET `name` = 'John' WHERE `id` = " + Integer.MAX_VALUE + ";";
        assertEquals(expectedQuery, db.getLastQuery());
    }

    @Test
    void testInvalidBelowBoundaryId() {
        Map<String, String> data = new HashMap<>();
        data.put("name", "John");
        Database db = new Database();

        db.update(data, -1); // id = -1

        String expectedQuery = "UPDATE `" + db.getTableName() + "` SET `name` = 'John' WHERE `id` = -1;";
        assertEquals(expectedQuery, db.getLastQuery());
    }

    @Test
    void testInvalidAboveBoundaryId() {
        Map<String, String> data = new HashMap<>();
        data.put("name", "John");
        Database db = new Database();

        db.update(data, Integer.MAX_VALUE + 1);

        String expectedQuery = "UPDATE `" + db.getTableName() + "` SET `name` = 'John' WHERE `id` = " + (Integer.MAX_VALUE + 1) + ";";
        assertEquals(expectedQuery, db.getLastQuery());
    }




}
