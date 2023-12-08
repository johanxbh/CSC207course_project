package use_case.pull;

import java.io.IOException;

public interface PullInputBoundary {
    void refreshPosts() throws IOException;
}
