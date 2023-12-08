package use_case.post;
import entities.postEntity;

public class postOutputData {
    private postEntity post;
    public postOutputData(postEntity post){
        this.post = post;
    }
    public postEntity getPost() {
        return post;
    }
}
