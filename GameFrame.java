import javax.swing.*;

public class GameFrame extends JFrame {
    public GameFrame(String user, int difficulty) {
        this.add(new GamePanel(user, difficulty));
        this.setTitle("Snake Game");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
}
