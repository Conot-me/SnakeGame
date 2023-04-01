import javax.swing.*;
import javax.swing.plaf.ColorUIResource;
import java.awt.*;

//This class is responsible for the initialization and configuration of the Snake game, including setting the window size, adding the game panel to the frame, and setting the background color.
public class SnakeGame{

    public static void main(String[] args){
                Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
                SnakePanels gamePanels = new SnakePanels();
                JFrame frame = new JFrame("Snake Game");
                frame.setIconImage(new ImageIcon("src/Images/snakeLogo2.jpg").getImage());
                frame.setLocation(dim.width/3,dim.height/5);
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setUndecorated(false);
                frame.setBackground(new Color(50, 50, 50));
                UIManager.put("InternalFrame.activeTitleBackground", new ColorUIResource(Color.black ));
                frame.add(gamePanels);
                frame.pack();
                frame.setVisible(true);
                frame.setResizable(false);
    }
}
