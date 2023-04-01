import javax.swing.*;
import javax.swing.plaf.basic.BasicSliderUI;
import java.awt.*;

// This class is a custom implementation of BasicSliderUI that overrides the paintTrack(), paintThumb(), and getThumbSize() methods to customize the look of a JSlider.
public class CustomSlider extends BasicSliderUI {

    public CustomSlider(JSlider b) {
        super(b);
    }

    @Override
    protected Dimension getThumbSize(){
        return new Dimension(14,14);
    }

    @Override
    public void paintFocus(Graphics g) {
    }

    @Override
    public void paintThumb(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(slider.getForeground());
        g2.fillOval(thumbRect.x, thumbRect.y, thumbRect.width, thumbRect.height);
    }

    @Override
    public void paintTrack(Graphics graphics){
        Graphics2D g2 = (Graphics2D) graphics;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(new Color(50,50,50));
        g2.drawRoundRect(trackRect.x, thumbRect.y + (thumbRect.height/2) - 1, trackRect.width,4,1,1);
        g2.fillRoundRect(trackRect.x, thumbRect.y + (thumbRect.height/2) - 1, thumbRect.x - (thumbRect.width),4,1,1);
        g2.setFont(new Font("TimesRoman", Font.BOLD, 30));
        g2.drawString("-", trackRect.x - 18, trackRect.height + 9);
        g2.drawString("+", trackRect.x + trackRect.width + 6, trackRect.height + 12);
    }
}
