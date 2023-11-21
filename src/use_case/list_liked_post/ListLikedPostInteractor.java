package use_case.list_liked_post;
import data_access.postDAO;
import interface_adapter.list_liked_post.ListLikedPostInputData;

public class ListLikedPostInteractor implements ListLikedPostInputBoundary{
    private postDAO postdao;
    @Override
    public void execute(ListLikedPostInputData inputData) {

    }
}
