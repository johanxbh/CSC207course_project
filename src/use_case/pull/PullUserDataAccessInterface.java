package use_case.pull;

import java.util.List;

import entities.postEntity;
public interface PullUserDataAccessInterface {
    List<postEntity> fetchNewestPosts();
}
