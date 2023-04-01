import java.util.ArrayList;

//This class is used to manage the current status of the game including the gameOver and gamePaused flags, game speed, grid size, cell dimensions, snakeBoard array, apple, and player.
//It also handles setting up the game, spawning the snake and apple, and moving the snake.
public class Status {
    public volatile Boolean gameOver;
    public volatile Boolean gamePaused;
    public int gameSpeed;
    public int gridRow;
    public int gridCol;
    private int cellWidth;
    private int cellHeight;
    public Cell[][] snakeBoard;
    private Apple apple = new Apple();
    public Snake player;
    Thread gameThread;

    public Status(int gridRow, int gridCol, int gameSpeed) {
        this.gameOver = true;
        this.gamePaused = true;
        this.gridRow = gridRow;
        this.gridCol = gridCol;
        this.gameSpeed = gameSpeed;
        this.player = new Snake(getGridRow() / 2 - 1, getGridCol()/ 2 - 1);
    }

    public void setWidthHeight(int width, int height){
        this.cellWidth = width/getGridRow();
        this.cellHeight = height/getGridCol();
    }

    public void startGameLoop(){
        gameThread = new Thread(){
            public void run() {
                gameSetup();

                while(true){
                    while(!getGameOver() && !getGamePaused()){
                        moveSnake(player.snakeCells.get(0));
                        try{
                            Thread.sleep(getGameSpeed());
                        }catch (Exception e){
                            System.out.println("Thread sleep error: " + e);
                        }
                    }
                }
            }
        };
        gameThread.start();
    }

    public void gameSetup(){
        snakeBoard = setGridArray();
        spawnSnake();
        spawnApple();
        setGameOver(false);
    }

    private Cell[][] setGridArray(){
        Cell[][] gridArray = new Cell[getGridRow()][getGridCol()];

        for (int i = 0; i < getGridRow(); i++){
            for (int j = 0; j < getGridCol(); j++){
                gridArray[i][j] = new Cell(cellWidth*i,cellHeight*j,cellWidth,cellHeight);
            }
        }
        return gridArray;
    }

    private void spawnApple(){
        if(apple.x >= 0) apple.relocateApple(findFreeCells());
        snakeBoard[apple.x][apple.y].state = apple.state;
    }

    private void spawnSnake(){
        player = new Snake(getGridRow() / 2 - 1, getGridCol()/ 2 - 1);
        SnakeCell snakeHead = player.getSnakeHead();
        snakeBoard[snakeHead.x][snakeHead.y].state = State.SNAKEHEAD;
    }

    private void moveSnake(SnakeCell head){
        snakeBoard[player.snakeCells.get(player.snakeCells.size()-1).x][player.snakeCells.get(player.snakeCells.size()-1).y].state = State.EMPTY;
        player.snakeMove(gameOver,snakeBoard);

        switch (snakeBoard[head.x][head.y].state) {
            case APPLE:
                player.incrementLength(1);
                player.bodyLength++;
                spawnApple();
                break;

            case SNAKEBODY, SNAKEHEAD:
                setGameOver(true);
                break;
        }

        if(getGameOver() == false){
            for (int i = 0; i < player.snakeCells.size(); i++) {
                snakeBoard[player.snakeCells.get(i).x][player.snakeCells.get(i).y].state = player.snakeCells.get(i).state;
            }
        }
    }

    private ArrayList<ArrayList<Integer>> findFreeCells(){
        ArrayList<ArrayList<Integer>> freeCells = new ArrayList<>();
        for (int i = 0; i < snakeBoard.length; i++) {
            for (int j = 0; j < snakeBoard[0].length; j++) {
                if(snakeBoard[i][j].state.getColor() == State.EMPTY.getColor()){
                    freeCells.add(new ArrayList<>());
                    freeCells.get(freeCells.size()-1).add(i);
                    freeCells.get(freeCells.size()-1).add(j);
                }
            }
        }
        return freeCells;
    }

    //getters and setters
    public Boolean getGameOver() {
        return gameOver;
    }

    public int getGameSpeed() {
        return gameSpeed;
    }

    public int getGridRow() {
        return gridRow;
    }

    public int getGridCol() {
        return gridCol;
    }

    public void setGameOver(Boolean gameOver) {
        this.gameOver = gameOver;
    }

    public Boolean getGamePaused() {
        return gamePaused;
    }

    public void setGamePaused(Boolean gamePaused) {
        this.gamePaused = gamePaused;
    }

    public void setGameSpeed(int gameSpeed) {
        this.gameSpeed = gameSpeed;
    }
}