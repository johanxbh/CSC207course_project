import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame extends JFrame {
    private JPanel panel1;
    private JButton openDialogButton;
    private JPanel dialogPanel; // Assuming dialogPanel is an instance variable

    public MainFrame() {
        setTitle("My Application");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 500);
        setLocationRelativeTo(null); // Center the frame

        // Initialize dialogPanel
        dialogPanel = new JPanel();
        dialogPanel.add(new JLabel("This is a dialog!"));

        // Create panel 1
        panel1 = new JPanel();
        panel1.setLayout(new FlowLayout());

        openDialogButton = new JButton("Open Dialog");
        openDialogButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Show the dialog when the button is clicked
                showDialog();
            }
        });

        panel1.add(openDialogButton);

        setContentPane(panel1);
        setVisible(true);
    }

    // Method to show the dialog using the dialogPanel instance variable
    private void showDialog() {
        // Create a JDialog to display the dialogPanel
        JDialog dialog = new JDialog(this, "Dialog", true);
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        dialog.getContentPane().add(dialogPanel);
        dialog.setSize(200, 100);
        dialog.setLocationRelativeTo(this); // Center the dialog relative to the JFrame
        dialog.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new MainFrame();
        });
    }
}
