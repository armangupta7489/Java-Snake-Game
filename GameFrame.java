import javax.swing.JFrame;

public class GameFrame extends JFrame{

	GameFrame(String user) {
			
		this.add(new GamePanel(user));
		this.setTitle("Snake");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.pack();
		this.setVisible(true);
		this.setLocationRelativeTo(null);
		
	}
}