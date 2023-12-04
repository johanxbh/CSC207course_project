package view;

import interface_adapter.comment.CommentController;
import interface_adapter.comment.CommentState;
import interface_adapter.comment.CommentViewModel;
import interface_adapter.post.postState;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class CommentView extends JPanel implements PropertyChangeListener, ActionListener {

    CommentViewModel commentViewModel;
    CommentController commentController;


    public CommentView(CommentViewModel commentViewModel, CommentController commentController) {
        this.commentViewModel = commentViewModel;
        this.commentController = commentController;
        this.add(this.createCommentPanel());
        commentViewModel.addPropertyChangeListener(this);
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
        commentTextField.addKeyListener(new KeyListener(){
            @Override
            public void keyTyped(KeyEvent e) {
                CommentState currentState = commentViewModel.getState();
                String text = commentTextField.getText() + e.getKeyChar();
                currentState.setComment(text);
                commentViewModel.setState(currentState);
            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });
        submitButton.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(submitButton)){
                            commentController.execute(commentViewModel.getState().getPost(), commentViewModel.getState().getComment());
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
        if (evt.getPropertyName().equals("comment state")){
            JOptionPane.showMessageDialog(this,"comment successfully");
            SwingUtilities.getWindowAncestor(this).dispose();
        }

    }
}