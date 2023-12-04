package view;

import javax.swing.*;

/**
 * A panel containing a label and a text field.
 */
class LabelTextPanel extends JPanel {
    LabelTextPanel(JLabel label, JTextArea textField) {
        this.add(label);
        this.add(textField);
    }
}
