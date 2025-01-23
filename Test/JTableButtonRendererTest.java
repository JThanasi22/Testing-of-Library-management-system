import static org.junit.jupiter.api.Assertions.*;

import UI.JTableButtonRenderer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.awt.*;

public class JTableButtonRendererTest {

    private JTableButtonRenderer renderer;

    @BeforeEach
    public void setUp() {
        renderer = new JTableButtonRenderer();
    }

    @Test
    public void testGetTableCellRendererComponent() {
        JTable table = new JTable();
        Object obj = "SampleData";

        Component component = renderer.getTableCellRendererComponent(table, obj, false, false, 0, 0);

        assertTrue(component instanceof JButton, "Component should be a JButton");
        assertEquals("Book Info", ((JButton) component).getText(), "Button text should be 'Book Info'");
        assertFalse(((JButton) component).getModel().isPressed(), "Button should not be pressed");
        assertFalse(((JButton) component).isSelected(), "Button should not be selected");
    }
}
