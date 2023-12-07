package use_case.like;
import entities.User;
import entities.postEntity;
public class LikeInputData {
    private postEntity postEntity;
    private User user;

    public LikeInputData(postEntity postEntity, User user) {
        this.postEntity = postEntity;
        this.user = user;
    }

    public postEntity getLikePost() {
        return this.postEntity;
    }
    public User getUser(){return this.user;}
}
