import java.util.ArrayList;

//This is the Snake class which creates a snake object with a body length, direction, and an ArrayList of SnakeCell objects.
//It also contains methods to move the snake, increment the length, or get the head of the snake.
public class Snake {
    public enum Direction {
        NORTH,
        SOUTH,
        EAST,
        WEST
    }

    public int bodyLength = 1;
    public Direction direction = Direction.NORTH;
    public ArrayList<SnakeCell> snakeCells = new ArrayList<>();

    public Snake(int x, int y){
        snakeCells.add(new SnakeCell(x,y, State.SNAKEHEAD));
        snakeCells.add(new SnakeCell(x, y , State.SNAKEBODY));
    }

    public void incrementLength(int index){
        int tempCellX = snakeCells.get(0).x;
        int tempCellY = snakeCells.get(0).y;
        snakeCells.add(index, new SnakeCell(tempCellX, tempCellY, State.SNAKEBODY));
    }

    private void decrementLength(){
        snakeCells.remove(snakeCells.size() -1);
    }

    public void snakeMove(boolean gameOver, Cell[][] snakeBoard){
            decrementLength();
        switch (direction){
            case NORTH:
                if(snakeCells.get(0).y - 1 < 0){
                    gameOver = true;
                    return;
                }
                incrementLength(1);
                snakeCells.get(0).decrementY();
                break;

            case SOUTH:
                if((snakeCells.get(0).y + 1) > snakeBoard.length - 1){
                    gameOver = true;
                    return;
                }
                incrementLength(1);
                snakeCells.get(0).incrementY();
                break;

            case EAST:
                if((snakeCells.get(0).x + 1) > snakeBoard.length - 1){
                    gameOver = true;
                    return;
                }
                incrementLength(1);
                snakeCells.get(0).incrementX();
                break;

            case WEST:
                if((snakeCells.get(0).x - 1) < 0){
                    gameOver = true;
                    return;
                }
                incrementLength(1);
                snakeCells.get(0).decrementX();
        }
    }
    public SnakeCell getSnakeHead(){
        return snakeCells.get(0);
    }
}
