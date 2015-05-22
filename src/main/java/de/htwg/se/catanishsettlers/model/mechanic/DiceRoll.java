package de.htwg.se.catanishsettlers.model.mechanic;

/**
 * Created by Stephan on 09.04.2015.
 */
public class DiceRoll {
    private int value;
    private int[] singleValues;

    public DiceRoll(int diceCount) {
        singleValues = new int[diceCount];
        value = 0;

        for (int i = 0; i < diceCount; i++) {
            singleValues[i] = Utility.getInstance().getRandom().nextInt(6) + 1;
            value += singleValues[i];
        }
    }

    public int getValue() {
        return value;
    }
    public int[] getSingleValues() {return singleValues; }
}
