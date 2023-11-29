package use_case.like;
import entities.postEntity;
public class LikeInputData {
    private postEntity postEntity;

    public LikeInputData(postEntity postEntity) {
        this.postEntity = postEntity;
    }

    public postEntity getLikePost() {
        return this.postEntity;
    }
}
