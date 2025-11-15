import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class DifficultyPage extends JFrame implements ActionListener {
    JButton easy, medium, hard;
    String user;

    public DifficultyPage(String user) {
        this.user = user;

        setTitle("Select Difficulty");
        setSize(400, 200);
        setLayout(new GridLayout(3, 1));

        easy = new JButton("Easy");
        medium = new JButton("Medium");
        hard = new JButton("Hard");

        easy.addActionListener(this);
        medium.addActionListener(this);
        hard.addActionListener(this);

        add(easy);
        add(medium);
        add(hard);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int difficulty = 2;
        if (e.getSource() == easy) difficulty = 1;
        if (e.getSource() == medium) difficulty = 2;
        if (e.getSource() == hard) difficulty = 3;

        new GameFrame(user, difficulty);
        dispose();
    }
}
