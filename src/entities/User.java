package entities;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

public class User {
    private final int id;
    private HashMap<Integer, Integer> likedpost = new HashMap<Integer, Integer>();

    public User(int userId) {
        this.id = userId;
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

    public int getName() {
        return id;
    }
}