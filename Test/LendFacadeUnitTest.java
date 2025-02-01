import static org.junit.jupiter.api.Assertions.*;
import DB.LendFacade;
import DB.Books;
import DB.BookLending;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.*;

public class LendFacadeUnitTest {

    @Mock
    private Books mockBooks;

    @Mock
    private BookLending mockBookLending;

    @InjectMocks
    private LendFacade lendFacade;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void lendBook_Success() {
        int bookId = 1;
        int userId = 1;

        doNothing().when(mockBooks).update(anyMap(), eq(bookId));
        doNothing().when(mockBookLending).insert(anyMap());

        assertDoesNotThrow(() -> LendFacade.lendBook(bookId, userId));
    }

    @Test
    void lendBook_Failure_BookId() {
        int bookId = -1;
        int userId = 1;

        Exception exception = assertThrows(Exception.class, () -> LendFacade.lendBook(bookId, userId));
        assertEquals("Invalid book ID", exception.getMessage());
    }

    @Test
    void lendBook_Failure_UserID() {
        int bookId = 1;
        int userId = -1;

        Exception exception = assertThrows(Exception.class, () -> LendFacade.lendBook(bookId, userId));
        assertEquals("Invalid User ID", exception.getMessage());
    }

    @Test
    void lendBook_Failure_Both() {
        int bookId = -1;
        int userId = -1;

        Exception exception = assertThrows(Exception.class, () -> LendFacade.lendBook(bookId, userId));
        assertEquals("Invalid book ID and user ID", exception.getMessage());
    }

    @Test
    public void testLendBook_DuplicateLend() {
        int bookId = 1;
        int userId = 1;
        LendFacade.lendBook(bookId, userId);
        Exception exception = assertThrows(IllegalStateException.class, () -> LendFacade.lendBook(bookId, userId));
        assertEquals("Book is already lent.", exception.getMessage());
    }

    @Test
    void returnBook_Success() {
        int bookId = 1;

        LendFacade.lendBook(bookId, 123);

        assertDoesNotThrow(() -> LendFacade.returnBook(bookId));
    }

    @Test
    void returnBook_Failure() {
        int bookId = -1;

        Exception exception = assertThrows(Exception.class, () -> LendFacade.returnBook(bookId));
        assertEquals("Invalid book ID", exception.getMessage());
    }

    @Test
    void returnBook_Unlent_Book() {
        int bookId = 2;

        Exception exception = assertThrows(Exception.class, () -> LendFacade.returnBook(bookId));
        assertEquals("Book not lent", exception.getMessage());
    }
}
