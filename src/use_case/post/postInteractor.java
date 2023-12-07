package use_case.post;

import entities.postEntity;
import data_access.postDAO;

import java.io.IOException;
import java.util.List;

public class postInteractor implements postInputBoundary{
    private postOutputBoundary postPresenter;
    private postDAO dataAccessObj;
    public postInteractor(postOutputBoundary postPresenter, postDAO dataAccessObj){
        this.postPresenter = postPresenter;
        this.dataAccessObj = dataAccessObj;
    }
    @Override
    public void execute(PostinputData data) throws IOException {
        if (data.getPostText() == null && data.getPostPicture() == null){
            postPresenter.prepareFailView();
        }
        else{

            System.out.println("from input data" + data.getPostPicture());
            postEntity newPosts = new postEntity(data.getPostText(), data.getPostPicture());
            dataAccessObj.savePost(newPosts);
            postPresenter.prepareSuccessView(new postOutputData(newPosts));
        }
    }

    public void refreshPosts() {
        List<postEntity> latestPosts = dataAccessObj.getLatestPosts();
        // Here you can format or process the posts as needed before sending to the presenter
        postPresenter.prepareRefreshView(latestPosts);
    }
}
