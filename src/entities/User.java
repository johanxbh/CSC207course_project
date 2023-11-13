package entities;

import java.util.HashMap;

public class User {
    final String id;
    HashMap<Integer, Integer> likedpost;

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

}
