package cold.yahtzee;

import android.widget.ImageButton;

import java.util.Random;

public class Dice {
    Random rand = new Random();
    ImageButton diceBox;
    public int value = 0;
    boolean selected = false;

    Dice(ImageButton db){
        this.diceBox = db;
        //Rand
        this.rollDice();
    }

    public void rollDice(){
        int lastValue = this.value;
        int n;
        while(true) {
            n = rand.nextInt(4) + 1;
            if (lastValue != n)break;
        }
        this.value = n;
        this.diceBox.setImageResource(getImage(this.value));
        this.selected = false;
        this.diceBox.setBackgroundColor(0x00FFFFFF);
    }

    public void selectDice(){
        if(!this.selected){
            this.selected = true;
            this.diceBox.setBackgroundColor(0xFF2196F3);
        }
        else{

            this.selected = false;
            this.diceBox.setBackgroundColor(0x00FFFFFF);
        }}

    int getImage(int i){
        switch(i){
            case 1:
                return R.mipmap.d1;
            case 2:
                return R.mipmap.d2;
            case 3:
                return R.mipmap.d3;
            case 4:
                return R.mipmap.d4;
            case 5:
                return R.mipmap.d5;
        }
        return 0;
    }
}
