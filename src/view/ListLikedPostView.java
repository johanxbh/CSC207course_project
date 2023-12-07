package view;

import entities.postEntity;
import interface_adapter.back.BackController;
import interface_adapter.list_liked_post.ListLikedPostController;
import interface_adapter.list_liked_post.ListLikedPostViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;

public class ListLikedPostView extends JPanel implements ActionListener, PropertyChangeListener {
    public final String viewName = "list liked post";
    private final ListLikedPostViewModel listLikedPostViewModel;
    private final ListLikedPostController listLikedPostController;
    private final BackController backController;
    final JButton back;
    private Dimension size;

    public ListLikedPostView(ListLikedPostViewModel listLikedPostViewModel, ListLikedPostController listLikedPostController, BackController backController) {
        this.listLikedPostViewModel = listLikedPostViewModel;
        this.listLikedPostController = listLikedPostController;
        this.backController = backController;
        this.listLikedPostViewModel.addPropertyChangeListener(this);
        JLabel title = new JLabel("Liked Post");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.add(title);
        ArrayList<postEntity> likedPost = this.listLikedPostViewModel.getState().getListOfLikedPostArray();


        JPanel postsPanel = new JPanel();
        postsPanel.setLayout(new BoxLayout(postsPanel, BoxLayout.Y_AXIS));
        for (postEntity post : likedPost) {
            JPanel onePost = this.createPostPanel(post);
            postsPanel.add(onePost);
            JPanel oneCommentPlaza = this.createPostCommentPanel(post);
            postsPanel.add(oneCommentPlaza);
            postsPanel.add(Box.createVerticalStrut(60));
        }
        JScrollPane postsScroll = new JScrollPane(postsPanel);
        postsScroll.getViewport().setPreferredSize(new Dimension(600, 500));
        this.add(postsScroll);
        back = new JButton(this.listLikedPostViewModel.BACK_LABEL);
        back.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(back)) {
                            backController.execute();
                        }
                    }
                }
        );
        this.add(back);

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }

    private JPanel createPostPanel(postEntity post) {
        String content = (String) post.getPostInfo();
        int postPanelHeight;
        if (content.length() > 200){
            postPanelHeight = 200;
        } else {
            postPanelHeight = content.length() * 3;
        }
        int postPanelWidth = 500; // Set your desired width
        JPanel postPanel = new JPanel();
        postPanel.setAlignmentX(JPanel.LEFT_ALIGNMENT);
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
        JScrollPane contentScrollPane = new JScrollPane(contentTextArea);
        postPanel.add(contentScrollPane);

        return postPanel;
    }


    private JPanel createPostCommentPanel(postEntity post){
        JTextArea contentTextArea = new JTextArea("-".repeat(50) + "below are the comment\n");
        ArrayList<String> commentList = post.getPostComment();
        int totalWords = 0;
        for (String i: commentList){
            totalWords += i.length();
        }
        JPanel commentPanel = new JPanel();
        commentPanel.setAlignmentX(JPanel.LEFT_ALIGNMENT);
        int panelWidth = 500;
        int panelHeight;
        if (totalWords > 100 ){
            panelHeight = 100;
        }else {
            panelHeight = totalWords * 4;
        }
        commentPanel.setLayout(new BoxLayout(commentPanel, BoxLayout.Y_AXIS));
        commentPanel.setPreferredSize(new Dimension(panelWidth, panelHeight));
        commentPanel.setMinimumSize(new Dimension(panelWidth, panelHeight));
        commentPanel.setMaximumSize(new Dimension(panelWidth, panelHeight));
        for (String i: commentList){
            contentTextArea.append(i + "\n" + "-".repeat(50));
        }
        contentTextArea.setWrapStyleWord(true);
        contentTextArea.setLineWrap(true);
        contentTextArea.setEditable(false);
        JScrollPane scrollComment = new JScrollPane(contentTextArea);
        commentPanel.add(scrollComment);
        JButton comment = new JButton("Comment");
        commentPanel.add(comment);
        return commentPanel;
    }
}
