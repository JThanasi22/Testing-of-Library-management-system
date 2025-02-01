import DB.Members;
import UI.MemberDetails;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import javax.swing.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class MemberDetailsUnitTest {

    @Mock
    private Members mockMembers;

    @InjectMocks
    private MemberDetails memberDetails;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        memberDetails.deleteMemberButton = new JButton();
        memberDetails.titleLabel = new JLabel("Member Details");
        memberDetails.saveButton = new JButton("Register Member");
        memberDetails.nameInput = new JTextField();
        memberDetails.emailInput = new JTextField();
        memberDetails.memberIDInput = new JTextField();
        memberDetails.memberData = new HashMap<>();
    }

    @Test
    public void testSetMode_NewMode() {
        memberDetails.SetMode("new");
        assertFalse(memberDetails.deleteMemberButton.isVisible());
        assertEquals("Member Details", memberDetails.titleLabel.getText());
        assertEquals("Register Member", memberDetails.saveButton.getText());
    }

    @Test
    public void testSetMode_UpdateMode() {
        memberDetails.SetMode("update");
        assertTrue(memberDetails.deleteMemberButton.isVisible());
        assertEquals("Update Member", memberDetails.titleLabel.getText());
        assertEquals("Update Member", memberDetails.saveButton.getText());
    }

    @Test
    public void testSetMember_BookExists() throws SQLException {
        String validMemberID = "1";
        ResultSet mockResultSet = mock(ResultSet.class);
        when(mockResultSet.next()).thenReturn(true);
        when(mockResultSet.getString("name")).thenReturn("Test Member");
        when(mockResultSet.getString("email")).thenReturn("test@example.com");
        when(mockMembers.getResultSet(anyString())).thenReturn(mockResultSet);

        memberDetails.setMember(validMemberID);

        assertEquals("1", memberDetails.memberIDInput.getText());
        assertEquals("Test Member", memberDetails.nameInput.getText());
        assertEquals("test@example.com", memberDetails.emailInput.getText());
    }

    @Test
    public void testSetMember_MemberDoesNotExist() throws SQLException {
        String invalidMemberID = "9999";
        ResultSet mockResultSet = mock(ResultSet.class);
        when(mockResultSet.next()).thenReturn(false);
        when(mockMembers.getResultSet(anyString())).thenReturn(mockResultSet);

        memberDetails.setMember(invalidMemberID);
        assertEquals("", memberDetails.memberIDInput.getText());
    }

    @Test
    public void testSaveButton_NewMember_ValidData() {
        memberDetails.mode = "new";
        memberDetails.nameInput.setText("New Member");
        memberDetails.emailInput.setText("newmember@example.com");

        memberDetails.saveButton.doClick();

        assertEquals("New Member", memberDetails.memberData.get("name"));
        assertEquals("newmember@example.com", memberDetails.memberData.get("email"));
        assertFalse(memberDetails.memberData.containsKey("id"));
    }

    @Test
    public void testSaveButton_UpdateMember_ValidData() {
        memberDetails.mode = "update";
        memberDetails.memberIDInput.setText("1");
        memberDetails.nameInput.setText("Updated Member");
        memberDetails.emailInput.setText("updatedmember@example.com");

        memberDetails.saveButton.doClick();

        assertEquals("Updated Member", memberDetails.memberData.get("name"));
        assertEquals("updatedmember@example.com", memberDetails.memberData.get("email"));
        assertEquals("1", memberDetails.memberData.get("id"));
    }

    @Test
    public void testDeleteMemberButton_Confirmed() {
        memberDetails.memberID = 1;
        memberDetails.deleteMemberButton = new JButton();
        memberDetails.deleteMemberButton.addActionListener(memberDetails::deleteMemberButtonActionPerformed);

        memberDetails.deleteMemberButton.doClick();
        verify(mockMembers, times(1)).delete(1);
    }
}