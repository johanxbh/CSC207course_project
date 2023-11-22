package use_case.list_liked_post;

import interface_adapter.list_liked_post.ListLikedPostInputData;

import java.util.ArrayList;
import entities.postEntity;
public class ListLikedPostOutputData {
    private ArrayList<postEntity> listOfPost;
    public ListLikedPostOutputData(ArrayList<postEntity> listOfPost){
        this.listOfPost = listOfPost;
    }
}
