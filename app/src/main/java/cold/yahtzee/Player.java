package cold.yahtzee;

import android.widget.Button;

import java.util.ArrayList;
import java.util.Arrays;

public class Player {
    Button[] chooseButtons;
    public boolean[] opt = new boolean[13];
    boolean ai;
    int rollsLeft = 2;
    int score = 0;

    Player(boolean isAi){
        this.ai = isAi;
    }
    Player(boolean isAi, Button[] buttonList){
        this.ai = isAi;
        Arrays.fill(this.opt,true);
        this.chooseButtons = buttonList;
    }
    public void turn(){

        rollsLeft = 2;}
    void addScore(int n){this.score += n;System.out.println(n);}
    void zeroScore(int n){this.score = 0;}
    public int getScore(){return this.score;}
}

class PlayerAi {

}
