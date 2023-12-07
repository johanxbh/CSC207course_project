package interface_adapter.list_liked_post;

import java.util.ArrayList;
import entities.postEntity;
public class ListLikedPostState {
    private ArrayList<postEntity> listOfLikedPost = new ArrayList<postEntity>();
    private String postError = null;
    private Boolean haveLikedPostState;
    public ListLikedPostState(ListLikedPostState listLikedPostState){
        this.listOfLikedPost = listLikedPostState.listOfLikedPost;
        this.haveLikedPostState = false;
    }
    public ListLikedPostState(){}
    public ArrayList<postEntity> getListOfLikedPostArray(){
        return listOfLikedPost;
    }
    public void setListOfLikedPostArray(ArrayList<postEntity> listOfLikedPost){
        for(postEntity i: listOfLikedPost){
            this.listOfLikedPost.add(i);
        }
    }
    public void setPostError(String postError) {
        this.postError = postError;
    }
    public String getPostError(){
        return this.postError;
    }
    public Boolean getHaveLikedPostState(){
        return haveLikedPostState;
    }
    public void setHaveLikedPostState(Boolean state){haveLikedPostState = state;}

}
