import DB.Database;
import DB.Users;
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
        try (MockedStatic<DriverManager> driverManagerMock = Mockito.mockStatic(DriverManager.class)) {

            Connection mockConnection = mock(Connection.class);
            Statement mockStatement = mock(Statement.class);

            // Mock the DriverManager.getConnection behavior
            driverManagerMock.when(() -> DriverManager.getConnection(
                            "jdbc:mysql://localhost:3306/" + MOCK_DB_NAME + "?useSSL=false&serverTimezone=UTC",
                            MOCK_DB_USERNAME, MOCK_DB_PASSWORD))
                    .thenReturn(mockConnection);

            // Mock the connection to return the statement
            when(mockConnection.createStatement()).thenReturn(mockStatement);

            // Instantiate and test the Database class
            Database db = new Database();

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
        data.put("fullName", "John");
        Database db = new Users();
        db.insert(data);
        String expectedQuery = "INSERT INTO `" + db.getTableName() + "`(`name`) VALUES ('John')";
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

    @Test
    void testValidUpdateSingleField() {
        Database db = new Database();
        Map<String, String> data = new HashMap<>();
        data.put("name", "John");

        int id = 1;
        db.update(data, id);

        String expectedQuery = "UPDATE `" + db.getTableName() + "` SET `name` = 'John' WHERE `id` = 1;";
        assertEquals(expectedQuery, db.getLastQuery(), "Query should match the expected SQL string for a valid update.");
    }

    @Test
    void testValidUpdateMultipleFields() {
        Database db = new Database();
        Map<String, String> data = new HashMap<>();
        data.put("name", "John");
        data.put("email", "john@example.com");

        int id = 42;
        db.update(data, id);

        String expectedQuery = "UPDATE `" + db.getTableName() + "` SET `name` = 'John',`email` = 'john@example.com' WHERE `id` = 42;";
        assertEquals(expectedQuery, db.getLastQuery(), "Query should match the expected SQL string for multiple fields.");
    }

    @Test
    void testEscapingSpecialCharacters() {
        Database db = new Database();
        Map<String, String> data = new HashMap<>();
        data.put("name", "O'Reilly");
        data.put("path", "C:\\Users\\Test");

        int id = 100;
        db.update(data, id);

        String expectedQuery = "UPDATE `" + db.getTableName() + "` SET `name` = 'O''Reilly',`path` = 'C:UsersTest' WHERE `id` = 100;";
        assertEquals(expectedQuery, db.getLastQuery(), "Special characters should be properly escaped in the query.");
    }

    @Test
    void testEmptyDataMap() {
        Database db = new Database();
        Map<String, String> data = new HashMap<>();

        int id = 1;

        Exception exception = assertThrows(IllegalArgumentException.class, () -> db.update(data, id),
                "An empty data map should throw an IllegalArgumentException.");
        assertEquals("Data map cannot be empty", exception.getMessage(), "Error message should match.");
    }

    @Test
    void testNullDataMap() {
        Database db = new Database();

        int id = 1;

        Exception exception = assertThrows(NullPointerException.class, () -> db.update(null, id),
                "A null data map should throw a NullPointerException.");
        assertEquals("Data map cannot be null", exception.getMessage(), "Error message should match.");
    }

    @Test
    void testInvalidIdZero() {
        Database db = new Database();
        Map<String, String> data = new HashMap<>();
        data.put("name", "John");

        int id = 0;

        Exception exception = assertThrows(IllegalArgumentException.class, () -> db.update(data, id),
                "An ID of 0 should throw an IllegalArgumentException.");
        assertEquals("ID must be greater than 0", exception.getMessage(), "Error message should match.");
    }

    @Test
    void testInvalidNegativeId() {
        Database db = new Database();
        Map<String, String> data = new HashMap<>();
        data.put("name", "John");

        int id = -5;

        Exception exception = assertThrows(IllegalArgumentException.class, () -> db.update(data, id),
                "A negative ID should throw an IllegalArgumentException.");
        assertEquals("ID must be greater than 0", exception.getMessage(), "Error message should match.");
    }

    @Test
    void testLargeDataMap() {
        Database db = new Database();
        Map<String, String> data = new HashMap<>();
        for (int i = 1; i <= 100; i++) {
            data.put("col" + i, "value" + i);
        }

        int id = 1;
        db.update(data, id);

        StringBuilder expectedQuery = new StringBuilder("UPDATE `" + db.getTableName() + "` SET ");
        for (int i = 1; i <= 100; i++) {
            expectedQuery.append("`col").append(i).append("` = 'value").append(i).append("',");
        }
        expectedQuery.deleteCharAt(expectedQuery.length() - 1); // Remove trailing comma
        expectedQuery.append(" WHERE `id` = 1;");
        assertEquals(expectedQuery.toString(), db.getLastQuery(), "Query should match for a large data map.");
    }




}
