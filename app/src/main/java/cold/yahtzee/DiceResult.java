package cold.yahtzee;

public class DiceResult {

    public static int count(Dice[] diceList,int n){
        int p = 0;
        for(Dice d : diceList){
            if(d.value == n)p+=d.value;
        }
        return p;
    }

    public static int threeOf(Dice[] diceList){
        int[] match = new int[6];
        for(Dice d : diceList){
            match[d.value - 1]++;
        }
        for(int i = 0;i < 6;i++){
            if(match[i] >= 3)return i * 3;
        }
        return 0;
    }

    public static int fourOf(Dice[] diceList){
        int[] match = new int[6];
        for(Dice d : diceList){
            match[d.value - 1]++;
        }
        for(int i = 0;i < 6;i++){
            if(match[i] >= 4)return i * 4;
        }
        return 0;
    }

    public static int full(Dice[] diceList){
        int[] match = new int[6];
        for(Dice d : diceList){
            match[d.value - 1]++;
        }
        for(int i = 0;i < 6;i++){
            if(match[i] == 1)return 0;
            if(match[i] == 5)return 0;
            return 25;
        }
        return 0;
    }

    public static int sstr(Dice[] diceList){
        int[] match = new int[6];
        for(Dice d : diceList){
            match[d.value - 1]++;
        }
        if(match[2] <= 1 && match[3] <= 1 && match[4] <= 1 && match[5] <= 1)return 30;
        if(match[1] <= 1 && match[2] <= 1 && match[3] <= 1 && match[4] <= 1)return 30;
        if(match[0] <= 1 && match[1] <= 1 && match[2] <= 1 && match[3] <= 1)return 30;
        return 0;
    }

    public static int lstr(Dice[] diceList){
        int[] match = new int[6];
        for(Dice d : diceList){
            match[d.value - 1]++;
        }
        if(match[1] == 1 && match[2] == 1 && match[3] == 1 && match[4] == 1 && match[5] == 1)return 40;
        if(match[0] == 1 && match[1] == 1 && match[2] == 1 && match[3] == 1 && match[4] == 1)return 40;
        return 0;
    }

    public static int yahtzee(Dice[] diceList){
        int[] match = new int[6];
        for(Dice d : diceList){
            match[d.value - 1]++;
        }
        for(int i = 0;i < 6;i++){
            if(match[i] == 5)return 50;
        }
        return 0;
    }
    public static int chance(Dice[] diceList){
        int p = 0;
        for(Dice d : diceList){
            p += d.value;
        }
        return p;
    }
}
