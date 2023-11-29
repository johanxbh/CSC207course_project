package use_case.post;

import entities.postEntity;
import interface_adapter.postPresenter;
import data_access.postDAO;

public class postInteractor implements postInputBoundary{
    private postOutputBoundary postPresenter;
    private postDAO dataAccessObj;
    public postInteractor(postOutputBoundary postPresenter, postDAO dataAccessObj){
        this.postPresenter = postPresenter;
        this.dataAccessObj = dataAccessObj;
    }
    @Override
    public void execute(PostinputData data) {
        if (data.getPostText() == null){
            postPresenter.prepareFailView();
        }
        else{
            postEntity newPosts = new postEntity(data.getPostText());
            dataAccessObj.savePost(newPosts);
            postPresenter.prepareSuccessView(new postOutputData(data.getPostText(), newPosts));
        }
    }
}
