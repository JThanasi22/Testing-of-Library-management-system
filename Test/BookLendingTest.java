import DB.BookLending;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class BookLendingTest {

    private BookLending bookLending;

    @BeforeEach
    public void setUp() {
        bookLending = new BookLending();
    }

    @Test
    public void testGetTableName() {
        assertEquals("book_lending", bookLending.getTableName(), "Should return 'book_lending' as the table name");
    }

    @Test
    public void testReturnBook() {
        int validBookId = 1;
        assertDoesNotThrow(() -> bookLending.return_book(validBookId), "Should not throw for valid book ID");
    }

    @Test
    public void testReturnBookDatabase() {
        int bookId = 1;
        bookLending.return_book(bookId);
    }

    @Test
    public void testReturnBookWithInvalidId() {
        int invalidBookId = -1;
        Exception exception = assertThrows(IllegalArgumentException.class, () -> bookLending.return_book(invalidBookId));
        assertEquals("Invalid book ID", exception.getMessage(), "Should throw for invalid book ID");
    }

    @Test
    public void testReturnAlreadyReturnedBook() {
        int alreadyReturnedBookId = 1;
        Exception exception = assertThrows(IllegalStateException.class, () -> bookLending.return_book(alreadyReturnedBookId));
        assertEquals("Book is already returned.", exception.getMessage(), "Should throw for already returned book");
    }

}
