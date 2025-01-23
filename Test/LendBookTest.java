import DB.Books;
import UI.LendBook;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

public class LendBookTest {
    private LendBook lendBook;

    @BeforeEach
    public void setUp() {
        lendBook = new LendBook();
        lendBook.infoLabel = new JLabel();
        lendBook.memberList = new JList<>();
        lendBook.searchByMemberIdInput = new JTextField();
        lendBook.searchByNameInput = new JTextField();
    }

    // Tests for setBookId

    // Boundary Value Testing

    @Test
    public void testSetBookId_MinValidBookId() {
        int validBookId = 1;
        lendBook.setBookId(validBookId);
        String expectedMessage = "Search and select a member to lend 'Test Book' book";
        assertEquals(expectedMessage, lendBook.infoLabel.getText());
    }

    @Test
    public void testSetBookId_JustUnderMin() {
        int invalidBookId = 0;
        lendBook.setBookId(invalidBookId);
        String expectedMessage = "";
        assertEquals(expectedMessage, lendBook.infoLabel.getText());
    }

    @Test
    public void testSetBookId_MaxValidBookId() {
        int maxValidBookId = Integer.MAX_VALUE;
        lendBook.setBookId(maxValidBookId);
        String expectedMessage = "Search and select a member to lend 'Test Book' book";
        assertEquals(expectedMessage, lendBook.infoLabel.getText());
    }

    @Test
    public void testSetBookId_OverMax() {
        long overMaxBookId = (long) Integer.MAX_VALUE + 1;
        try {
            lendBook.setBookId((int) overMaxBookId);
        } catch (Exception e) {
            assertTrue(e instanceof IllegalArgumentException || e instanceof SQLException);
        }
        String expectedMessage = "";
        assertEquals(expectedMessage, lendBook.infoLabel.getText());
    }

    // Class Evaluation testing

    @Test
    public void testSetBookId_BookExists() {
        int validBookId = 1;
        lendBook.setBookId(validBookId);
        String expectedMessage = "Search and select a member to lend 'Test Book' book";
        assertEquals(expectedMessage, lendBook.infoLabel.getText());
    }

    @Test
    public void testSetBookId_BookDoesNotExist() {
        int nonExistingBookId = 9999;
        lendBook.setBookId(nonExistingBookId);
        String expectedMessage = "";
        assertEquals(expectedMessage, lendBook.infoLabel.getText());
    }

    @Test
    public void testSetBookId_InvalidBookId() {
        int invalidBookId = -1;
        lendBook.setBookId(invalidBookId);
        String expectedMessage = "";
        assertEquals(expectedMessage, lendBook.infoLabel.getText());
    }

    // Coverage Testing

    @Test
    public void testSetBookId_SQLExceptionThrown() {
        int invalidBookId = -999;

        lendBook.setBookId(invalidBookId);
        String expectedMessage = "";
        assertEquals(expectedMessage, lendBook.infoLabel.getText());
    }

    // Other Tests

    @Test
    public void testSetBookId_EmptyBookName() {
        int emptyNameBookId = 2;
        lendBook.setBookId(emptyNameBookId);
        assertEquals("Search and select a member to lend '' book", lendBook.infoLabel.getText());
    }

    // Tests for lendButtonActionPerformed
    @Test
    public void testLendButton_NoMemberSelected() {
        lendBook.memberList.setListData(new String[]{});
        lendBook.memberList.clearSelection();
        lendBook.lendButtonActionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, null));
    }

    @Test
    public void testLendButton_ValidMemberSelected() {
        lendBook.bookId = 1;
        lendBook.memberList.setListData(new String[]{"1-Test User"});
        lendBook.memberList.setSelectedIndex(0);
        lendBook.lendButtonActionPerformed(new ActionEvent(this, ActionEvent.ACTION_PERFORMED, null));
    }

    // Tests for Cancel Button

    @Test
    public void testCancelButtonActionPerformed() {
        lendBook.cancelButton.doClick(); // Simulate button click
        assertEquals("", lendBook.searchByNameInput.getText());
        assertEquals("", lendBook.searchByMemberIdInput.getText());
    }


    // Tests for search
    @Test
    public void testSearch_EmptyInputs() {
        lendBook.searchByMemberIdInput.setText("");
        lendBook.searchByNameInput.setText("");

        lendBook.search();

        ListModel listModel = lendBook.memberList.getModel();
        assertEquals(1, listModel.getSize());
    }

    @Test
    public void testSearch_ByMemberId() {
        lendBook.searchByMemberIdInput.setText("1");
        lendBook.searchByNameInput.setText("");

        lendBook.search();

        ListModel listModel = lendBook.memberList.getModel();
        assertEquals(1, listModel.getSize());
        assertEquals("1-User", listModel.getElementAt(0));
    }

    @Test
    public void testSearch_ByName() {
        lendBook.searchByMemberIdInput.setText("");
        lendBook.searchByNameInput.setText("User");

        lendBook.search();

        ListModel listModel = lendBook.memberList.getModel();
        assertEquals(1, listModel.getSize());
        assertEquals("1-User", listModel.getElementAt(0));
    }

    @Test
    public void testSearch_ByNameAndId() {
        lendBook.searchByMemberIdInput.setText("1");
        lendBook.searchByNameInput.setText("User");
        lendBook.search();
        ListModel listModel = lendBook.memberList.getModel();
        assertEquals(1, listModel.getSize());
        assertEquals("1-User", listModel.getElementAt(0));
    }

    @Test
    public void testSearch_SpecialCharacters() {
        lendBook.searchByMemberIdInput.setText("1'");
        lendBook.searchByNameInput.setText("User@&!");

        lendBook.search();

        ListModel listModel = lendBook.memberList.getModel();
        assertEquals(0, listModel.getSize());
    }

    @Test
    public void testSearch_NoResults() {
        lendBook.searchByMemberIdInput.setText("9999");
        lendBook.searchByNameInput.setText("NonExistentName");

        lendBook.search();

        ListModel listModel = lendBook.memberList.getModel();
        assertEquals(0, listModel.getSize());
    }

    @Test
    public void testSearch_CaseInsensitive() {
        lendBook.searchByMemberIdInput.setText("1");
        lendBook.searchByNameInput.setText("user");

        lendBook.search();

        ListModel listModel = lendBook.memberList.getModel();
        assertEquals(1, listModel.getSize());
        assertEquals("1-User", listModel.getElementAt(0));
    }

}
