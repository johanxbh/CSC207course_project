package use_case.pull;
import java.util.List;
import entities.postEntity;

public class PullOutputData {
        private final List<postEntity> posts;

        public PullOutputData(List<postEntity> posts) {
            this.posts = posts;
        }

        public List<postEntity> getPosts() {
            return posts;
        }
    }


