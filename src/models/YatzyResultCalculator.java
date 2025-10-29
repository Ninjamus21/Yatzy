package models;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * Used to calculate the score of throws with 5 dice
 */
public class YatzyResultCalculator {
    private Die[] dice;


    /**
     *
     * @param dice
     */
    public YatzyResultCalculator(Die[] dice) {
        //TODO: implement YatzyResultCalculator constructor.
        this.dice = dice;
    }

    /**
     * Calculates the score for Yatzy uppersection
     *
     * @param eyes eye value to calculate score for. eyes should be between 1 and 6
     * @return the score for specified eye value
     */
    public int upperSectionScore(int eyes) {
        //TODO: Implement upperSectionScore method.
        int sum = 0;
        for (Die die : dice) {
            if (die.getEyes() == eyes) {
                sum += die.getEyes();
            }
        }

        return sum;
    }

    private int[] sortEyeToLow() {
        int[] eyes = new int[dice.length];

        for (int index = 0; index < eyes.length; index++) {
            eyes[index] = dice[index].getEyes();
        }
        Arrays.sort(eyes);
        return eyes;
    }

    public int onePairScore() {
        int[] eyes = sortEyeToLow();
        for (int index = eyes.length - 1; index > 0; index--) {
            if (eyes[index] == eyes[index - 1]) {
                return eyes[index] * 2;
            }
        }
        return 0;
    }

    public int twoPairScore() {
        int[] eyes = sortEyeToLow();
        int[] pairs = new int[2];

        for (int index = eyes.length - 1; index > eyes.length - 4; index--) {
            if (eyes[index] == eyes[index - 1]) {
                pairs[0] = eyes[index];
            }
        }
        for (int index = eyes.length - 1; index > 0; index--) {
            if (eyes[index] == eyes[index - 1]) {
                if (pairs[0] != eyes[index]) {
                    pairs[1] = eyes[index];
                    return (pairs[0] + pairs[1]) * 2;
                }
            }
        }
        return 0;
    }

    public int threeOfAKindScore() {
        int[] eyes = sortEyeToLow();
        for (int index = eyes.length - 1; index > eyes.length - 3; index--) {
            if (eyes[index] == eyes[index - 1] && eyes[index] == eyes[index - 2]) {
                return eyes[index] * 3;
            }
        }
        return 0;
    }

    public int fourOfAKindScore() {
        int[] eyes = sortEyeToLow();
        for (int index = eyes.length - 1; index > eyes.length - 2; index--) {
            if (eyes[index] == eyes[index - 1] && eyes[index] == eyes[index - 2] && eyes[index] == eyes[index - 3]) {
                return eyes[index] * 4;
            }
        }
        return 0;
    }

    public int smallStraightScore() {
        int[] eyes = sortEyeToLow();
        boolean isSmallStraight = false;

        for (int i = 0; i < eyes.length; i++) {
            if (eyes[i] != i + 1) {
                isSmallStraight = false;
                break;
            } else if (6 == eyes[i]) {
                isSmallStraight = false;
                break;

            } else {
                isSmallStraight = true;
            }
        }
        if (isSmallStraight) {
            return 15;
        }
        return 0;
    }

    public int largeStraightScore() {
        int[] eyes = sortEyeToLow();
        boolean isLargeStraight = false;
        for (int i = 0; i < eyes.length; i++) {
            if (eyes[i] != i + 2) {
                isLargeStraight = false;
                break;
            } else if (1 == eyes[i]) {
                isLargeStraight = false;
                break;
            } else {
                isLargeStraight = true;
            }

        }
        if (isLargeStraight) {
            return 20;
        }

        return 0;
    }

    public int fullHouseScore() {
        if (threeOfAKindScore() > 0 && onePairScore() > 0 && fourOfAKindScore() == 0) {
            return threeOfAKindScore() + onePairScore();
        }
        return 0;
    }

    public int chanceScore() {
        int sum = 0;
        for (Die die : dice) {
            sum += die.getEyes();
        }
        return sum;
    }

    public int yatzyScore() {
        int[] eyes = sortEyeToLow();
        for (int i = 0; i < eyes.length - 1; i++) {
            if (eyes[i] != eyes[i + 1]) {
                return 0;
            }
        }
        return 50;
    }
}
