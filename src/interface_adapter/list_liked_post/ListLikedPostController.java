package interface_adapter.list_liked_post;

import use_case.list_liked_post.ListLikedPostInputBoundary;
import entities.User;

public class ListLikedPostController {
    private final ListLikedPostInputBoundary listLikedPostInteractor;
    public ListLikedPostController(ListLikedPostInputBoundary listLikedPostInteractor){
        this.listLikedPostInteractor = listLikedPostInteractor;
    }
    public void execute(User user){
        ListLikedPostInputData listLikedPostInputData = new ListLikedPostInputData(user);
        listLikedPostInteractor.execute(listLikedPostInputData);
    }

}
