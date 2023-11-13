package data_access;

import entities.postEntity;

import java.util.ArrayList;

public class InMemoryDataAccessObject implements postDAO{
    private ArrayList<postEntity> posts;
    private InMemoryDataAccessObject(){
        posts = new ArrayList<postEntity>();
    }
    @Override
    public ArrayList<postEntity> getMostRecentPost() {
        return posts;
    }

    @Override
    public void savePost(postEntity post) {
        posts.add(post);
    }

    @Override
    public void cleanAllPost() {
        posts.clear();
    }

    @Override
    public postEntity getMostPopularPost() {
        int i = 0;
        postEntity mostPopular = posts.get(0);
        while (i < posts.size()){
            if (posts.get(i).getPostLiked() > mostPopular.getPostLiked()){
                mostPopular = posts.get(i);
            }
        }
        return mostPopular;
    }
}