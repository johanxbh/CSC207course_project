package interface_adapter.like;

public class LikeState {

    private Boolean like;
    private Integer likeCount;


    public LikeState(Boolean like) {
        this.like = like;
    }

    public LikeState() {
    }

    public Boolean getLike() {
        return like;
    }

    public void setLike(Boolean like) {
        this.like = like;
    }

    public Integer getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(Integer likeCount) {
        this.likeCount = likeCount;
    }

}
