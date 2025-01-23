import UI.JTableButtonEditor;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.util.logging.Logger;
public class JTableButtonEditorTest {

        private JTableButtonEditor editor;
        private JTextField textField;
        private JFrame mockFrame;

        @BeforeEach
        public void setUp() {
            textField = new JTextField();
            mockFrame = new JFrame();
            editor = new JTableButtonEditor(textField, mockFrame);
        }

        @Test
        public void testGetTableCellEditorComponent() {
            JTable table = new JTable();
            Object obj = "SampleBook";
            Component component = editor.getTableCellEditorComponent(table, obj, false, 0, 0);

            assertTrue(component instanceof JButton, "Component should be a JButton");
            assertEquals("Book Info", ((JButton) component).getText(), "Button text should be 'Book Info'");
        }

        @Test
        public void testGetCellEditorValue() {
            JTable table = new JTable();
            Object obj = "Books";
            editor.getTableCellEditorComponent(table, obj, false, 0, 0);  // Simulate table cell editing

            Object value = editor.getCellEditorValue();

            assertEquals("TestBook", value, "The editor value should match the button label");
        }

        @Test
        public void testStopCellEditing() {
            JTable table = new JTable();
            Object obj = "BookToStopEditing";
            editor.getTableCellEditorComponent(table, obj, false, 0, 0);  // Simulate table cell editing

            assertTrue(editor.stopCellEditing(), "stopCellEditing should return true");
        }

        @Test
        public void testFireEditingStopped() {
            JTable table = new JTable();
            Object obj = "FireEditStopped";
            editor.getTableCellEditorComponent(table, obj, false, 0, 0);  // Simulate table cell editing

            editor.fireEditingStopped();  // Manually trigger editing stopped

            // You can't directly test visual behavior, but you can ensure it doesn't throw exceptions
            assertDoesNotThrow(() -> editor.fireEditingStopped(), "fireEditingStopped should not throw exceptions");
        }
}
