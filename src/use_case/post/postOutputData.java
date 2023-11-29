package use_case.post;
import entities.postEntity;

public class postOutputData {
    private String value;
    private postEntity post;
    public postOutputData(String value, postEntity post){
        this.post = post;
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
