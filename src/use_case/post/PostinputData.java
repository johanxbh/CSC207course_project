package use_case.post;

public class PostinputData {
    private String postText;
    private String postPicture;
    public PostinputData(String text, String postPicture){
        this.postPicture = postPicture;
        postText = text;
    }
    public String getPostText(){
        return postText;
    }

    public String getPostPicture() {
        return postPicture;
    }
}
