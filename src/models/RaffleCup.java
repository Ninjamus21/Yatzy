package models;

import java.util.Random;

public class RaffleCup {
    private Die[] dice = new Die[5];

    public RaffleCup() {
        for (int i = 0; i < dice.length; i++) {
            dice[i] = new Die();
        }
    }

    public void throwDice() {
        Random random = new Random();
        for (Die die : dice) {
            if (!die.getisHeld()) {
                die.roll();
            }
        }
    }
    public Die[] getDice() {
        return dice;
    }
    public String toString(int index) {
        return String.valueOf(dice[index].getEyes());
    }
}
