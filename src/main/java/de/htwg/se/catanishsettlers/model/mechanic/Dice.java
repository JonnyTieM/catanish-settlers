package de.htwg.se.catanishsettlers.model.mechanic;

import java.util.Observable;

/**
 * Created by Stephan on 09.04.2015.
 */
public class Dice extends Observable {
    private int value;
    private final int[] singleValues;

    private final int diceCount, faceCount;

    public Dice(int diceCount, int faceCount) {
        this.diceCount = diceCount;
        this.faceCount = faceCount;
        this.singleValues = new int[diceCount];
    }

    public void roll() {
        value = 0;

        for (int i = 0; i < diceCount; i++) {
            singleValues[i] = Utility.getInstance().getRandom().nextInt(faceCount) + 1;
            value += singleValues[i];
        }

        setChanged();
        notifyObservers();
    }

    public int getValue() {
        return value;
    }
    public int[] getSingleValues() {return singleValues; }
    public int getDiceCount() { return diceCount; }
}
