package interface_adapter.post_plaza;

import entities.postEntity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PostPlazaState {
    private Map<Integer, postEntity> postMap = new HashMap<Integer, postEntity>();
    private String postPlazaError = null;
    private Boolean havePost = false;
    private String CLOSE_ERROR = "close";
    private Boolean needUpdate = false;
    private Boolean showListedLikedPost = false;

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
    public void setNeedUpdate(Boolean update){
        this.needUpdate = update;
    }
    public boolean getNeedUpdate(){
        return this.needUpdate;
    }
    public void setPostList(List<postEntity> postList){
        HashMap<Integer, postEntity> newPostMap = new HashMap<Integer, postEntity>();
        for(postEntity i: postList){
            newPostMap.put(i.getId(), i);
        }
        setPostMap(newPostMap);
    }

    public void setShowListedLikedPost(Boolean showListedLikedPost) {
        this.showListedLikedPost = showListedLikedPost;
    }
    public boolean getShowListedLikedPost(){
        return this.showListedLikedPost;
    }
}
