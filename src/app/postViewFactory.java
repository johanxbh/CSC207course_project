package app;
import interface_adapter.ViewManagerModel;
import view.postView;
import data_access.postDAO;
import interface_adapter.postViewModel;
import interface_adapter.postController;
import interface_adapter.postPresenter;
import use_case.post.postInteractor;
import use_case.post.postInputBoundary;
public class postViewFactory {
    private postViewFactory(){}
    public static postView create(ViewManagerModel viewManagerModel, postDAO postDataAccessObj, postViewModel postViewModel){
        postController postController = createPostController(viewManagerModel,postViewModel, postDataAccessObj);
        return new postView(postViewModel, postController);
    }
    public static postController createPostController(ViewManagerModel viewManagerModel, postViewModel postViewModel, postDAO postDataAccessObj){
        postPresenter postPresenter = new postPresenter(postViewModel, viewManagerModel);
        postInputBoundary postInteractor = new postInteractor(postPresenter,postDataAccessObj);
        return new postController(postInteractor);
    }
}
