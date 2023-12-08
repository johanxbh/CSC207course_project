package view;

import entities.User;
import entities.postEntity;
import interface_adapter.ViewManagerModel;
import interface_adapter.comment.CommentViewModel;
import interface_adapter.like.LikeController;
import interface_adapter.list_liked_post.ListLikedPostController;
import interface_adapter.list_liked_post.ListLikedPostState;
import interface_adapter.list_liked_post.ListLikedPostViewModel;
import interface_adapter.post.postState;
import interface_adapter.post.postViewModel;
import interface_adapter.post_plaza.PostPlazaState;
import interface_adapter.post_plaza.PostPlazaViewModel;
import interface_adapter.pull.PullController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static interface_adapter.post_plaza.PostPlazaViewModel.CHECK_LIKED_POST_LABEL;
import static interface_adapter.post_plaza.PostPlazaViewModel.LIKE_LABEL;

public class PostPlazaView extends JPanel implements ActionListener, PropertyChangeListener {
    public final String viewName = "post plaza";
    private final PostPlazaViewModel postPlazaViewModel;
    private final CommentViewModel commentViewModel;
    private final ViewManagerModel viewManagerModel;
    private final ListLikedPostViewModel listLikedPostViewModel;
    private final postViewModel postViewModel;
    private final LikeController likeController;
    private final ListLikedPostController listLikedPostController;
    private final PullController pullController;
    private final postView postView;
    private final CommentView commentView;
    private final ListLikedPostView listLikedPostView;

    private Dimension size;
    private JFrame openedJFrame;
    private boolean haveOpenedJFrame;
    private final User user;


    public PostPlazaView(CommentViewModel commentViewModel,
                         PostPlazaViewModel postPlazaViewModel,
                         ViewManagerModel viewManagerModel,
                         ListLikedPostViewModel listLikedPostViewModel,
                         postViewModel postViewModel,
                         LikeController likeController,
                         ListLikedPostController listLikedPostController,
                         PullController pullController,
                         postView postView,
                         CommentView commentView,
                         ListLikedPostView listLikedPostView,
                         User user) {
        this.commentViewModel = commentViewModel;
        commentViewModel.addPropertyChangeListener(this);
        this.postPlazaViewModel = postPlazaViewModel;
        this.postViewModel = postViewModel;
        postViewModel.addPropertyChangeListener(this);

        this.viewManagerModel = viewManagerModel;
        this.listLikedPostViewModel = listLikedPostViewModel;
        listLikedPostViewModel.addPropertyChangeListener(this);
        this.pullController = pullController;
        this.postView = postView;
        this.commentView = commentView;
        this.listLikedPostView = listLikedPostView;
        this.likeController = likeController;
        this.listLikedPostController = listLikedPostController;
        this.postPlazaViewModel.addPropertyChangeListener(this);
        this.user = user;
        JLabel title = new JLabel("Post Plaza");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(title);
        Map<Integer, postEntity> postPoolMap = this.postPlazaViewModel.getState().getPostMap();
        createPanel(this);
    }
    private void createPanel(JPanel jPanel){
        Map<Integer, postEntity> postMap = this.postPlazaViewModel.getState().getPostMap();

        ArrayList<postEntity> postPoolList = new ArrayList<postEntity>(postMap.values());
        Collections.reverse(postPoolList);
        System.out.println("post pool list size:"+ postPoolList.size());
        JPanel postsPanel = new JPanel();
        postsPanel.setLayout(new BoxLayout(postsPanel, BoxLayout.Y_AXIS));
        int i = 0;
        for (postEntity post : postPoolList) {
            JPanel onePicture = this.createPostPicturePanel(post);
            JPanel onePost = this.createPostPanel(post);
            if (!(onePicture == null)){postsPanel.add(onePicture);}
            postsPanel.add(onePost);
            JPanel oneCommentPlaza = this.createPostCommentPanel(post);
            postsPanel.add(oneCommentPlaza);
            postsPanel.add(createButtonsForPost(new Dimension(500, 30), post));
            postsPanel.add(Box.createVerticalStrut(80));
            System.out.println("this is the "+ i+++"iteration");

        }
        JScrollPane postsScroll = new JScrollPane(postsPanel);
        postsScroll.getViewport().setPreferredSize(new Dimension(700, 700));
        jPanel.add(postsScroll);
        System.out.println("add the postScroll");
        JPanel buttonPanel = this.createButtonsForPlaza(new Dimension(200, 500));
        jPanel.add(buttonPanel);
        System.out.println("add the PostButton");
        viewManagerModel.setActiveView(this.viewName);
        viewManagerModel.firePropertyChanged();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        System.out.println("propertyChanged for post plaza get called");
        System.out.println("evt"+ evt.getNewValue());
        if (evt.getNewValue() instanceof postState) {
            openedJFrame.dispose();
            haveOpenedJFrame = false;
        }
        else if (evt.getNewValue() instanceof PostPlazaState){
            PostPlazaState postPlazaState = (PostPlazaState) evt.getNewValue();
            System.out.println(postPlazaState.getNeedUpdate());
            if (postPlazaState.getNeedUpdate()){
                Update();
            } else if (postPlazaState.getShowListedLikedPost()) {
                System.out.println("LISTED property changed get called");
                try{
                    JButton checkLiked = new JButton(postPlazaViewModel.CHECK_LIKED_POST_LABEL);
                    checkLiked.setName(postPlazaViewModel.CHECK_LIKED_POST_LABEL);
                    showDialog(checkLiked);
                } catch(IOException e) {
                    throw new RuntimeException(e);
                }}
        }else if (evt.getNewValue() instanceof ListLikedPostState){
            ListLikedPostState listLikedPostState = (ListLikedPostState) evt.getNewValue();
            if (!listLikedPostState.getHaveLikedPostState()){
                System.out.println("show the error dialog");
                JOptionPane.showMessageDialog(this,listLikedPostState.getPostError());
            }
            }
        }

    private JPanel createPostPanel(postEntity post) {
        if (post.getPostInfo() == null){
            JPanel jPanel = new JPanel();
            jPanel.setPreferredSize(new Dimension(500, 10));
            jPanel.setMinimumSize(new Dimension(500, 10));
            jPanel.setMaximumSize(new Dimension(500, 10));
            return jPanel;
        }
        String content = (String) post.getPostInfo();
        int postPanelHeight;
        if (content.length() < 5){
            postPanelHeight = 20;
        }
        else if (content.length() > 200) {
            postPanelHeight = 200;
        } else {
            postPanelHeight = content.length() * 3;
        }
        int postPanelWidth = 500; // Set your desired width
        JPanel postPanel = new JPanel();
        postPanel.setAlignmentX(JPanel.CENTER_ALIGNMENT);
        postPanel.setLayout(new BoxLayout(postPanel, BoxLayout.Y_AXIS));
//
        postPanel.setPreferredSize(new Dimension(postPanelWidth, postPanelHeight));
        postPanel.setMinimumSize(new Dimension(postPanelWidth, postPanelHeight));
        postPanel.setMaximumSize(new Dimension(postPanelWidth, postPanelHeight));

        Object postInfo = post.getPostInfo();
        JTextArea contentTextArea = new JTextArea(String.valueOf(postInfo));
        contentTextArea.setWrapStyleWord(true);
        contentTextArea.setLineWrap(true);
        contentTextArea.setEditable(false);
        contentTextArea.setFont(new Font(contentTextArea.getFont().getFontName(), contentTextArea.getFont().getStyle(), contentTextArea.getFont().getSize() + 5));
        JScrollPane contentScrollPane = new JScrollPane(contentTextArea);
        postPanel.add(contentScrollPane);
        return postPanel;
    }


    private JPanel createPostCommentPanel(postEntity post) {
        JTextArea contentTextArea = new JTextArea("-".repeat(50) + "below are the comment\n");
        ArrayList<String> commentList = post.getPostComment();
        int totalWords = 0;
        for (String i : commentList) {
            totalWords += i.length();
        }
        JPanel commentPanel = new JPanel();
        commentPanel.setAlignmentX(JPanel.CENTER_ALIGNMENT);
        int panelWidth = 500;
        int panelHeight;
        if (totalWords > 100) {
            panelHeight = 200;
        } else {
            panelHeight = totalWords * 3;
        }
        commentPanel.setLayout(new BoxLayout(commentPanel, BoxLayout.Y_AXIS));
        commentPanel.setPreferredSize(new Dimension(panelWidth, panelHeight));
        commentPanel.setMinimumSize(new Dimension(panelWidth, panelHeight));
        commentPanel.setMaximumSize(new Dimension(panelWidth, panelHeight));
        for (String i : commentList) {
            contentTextArea.append(i + "\n" + "-".repeat(50));
        }
        contentTextArea.setWrapStyleWord(true);
        contentTextArea.setLineWrap(true);
        contentTextArea.setEditable(false);
        JScrollPane scrollComment = new JScrollPane(contentTextArea);
        scrollComment.setPreferredSize(new Dimension(panelWidth, panelHeight));
        scrollComment.setMaximumSize(new Dimension(new Dimension(panelWidth, panelHeight)));
        scrollComment.setMinimumSize(new Dimension(panelWidth, panelHeight));
        commentPanel.add(scrollComment);
        return commentPanel;
    }

    private JPanel createButtonsForPost(Dimension dimension, postEntity post) {
        JPanel buttonPanel = new JPanel();
        buttonPanel.setAlignmentX(JPanel.CENTER_ALIGNMENT);
        buttonPanel.setLayout(new FlowLayout());
        buttonPanel.setPreferredSize(dimension);
        buttonPanel.setMaximumSize(dimension);
        buttonPanel.setMinimumSize(dimension);
        JButton comment = new JButton(postPlazaViewModel.COMMENT_LABEL);
        comment.setName(PostPlazaViewModel.COMMENT_LABEL + post.getId().toString());
        comment.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(comment)) {
                            // TODO: implement back usecase and come back to this
                            try {
                                showDialog(comment);
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                        }
                    }
                }
        );
        buttonPanel.add(comment);
        JButton like = new JButton(PostPlazaViewModel.LIKE_LABEL);
        like.setName(PostPlazaViewModel.LIKE_LABEL + post.getId().toString());
        like.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(like)) {
                            try {
                                showDialog(like);
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                        }
                    }
                }
        );
        buttonPanel.add(like);
        return buttonPanel;
    }

    public JPanel createButtonsForPlaza(Dimension dimension) {
        JPanel buttonPanel = new JPanel();
        buttonPanel.setPreferredSize(dimension);
        buttonPanel.setMaximumSize(dimension);
        buttonPanel.setMinimumSize(dimension);
        JButton refresh = new JButton(postPlazaViewModel.PULL_LABEL);
        JButton newpost = new JButton(postPlazaViewModel.NEW_POST_LABEL);
        JButton checklikedpost = new JButton(CHECK_LIKED_POST_LABEL);
        newpost.setName(PostPlazaViewModel.NEW_POST_LABEL);
        refresh.setName(postPlazaViewModel.PULL_LABEL);
        checklikedpost.setName(CHECK_LIKED_POST_LABEL);
        refresh.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(refresh)) {
                            // TODO: implement back usecase and come back to this
                            try {
                                showDialog(refresh);
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                        }
                    }
                }
        );
        newpost.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(newpost)) {
                            // TODO: implement back usecase and come back to this
                            if (!haveOpenedJFrame) {
                                try {
                                    showDialog(newpost);
                                } catch (IOException e) {
                                    throw new RuntimeException(e);
                                }
                            }

                        }
                    }
                }

        );
        checklikedpost.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(checklikedpost)) {
                            // TODO: implement back usecase and come back to this
                            try {
                                listLikedPostController.execute(user);
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                        }
                    }
                }
        );
        buttonPanel.add(refresh);
        buttonPanel.add(newpost);
        buttonPanel.add(Box.createVerticalStrut(50));
        buttonPanel.add(checklikedpost);
        return buttonPanel;
    }


    private void showDialog(JButton button) throws IOException {
        System.out.println("showDialog has been called");
        String buttonName = button.getName();
        JFrame newFrame = new JFrame();
        newFrame.setLocationRelativeTo(null);
        System.out.println(button.getName());
        System.out.println(button.getName() == PostPlazaViewModel.COMMENT_LABEL);
        if (button.getName().startsWith(PostPlazaViewModel.COMMENT_LABEL)) {
            commentViewModel.getState().setPost(postPlazaViewModel.getState().getPostMap().get(checkNumber(button.getName())));
            newFrame.setSize(new Dimension(1000, 600));
            newFrame.add(commentView);
            openedJFrame = newFrame;
            haveOpenedJFrame = true;
            newFrame.setVisible(true);
            newFrame.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    System.out.println("New Frame is closing...");
                    haveOpenedJFrame = false; // Reset the frame reference when it's closed
                }
            });
        } else if (buttonName == PostPlazaViewModel.NEW_POST_LABEL) {

            newFrame.setSize(new Dimension(1000, 600));
            newFrame.add(postView);
            openedJFrame = newFrame;
            haveOpenedJFrame = true;
            newFrame.setVisible(true);
            newFrame.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    System.out.println("New Frame is closing...");
                    haveOpenedJFrame = false; // Reset the frame reference when it's closed
                }
            });
        } else if (buttonName.startsWith(LIKE_LABEL)) {
            postEntity likedPost = postPlazaViewModel.getState().getPostMap().get(checkNumber(buttonName));
            likeController.execute(likedPost, user);

        } else if (buttonName == postPlazaViewModel.PULL_LABEL){
            if (haveOpenedJFrame){
                closeWindows();
            }
            pullController.refreshPosts();

        } else if(buttonName == CHECK_LIKED_POST_LABEL){
            System.out.println("call the success dialog");
            newFrame.setSize(new Dimension(1000, 600));
            newFrame.add(listLikedPostView);
            openedJFrame = newFrame;
            haveOpenedJFrame = true;
            newFrame.setVisible(true);
            newFrame.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                    System.out.println("New Frame is closing...");
                    haveOpenedJFrame = false; // Reset the frame reference when it's closed
                }
            });
        }
    }

    private void closeWindows() {
        openedJFrame.dispose();
        haveOpenedJFrame = false;
    }

    private JPanel createPostPicturePanel(postEntity post) {
        if (post.getPostPicture() == null) {
            return null;
        }
        JPanel newPiture = new JPanel();
        newPiture.setAlignmentX(JPanel.CENTER_ALIGNMENT);
        JLabel newJLabel = new JLabel();
        String picturePath = post.getPostPicture();
        ImageIcon imageIcon = new ImageIcon(picturePath);
        Image image = imageIcon.getImage();
        int imageWidth = image.getWidth(null);
        int imageHeight = image.getHeight(null);
        double ratio = Math.min(1 / (Math.ceil(imageWidth / 500)), 1 / (Math.ceil(imageHeight / 500)));
        if (Math.max(imageHeight, imageWidth) > 700){
        ratio = Math.min(1 / (2 * Math.ceil(imageWidth / 500)), 1 / (2 * Math.ceil(imageHeight / 500)));}

        double newWidth = imageWidth * ratio;
        double newHeight = imageHeight * ratio;
        Image resizedImage = image.getScaledInstance((int) newWidth, (int) newHeight, Image.SCALE_SMOOTH);
        imageIcon = new ImageIcon(resizedImage);
        newJLabel.setIcon(imageIcon);
        newPiture.add(newJLabel);
        return newPiture;

    }

private Integer checkNumber(String checkString){
    Pattern pattern = Pattern.compile("(\\d+)$");
    Matcher matcher = pattern.matcher(checkString);

    if (matcher.find()) {
        String integerString = matcher.group(1);
        int number = Integer.parseInt(integerString);
        return number;
    } else {
        return null;
    }
}
private void Update(){
        this.removeAll();
        createPanel(this);
        revalidate();
        repaint();
}
}
