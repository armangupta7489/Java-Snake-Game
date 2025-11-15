import java.awt.*;
import java.awt.event.*;
import java.util.Random;
import javax.swing.*;

public class GamePanel extends JPanel implements ActionListener {
    static final int SCREEN_WIDTH = 600;
    static final int SCREEN_HEIGHT = 600;
    static final int UNIT_SIZE = 25;
    static final int GAME_UNITS = (SCREEN_WIDTH * SCREEN_HEIGHT) / (UNIT_SIZE * UNIT_SIZE);

    final int x[] = new int[GAME_UNITS];
    final int y[] = new int[GAME_UNITS];

    int bodyParts = 6;
    int applesEaten = 0;
    int appleX;
    int appleY;
    char direction = 'R';
    boolean running = false;
    Timer timer;
    int delay;

    int lives = 1;
    String user;

    Random random;

    public GamePanel(String user, int difficulty) {
        this.user = user;
        random = new Random();

        if (difficulty == 1) delay = 150;
        if (difficulty == 2) delay = 100;
        if (difficulty == 3) delay = 60;

        setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        setBackground(Color.black);
        setFocusable(true);
        addKeyListener(new MyKeyAdapter());

        startGame();
    }

    public void startGame() {
        newApple();
        running = true;
        timer = new Timer(delay, this);
        timer.start();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    public void draw(Graphics g) {
        if (running) {
            g.setColor(Color.red);
            g.fillOval(appleX, appleY, UNIT_SIZE, UNIT_SIZE);

            for (int i = 0; i < bodyParts; i++) {
                g.setColor(new Color(255 - i * 2, 50 + i * 3, 100));
                g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
            }

            g.setColor(Color.white);
            g.setFont(new Font("Arial", Font.BOLD, 16));
            g.drawString("User: " + user, 10, 20);
            g.drawString("Score: " + applesEaten, 10, 40);
            g.drawString("Lives: " + lives, 10, 60);

        } else gameOver(g);
    }

    public void move() {
    for (int i = bodyParts; i > 0; i--) {
        x[i] = x[i - 1];
        y[i] = y[i - 1];
    }

    switch (direction) {
        case 'U': y[0] -= UNIT_SIZE; break;
        case 'D': y[0] += UNIT_SIZE; break;
        case 'L': x[0] -= UNIT_SIZE; break;
        case 'R': x[0] += UNIT_SIZE; break;
    }

    if (x[0] < 0)
        x[0] = SCREEN_WIDTH - UNIT_SIZE;

    if (x[0] >= SCREEN_WIDTH)
        x[0] = 0;

    if (y[0] < 0)
        y[0] = SCREEN_HEIGHT - UNIT_SIZE;

    if (y[0] >= SCREEN_HEIGHT)
        y[0] = 0;
}


    public void newApple() {
        appleX = random.nextInt((int) (SCREEN_WIDTH / UNIT_SIZE)) * UNIT_SIZE;
        appleY = random.nextInt((int) (SCREEN_HEIGHT / UNIT_SIZE)) * UNIT_SIZE;
    }

    public void checkApple() {
        if ((x[0] == appleX) && (y[0] == appleY)) {
            bodyParts++;
            applesEaten++;

            if (applesEaten % 5 == 0)
                timer.setDelay(Math.max(40, timer.getDelay() - 5));

            newApple();
        }
    }

    public void checkCollisions() {
        for (int i = bodyParts; i > 0; i--) {
            if (x[0] == x[i] && y[0] == y[i]) {
                lives--;
                if (lives > 0) resetSnake();
                else running = false;
            }
        }
    }

    public void resetSnake() {
        bodyParts = 6;
        direction = 'R';
        for (int i = 0; i < bodyParts; i++) {
            x[i] = 50 - i * UNIT_SIZE;
            y[i] = 50;
        }
    }

    public void gameOver(Graphics g) {
        ScoreManager.saveScore(user, applesEaten);
        JFrame top = (JFrame) SwingUtilities.getWindowAncestor(this);
        new FeedbackPage(user, applesEaten);
        top.dispose();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (running) {
            move();
            checkApple();
            checkCollisions();
        }
        repaint();
    }

    public class MyKeyAdapter extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            switch (e.getKeyCode()) {

                case KeyEvent.VK_LEFT:
                    if (direction != 'R') direction = 'L'; break;

                case KeyEvent.VK_RIGHT:
                    if (direction != 'L') direction = 'R'; break;

                case KeyEvent.VK_UP:
                    if (direction != 'D') direction = 'U'; break;

                case KeyEvent.VK_DOWN:
                    if (direction != 'U') direction = 'D'; break;

                case KeyEvent.VK_SPACE:
                case KeyEvent.VK_P:
                    running = !running;
                    if (running) timer.start(); else timer.stop();
                    break;
            }
        }
    }
}
