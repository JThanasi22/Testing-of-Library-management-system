import DB.Users;
import UI.LoginForm;
import UI.MainUI;
import UI.RegisterForm;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class LoginFormUnitTest {

    @Mock
    private Users mockUsers;

    @InjectMocks
    private LoginForm loginForm;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        loginForm.userNameInput = new javax.swing.JTextField();
        loginForm.passwordInput = new javax.swing.JPasswordField();
    }

    @Test
    public void testLoginButtonActionPerformed_ValidCredentials() {
        when(mockUsers.authenticate("testuser", "testPassword")).thenReturn(true);
        loginForm.userNameInput.setText("testuser");
        loginForm.passwordInput.setText("testPassword");

        loginForm.loginhButton.doClick();

        assertFalse(loginForm.isDisplayable(), "LoginForm should be closed after successful login.");
    }

    @Test
    public void testLoginButtonActionPerformed_InvalidCredentials() {
        when(mockUsers.authenticate("invalidUser", "wrongPassword")).thenReturn(false);
        loginForm.userNameInput.setText("invalidUser");
        loginForm.passwordInput.setText("wrongPassword");

        loginForm.loginhButton.doClick();

        assertTrue(loginForm.isDisplayable(), "LoginForm should remain open after failed login.");
    }

    @Test
    public void testRegisterButtonActionPerformed() {
        loginForm.registerButton.doClick();
        RegisterForm registerForm = new RegisterForm();
        assertTrue(registerForm.isDisplayable(), "RegisterForm should be opened and visible.");
        assertFalse(loginForm.isDisplayable(), "LoginForm should be closed after clicking the Register button.");
    }

    @Test
    public void testLogin_Successful() {
        when(mockUsers.authenticate("testuser", "testPassword")).thenReturn(true);
        loginForm.userNameInput.setText("testuser");
        loginForm.passwordInput.setText("testPassword");
        loginForm.login();

        assertFalse(loginForm.isDisplayable(), "Login form should be closed after successful login.");
    }

    @Test
    public void testLogin_Failed() {
        when(mockUsers.authenticate("invalidUser", "invalidPassword")).thenReturn(false);
        loginForm.userNameInput.setText("invalidUser");
        loginForm.passwordInput.setText("invalidPassword");
        loginForm.login();

        assertTrue(loginForm.isDisplayable(), "Login form should remain open after failed login.");
    }
}
