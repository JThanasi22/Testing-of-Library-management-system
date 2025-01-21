import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import DB.Books;

public class BooksTest {

    private Books books;

    @BeforeEach
    public void setUp() {
        books = new Books();
    }

    @Test
    public void testBooksGetTableName() {
        // Test that the Books class returns the correct table name
        assertEquals("books", books.getTableName(), "Should return 'books' as the table name");
    }
}