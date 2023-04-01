import java.awt.*;
import javax.swing.*;

// This class defines a SnakePanels object which is a JPanel with two subpanels, GamePanel and MenuPanel, and a Status object. It is used to display the game and menu of the snake game.
public class SnakePanels extends JPanel{
    private GamePanel game;
    private MenuPanel menu;
    public Status gameStatus;

    public SnakePanels() {
        setSize(800,900);
        setPreferredSize(new Dimension(800,900));
        FlowLayout flowLayout = new FlowLayout();
        flowLayout.setVgap(0);
        flowLayout.setHgap(0);
        setBackground(Color.GREEN);
        setLayout(flowLayout);
        this.gameStatus= new Status(40,40, 50);
        game = new GamePanel(gameStatus);
        menu = new MenuPanel(gameStatus);
        add(game);
        add(menu);
        setVisible(true);
    }
}
