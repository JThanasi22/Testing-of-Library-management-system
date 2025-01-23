import DB.Books;
import UI.BookDetails;
import UI.MainUI;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

public class BookDetailsTest {
    private BookDetails bookDetails;

    @BeforeEach
    public void setUp() {
        bookDetails = new BookDetails();

        bookDetails.nameInput = new JTextField();
        bookDetails.authorInput = new JTextField();
        bookDetails.categoryInput = new JComboBox<>();
        bookDetails.isbnInput = new JTextField();
        bookDetails.descriptionInput = new JTextArea();
        bookDetails.languageInput = new JComboBox<>();
        bookDetails.saveButton = new JButton();
    }

    // Tests for SetMode
    @Test
    public void testSetMode_NewMode() {
        bookDetails.SetMode("new");

        assertFalse(bookDetails.stateShowLabel.isVisible(), "State Show Label should be hidden in 'new' mode.");
        assertFalse(bookDetails.stateLabel.isVisible(), "State Label should be hidden in 'new' mode.");
        assertFalse(bookDetails.deleteBookButton.isVisible(), "Delete Book Button should be hidden in 'new' mode.");
    }

    @Test
    public void testSetMode_UpdateMode() {
        bookDetails.SetMode("update");

        assertTrue(bookDetails.stateShowLabel.isVisible(), "State Show Label should be visible in 'update' mode.");
        assertTrue(bookDetails.stateLabel.isVisible(), "State Label should be visible in 'update' mode.");
        assertTrue(bookDetails.deleteBookButton.isVisible(), "Delete Book Button should be visible in 'update' mode.");
        assertEquals("Update Book", bookDetails.titleLabel.getText(), "Title Label should display 'Update Book' in 'update' mode.");
        assertEquals("Update Book", bookDetails.saveButton.getText(), "Save Button should display 'Update Book' in 'update' mode.");
    }

    // Testing Analysis (Chosen Method "setBook")

    // Boundary Value Testing

    @Test
    public void testSetBook_MinimumValue() throws SQLException {
        String minBookID = "1";
        bookDetails.SetBook(minBookID);

        assertEquals("Test Book", bookDetails.nameInput.getText(), "Name should match the book's name.");
        assertEquals("12345678", bookDetails.isbnInput.getText(), "ISBN should match the book's ISBN.");
        assertEquals("Test Author", bookDetails.authorInput.getText(), "Author should match the book's author.");
    }

    @Test
    public void testSetBook_UnderMinimumValue() {
        String underMinBookID = "0";
        SQLException thrown = assertThrows(SQLException.class, () -> {
            bookDetails.SetBook(underMinBookID);
        });
        assertEquals("Illegal operation on empty result set.", thrown.getMessage(), "Expected an exception for ID below valid range.");
    }

    @Test
    public void testSetBook_MaximumValidID() throws SQLException {
        String maxValidID = String.valueOf(Integer.MAX_VALUE);
        bookDetails.SetBook(maxValidID);
        assertEquals("Test Book", bookDetails.nameInput.getText(), "Name should match the book's name.");
    }

    @Test
    public void testSetBook_AboveMaximumID() {
        String aboveMaxID = String.valueOf((long) Integer.MAX_VALUE + 1);

        SQLException thrown = assertThrows(SQLException.class, () -> {
            bookDetails.SetBook(aboveMaxID);
        });
        assertEquals("Illegal operation on empty result set.", thrown.getMessage(), "Expected an exception for ID exceeding maximum range.");
    }

    // Class Evaluation Testing

    @Test
    public void testSetBook_ValidBookID() throws SQLException {
        String validBookID = "1";
        bookDetails.SetBook(validBookID);
        assertEquals("Test Book", bookDetails.nameInput.getText(), "Fields should be populated with book details.");
    }

    @Test
    public void testSetBook_InValidBookID() {
        String invalidBookID = "0";
        SQLException thrown = assertThrows(SQLException.class, () -> {
            bookDetails.SetBook(invalidBookID);
        });
        assertEquals("Illegal operation on empty result set.", thrown.getMessage(), "Should throw an exception for invalid book ID.");
    }

    @Test
    public void testSetBook_InvalidFormatBookID() {
        String invalidFormatBookID = "abc123"; // Malformed book ID
        assertThrows(NumberFormatException.class, () -> {
            bookDetails.SetBook(invalidFormatBookID);
        });
    }

    // Code Coverage testing

    @Test
    public void testSetBook_StateAvailable() throws SQLException {
        String validBookID = "1";
        bookDetails.SetBook(validBookID);

        assertEquals("Available", bookDetails.stateShowLabel.getText(), "State should display 'Available'.");
        assertEquals("Test Book", bookDetails.nameInput.getText(), "Name should be populated correctly.");
    }

    @Test
    public void testSetBook_StateBorrowed() throws SQLException {
        String validBookID = "2";
        bookDetails.SetBook(validBookID);

        assertEquals("Barrowed", bookDetails.stateShowLabel.getText(), "State should display 'Borrowed'.");
        assertEquals("Another Test Book", bookDetails.nameInput.getText(), "Name should be populated correctly.");
    }

    // Other Tests for SetBook

    @Test
    public void testSetBook_StateHandling() throws SQLException {
        String validBookID = "1";
        bookDetails.SetBook(validBookID);
        assertEquals("Barrowed", bookDetails.stateShowLabel.getText(), "State should display 'Borrowed' for state 2.");
    }

    // Tests for testBackToHomePerformed

    @Test
    public void testBackToHomeButtonActionPerformed() throws InterruptedException, InvocationTargetException {
        SwingUtilities.invokeAndWait(() -> {
            bookDetails.setVisible(true);
            bookDetails.backToHomeButton = new JButton();
            bookDetails.backToHomeButton.addActionListener(bookDetails::backToHomeButtonActionPerformed);
        });

        SwingUtilities.invokeAndWait(() -> bookDetails.backToHomeButton.doClick());
        SwingUtilities.invokeAndWait(() -> {
            assertFalse(bookDetails.isDisplayable(), "The BookDetails frame should be disposed.");
        });
    }

    // Tests for saveButtonActionPerformed
    @Test
    public void testSaveButton_EmptyName() {
        bookDetails.nameInput.setText("");
        bookDetails.isbnInput.setText("12345678");

        bookDetails.saveButton.doClick();
        assertTrue(bookDetails.nameInput.getText().isEmpty(), "The save operation should stop if the name is empty.");
    }

    @Test
    public void testSaveButton_EmptyISBN() {
        bookDetails.nameInput.setText("Test Book");
        bookDetails.isbnInput.setText("");

        bookDetails.saveButton.doClick();
        assertTrue(bookDetails.isbnInput.getText().isEmpty(), "The save operation should stop if the ISBN is empty.");
    }

    @Test
    public void testSaveButton_NewMode_ValidInputs() {
        bookDetails.mode = "new";
        bookDetails.nameInput.setText("Valid Book");
        bookDetails.isbnInput.setText("12345678");
        bookDetails.authorInput.setText("Test Author");
        bookDetails.categoryInput.setSelectedItem("Fiction");
        bookDetails.languageInput.setSelectedItem("English");
        bookDetails.descriptionInput.setText("Test Description");
        bookDetails.saveButton.doClick();

        assertEquals("Valid Book", bookDetails.bookData.get("name"), "Book name should be saved.");
        assertEquals("12345678", bookDetails.bookData.get("isbn"), "ISBN should be saved.");
        assertEquals("Test Author", bookDetails.bookData.get("author"), "Author should be saved.");
        assertEquals("Fiction", bookDetails.bookData.get("category"), "Category should be saved.");
        assertEquals("English", bookDetails.bookData.get("language"), "Language should be saved.");
        assertEquals("Test Description", bookDetails.bookData.get("description"), "Description should be saved.");
        assertEquals("1", bookDetails.bookData.get("state"), "State should be set to '1' for new books.");
    }


    @Test
    public void testSaveButton_UpdateMode_ValidInputs() {
        bookDetails.mode = "update";
        bookDetails.nameInput.setText("Updated Book");
        bookDetails.isbnInput.setText("87654321");
        bookDetails.authorInput.setText("Updated Author");
        bookDetails.categoryInput.addItem("Non-Fiction");
        bookDetails.categoryInput.setSelectedItem("Non-Fiction");
        bookDetails.languageInput.addItem("French");
        bookDetails.languageInput.setSelectedItem("French");
        bookDetails.descriptionInput.setText("Updated Description");
        bookDetails.saveButton.doClick();

        assertEquals("Updated Book", bookDetails.bookData.get("name"), "Book name should be updated.");
        assertEquals("87654321", bookDetails.bookData.get("isbn"), "ISBN should be updated.");
        assertEquals("Updated Author", bookDetails.bookData.get("author"), "Author should be updated.");
        assertEquals("Non-Fiction", bookDetails.bookData.get("category"), "Category should be updated.");
        assertEquals("French", bookDetails.bookData.get("language"), "Language should be updated.");
        assertEquals("Updated Description", bookDetails.bookData.get("description"), "Description should be updated.");
    }

    @Test
    public void testDeleteBook_Confirmed() {
        bookDetails.bookID = 4;
        JOptionPane pane = new JOptionPane();
        int userInput = JOptionPane.YES_OPTION;
        pane.setInputValue(userInput);

        bookDetails.deleteBookButton.doClick();
        Books books = new Books();
        boolean isDeleted = books.get("`id`=" + bookDetails.bookID).size() == 0;
        assertTrue(isDeleted, "The book should be deleted from the database.");
    }

    @Test
    public void testDeleteBook_InvalidID() {
        bookDetails.bookID = 9999;
        JOptionPane pane = new JOptionPane();
        int userInput = JOptionPane.YES_OPTION;
        pane.setInputValue(userInput);

        bookDetails.deleteBookButton.doClick();
        Books books = new Books();
        boolean isDeleted = books.get("`id`=" + bookDetails.bookID).size() == 0;
        assertTrue(isDeleted, "No book should be deleted since the ID does not exist in the database.");
    }

    @Test
    public void testDeleteBook_Cancelled() {
        bookDetails.bookID = 1;
        JOptionPane pane = new JOptionPane();
        int userInput = JOptionPane.NO_OPTION;
        pane.setInputValue(userInput);

        bookDetails.deleteBookButton.doClick();
        Books books = new Books();
        boolean isDeleted = books.get("`id`=" + bookDetails.bookID).size() == 0;
        assertFalse(isDeleted, "The book should not be deleted from the database.");
    }

    @Test
    public void testAddBook_ValidData() {
        bookDetails.bookData.put("name", "Test Book1212");
        bookDetails.bookData.put("isbn", "12345678");
        bookDetails.bookData.put("author", "Test Author");
        bookDetails.bookData.put("category", "Fiction");
        bookDetails.bookData.put("language", "English");
        bookDetails.bookData.put("description", "Test Description");

        bookDetails.addBook();
        assertEquals("1", bookDetails.bookData.get("state"), "State should be set to '1' for new books.");

        Books books = new Books();
        boolean exists = books.get("`name`='Test Book1212'").size() > 0;
        assertTrue(exists, "Book should be inserted into the database.");
    }

    @Test
    public void testUpdateBook_ValidData() {
        bookDetails.bookID = 1;
        bookDetails.bookData.put("name", "Updated Book");
        bookDetails.bookData.put("isbn", "87654321");
        bookDetails.bookData.put("author", "Updated Author");
        bookDetails.bookData.put("category", "Non-Fiction");
        bookDetails.bookData.put("language", "French");
        bookDetails.bookData.put("description", "Updated Description");

        bookDetails.updateBook();
        Books books = new Books();
        boolean isUpdated = books.get("`id`=" + bookDetails.bookID + " AND `name`='Updated Book'").size() > 0;
        assertTrue(isUpdated, "Book data should be updated in the database.");
    }

    @Test
    public void testUpdateBook_InvalidID() {
        bookDetails.bookID = 9999;
        bookDetails.bookData.put("name", "Non-Existent Book");
        bookDetails.bookData.put("isbn", "00000000");
        bookDetails.bookData.put("author", "Invalid Author");
        bookDetails.bookData.put("category", "Fiction");
        bookDetails.bookData.put("language", "English");
        bookDetails.bookData.put("description", "This book does not exist in the database.");

        bookDetails.updateBook();
        Books books = new Books();
        boolean isUpdated = books.get("`id`=" + bookDetails.bookID).size() > 0;
        assertFalse(isUpdated, "No book data should be updated in the database for an invalid ID.");
    }

}
