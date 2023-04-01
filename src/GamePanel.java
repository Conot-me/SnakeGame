import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
//This class is used to create a game panel, which contains the Snake game and its related components.
//It implements KeyListener to respond to the user's key press events, adding game logic for the movement of the snake and displaying the game status (paused, game over, etc.).
public class GamePanel extends JPanel implements KeyListener {
    public Status gameStatus;

    public GamePanel(Status gameStatus) {
        setSize(800,800);
        setPreferredSize(new Dimension(800,800));
        this.gameStatus = gameStatus;
        this.gameStatus.setWidthHeight(getWidth(),getHeight());
        addKeyListener(this);
        setFocusable(true);
        requestFocusInWindow();

        Thread thread = new Thread(){
            public void run() {
                while (true){
                    repaint();
                }
            }
        };
        thread.start();
        gameStatus.startGameLoop();
    }

    @Override
    public void paint(Graphics g){
        super.paint(g);
        for (int i = 0; i < gameStatus.getGridRow(); i++){
            for (int j = 0; j < gameStatus.getGridCol(); j++){
                g.setColor(gameStatus.snakeBoard[i][j].state.getColor());
                g.fillRect(gameStatus.snakeBoard[i][j].x,gameStatus.snakeBoard[i][j].y,gameStatus.snakeBoard[i][j].width,gameStatus.snakeBoard[i][j].height);
            }
        }

        if (gameStatus.getGameOver()){
            g.setFont(new Font("TimesRoman", Font.BOLD, 30));
            g.setColor(Color.lightGray);
            g.drawString("GAME OVER!", getWidth() - getWidth()/4,getHeight()/25);
            return;
        }
        if (gameStatus.getGamePaused()){
            g.setFont(new Font("Nordic Light", Font.BOLD, 30));
            g.setColor(Color.lightGray);
            g.drawString("GAME PAUSED!", getWidth() - getWidth()/3,getHeight()/25);
            return;
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        try {
            switch (e.getKeyCode()) {
                case 65:
                    if (gameStatus.snakeBoard[gameStatus.player.getSnakeHead().x - 1][gameStatus.player.getSnakeHead().y].state == State.SNAKEBODY) return;
                    gameStatus.player.direction = Snake.Direction.WEST;
                    break;
                case 87:
                    if (gameStatus.snakeBoard[gameStatus.player.getSnakeHead().x][gameStatus.player.getSnakeHead().y - 1].state == State.SNAKEBODY) return;
                    gameStatus.player.direction = Snake.Direction.NORTH;
                    break;
                case 83:
                    if (gameStatus.snakeBoard[gameStatus.player.getSnakeHead().x][gameStatus.player.getSnakeHead().y + 1].state == State.SNAKEBODY) return;
                    gameStatus.player.direction = Snake.Direction.SOUTH;
                    break;
                case 68:
                    if (gameStatus.snakeBoard[gameStatus.player.getSnakeHead().x + 1][gameStatus.player.getSnakeHead().y].state == State.SNAKEBODY) return;
                    gameStatus.player.direction = Snake.Direction.EAST;
                    break;
                case 27, 32:
                    gameStatus.setGamePaused(!gameStatus.getGamePaused());
                    break;
                case 82:
                    gameStatus.gameSetup();
            }
        } catch (ArrayIndexOutOfBoundsException r) {
            System.out.println("Cant Move Out Of Bounds!" + '\n' + r);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}
