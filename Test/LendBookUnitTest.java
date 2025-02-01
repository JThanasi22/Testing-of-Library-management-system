import DB.Books;
import DB.LendFacade;
import DB.Members;
import UI.LendBook;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ClassUnitTest {

    @Mock
    private Books mockBooks;

    @Mock
    private Members mockMembers;

    @Mock
    private LendFacade mockLendFacade;

    @InjectMocks
    private LendBook lendBook;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        lendBook.infoLabel = new JLabel();
        lendBook.memberList = new JList<>();
        lendBook.searchByMemberIdInput = new JTextField();
        lendBook.searchByNameInput = new JTextField();
    }

    @Test
    public void testSetBookId_BookExists() throws SQLException {
        int validBookId = 1;
        ResultSet mockResultSet = mock(ResultSet.class);
        when(mockResultSet.next()).thenReturn(true);
        when(mockResultSet.getString("name")).thenReturn("Test Book");
        when(mockBooks.getResultSet(anyString())).thenReturn(mockResultSet);

        lendBook.setBookId(validBookId);
        assertEquals("Search and select a member to lend 'Test Book' book", lendBook.infoLabel.getText());
    }

    @Test
    public void testSetBookId_BookDoesNotExist() throws SQLException {
        int nonExistingBookId = 9999;
        ResultSet mockResultSet = mock(ResultSet.class);
        when(mockResultSet.next()).thenReturn(false);
        when(mockBooks.getResultSet(anyString())).thenReturn(mockResultSet);

        lendBook.setBookId(nonExistingBookId);
        assertEquals("", lendBook.infoLabel.getText());
    }

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

    @Test
    public void testSearch_ByMemberId() {
        List<HashMap> mockResults = new ArrayList<>();
        HashMap<String, String> member = new HashMap<>();
        member.put("id", "1");
        member.put("name", "User");
        mockResults.add(member);
        when(mockMembers.get(anyString())).thenReturn(mockResults);

        lendBook.searchByMemberIdInput.setText("1");
        lendBook.searchByNameInput.setText("");
        lendBook.search();

        ListModel<String> model = lendBook.memberList.getModel();
        assertEquals(1, model.getSize());
        assertEquals("1-User", model.getElementAt(0));
    }
}
