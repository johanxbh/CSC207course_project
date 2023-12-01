package interface_adapter.post;
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
    public void execute(String textInformation, String postPictre){
        System.out.println("from input data" + postPictre);
        postInteractor.execute(new PostinputData(textInformation, postPictre));
    }
}
