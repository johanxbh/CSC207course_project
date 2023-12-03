package view;

import entities.postEntity;
import interface_adapter.back.BackController;
import interface_adapter.comment.CommentController;
import interface_adapter.post.postState;
import interface_adapter.post_plaza.PostPlazaViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;
import java.util.Map;

public class PostPlazaView extends JPanel implements ActionListener, PropertyChangeListener {
    public final String viewName = "post plaza";
    private final PostPlazaViewModel postPlazaViewModel;
    private final BackController backController;
    private final CommentController commentController;
    private final postView postView;

    private Dimension size;
    private JFrame openedJFrame;
    private boolean haveOpenedJFrame;


    public PostPlazaView(PostPlazaViewModel postPlazaViewModel, BackController backController, CommentController commentController, view.postView postView) {
        this.postPlazaViewModel = postPlazaViewModel;
        this.backController = backController;
        this.commentController = commentController;
        this.postView = postView;
        this.postPlazaViewModel.addPropertyChangeListener(this);
        JLabel title = new JLabel("Post Plaza");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(title);
        Map<Integer, postEntity> postPoolMap = this.postPlazaViewModel.getState().getPostMap();
        ArrayList<postEntity> postPoolList = new ArrayList<postEntity>(postPoolMap.values());
        JPanel postsPanel = new JPanel();
        postsPanel.setLayout(new BoxLayout(postsPanel, BoxLayout.Y_AXIS));
        for (postEntity post : postPoolList) {
            JPanel onePicture = this.createPostPicturePanel(post);
            JPanel onePost = this.createPostPanel(post);
            postsPanel.add(onePicture);
            postsPanel.add(onePost);
            JPanel oneCommentPlaza = this.createPostCommentPanel(post);
            postsPanel.add(oneCommentPlaza);
            postsPanel.add(createButtonsForPost(new Dimension(500, 30)));
            postsPanel.add(Box.createVerticalStrut(80));

        }
        JScrollPane postsScroll = new JScrollPane(postsPanel);
        postsScroll.getViewport().setPreferredSize(new Dimension(700, 500));
        this.add(postsScroll);
        JPanel buttonPanel = this.createButtonsForPlaza(new Dimension(200, 500));
        this.add(buttonPanel);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getNewValue() instanceof postState){
            openedJFrame.dispose();
        }
    }

    private JPanel createPostPanel(postEntity post) {
        String content = (String) post.getPostInfo();
        int postPanelHeight;
        if (content.length() > 200) {
            postPanelHeight = 200;
        } else {
            postPanelHeight = content.length() * 2;
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

    private JPanel createButtonsForPost(Dimension dimension) {
        JPanel buttonPanel = new JPanel();
        buttonPanel.setAlignmentX(JPanel.CENTER_ALIGNMENT);
        buttonPanel.setLayout(new FlowLayout());
        buttonPanel.setPreferredSize(dimension);
        buttonPanel.setMaximumSize(dimension);
        buttonPanel.setMinimumSize(dimension);
        JButton comment = new JButton(postPlazaViewModel.COMMENT_LABEL);
        comment.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(comment)) {
                            // TODO: implement back usecase and come back to this
                            backController.execute();
                        }
                    }
                }
        );
        buttonPanel.add(comment);
        JButton like = new JButton(PostPlazaViewModel.LIKE_LABEL);
        like.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(like)) {
                            // TODO: implement back usecase and come back to this
                            backController.execute();
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
        JButton checklikedpost = new JButton(postPlazaViewModel.CHECK_LIKED_POST_LABEL);
        refresh.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(refresh)) {
                            // TODO: implement back usecase and come back to this
                            backController.execute();
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
                                showDialog(newpost);
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
                            backController.execute();
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


    private void showDialog(JButton button) {
        String buttonName = button.getName();
        JFrame newFrame = new JFrame();
        newFrame.setLocationRelativeTo(null);
        newFrame.setSize(new Dimension(800, 600));
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
    }
    private void closeWindows(){
        openedJFrame.dispose();
        haveOpenedJFrame = false;
    }

    private JPanel createPostPicturePanel(postEntity post){
        JPanel newPiture = new JPanel();
        newPiture.setAlignmentX(JPanel.CENTER_ALIGNMENT);
        JLabel newJLabel = new JLabel();
        String picturePath = post.getPostPicture();
        ImageIcon imageIcon = new ImageIcon(picturePath);
        Image image = imageIcon.getImage();
        int imageWidth = image.getWidth(null);
        int imageHeight = image.getHeight(null);
        double ratio = Math.min(1 /Math.ceil(imageWidth / 500) , 1 / Math.ceil(imageHeight / 500));
        double newWidth = imageWidth * ratio;
        double newHeight = imageHeight * ratio;
        Image resizedImage = image.getScaledInstance((int) newWidth, (int) newHeight, Image.SCALE_SMOOTH);
        imageIcon = new ImageIcon(resizedImage);
        newJLabel.setIcon(imageIcon);
        newPiture.add(newJLabel);
        return newPiture;

    }
}
