package entities;

import java.util.ArrayList;

    public class postEntity {
    private Object postInfo;
    private ArrayList<String> postComment;
    private Integer postLiked;
    private static Integer countpostID = 0;
    private Integer postID;
    public postEntity(Object postInfo){
        this.postInfo = postInfo;
        postLiked = 0;
        postComment = new ArrayList<String>();
        countpostID ++;
        postID = countpostID;
    }
    public void updatePostComment(String newComment){
            postComment.add(newComment);
        }
    public void updatedPostLiked(){
            postLiked += 1;
        }
    public Integer getPostLiked() {
            return postLiked;
        }
    public Object getPostInfo() {
            return postInfo;
        }
    public ArrayList<String> getPostComment() {
            return postComment;
        }
    public Integer getId(){
        return this.postID;
    }
    }


