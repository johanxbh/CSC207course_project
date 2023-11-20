package interface_adapter;
import use_case.post.postOutputBoundary;
import use_case.post.postOutputData;

public class postPresenter implements postOutputBoundary{
    @Override
    public void prepareSuccessView(postOutputData data) {

    }

    @Override
    public void prepareFailView() {

    }
}
