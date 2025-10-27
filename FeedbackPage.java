import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class FeedbackPage extends JFrame implements ActionListener {
    
    private JButton[] starButtons = new JButton[5];
    private int rating = 0;
    private JTextArea commentArea;
    private JButton submitButton;
    public static String user;
    public FeedbackPage(String user) {
        setTitle("Feedback Page");

        this.user = user; 
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        JPanel starsPanel = new JPanel();
        starsPanel.setLayout(new FlowLayout());

        for (int i = 0; i < 5; i++) {
            final int starValue = i + 1;
            JButton starButton = new JButton("☆");
            starButton.setFont(new Font("Serif", Font.PLAIN, 40));
            starButton.setFocusPainted(false);
            starButton.addActionListener(e -> {
                rating = starValue;
                updateStars();
            });
            starButtons[i] = starButton;
            starsPanel.add(starButton);
        }

        commentArea = new JTextArea(5, 30);
        JScrollPane scrollPane = new JScrollPane(commentArea);
        commentArea.setLineWrap(true);
        commentArea.setWrapStyleWord(true);

        JPanel commentPanel = new JPanel();
        commentPanel.setLayout(new BorderLayout());
        commentPanel.setBorder(BorderFactory.createTitledBorder("Additional Comments"));
        commentPanel.add(scrollPane, BorderLayout.CENTER);

        submitButton = new JButton("Submit Feedback");
        submitButton.addActionListener(this);

        add(starsPanel, BorderLayout.NORTH);
        add(commentPanel, BorderLayout.CENTER);
        add(submitButton, BorderLayout.SOUTH);

        setLocationRelativeTo(null);
        
        setVisible(true);
        
    }

    private void updateStars() {
        for (int i = 0; i < 5; i++) {
            if (i < rating) {
                starButtons[i].setText("★");
            } else {
                starButtons[i].setText("☆");
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String comment = commentArea.getText().trim();

        if (rating == 0) {
            JOptionPane.showMessageDialog(this, "Please select a star rating.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        System.out.println("Rating: " + rating + " stars");
        System.out.println("Comment: " + comment);

        JOptionPane.showMessageDialog(this,
                "Thank you for your feedback!\nRating: " + rating + " stars",
                "Submitted",
                JOptionPane.INFORMATION_MESSAGE);
                if(JOptionPane.INFORMATION_MESSAGE==1){
                    int response = JOptionPane.showConfirmDialog(null,"Do you want to play again?","Play Again?",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
                    if(response == JOptionPane.YES_OPTION){
                        new GameFrame(user);
                    } else {
                        System.exit(0);
                    }
                }
                

        rating = 0;
        commentArea.setText("");
        updateStars();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new FeedbackPage(user));
    }

}
