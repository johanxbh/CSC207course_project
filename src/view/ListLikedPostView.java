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

    public ListLikedPostView(ListLikedPostViewModel listLikedPostViewModel, ListLikedPostController listLikedPostController, BackController backController) {
        this.listLikedPostViewModel = listLikedPostViewModel;
        this.listLikedPostController = listLikedPostController;
        this.backController = backController;
        this.listLikedPostViewModel.addPropertyChangeListener(this);
        JLabel title = new JLabel("Liked Post");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        ArrayList<postEntity> likedPost = this.listLikedPostViewModel.getState().getListOfLikedPostArray();
        JPanel postsPanel = new JPanel();
        for (postEntity post : likedPost) {
            JPanel onePost = this.createPostPanel(post);
            postsPanel.add(onePost);
        }
        JScrollPane postsScroll = new JScrollPane(postsPanel);
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

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }

    private JPanel createPostPanel(postEntity post) {
        JPanel postPanel = new JPanel();
        postPanel.setLayout(new BorderLayout());
        Object postInfo = post.getPostInfo();
        JTextArea contentTextArea = new JTextArea(String.valueOf(postInfo));
        contentTextArea.setWrapStyleWord(true);
        contentTextArea.setLineWrap(true);
        contentTextArea.setEditable(false);
        JScrollPane contentScrollPane = new JScrollPane(contentTextArea);
        postPanel.add(contentScrollPane, BorderLayout.CENTER);
        return postPanel;
    }
}
