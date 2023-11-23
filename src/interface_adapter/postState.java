package data_access;

public class postState {
    private String postText;
    public postState(){
    }
    public String getPostText(){
        return postText;
    }
    public postState(postState copy){
        this.postText = copy.getPostText();
    }
    public void setPostText(String newPostText){
        this.postText = newPostText;
    }
}
