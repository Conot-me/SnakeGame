import javax.swing.*;
import javax.swing.event.ChangeEvent;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.Hashtable;

//This class creates the MenuPanel which contains the game's GUI components, such as the restart button, the pause button, the speed slider, and the score label.
//The different methods are used to control the game, such as restarting the game or changing the game speed.
public class MenuPanel extends JPanel {
    private Status gameStatus;
    private JButton restartButton = new JButton("RESTART");
    private JButton pauseButton = new JButton("\u23EF");
    private JLabel scoreLabel;
    private JSlider speedSlider;
    private JLabel speedLabel = new JLabel("SPEED");
    private final Font gameFontBig = new Font("Arial Rounded MT Solid", Font.BOLD, 25);
    private final Font gameFontMedium = new Font("Arial Rounded MT Solid", Font.BOLD, 15);
    private final Color foregroundColor = Color.LIGHT_GRAY;
    private final Color backgroundColor = new Color(60, 63, 65);

    public MenuPanel(Status gameStatus) {
        setSize(800,100);
        setPreferredSize(new Dimension(800,100));
        this.gameStatus = gameStatus;

        scoreLabel = new JLabel("SCORE: " + gameStatus.player.bodyLength);
        scoreLabel.setHorizontalAlignment(JLabel.CENTER);
        scoreLabel.setFont(gameFontMedium);
        scoreLabel.setForeground(foregroundColor);

        speedSlider = new JSlider(JSlider.HORIZONTAL, 1, 100, 50);
        speedSlider.setBackground(backgroundColor);
        speedSlider.setForeground(foregroundColor);
        speedSlider.setInverted(true);
        Hashtable labelTable = new Hashtable();
        speedLabel.setFont(gameFontMedium);
        speedLabel.setForeground(foregroundColor);
        labelTable.put(50,speedLabel);
        speedSlider.setFont(gameFontMedium);
        speedSlider.setLabelTable(labelTable);
        speedSlider.setPaintLabels(true);
        speedSlider.addChangeListener(this::speedChange);
        speedSlider.setUI(new CustomSlider(speedSlider));

        restartButton.addActionListener(this::restart);
        restartButton.setFont(gameFontMedium);
        restartButton.setBackground(backgroundColor);
        restartButton.setForeground(foregroundColor);
        restartButton.setBorder(BorderFactory.createRaisedBevelBorder());

        pauseButton.addActionListener(this::pause);
        pauseButton.setBackground(backgroundColor);
        pauseButton.setForeground(foregroundColor);
        pauseButton.setFont(gameFontBig);
        pauseButton.setBorder(BorderFactory.createRaisedBevelBorder());
        setBorder(BorderFactory.createMatteBorder(1,0,0,0, Color.BLACK));
        setLayout(new GridLayout(2,2));

        add(restartButton).setFocusable(false);
        add(pauseButton).setFocusable(false);
        add(speedSlider).setFocusable(false);
        add(scoreLabel).setFocusable(false);

        setBackground(backgroundColor);

        Thread thread = new Thread(){
            @Override
            public void run() {
                while(true){
                    scoreLabel.setText("SCORE: " + gameStatus.player.bodyLength);
                }
            }
        };
        thread.start();
    }

    private void restart(ActionEvent e) {
        gameStatus.gameSetup();
        gameStatus.setGamePaused(true);
    }

    private void pause(ActionEvent e) {
        gameStatus.setGamePaused(!gameStatus.getGamePaused());
    }

    private void speedChange(ChangeEvent e){
        JSlider source = (JSlider)e.getSource();
        if (!source.getValueIsAdjusting()) {
            gameStatus.setGameSpeed((int)source.getValue());
        }
    }
}
