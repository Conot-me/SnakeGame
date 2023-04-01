import java.awt.*;

//Enum State that stores the different states of the game (Snakehead, Snakebody, Apple, Empty) and their associated colors.
public enum State {
    SNAKEHEAD(new Color(0,225,0)),
    SNAKEBODY(new Color(0,200,0)),
    APPLE(new Color(255,0, 0)),
    EMPTY(new Color(50,50,50));

    private Color color;

    State(Color color){
        this.color = color;
    }

    public Color getColor(){
        return color;
    }
}
