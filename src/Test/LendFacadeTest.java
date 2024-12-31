package Test;

import static org.junit.jupiter.api.Assertions.*;
import DB.LendFacade;
import org.junit.jupiter.api.Test;

class LendFacadeTest {

    @Test
    void lendBook_Success() {
        int bookId = 1;
        int userId = 1;

        assertDoesNotThrow(() -> LendFacade.lendBook(bookId, userId));
    }

    @Test
    void lendBook_FailureBookId() {
        int bookId = -1;
        int userId = 1;

        Exception exception = assertThrows(Exception.class, () -> LendFacade.lendBook(bookId, userId));
        assertEquals("Invalid book ID", exception.getMessage());
    }

    @Test
    void lendBook_FailureUserID() {
        int bookId = 1;
        int userId = -1;

        Exception exception = assertThrows(Exception.class, () -> LendFacade.lendBook(bookId, userId));
        assertEquals("Invalid User ID", exception.getMessage());
    }

    @Test
    void lendBook_FailureBoth() {
        int bookId = -1;
        int userId = -1;

        Exception exception = assertThrows(Exception.class, () -> LendFacade.lendBook(bookId, userId));
        assertEquals("Invalid book ID and user ID", exception.getMessage());
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
    void returnBook_UnlentBook() {
        int bookId = 2;

        Exception exception = assertThrows(Exception.class, () -> LendFacade.returnBook(bookId));
        assertEquals("Book not lent", exception.getMessage());
    }

}
