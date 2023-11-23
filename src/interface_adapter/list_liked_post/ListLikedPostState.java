package interface_adapter.list_liked_post;

import java.util.ArrayList;
import entities.postEntity;
public class ListLikedPostState {
    private ArrayList<postEntity> listOfLikedPost = new ArrayList<postEntity>();
    private String postError = null;
    public ListLikedPostState(ListLikedPostState listLikedPostState){
        this.listOfLikedPost = listLikedPostState.listOfLikedPost;
    }
    public ListLikedPostState(){}
    public ArrayList<postEntity> getListOfLikedPostArray(){
        return listOfLikedPost;
    }

    public void setPostError(String postError) {
        this.postError = postError;
    }
    public String getPostError(){
        return this.postError;
    }
}
