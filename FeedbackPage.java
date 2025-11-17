import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class FeedbackPage extends JFrame implements ActionListener {

    JButton[] stars = new JButton[6];
    JTextArea commentBox;
    JButton submit, quit;
    String user;
    int score;
    Color defaultColor = Color.LIGHT_GRAY;
    Color selectedColor = new Color(229, 184, 11);
    int selectedRating = 0;


    public FeedbackPage(String user, int score) {
        this.user = user;
        this.score = score;

        setTitle("Rate the Game");
        setSize(500, 400);
        setLayout(new BorderLayout());

        JPanel starPanel = new JPanel();
        starPanel.setLayout(new FlowLayout());

        for (int i = 0; i < 5; i++) {
    stars[i] = new JButton("★");
    stars[i].setFont(new Font("Arial", Font.BOLD, 28));
    stars[i].setForeground(defaultColor);

    int index = i;  // Needed for lambda capturing
    stars[i].addActionListener(ev -> highlightStars(index));

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
    private void highlightStars(int index) {
    selectedRating = index + 1;  // Rating from 1–5

    for (int i = 0; i < 5; i++) {
        if (i <= index) {
            stars[i].setForeground(selectedColor);
        } else {
            stars[i].setForeground(defaultColor);
        }
    }
}

}
