package use_case.post;

public interface postOutputBoundary {
    void prepareSuccessView(postOutputData data);
    void prepareFailView();
}
