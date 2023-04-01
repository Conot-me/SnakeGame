// This class represents a Cell, which contains four ints: x, y, width, and height, as well as a State enum.
public class Cell {
    public int x;
    public int y;
    public int width;
    public int height;
    public State state = State.EMPTY;

    //The constructor takes in four ints for the x, y, width, and height values.
    public Cell(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }
}
