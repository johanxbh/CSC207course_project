package app;
import interface_adapter.*;
import interface_adapter.cancel.cancelController;
import interface_adapter.cancel.cancelViewModel;
import view.postView;
import data_access.postDAO;
import use_case.post.postInteractor;
import use_case.post.postInputBoundary;
import interface_adapter.cancel.cancelPresenter;
import use_case.cancel.cancelInteractor;
public class postViewFactory {
    private postViewFactory(){}
    public static postView create(ViewManagerModel viewManagerModel, postDAO postDataAccessObj, postViewModel postViewModel, cancelViewModel cancelViewModel){
        postController postController = createPostController(viewManagerModel,postViewModel, postDataAccessObj);
        cancelPresenter cancelPresenter = new cancelPresenter(cancelViewModel);
        cancelInteractor cancelInteractor = new cancelInteractor(cancelPresenter);
        return new postView(postViewModel, cancelViewModel, postController, new cancelController(cancelInteractor));
    }
    public static postController createPostController(ViewManagerModel viewManagerModel, postViewModel postViewModel, postDAO postDataAccessObj){
        postPresenter postPresenter = new postPresenter(postViewModel, viewManagerModel);
        postInputBoundary postInteractor = new postInteractor(postPresenter,postDataAccessObj);
        return new postController(postInteractor);
    }
}
