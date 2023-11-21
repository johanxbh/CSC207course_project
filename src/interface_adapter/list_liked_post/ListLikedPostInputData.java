package interface_adapter.list_liked_post;

import entities.User;

import java.util.ArrayList;

public class ListLikedPostInputData {
    private User user;
    private ArrayList<Integer> likedpost;
    public ListLikedPostInputData(User user){
        this.user = user;
        this.likedpost = user.getLikedPost();
    }
    public ArrayList<Integer> getLikedPostList(){
        return likedpost;
    }
}
