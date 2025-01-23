import UI.LoginForm;
import UI.MainUI;
import UI.RegisterForm;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class LoginFormTest {
    private LoginForm loginForm;

    @BeforeEach
    public void setUp() {
        loginForm = new LoginForm();
        loginForm.userNameInput = new javax.swing.JTextField();
        loginForm.passwordInput = new javax.swing.JPasswordField();
    }

    @Test
    public void testLoginButtonActionPerformed_ValidCredentials() {
        LoginForm loginForm = new LoginForm();
        loginForm.userNameInput.setText("testuser");
        loginForm.passwordInput.setText("testPassword");

        loginForm.loginhButton.doClick();

        assertFalse(loginForm.isDisplayable(), "LoginForm should be closed after successful login.");
        MainUI mainUI = new MainUI();
        assertTrue(mainUI.isDisplayable(), "MainUI should be opened and visible after successful login.");
    }

    @Test
    public void testLoginButtonActionPerformed_InvalidCredentials() {
        LoginForm loginForm = new LoginForm();
        loginForm.userNameInput.setText("invalidUser");
        loginForm.passwordInput.setText("wrongPassword");

        loginForm.loginhButton.doClick();

        assertTrue(loginForm.isDisplayable(), "LoginForm should remain open after failed login.");
        MainUI mainUI = new MainUI();
        assertFalse(mainUI.isVisible(), "MainUI should not open after failed login.");
    }

    @Test
    public void testRegisterButtonActionPerformed() {
        LoginForm loginForm = new LoginForm();
        loginForm.registerButton.doClick();

        RegisterForm registerForm = new RegisterForm();
        assertTrue(registerForm.isDisplayable(), "RegisterForm should be opened and visible.");
        assertFalse(loginForm.isDisplayable(), "LoginForm should be closed after clicking the Register button.");
    }


    @Test
    public void testLogin_ValidCredentials() {
        String validUsername = "testUser";
        String validPassword = "testPassword";

        loginForm.userNameInput.setText(validUsername);
        loginForm.passwordInput.setText(validPassword);
        loginForm.login();
        assertFalse(loginForm.isDisplayable(), "LoginForm should be disposed after successful login.");
    }

    @Test
    public void testLogin_WrongUsername() {
        String invalidUsername = "wrongUser";
        String validPassword = "testPassword";

        loginForm.userNameInput.setText(invalidUsername);
        loginForm.passwordInput.setText(validPassword);
        loginForm.login();
        assertTrue(loginForm.isDisplayable(), "LoginForm should remain visible after failed login due to incorrect username.");
    }

    @Test
    public void testLogin_WrongPassword() {
        String validUsername = "testUser";
        String invalidPassword = "wrongPassword";

        loginForm.userNameInput.setText(validUsername);
        loginForm.passwordInput.setText(invalidPassword);
        loginForm.login();
        assertTrue(loginForm.isDisplayable(), "LoginForm should remain visible after failed login due to incorrect password.");
    }


    @Test
    public void testLogin_InvalidCredentials() {
        String invalidUsername = "invaliduser";
        String invalidPassword = "invalidpassword";

        loginForm.userNameInput.setText(invalidUsername);
        loginForm.passwordInput.setText(invalidPassword);
        loginForm.login();
        assertTrue(loginForm.isDisplayable(), "LoginForm should remain visible after failed login.");
    }

    @Test
    public void testLogin_Successful() {

        LoginForm loginForm = new LoginForm();
        loginForm.userNameInput.setText("testuser");
        loginForm.passwordInput.setText("testPassword");

        loginForm.login();

        assertFalse(loginForm.isDisplayable(), "Login form should be closed after successful login.");
        MainUI mainUI = new MainUI();
        assertTrue(mainUI.isDisplayable(), "MainUI should be opened and visible after successful login.");
    }

    @Test
    public void testLogin_Failed() {
        LoginForm loginForm = new LoginForm();
        loginForm.userNameInput.setText("invalidUser");
        loginForm.passwordInput.setText("invalidPassword");

        loginForm.login();

        assertTrue(loginForm.isDisplayable(), "Login form should remain open after failed login.");
        MainUI mainUI = new MainUI();
        assertFalse(mainUI.isVisible(), "MainUI should not be opened after failed login.");
    }

}
