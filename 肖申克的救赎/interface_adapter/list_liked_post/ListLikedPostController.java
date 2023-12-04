package interface_adapter.list_liked_post;

import use_case.list_liked_post.ListLikedPostInputBoundary;
import entities.User;

import java.io.IOException;

public class ListLikedPostController {
    private final ListLikedPostInputBoundary listLikedPostInteractor;
    public ListLikedPostController(ListLikedPostInputBoundary listLikedPostInteractor){
        this.listLikedPostInteractor = listLikedPostInteractor;
    }
    public void execute(User user) throws IOException {
        ListLikedPostInputData listLikedPostInputData = new ListLikedPostInputData(user);
        listLikedPostInteractor.execute(listLikedPostInputData);
    }

}
