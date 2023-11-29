package interface_adapter;

public class postState {
    private String postText;
    private String postError;
    private String postSuccess;
    private String postInputText;
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

    public void setPostError(String postError) {
        this.postError = postError;
    }

    public String getPostError() {
        return postError;
    }
    public String getPostSuccess(){
        return postSuccess;
    }
    public void setPostSuccess(String c){
        postSuccess = c;
    }

    public String getPostInputText() {
        return postInputText;
    }
    public void setPostInputText(String text){
        postInputText = text;
    }
}
