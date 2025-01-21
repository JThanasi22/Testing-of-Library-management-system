import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import DB.Members;

public class MembersTest {

    private Members members;

    @BeforeEach
    public void setUp() {
        members = new Members();
    }

    @Test
    public void testMembersGetTableName() {
        assertEquals("members", members.getTableName(), "Should return 'members' as the table name");
    }
}
