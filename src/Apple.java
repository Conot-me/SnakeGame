import java.util.ArrayList;
//This class represents an apple in the game. It has two properties (x and y) to represent the position of the apple, and a state to represent the current state of the apple.
// The relocateApple() method is used to randomly relocate the apple in the game.
public class Apple {
    public int x,y = 0;
    public State state = State.APPLE;

    //
    public void relocateApple(ArrayList<ArrayList<Integer>> cells){
        ArrayList<Integer> pos = cells.get((int)(Math.random()*cells.size()));
        x = pos.get(0);
        y = pos.get(1);
    }

}
