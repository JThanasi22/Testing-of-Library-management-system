package Test; 

import DB.Users;
import UI.RegisterForm;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;


class UsersTest {

    // Authentication Testing

    @Test
    void testAuthenticateWithValidCredentials() {
        String validUsername = "testUser";
        String validPassword = "testPassword";
        assertTrue(new Users().authenticate(validUsername, validPassword));
    }

    @Test
    void testAuthenticateWithInvalidUsername() {
        String invalidUsername = "wrongUser";
        String validPassword = "testPassword";
        assertFalse(new Users().authenticate(invalidUsername, validPassword));
    }

    @Test
    void testAuthenticateWithInvalidPassword() {
        String validUsername = "testUser";
        String invalidPassword = "wrongPassword";
        assertFalse(new Users().authenticate(validUsername, invalidPassword));
    }

    @Test
    void testClassEvaluationForValidLogin() {
        String validUsername = "testUser";
        String validPassword = "testPassword";
        assertTrue(new Users().authenticate(validUsername, validPassword));

        String invalidUsername = "wrongUser";
        String invalidPassword = "wrongPassword";
        assertFalse(new Users().authenticate(invalidUsername, invalidPassword));
    }

    @Test
    void testSpecialCharacterHandlingForLogin() {
        String specialUsername = "test@User";
        String validPassword = "testPassword";
        assertFalse(new Users().authenticate(specialUsername, validPassword));
    }

    @Test
    void testAuthenticateCodeCoverage() {
        String validUsername = "testUser";
        String validPassword = "testPassword";
        String invalidPassword = "wrongPassword";
        String invalidUsername = "wrongUser";

        assertTrue(new Users().authenticate(validUsername, validPassword));
        assertFalse(new Users().authenticate(invalidUsername, validPassword));

        assertFalse(new Users().authenticate(validUsername, invalidPassword));
        assertFalse(new Users().authenticate(invalidUsername, invalidPassword));
    }

    // Password Hash testing

    @Test
    public void testPasswordHashCorrect() {
        String password = "testPassword123";
        String expectedHash = "a83fd3dbcde693af1f000650fe01f9ad"; 
        String actualHash = Users.passwordHash(password);

        assertEquals(expectedHash, actualHash, "The hashed password doesn't match the expected value.");
    }

    @Test
    public void testPasswordHashWrong() {
        String password = "testPassword123";
        String expectedWrongHash = "wrongExpectedHash"; 
        String actualHash = Users.passwordHash(password);

        assertNotEquals(expectedWrongHash, actualHash, "The hashed password unexpectedly matches the wrong expected value.");
    }
}