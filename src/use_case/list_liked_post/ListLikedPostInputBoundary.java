package use_case.list_liked_post;

import interface_adapter.list_liked_post.ListLikedPostInputData;

public interface ListLikedPostInputBoundary {
    void execute(ListLikedPostInputData inputData);
}
