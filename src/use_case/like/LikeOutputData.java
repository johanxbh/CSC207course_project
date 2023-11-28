package use_case.like;

public class LikeOutputData {
    private final int likeCount;

    public LikeOutputData(int likeCount) {
        this.likeCount = likeCount;
    }

    public int getLikeCount() {
        return likeCount;
    }
}
