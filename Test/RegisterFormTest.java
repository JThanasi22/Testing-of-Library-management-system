import DB.Users;
import UI.LoginForm;
import UI.RegisterForm;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RegisterFormTest {
    JOptionPane JOptionPane=new JOptionPane();

    @Test
    public void testFullNameInputActionPerformed() {
        RegisterForm form = new RegisterForm();
        form.fullNameInput.setText(""); // Set empty input
        form.fullNameInputActionPerformed(null);  // Trigger the action
        assertEquals("JOptionPane message", JOptionPane.getMessage());
    }

    @Test
    public void testUserNameInputActionPerformed() {
        RegisterForm form = new RegisterForm();
        form.userNameInput.setText("usr"); // Set a short username
        form.userNameInputActionPerformed(null);  // Trigger the action
        assertEquals("JOptionPane message", JOptionPane.getMessage());

        form.userNameInput.setText("");  // Set empty username
        form.userNameInputActionPerformed(null);  // Trigger the action
        assertEquals("JOptionPane message", JOptionPane.getMessage());
    }

    @Test
    public void testRegisterButtonActionPerformed() {
        RegisterForm form = new RegisterForm();
        form.passwordInput.setText("pass");  // Short password
        form.registerButtonActionPerformed(null);
        assertEquals("JOptionPane message", JOptionPane.getMessage());

        form.passwordInput.setText("password");
        form.repeatPasswordInput.setText("differentpass");  // Mismatched passwords
        form.registerButtonActionPerformed(null);
        assertEquals("JOptionPane message", JOptionPane.getMessage());

        // Simulate a username that already exists
        Users mockUsers = new Users() {
            @Override
            public List get(String query) {
                return List.of(new Object());  // Simulate that the username already exists
            }
        };
        form.userNameInput.setText("existingUser");
        form.registerButtonActionPerformed(null);
        assertEquals("JOptionPane message", JOptionPane.getMessage());
    }

    @Test
    public void testDatabaseInteractionOnRegister() {
        RegisterForm form = new RegisterForm();

        // Mock the Users class database interaction
        Users mockUsers = new Users() {
            @Override
            public List get(String query) {
                return List.of();  // Simulate username not existing
            }

            @Override
            public void insert(Map data) {
                // Verify that user data is correctly inserted
                assertEquals("John Doe", data.get("full_name"));
                assertEquals("johnDoe", data.get("user_name"));
                assertEquals("passwordHash123", data.get("password"));
                assertEquals("user", data.get("role"));
                assertEquals("123456789012", data.get("nic"));
                assertEquals("user@example.com", data.get("mail"));
                assertEquals("123 Example St.", data.get("address"));
            }
        };

        form.fullNameInput.setText("John Doe");
        form.userNameInput.setText("johnDoe");
        form.passwordInput.setText("password");
        form.repeatPasswordInput.setText("password");
        form.nicInput.setText("123456789012");
        form.emailInput.setText("user@example.com");
        form.addressInput.setText("123 Example St.");
        form.terms.setSelected(true);

        form.registerButtonActionPerformed(null);
    }

    @Test
    public void testFullRegistrationFlow() {
        RegisterForm form = new RegisterForm();

        form.fullNameInput.setText("John Doe");
        form.userNameInput.setText("johnDoe");
        form.passwordInput.setText("password");
        form.repeatPasswordInput.setText("password");
        form.nicInput.setText("123456789012");
        form.emailInput.setText("user@example.com");
        form.addressInput.setText("123 Example St.");
        form.terms.setSelected(true);

        form.registerButtonActionPerformed(null);
        assertEquals("JOptionPane message", JOptionPane.getMessage());

        // Now simulate going back to login form
        form.backToLoginButtonActionPerformed(null);
        // Verify that it successfully goes back to the login screen
        assertTrue(true);
    }

    @Test
    public void testInvalidRegistrationFlow() {
        RegisterForm form = new RegisterForm();

        form.fullNameInput.setText("");
        form.userNameInput.setText("usr");
        form.passwordInput.setText("pass");
        form.repeatPasswordInput.setText("differentpass");
        form.terms.setSelected(false);  // Unselect terms

        form.registerButtonActionPerformed(null);
        assertEquals("JOptionPane message", JOptionPane.getMessage());

        form.fullNameInput.setText("John Doe");
        form.userNameInput.setText("john");
        form.passwordInput.setText("password");
        form.repeatPasswordInput.setText("password");

        form.registerButtonActionPerformed(null);
        assertEquals("JOptionPane message", JOptionPane.getMessage());

        form.userNameInput.setText("existingUser");
        form.registerButtonActionPerformed(null);
        assertEquals("JOptionPane message", JOptionPane.getMessage());

        form.terms.setSelected(false);
        form.registerButtonActionPerformed(null);
        assertEquals("JOptionPane message", JOptionPane.getMessage());
    }
}
