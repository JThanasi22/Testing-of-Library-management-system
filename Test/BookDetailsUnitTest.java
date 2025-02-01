import DB.Books;
import UI.BookDetails;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.swing.*;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class BookDetailsUnitTest {

    @Mock
    private Books mockBooks;

    @InjectMocks
    private BookDetails bookDetails;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        bookDetails.nameInput = new JTextField();
        bookDetails.authorInput = new JTextField();
        bookDetails.categoryInput = new JComboBox<>();
        bookDetails.isbnInput = new JTextField();
        bookDetails.descriptionInput = new JTextArea();
        bookDetails.languageInput = new JComboBox<>();
        bookDetails.saveButton = new JButton();
    }

    @Test
    public void testSetMode_NewMode() {
        bookDetails.SetMode("new");
        assertFalse(bookDetails.stateShowLabel.isVisible());
        assertFalse(bookDetails.stateLabel.isVisible());
        assertFalse(bookDetails.deleteBookButton.isVisible());
    }

    @Test
    public void testSetMode_UpdateMode() {
        bookDetails.SetMode("update");
        assertTrue(bookDetails.stateShowLabel.isVisible());
        assertTrue(bookDetails.stateLabel.isVisible());
        assertTrue(bookDetails.deleteBookButton.isVisible());
        assertEquals("Update Book", bookDetails.titleLabel.getText());
        assertEquals("Update Book", bookDetails.saveButton.getText());
    }

    @Test
    public void testSetBook_ValidBook() throws SQLException {
        String validBookID = "1";
        ResultSet mockResultSet = mock(ResultSet.class);
        when(mockResultSet.next()).thenReturn(true);
        when(mockResultSet.getString("name")).thenReturn("Test Book");
        when(mockResultSet.getString("author")).thenReturn("Test Author");
        when(mockResultSet.getString("isbn")).thenReturn("12345678");
        when(mockBooks.getResultSet(anyString())).thenReturn(mockResultSet);

        bookDetails.SetBook(validBookID);

        assertEquals("Test Book", bookDetails.nameInput.getText());
        assertEquals("Test Author", bookDetails.authorInput.getText());
        assertEquals("12345678", bookDetails.isbnInput.getText());
    }

    @Test
    public void testSetBook_InvalidBook() throws SQLException {
        String invalidBookID = "9999";
        ResultSet mockResultSet = mock(ResultSet.class);
        when(mockResultSet.next()).thenReturn(false);
        when(mockBooks.getResultSet(anyString())).thenReturn(mockResultSet);

        bookDetails.SetBook(invalidBookID);
        assertEquals("", bookDetails.nameInput.getText());
    }

    @Test
    public void testSaveButton_NewBook_ValidData() {
        bookDetails.mode = "new";
        bookDetails.nameInput.setText("Valid Book");
        bookDetails.isbnInput.setText("12345678");
        bookDetails.authorInput.setText("Test Author");
        bookDetails.categoryInput.setSelectedItem("Fiction");
        bookDetails.languageInput.setSelectedItem("English");
        bookDetails.descriptionInput.setText("Test Description");
        bookDetails.saveButton.doClick();

        assertEquals("Valid Book", bookDetails.bookData.get("name"));
        assertEquals("12345678", bookDetails.bookData.get("isbn"));
        assertEquals("Test Author", bookDetails.bookData.get("author"));
        assertEquals("Fiction", bookDetails.bookData.get("category"));
        assertEquals("English", bookDetails.bookData.get("language"));
        assertEquals("Test Description", bookDetails.bookData.get("description"));
        assertEquals("1", bookDetails.bookData.get("state"));
    }

    @Test
    public void testDeleteBookButton_Confirmed() {
        bookDetails.bookID = 1;
        bookDetails.deleteBookButton = new JButton();
        bookDetails.deleteBookButton.addActionListener(bookDetails::deleteBookButtonActionPerformed);

        bookDetails.deleteBookButton.doClick();
        verify(mockBooks, times(1)).delete(1);
    }
}
