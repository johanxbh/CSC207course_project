package interface_adapter.post;

public class postState {
    private String postInfo;
    private String postError;
    private String postSuccess;
    private String postInputText;
    private String postPictureText;
    public postState(){
    }
    public String getPostInfo(){
        return postInfo;
    }
    public postState(postState copy){
        this.postInfo = copy.getPostInfo();
    }
    public void setPostText(String newPostText){
        this.postInfo = newPostText;
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
    public void setPostPictureText(String text){postPictureText = text;}

    public String getPostPictureText() {
        return postPictureText;
    }
}
