package view;

import interface_adapter.comment.CommentController;
import interface_adapter.comment.CommentViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CommentView{

    CommentViewModel commentViewModel;
    CommentController commentController;


    public CommentView(CommentViewModel commentViewModel, CommentController commentController) {
        this.commentViewModel = commentViewModel;
        this.commentController = commentController;
    }

    public JPanel createCommentPanel(){
        JTextArea commentTextField = new JTextArea("Enter text");
        commentTextField.setPreferredSize(new Dimension(400, 60));
        JPanel commentTextPanel = new JPanel();
        commentTextPanel.add(commentTextField);
        JButton submitButton = new JButton("Submit Comment");
        submitButton.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(submitButton)){
                            commentController.execute(commentViewModel.getState().getPost(), commentTextField.getText());
                        }
                    }
                }
        );
        commentTextPanel.add(submitButton);

        return commentTextPanel;
    }
}
