import static org.junit.jupiter.api.Assertions.*;
import DB.Users;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.List;
import static org.mockito.Mockito.*;

public class UsersUnitTest {

    @Mock
    private Users mockUsers;

    @InjectMocks
    private Users users;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    // Authentication Testing

    @Test
    void testAuthenticateWithValidCredentials() {
        String validUsername = "testUser";
        String validPassword = "testPassword";

        when(mockUsers.authenticate(validUsername, validPassword)).thenReturn(true);

        assertTrue(users.authenticate(validUsername, validPassword));
    }

    @Test
    void testAuthenticateWithInvalidUsername() {
        String invalidUsername = "wrongUser";
        String validPassword = "testPassword";

        when(mockUsers.authenticate(invalidUsername, validPassword)).thenReturn(false);

        assertFalse(users.authenticate(invalidUsername, validPassword));
    }

    @Test
    void testAuthenticateWithInvalidPassword() {
        String validUsername = "testUser";
        String invalidPassword = "wrongPassword";

        when(mockUsers.authenticate(validUsername, invalidPassword)).thenReturn(false);

        assertFalse(users.authenticate(validUsername, invalidPassword));
    }

    @Test
    void testAuthenticateWithSpecialCharacters() {
        String specialUsername = "test@User";
        String validPassword = "testPassword";

        when(mockUsers.authenticate(specialUsername, validPassword)).thenReturn(false);

        assertFalse(users.authenticate(specialUsername, validPassword));
    }

    @Test
    void testAuthenticateCodeCoverage() {
        String validUsername = "testUser";
        String validPassword = "testPassword";
        String invalidPassword = "wrongPassword";
        String invalidUsername = "wrongUser";

        when(mockUsers.authenticate(validUsername, validPassword)).thenReturn(true);
        when(mockUsers.authenticate(invalidUsername, validPassword)).thenReturn(false);
        when(mockUsers.authenticate(validUsername, invalidPassword)).thenReturn(false);
        when(mockUsers.authenticate(invalidUsername, invalidPassword)).thenReturn(false);

        assertTrue(users.authenticate(validUsername, validPassword));
        assertFalse(users.authenticate(invalidUsername, validPassword));
        assertFalse(users.authenticate(validUsername, invalidPassword));
        assertFalse(users.authenticate(invalidUsername, invalidPassword));
    }
}