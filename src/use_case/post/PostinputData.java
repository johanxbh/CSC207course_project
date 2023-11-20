package use_case.post;

public class PostinputData {
    private String postText;
    public PostinputData(String text){
        postText = text;
    }
    public String getPostText(){
        return postText;
    }
}
