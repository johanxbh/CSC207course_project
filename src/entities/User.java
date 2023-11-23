package entities;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

public class User {
    private final String id;
    private HashMap<Integer, Integer> likedpost;

    public User(String id) {
        this.id = id;
    }
    public void addLikedPost(postEntity newLikedPost){
        Integer postid = newLikedPost.getId();
        this.likedpost.put(postid, 1);
    }
    public boolean checkedLikedPost(postEntity newPossiblePost){
        Integer postid = newPossiblePost.getId();
        return likedpost.containsKey(postid);
    }
    public ArrayList<Integer> getLikedPost(){
        Collection<Integer> keys = likedpost.keySet();
        return new ArrayList<Integer>(keys);
    }

}
