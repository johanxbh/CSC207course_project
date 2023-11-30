package interface_adapter.post_plaza;

import entities.postEntity;

import java.util.HashMap;
import java.util.Map;

public class PostPlazaState {
    private Map<Integer, postEntity> postMap = new HashMap<Integer, postEntity>();
    private String postPlazaError = null;
    private Boolean havePost = false;
    private String CLOSE_ERROR = "close";

    public PostPlazaState(PostPlazaState postPlazaState){
        this.postMap = postPlazaState.postMap;
        this.havePost = this.updateHavePost();
    }
    public PostPlazaState(){}
    public Boolean updateHavePost(){
        return havePost = ! this.postMap.isEmpty();
    }
    public void setPostMap(Map<Integer, postEntity> newPostMap){
        this.postMap = newPostMap;
        this.updateHavePost();
    }
    public String getPostPlazaError(){
        return this.postPlazaError;
    }
    public void setPostPlazaError(String error){
        this.postPlazaError = error;
    }
    public Map<Integer, postEntity> getPostMap(){
        return this.postMap;
    }

}
