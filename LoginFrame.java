import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

class IDandPasswords {
    HashMap<String, String> logininfo = new HashMap<>();

    IDandPasswords() {
        logininfo.put("", "");
        //logininfo.put("Ojaswi", "4321");
        logininfo.put("Arman", "1234");
    }

    public HashMap<String, String> getLoginInfo() {
        return logininfo;
    }
}

class WelcomePage {
    JFrame frame = new JFrame();
    JLabel welcomeLabel = new JLabel("Hello!");

    WelcomePage(String userID) {
        welcomeLabel.setBounds(0, 0, 300, 35);
        welcomeLabel.setFont(new Font("MV Boli", Font.PLAIN, 25));
        welcomeLabel.setText("Hello " + userID);

        frame.add(welcomeLabel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(420, 420);
        frame.setLayout(null);
        frame.setVisible(true);
    }
}

public class LoginFrame extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton, exitButton;
    private HashMap<String, String> logininfo;

    public LoginFrame() {
        IDandPasswords idandPasswords = new IDandPasswords();
        logininfo = idandPasswords.getLoginInfo();

        setTitle("Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);
        setLayout(new GridLayout(4, 2, 30, 30));

        JLabel usernameLabel = new JLabel("Username:");
        usernameLabel.setFont(new Font("MV Boli", Font.PLAIN, 26));
        usernameLabel.setBorder(new EmptyBorder(0, 40, 0, 0));

        usernameField = new JTextField();
        usernameField.setFont(new Font("MV Boli", Font.PLAIN, 26));

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setFont(new Font("MV Boli", Font.PLAIN, 26));
        passwordLabel.setBorder(new EmptyBorder(0, 40, 0, 0));

        passwordField = new JPasswordField();
        passwordField.setFont(new Font("MV Boli", Font.PLAIN, 26));

        loginButton = new JButton("Login");
        loginButton.setFont(new Font("MV Boli", Font.PLAIN, 26));

        exitButton = new JButton("Exit");
        exitButton.setFont(new Font("MV Boli", Font.PLAIN, 26));

        add(usernameLabel);
        add(usernameField);
        add(passwordLabel);
        add(passwordField);
        add(new JLabel());
        add(loginButton);
        add(new JLabel());
        add(exitButton);

        getContentPane().setBackground(Color.BLACK);
        usernameLabel.setForeground(Color.WHITE);
        passwordLabel.setForeground(Color.WHITE);

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String userID = usernameField.getText();
                String password = String.valueOf(passwordField.getPassword());

                if (logininfo.containsKey(userID)) {
                    if (logininfo.get(userID).equals(password)) {
                        JOptionPane.showMessageDialog(LoginFrame.this, "Login successful!", "Success", JOptionPane.INFORMATION_MESSAGE);
                        dispose();
                        new GameFrame(userID);
                    } else {
                        JOptionPane.showMessageDialog(LoginFrame.this, "Wrong password!", "Login Failed", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(LoginFrame.this, "Username not found!", "Login Failed", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        exitButton.addActionListener(e -> System.exit(0));

        setVisible(true);
    }

    public static void main(String[] args) {
        new LoginFrame();
    }
}
