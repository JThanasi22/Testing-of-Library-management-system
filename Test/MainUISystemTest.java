import DB.LendFacade;
import UI.BookDetails;
import UI.MainUI;
import org.junit.Before;
import org.junit.Test;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

import static org.junit.Assert.*;

public class MainUISystemTest {

    private MainUI mainUI;

    @Before
    public void setUp() {
        mainUI = new MainUI();
    }

    @Test
    public void testIntegrationRendererAndEditor() {
        // Step 1: Set up the table with the renderer and editor
        JTable table = new JTable();
        DefaultTableModel model = new DefaultTableModel(new Object[][]{{"Available,1"}}, new String[]{"Status"});
        table.setModel(model);

        table.getColumn("Status").setCellRenderer(new MainUI.LendButtonRenderer());
        table.getColumn("Status").setCellEditor(new MainUI.LendButtonEditor(new JTextField(), mainUI));

        // Step 2: Verify initial rendering
        JButton button = (JButton) table.getCellRenderer(0, 0)
                .getTableCellRendererComponent(table, model.getValueAt(0, 0), false, false, 0, 0);
        assertEquals("Lend", button.getText());

        // Step 3: Simulate editing the first cell (Available)
        table.editCellAt(0, 0);
        Component editorComponent = table.getEditorComponent();
        assertTrue(editorComponent instanceof JButton);

        JButton btn = (JButton) editorComponent;
        btn.doClick();  // Simulate button click

        // Step 4: Confirm action
        Object value = table.getCellEditor();
        assertNull(value);

        // Step 5: Change status to "Unavailable" and recheck
        model.setValueAt("Unavailable,2", 0, 0);
        table.editCellAt(0, 0);
        editorComponent = table.getEditorComponent();
        btn = (JButton) editorComponent;
        btn.doClick();  // Simulate button click

        value = table.getCellEditor();
        assertNull(value);
    }

    @Test
    public void testSystemBehaviorOnBookLending() {
        // Simulating the system's end-to-end behavior when a button is clicked
        JTable table = new JTable();
        DefaultTableModel model = new DefaultTableModel(new Object[][]{{"Available,1"}}, new String[]{"Status"});
        table.setModel(model);

        table.getColumn("Status").setCellRenderer(new MainUI.LendButtonRenderer());
        table.getColumn("Status").setCellEditor(new MainUI.LendButtonEditor(new JTextField(), mainUI));

        table.editCellAt(0, 0);
        Component editorComponent = table.getEditorComponent();
        JButton btn = (JButton) editorComponent;
        btn.doClick();  // Simulate lending action

        model.setValueAt("Unavailable,2", 0, 0);
        table.editCellAt(0, 0);
        editorComponent = table.getEditorComponent();
        btn = (JButton) editorComponent;
        btn.doClick();  // Simulate return action

        assertTrue(LendFacade.returnBook(2));  // Assuming returnBook method successfully returns the book
    }
}
