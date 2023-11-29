package interface_adapter;
import use_case.post.postInputBoundary;
import use_case.post.postInteractor;
import use_case.post.postOutputBoundary;
import data_access.postDAO;
import use_case.post.PostinputData;
public class postController {
    private postInputBoundary postInteractor;
    public postController(postInputBoundary postInteractor){
        this.postInteractor = postInteractor;
    }
    public void execute(String textInformation){
        postInteractor.execute(new PostinputData(textInformation));
    }
}
