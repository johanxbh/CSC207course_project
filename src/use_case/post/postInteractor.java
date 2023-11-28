package use_case.post;

import entities.postEntity;
import interface_adapter.postPresenter;
import data_access.postDAO;
import java.util.ArrayList;
import java.util.List;

public class postInteractor implements postInputBoundary{
    private postOutputBoundary postPresenter;
    private postDAO dataAccessObj;
    public postInteractor(postOutputBoundary postPresenter, postDAO dataAccessObj){
        this.postPresenter = postPresenter;
        this.dataAccessObj = dataAccessObj;
    }
    @Override
    public void execute(PostinputData data) {
        if (data.getPostText() == ""){
            postPresenter.prepareFailView();
        }
        else{
            postEntity newPosts = new postEntity(data.getPostText());
            dataAccessObj.savePost(newPosts);
            postPresenter.prepareSuccessView(new postOutputData("successfully posted",newPosts));
        }
    }

    public void refreshPosts() {
        List<postEntity> latestPosts = dataAccessObj.getLatestPosts();
        // Here you can format or process the posts as needed before sending to the presenter
        postPresenter.prepareRefreshView(latestPosts);
    }
}
