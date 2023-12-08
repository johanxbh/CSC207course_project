package use_case.list_liked_post;

public interface ListLikedPostOutputBoundary {
    void prepareSuccessView(ListLikedPostOutputData listLikedPostOutputData);
    void prepareFailView(String s);
}
