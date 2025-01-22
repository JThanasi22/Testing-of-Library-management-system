import static org.junit.jupiter.api.Assertions.*;

import DB.Members;
import UI.MemberDetails;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.HashMap;

public class MemberDetailsTest {

    private MemberDetails memberDetails;

    @BeforeEach
    public void setUp() {
        memberDetails = new MemberDetails();
        memberDetails.deleteMemberButton = new javax.swing.JButton();
        memberDetails.titleLabel = new javax.swing.JLabel("Member Details");
        memberDetails.saveButton = new javax.swing.JButton("Register Member");
        memberDetails.nameInput = new javax.swing.JTextField();
        memberDetails.emailInput = new javax.swing.JTextField();
        memberDetails.memberIDInput = new javax.swing.JTextField();
        memberDetails.memberData = new HashMap<>();
    }

    // Testing for setMode

    @Test
    public void testSetMode_NewMode() {
        memberDetails.SetMode("new");
        assertFalse(memberDetails.deleteMemberButton.isVisible(), "Delete Member Button should be hidden in 'new' mode.");
        assertEquals("Member Details", memberDetails.titleLabel.getText(), "Title should remain 'Member Details' in 'new' mode.");
        assertEquals("Register Member", memberDetails.saveButton.getText(), "Save Button text should be 'Register Member' in 'new' mode.");
    }

    @Test
    public void testSetMode_UpdateMode() {
        memberDetails.SetMode("update");
        assertTrue(memberDetails.deleteMemberButton.isVisible(), "Delete Member Button should be visible in 'update' mode.");
        assertEquals("Update Member", memberDetails.titleLabel.getText(), "Title should be updated to 'Update Member' in 'update' mode.");
        assertEquals("Update Member", memberDetails.saveButton.getText(), "Save Button text should be 'Update Member' in 'update' mode.");
    }

    // Testing for setMember

    @Test
    public void testSetMember_ValidID() throws SQLException {
        String validMemberID = "1";
        memberDetails.setMember(validMemberID);

        assertEquals("1", memberDetails.memberIDInput.getText(), "Member ID should match.");
        assertEquals("Test Member", memberDetails.nameInput.getText(), "Name should match.");
        assertEquals("test@example.com", memberDetails.emailInput.getText(), "Email should match.");
        assertTrue(memberDetails.nicTypeRadio.isSelected(), "NIC type should be selected.");
        assertEquals("Active", memberDetails.memberStatusCheckBox.getText(), "Status should match.");
    }

    @Test
    public void testSetMember_InvalidID() {
        String invalidMemberID = "0";

        SQLException thrown = assertThrows(SQLException.class, () -> {
            memberDetails.setMember(invalidMemberID);
        });
        assertEquals("Illegal operation on empty result set.", thrown.getMessage(), "Expected exception for invalid ID.");
    }

    @Test
    public void testSetMember_NICSelected() throws SQLException {
        String validMemberID = "1";
        memberDetails.setMember(validMemberID);
        assertTrue(memberDetails.nicTypeRadio.isSelected(), "NIC radio button should be selected.");
        assertFalse(memberDetails.studentTypeRadio.isSelected(), "Student radio button should not be selected.");
    }

    @Test
    public void testSetMember_StudentIDSelected() throws SQLException {
        String validMemberID = "2";
        memberDetails.setMember(validMemberID);
        assertTrue(memberDetails.studentTypeRadio.isSelected(), "Student radio button should be selected.");
        assertFalse(memberDetails.nicTypeRadio.isSelected(), "NIC radio button should not be selected.");
    }

    @Test
    public void testSetMember_MaleSelection() throws SQLException {
        String validMemberID = "1";
        memberDetails.setMember(validMemberID);
        assertEquals("Male", memberDetails.genderSelector.getSelectedItem(), "Gender should be set to 'Male'.");
    }

    @Test
    public void testSetMember_FemaleSelection() throws SQLException {
        String validMemberID = "2";
        memberDetails.setMember(validMemberID);
        assertEquals("Female", memberDetails.genderSelector.getSelectedItem(), "Gender should be set to 'Male'.");
    }

    @Test
    public void testSetMember_StatusActive() throws SQLException {
        String validMemberID = "1";
        memberDetails.setMember(validMemberID);
        assertTrue(memberDetails.memberStatusCheckBox.isSelected(), "Checkbox should be selected for active status.");
    }

    @Test
    public void testSetMember_StatusInactive() throws SQLException {
        String validMemberID = "2";
        memberDetails.setMember(validMemberID);
        assertFalse(memberDetails.memberStatusCheckBox.isSelected(), "Checkbox should not be selected for inactive status.");
    }

    // Testing for saveButtonActionPerformed

    @Test
    public void testSaveButton_NewMember_ValidData() {
        memberDetails.mode = "new";
        memberDetails.nameInput.setText("New Member");
        memberDetails.emailInput.setText("newmember@example.com");

        memberDetails.saveButton.doClick();

        assertEquals("New Member", memberDetails.memberData.get("name"), "Name should be saved.");
        assertEquals("newmember@example.com", memberDetails.memberData.get("email"), "Email should be saved.");
        assertFalse(memberDetails.memberData.containsKey("id"), "ID should not be saved in 'new' mode.");
    }

    @Test
    public void testSaveButton_UpdateMember_ValidData() {
        memberDetails.mode = "update";
        memberDetails.memberIDInput.setText("1");
        memberDetails.nameInput.setText("Updated Member");
        memberDetails.emailInput.setText("updatedmember@example.com");

        memberDetails.saveButton.doClick();

        assertEquals("Updated Member", memberDetails.memberData.get("name"), "Name should be saved.");
        assertEquals("updatedmember@example.com", memberDetails.memberData.get("email"), "Email should be saved.");
        assertEquals("1", memberDetails.memberData.get("id"), "ID should be saved in 'update' mode.");
    }

    // Testing of newMember

    @Test
    public void testNewMember_ValidData() {
        memberDetails.memberData.put("name", "user");
        memberDetails.memberData.put("email", "user@example.com");
        memberDetails.memberData.put("phone", "123456789");
        memberDetails.memberData.put("id_number", "A1234567");
        memberDetails.memberData.put("id_type", "NIC");
        memberDetails.memberData.put("birthday", "2000-01-01");
        memberDetails.memberData.put("gender", "Male");
        memberDetails.memberData.put("status", "1");

        memberDetails.newMember();
        Members members = new Members();
        boolean exists = members.get("`name`='User'").size() > 0;
        assertTrue(exists, "The new member should be added to the database.");
    }

    // testing of updateMember

    @Test
    public void testUpdateMember_ValidData() {
        memberDetails.memberID = 2;
        memberDetails.memberData.put("name", "Updated Member");
        memberDetails.memberData.put("email", "updated.email@example.com");
        memberDetails.memberData.put("phone", "987654321");
        memberDetails.memberData.put("id_number", "B1234567");
        memberDetails.memberData.put("id_type", "Student");
        memberDetails.memberData.put("birthday", "1990-12-12");
        memberDetails.memberData.put("gender", "Female");
        memberDetails.memberData.put("status", "1");
        memberDetails.updateMember();
        Members members = new Members();
        boolean isUpdated = members.get("`id`=1 AND `name`='Updated Member' AND `email`='updated.email@example.com'").size() > 0;
        assertTrue(isUpdated, "The member's details should be updated in the database.");
    }

    @Test
    public void testUpdateMember_InvalidID() {
        memberDetails.memberID = 0;
        memberDetails.memberData.put("name", "Non-Existent Member");
        memberDetails.memberData.put("email", "nonexistent@example.com");
        memberDetails.updateMember();
        Members members = new Members();
        boolean isUpdated = members.get("`id`=0").size() > 0;
        assertFalse(isUpdated, "No member should be updated since the ID does not exist in the database.");
    }

    // Testing of backToHomeButtonActionPerformed

    @Test
    public void testBackToHomeButtonActionPerformed() throws InterruptedException, InvocationTargetException {
        SwingUtilities.invokeAndWait(() -> {
            memberDetails.setVisible(true);
            memberDetails.backToHomeButton = new JButton();
            memberDetails.backToHomeButton.addActionListener(memberDetails::backToHomeButtonActionPerformed);
        });
        SwingUtilities.invokeAndWait(() -> memberDetails.backToHomeButton.doClick());
        SwingUtilities.invokeAndWait(() -> {
            assertFalse(memberDetails.isDisplayable(), "The MemberDetails frame should be disposed.");
        });
    }

    // Testing for deleteMemberButtonActionPerformed

    @Test
    public void testDeleteMemberButton_Confirmed() {
        memberDetails.memberID = 1; // Set a valid member ID
        memberDetails.deleteMemberButton = new JButton();
        memberDetails.deleteMemberButton.addActionListener(memberDetails::deleteMemberButtonActionPerformed);
        JOptionPane pane = new JOptionPane();
        pane.setInputValue(JOptionPane.YES_OPTION); // Simulate clicking "Yes"

        memberDetails.deleteMemberButton.doClick();
        Members members = new Members();
        boolean isDeleted = members.get("`id`=" + memberDetails.memberID).isEmpty();
        assertTrue(isDeleted, "The member should be deleted from the database.");
    }


    @Test
    public void testDeleteMemberButton_Canceled() {
        memberDetails.memberID = 2;
        memberDetails.deleteMemberButton = new JButton();
        memberDetails.deleteMemberButton.addActionListener(memberDetails::deleteMemberButtonActionPerformed);
        JOptionPane pane = new JOptionPane();
        pane.setInputValue(JOptionPane.NO_OPTION);
        memberDetails.deleteMemberButton.doClick();

        Members members = new Members();
        boolean isDeleted = members.get("`id`=" + memberDetails.memberID).isEmpty();
        assertFalse(isDeleted, "The member should not be deleted from the database.");
    }

}
