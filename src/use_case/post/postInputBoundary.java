package use_case.post;

import java.io.IOException;

public interface postInputBoundary {
    void execute(PostinputData data) throws IOException;
}
