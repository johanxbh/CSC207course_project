package use_case.list_liked_post;

import data_access.postDAO;
import entities.postEntity;
import interface_adapter.list_liked_post.ListLikedPostInputData;

import java.io.IOException;
import java.util.ArrayList;
public class ListLikedPostInteractor implements ListLikedPostInputBoundary{
    private postDAO postdao;
    private ListLikedPostOutputBoundary listLikedPostPresenter;
    public ListLikedPostInteractor(postDAO postdao, ListLikedPostOutputBoundary listLikedPostPresenter){
        this.postdao = postdao;
        this.listLikedPostPresenter = listLikedPostPresenter;
    }
    @Override
    public void execute(ListLikedPostInputData inputData) throws IOException {
        System.out.println(inputData.getLikedPostList().size());
        if (inputData.getLikedPostList() == null){
            listLikedPostPresenter.prepareFailView("No liked post");
        }
        else {
        ArrayList<Integer> likedlist = inputData.getLikedPostList();
        if (likedlist.size() == 0){
            listLikedPostPresenter.prepareFailView("No liked post");
        }
        else{
        ArrayList<postEntity> likedPost = new ArrayList<postEntity>();
        for (Integer i: likedlist) {
            likedPost.add(postdao.getPost(i));
        }
        ListLikedPostOutputData listLikedPostOutputData = new ListLikedPostOutputData(likedPost);
        listLikedPostPresenter.prepareSuccessView(listLikedPostOutputData);
        }
    }}
}
