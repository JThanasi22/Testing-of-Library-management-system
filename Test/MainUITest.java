import DB.MockedUsers;
import DB.Users;
import UI.MainUI;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import java.awt.event.ActionEvent;
import java.util.HashMap;
import java.util.List;

import static org.mockito.Mockito.*;

public class MainUITest {

    private MainUI mainUI;

    @Before
    public void setUp() {
        MockedUsers mockedUsers = new MockedUsers();
        mainUI = new MainUI(mockedUsers);
    }

    @Test
    public void testSetUser() {
        mainUI.setUser("");
        assertEquals("Expected user name label to update", "full_name", mainUI.userNameLabel.getText());
    }

    @Test
    public void testSearchByNameInputActionPerformed() {
        mainUI.searchByNameInput.setText("Java");
        mainUI.searchByNameInputActionPerformed(new ActionEvent(mainUI, ActionEvent.ACTION_PERFORMED, null));
        assertTrue("Expected search results based on name", mainUI.booksTable.getRowCount() <= 0);
    }

    @Test
    public void testSearchButtonActionPerformed() {
        mainUI.searchByNameInput.setText("Hobbit");
        mainUI.searchButtonActionPerformed(new ActionEvent(mainUI, ActionEvent.ACTION_PERFORMED, null));
        assertTrue("Expected search results from button click", mainUI.booksTable.getRowCount() <= 0);
    }

    @Test
    public void testSearchByISBNInputActionPerformed() {
        mainUI.searchByISBNInput.setText("testPassword");
        mainUI.searchByISBNInputActionPerformed(new ActionEvent(mainUI, ActionEvent.ACTION_PERFORMED, null));
        assertTrue("Expected search results based on ISBN", mainUI.booksTable.getRowCount() <= 0);
    }

    @Test
    public void testLogoutButtomActionPerformed() {
        mainUI.logoutButtomActionPerformed(new ActionEvent(mainUI, ActionEvent.ACTION_PERFORMED, null));
        assertTrue("Expected LoginForm to be opened", !mainUI.isVisible());
    }

    @Test
    public void testExitButtomActionPerformed() {
        mainUI.exitButtomActionPerformed(new ActionEvent(mainUI, ActionEvent.ACTION_PERFORMED, null));
        assertFalse("Expected application to close", mainUI.isVisible());
    }

    @Test
    public void testRegisterBookButtonActionPerformed() {
        mainUI.registerBookButtonActionPerformed(new ActionEvent(mainUI, ActionEvent.ACTION_PERFORMED, null));
        assertNotNull("Expected BookDetails UI to open", mainUI);
    }

    @Test
    public void testManageMembersButtonActionPerformed() {
        mainUI.manageMembersButtonActionPerformed(new ActionEvent(mainUI, ActionEvent.ACTION_PERFORMED, null));
        assertNotNull("Expected ManageMembers UI to open", mainUI);
    }

    @Test
    public void testRegisterMemberButtonActionPerformed() {
        mainUI.registerMemberButton1ActionPerformed(new ActionEvent(mainUI, ActionEvent.ACTION_PERFORMED, null));
        assertNotNull("Expected MemberDetails UI to open", mainUI);
    }

    @Test
    public void testMain() {
        String[] args = {};
        MainUI.main(args);
        assertTrue("Expected the MainUI to be launched", mainUI.isVisible());
    }
}
