package view;

import app.generatePosts;
import interface_adapter.cancel.cancelController;
import interface_adapter.cancel.cancelViewModel;
import interface_adapter.post.postController;
import interface_adapter.post.postState;
import interface_adapter.post.postViewModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.IOException;




public class postView extends JPanel implements PropertyChangeListener, ActionListener {
    public final String viewName = "post";
    private final JTextArea postTextField = new JTextArea();
    private final JTextArea postPictureField = new JTextArea();
    private final interface_adapter.post.postController postController;
    private final interface_adapter.post.postViewModel postViewModel;
    private final interface_adapter.cancel.cancelViewModel cancelViewModel;
    private final JButton post;
    private final JButton cancel;
    private final JButton selectPictures;
    private final cancelController cancelController;
    private JLabel imageLabel = new JLabel();
    private final JButton randomPost;
    private String selectedImagePath;

    public postView(postViewModel postViewModel,cancelViewModel cancelViewModel, postController postController, cancelController cancelController) {
        postTextField.setLineWrap(true);
        postTextField.setWrapStyleWord(true);
        postTextField.setEditable(true);
        postTextField.setPreferredSize(new Dimension(200, 50));
        //create a JTextArea below
        this.cancelController = cancelController;
        this.postController = postController;
        this.postViewModel = postViewModel;
        this.cancelViewModel = cancelViewModel;
        JLabel title = new JLabel(postViewModel.LABLE_TITLE);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        postViewModel.addPropertyChangeListener(this);
        cancelViewModel.addPropertyChangeListener(this);
        JPanel buttons = new JPanel();
        JPanel centerPanel = new JPanel(new FlowLayout());
        post = new JButton(postViewModel.POST_BUTTON_LABEL);
        cancel = new JButton(cancelViewModel.CANCEL_BUTTON_LABEL);
        selectPictures = new JButton(postViewModel.PICTURE_BUTTON_LABEL);
        randomPost = new JButton(postViewModel.GENERATE_POST_LABEL);
        centerPanel.add(post);
        centerPanel.add(randomPost);
        centerPanel.add(cancel);
        centerPanel.add(selectPictures);
        LabelTextPanel textInfo = new LabelTextPanel(new JLabel(postViewModel.POST_BUTTON_LABEL), postTextField);
        centerPanel.add(textInfo);
        centerPanel.add(Box.createVerticalStrut(40));
        add(centerPanel, BorderLayout.CENTER);
        imageLabel.setPreferredSize(new Dimension(800, 800));
        centerPanel.add(imageLabel);
        randomPost.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                if(e.getSource().equals(randomPost)){
                    generatePosts generator = new generatePosts();
                    postState currentState = postViewModel.getState();
                    try {
                        currentState.setPostInputText(generator.getFakePost());
                    } catch (Exception ex) {
                        throw new RuntimeException(ex);
                    }
                    postViewModel.setPostState(currentState);
                    try {
                        postController.execute(currentState.getPostInputText(),null);
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                }
            }
        });
        selectPictures.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource().equals(selectPictures)){
                    JFileChooser fileChooser = new JFileChooser();
                    fileChooser.setFileFilter(new javax.swing.filechooser.FileFilter() {
                        @Override
                        public boolean accept(java.io.File f) {
                            return f.getName().toLowerCase().endsWith(".png")
                                    || f.getName().toLowerCase().endsWith(".jpg")
                                    || f.getName().toLowerCase().endsWith(".jpeg")
                                    || f.getName().toLowerCase().endsWith(".gif")
                                    || f.isDirectory();
                        }

                        @Override
                        public String getDescription() {
                            return "Image Files";
                        }
                    });

                    int result = fileChooser.showOpenDialog(postView.this);
                    if (result == JFileChooser.APPROVE_OPTION) {
                        selectedImagePath = fileChooser.getSelectedFile().getAbsolutePath();
                        postPictureField.setText(selectedImagePath);
                        displayImage(selectedImagePath);
                    }

                }
            }
        });
        cancel.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(cancel)) {
                            cancelController.execute();
                        }
                    }
                }
        );

        post.addActionListener(
                new ActionListener() {
                    public void actionPerformed(ActionEvent evt) {
                        if (evt.getSource().equals(post)) {
                            postState currentState = postViewModel.getState();
                            System.out.println("begin" + currentState.getPostPictureText());
                            try {
                                postController.execute(currentState.getPostInputText(), selectedImagePath);
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                        }
                    }
                }
        );
        //read path from postPictureField
        postPictureField.addKeyListener(
                new KeyListener() {
                    @Override
                    public void keyTyped(KeyEvent e) {
                        postState currentState = postViewModel.getState();
                        String text = postPictureField.getText() + e.getKeyChar();
                        currentState.setPostPictureText(text);
                        postViewModel.setPostState(currentState);
                    }

                    @Override
                    public void keyPressed(KeyEvent e) {
                    }

                    @Override
                    public void keyReleased(KeyEvent e) {
                    }
                });
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

        if (evt.getPropertyName().equals(postViewModel.POST_BUTTON_LABEL)) {
            postState state = postViewModel.getState();
            if (state.getPostSuccess().equals("false")) {
                JOptionPane.showMessageDialog(this, state.getPostError());
            }
            else {
                JOptionPane.showMessageDialog(this, "successfully posted:" + state.getPostInfo() + ";" + state.getPostPictureText());
                SwingUtilities.getWindowAncestor(this).dispose();
                SwingUtilities.invokeLater(() -> {
                    JFrame frame = new JFrame("Image Display");
                    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    JLabel imageLabel = new JLabel();
                    ImageIcon imageIcon = null;
                    try {
                        BufferedImage image = javax.imageio.ImageIO.read(new File(state.getPostPictureText()));
                        imageIcon = new ImageIcon(image);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    imageLabel.setIcon(imageIcon);

                    frame.getContentPane().add(imageLabel);

                    frame.setSize(1200, 1200);
                    frame.setLocationRelativeTo(null);
                    frame.setVisible(true);
                });

            }
        }
        else{
            JOptionPane.showMessageDialog(this,"cancel successfully");
            SwingUtilities.getWindowAncestor(this).dispose();

        }

    }
    @Override
    public void actionPerformed(ActionEvent e) {

    }
    private void displayImage(String imagePath) {
        try {
            BufferedImage image = javax.imageio.ImageIO.read(new File(imagePath));
            ImageIcon imageIcon = new ImageIcon(image);
            imageLabel.setIcon(imageIcon);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
}}
