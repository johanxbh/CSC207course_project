package view;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import interface_adapter.postViewModel;
import interface_adapter.postController;
import interface_adapter.postState;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;


public class postView extends JPanel implements PropertyChangeListener, ActionListener {
    public final String viewName = "post";
    private final JTextArea postTextField = new JTextArea();
    private final postController postController;
    private final postViewModel postViewModel;
    private final JButton post;

    public postView(postViewModel postViewModel, postController postController) {
        postTextField.setLineWrap(true);
        postTextField.setWrapStyleWord(true);
        postTextField.setEditable(true);
        postTextField.setPreferredSize(new Dimension(500, 500));
        this.postController = postController;
        this.postViewModel = postViewModel;
        JLabel title = new JLabel(postViewModel.LABLE_TITLE);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        postViewModel.addPropertyChangeListener(this);
        JPanel buttons = new JPanel();
        JPanel centerPanel = new JPanel(new FlowLayout());
        post = new JButton(postViewModel.POST_BUTTON_LABEL);
        centerPanel.add(post);
        LabelTextPanel textInfo = new LabelTextPanel(new JLabel(postViewModel.POST_BUTTON_LABEL), postTextField);
        centerPanel.add(textInfo);
        add(centerPanel, BorderLayout.CENTER);

        post.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(post)) {
                            postState currentState = postViewModel.getState();
                            postController.execute(currentState.getPostInputText());
                        }
                    }
                }
        );
        postTextField.addKeyListener(
                new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                        postState currentState = postViewModel.getState();
                        String text = postTextField.getText() + e.getKeyChar();
                        currentState.setPostInputText(text);
                        postViewModel.setPostState(currentState);
                    }

                    @Override
                    public void keyPressed(KeyEvent e) {
                    }

                    @Override
                    public void keyReleased(KeyEvent e) {
                    }
                });
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        /*if (evt.getSource().equals(post)){
            postState state = (postState) evt.getNewValue();
            if (state.getPostError() != null) {
                JOptionPane.showMessageDialog(this, state.getPostError());
            }
            else{
                JOptionPane.showMessageDialog(this,state.getPostText());
            }
        }*/
        if (evt.getPropertyName().equals(postViewModel.POST_BUTTON_LABEL)) {
            postState state = postViewModel.getState();
            if (state.getPostSuccess() == "false") {
                JOptionPane.showMessageDialog(this, state.getPostError());
            }
            else {
                JOptionPane.showMessageDialog(this, "successfully posted:" + state.getPostText());
            }
        }

    }
    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
