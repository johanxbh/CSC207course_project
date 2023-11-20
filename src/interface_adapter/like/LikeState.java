package interface_adapter.like;

public class LikeState {

    private Boolean Like;
    private Integer LikeCount;


    public LikeState(Boolean Like) {
        this.Like = Like;
    }

    public LikeState() {
    }

    public Boolean getLike() {
        return Like;
    }

    public void setLike(Boolean Like) {
        this.Like = Like;
    }

    public Integer getLikeCount() {
        return LikeCount;
    }

    public void setLikeCount(Integer LikeCount) {
        this.LikeCount = LikeCount;
    }

    public void updateLikeCount() {
        LikeCount += 1;
    }

    public void updateLikeState() {
        Like = !Like;
    }

    public void updateLikeState(Boolean Like) {
        this.Like = Like;
    }

    public void updateLikeCount(Integer LikeCount) {
        this.LikeCount = LikeCount;
    }
}
