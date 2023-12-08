package interface_adapter.post;

import use_case.post.PostinputData;
import use_case.post.postInputBoundary;

import java.io.IOException;

public class postController {
    private postInputBoundary postInteractor;
    public postController(postInputBoundary postInteractor){
        this.postInteractor = postInteractor;
    }
    public void execute(String textInformation, String postPictre) throws IOException {
        System.out.println("from input data" + postPictre);
        postInteractor.execute(new PostinputData(textInformation, postPictre));

    }
}
