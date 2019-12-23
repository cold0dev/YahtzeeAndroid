package cold.yahtzee;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ImageButton dice1b;
    ImageButton dice2b;
    ImageButton dice3b;
    ImageButton dice4b;
    ImageButton dice5b;
    Button rollButton;
    Button[] buttonList;

    public static Dice[] diceList;
    public static List<Player> playerList = new ArrayList<>();
    public static int activePlayer = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dice1b = (ImageButton)findViewById(R.id.dice1);
        dice2b = (ImageButton)findViewById(R.id.dice2);
        dice3b = (ImageButton)findViewById(R.id.dice3);
        dice4b = (ImageButton)findViewById(R.id.dice4);
        dice5b = (ImageButton)findViewById(R.id.dice5);
        rollButton = (Button)findViewById(R.id.rollButton);

        diceList = new Dice[]{
                new Dice(dice1b),
                new Dice(dice2b),
                new Dice(dice3b),
                new Dice(dice4b),
                new Dice(dice5b),
        };

        Player player1 = new Player(false);
        playerList.add(player1);
    }

    void goToMain(){
        setContentView(R.layout.activity_main);
        dice1b = (ImageButton)findViewById(R.id.dice1);
        dice2b = (ImageButton)findViewById(R.id.dice2);
        dice3b = (ImageButton)findViewById(R.id.dice3);
        dice4b = (ImageButton)findViewById(R.id.dice4);
        dice5b = (ImageButton)findViewById(R.id.dice5);
        rollButton = (Button)findViewById(R.id.rollButton);

        diceList = new Dice[]{
                new Dice(dice1b),
                new Dice(dice2b),
                new Dice(dice3b),
                new Dice(dice4b),
                new Dice(dice5b),
        };
    }

    //Logic
    public void roll(View v){
        if(playerList.get(activePlayer).rollsLeft == 0){goToChoose();return;}
        for(Dice d : diceList){
            if(d.selected)d.rollDice();
        }
        playerList.get(activePlayer).rollsLeft -= 1;
        if(playerList.get(activePlayer).rollsLeft == 0)rollButton.setText(R.string.choose);
    }

    public void reset(){
        for(Dice d : diceList){
            if(d.selected)d.selectDice();
            d.rollDice();
        }
    }

    public void select(View v){
        if(playerList.get(activePlayer).rollsLeft == 0)return;
        String diceNumberS = v.getTag().toString();
        int diceNumber = Integer.parseInt(diceNumberS);
        diceList[diceNumber - 1].selectDice();
    }

    //Button
    public void goToChoose(){
        setContentView(R.layout.activity_choose);
        buttonList = new Button[]
                {
                        (Button) findViewById(R.id.acesButton),
                        (Button) findViewById(R.id.twosButton),
                        (Button) findViewById(R.id.threesButton),
                        (Button) findViewById(R.id.foursButton),
                        (Button) findViewById(R.id.fivesButton),
                        (Button) findViewById(R.id.sixesButton),
                        (Button) findViewById(R.id.threeOfbutton),
                        (Button) findViewById(R.id.fourOfButton),
                        (Button) findViewById(R.id.fullButton),
                        (Button) findViewById(R.id.sstrButton),
                        (Button) findViewById(R.id.lstrButton),
                        (Button) findViewById(R.id.yahtzeeButton),
                        (Button) findViewById(R.id.chanceButton)};
        for(int i = 0;i < 13;i++){
            if(playerList.get(activePlayer).opt[i])buttonList[i].setEnabled(false);
        }
        rollButton.setText(R.string.roll);
        //TURN CHANGE PLAYER


    }

    public void choose(View v){
        String opt = v.getTag().toString();
        int points = 0;
        switch(opt){
            case "aces":
                points = DiceResult.count(MainActivity.diceList,1);
                playerList.get(activePlayer).opt[0] = true;
                break;
            case "twos":
                points = DiceResult.count(MainActivity.diceList,2);
                playerList.get(activePlayer).opt[1] = true;
                break;
            case "threes":
                points = DiceResult.count(MainActivity.diceList,3);
                playerList.get(activePlayer).opt[2] = true;
                break;
            case "fours":
                points = DiceResult.count(MainActivity.diceList,4);
                playerList.get(activePlayer).opt[3] = true;
                break;
            case "fives":
                points = DiceResult.count(MainActivity.diceList,5);
                playerList.get(activePlayer).opt[4] = true;
                break;
            case "sixes":
                points = DiceResult.count(MainActivity.diceList,6);
                playerList.get(activePlayer).opt[5] = true;
                break;
            case "threeof":
                points = DiceResult.threeOf(MainActivity.diceList);
                playerList.get(activePlayer).opt[6] = true;
                break;
            case "fourof":
                points = DiceResult.fourOf(MainActivity.diceList);
                playerList.get(MainActivity.activePlayer).opt[7] = true;
                break;
            case "full":
                points = DiceResult.full(MainActivity.diceList);
                playerList.get(activePlayer).opt[8] = true;
                break;
            case "sstr":
                points = DiceResult.sstr(MainActivity.diceList);
                playerList.get(activePlayer).opt[9] = true;
                break;
            case "lstr":
                points = DiceResult.lstr(MainActivity.diceList);
                playerList.get(activePlayer).opt[10] = true;
                break;
            case "yahtzee":
                points = DiceResult.yahtzee(MainActivity.diceList);
                playerList.get(activePlayer).opt[11] = true;
                break;
            case "chance":
                points = DiceResult.chance(MainActivity.diceList);
                playerList.get(activePlayer).opt[12] = true;
                break;
        }
        playerList.get(activePlayer).turn();
        playerList.get(activePlayer).addScore(points);
        goToMain();
        check();
        reset();
    }

    public void check(){
        int[] points = new int[playerList.size()];
        for(int i = 0;i < playerList.size();i++){
            for(boolean o : playerList.get(i).opt){
                if(!o)return;
            }
            points[i] = playerList.get(i).getScore();
        }
        setContentView(R.layout.activity_result);
        TextView player1 = (TextView)findViewById(R.id.player1);
        TextView player2 = (TextView)findViewById(R.id.player2);
        TextView player3 = (TextView)findViewById(R.id.player3);
        TextView player4 = (TextView)findViewById(R.id.player4);
        TextView[] playerTexts = new TextView[]{
                player1,
                player2,
                player3,
                player4,
        };
        int playersNumber = playerList.size();
        for(int i = 0;i < playersNumber;i++){
            String player = "Player " + playersNumber + ":" + points[i];
            playerTexts[i].setText(player);
        }
    }

}
