package DB;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MockedUsers implements UserFetcher {
    @Override
    public List<HashMap> getUser(String userName) {
        List<HashMap> mockedUsers = new ArrayList<>();

        if ("testUser".equals(userName)) {
            HashMap<String, String> user = new HashMap<>();
            user.put("full_name", "Test User");
            user.put("user_name", "testUser");
            mockedUsers.add(user);
        }

        return mockedUsers;
    }
}
