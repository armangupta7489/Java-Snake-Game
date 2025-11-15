import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class FeedbackPage extends JFrame implements ActionListener {

    JButton[] stars = new JButton[6];
    JTextArea commentBox;
    JButton submit, quit;
    String user;
    int score;

    public FeedbackPage(String user, int score) {
        this.user = user;
        this.score = score;

        setTitle("Rate the Game");
        setSize(500, 400);
        setLayout(new BorderLayout());

        // ⭐⭐⭐⭐⭐ Star Panel
        JPanel starPanel = new JPanel();
        starPanel.setLayout(new FlowLayout());

        for (int i = 0; i < 5; i++) {
            stars[i] = new JButton("★");
            stars[i].setFont(new Font("Arial", Font.BOLD, 24));
            starPanel.add(stars[i]);
        }

        // Comment Box
        commentBox = new JTextArea(5, 30);

        // Bottom Buttons Panel
        JPanel bottomPanel = new JPanel();
        bottomPanel.setLayout(new FlowLayout());

        // Submit Button
        submit = new JButton("Submit & Play Again");
        submit.addActionListener(this);

        // Quit Button
        quit = new JButton("Quit");
        quit.addActionListener(e -> System.exit(0));

        bottomPanel.add(submit);
        bottomPanel.add(quit);

        // Add components to Frame
        add(starPanel, BorderLayout.NORTH);
        add(new JScrollPane(commentBox), BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);

        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        new DifficultyPage(user);
        dispose();
    }
}
