package DB;

import java.util.HashMap;
import java.util.List;

public interface UserFetcher {
    List<HashMap> getUser(String userName);
}