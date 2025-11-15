import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class LoginPage extends JFrame implements ActionListener {
    JTextField userField;
    JButton loginButton;

    public LoginPage() {
        setTitle("Snake Game - Login");
        setSize(350, 200);
        setLayout(new FlowLayout());

        add(new JLabel("Enter Username:"));
        userField = new JTextField(20);
        add(userField);

        loginButton = new JButton("Login");
        loginButton.addActionListener(this);
        add(loginButton);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String user = userField.getText();
        if (user.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter a username");
            return;
        }
        new DifficultyPage(user);
        dispose();
    }
}
