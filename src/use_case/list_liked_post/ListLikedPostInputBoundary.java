package use_case.list_liked_post;

import interface_adapter.list_liked_post.ListLikedPostInputData;

import java.io.IOException;

public interface ListLikedPostInputBoundary {
    void execute(ListLikedPostInputData inputData) throws IOException;
}
