package entities;

import java.util.ArrayList;

    public class postEntity {
    private String postPicture;
    private String posttext;
    private ArrayList<String> postComment;
    private Integer postLiked;
    private static Integer countpostID = 0;
    private Integer postID;
    public postEntity(String posttext, String postPicture){
        this.postPicture = postPicture;
        this.posttext = posttext;
        postLiked = 0;
        postComment = new ArrayList<String>();
        countpostID ++;
        postID = countpostID;
    }
    public void updatePostComment(String newComment){
            postComment.add(newComment);
        }
    public void setPostLiked(Integer integer){
            postLiked = integer;
        }
    public Integer getPostLiked() {
            return postLiked;
        }
    public String getPosttext() {
            return posttext;
        }
    public ArrayList<String> getPostComment() {
            return postComment;
        }
    public Integer getId(){
        return this.postID;
    }
    public String getPostPicture(){
        return postPicture;
    }
    public String getPostInfo(){
        return this.posttext;
    }

    public void setPostID(Integer newPostID) {
    this.postID = newPostID;
    }
    public void setPostPicture(String postPicture){
    this.postPicture = postPicture;
    }
    }


