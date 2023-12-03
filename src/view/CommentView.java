package view;

import interface_adapter.comment.CommentController;
import interface_adapter.comment.CommentViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class CommentView extends JPanel implements PropertyChangeListener, ActionListener {

    CommentViewModel commentViewModel;
    CommentController commentController;


    public CommentView(CommentViewModel commentViewModel, CommentController commentController) {
        this.commentViewModel = commentViewModel;
        this.commentController = commentController;
        this.add(this.createCommentPanel());
    }

    public JPanel createCommentPanel(){
        JTextArea commentTextField = new JTextArea();
        commentTextField.setPreferredSize(new Dimension(500, 500));
        commentTextField.setLineWrap(true);
        commentTextField.setWrapStyleWord(true);
        commentTextField.setEditable(true);
        JPanel commentTextPanel = new JPanel();
        commentTextPanel.setLayout(new BoxLayout(commentTextPanel, BoxLayout.Y_AXIS));
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

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }
}
