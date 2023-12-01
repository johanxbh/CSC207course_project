package data_access;

import entities.postEntity;

import java.util.List;
public class postDataAccessObject implements postDAO{
    String API_TOKEN;
    public postDataAccessObject(String api){
        this.API_TOKEN = api;

    }
    @Override
    public postEntity getMostRecentPost() {
        return null;
    }

    @Override
    public void savePost(postEntity post) {

    }

    @Override
    public void cleanAllPost() {

    }

    @Override
    public postEntity getMostPopularPost() {
        return null;
    }

    @Override
    public postEntity getPost(Integer postid) {
        return null;
    }

    @Override
    public List<postEntity> getLatestPosts() {
        return null;
    }

    @Override
    public postEntity getlatestPost(List<postEntity> posts) {
        return null;
    }
}
