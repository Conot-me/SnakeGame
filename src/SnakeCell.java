// This class represents a single cell of a snake game.
//It stores the coordinates (x,y) and the current state of the cell. It also provides methods for decrementing/incrementing the coordinates.
public class SnakeCell {

    public State state;
    public int x,y;
    public SnakeCell(int x, int y, State state){
        this.x = x;
        this.y = y;
        this.state = state;
    }

    public void decrementX(){
        x--;
    }

    public void decrementY(){
        y--;
    }

    public void incrementX(){
        x++;
    }

    public void incrementY(){
        y++;
    }

}
