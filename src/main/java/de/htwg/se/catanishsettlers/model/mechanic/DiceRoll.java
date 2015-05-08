package de.htwg.se.catanishsettlers.model.mechanic;

import de.htwg.se.catanishsettlers.view.IGenerateMessages;
import de.htwg.se.catanishsettlers.view.Message;

import java.util.ArrayList;

/**
 * Created by Stephan on 09.04.2015.
 */
public class DiceRoll implements IGenerateMessages {
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

    public Message[] getMessages() {
        ArrayList<Message> messages = new ArrayList<Message>();

        String text;
        Message.Detail detail;

        detail = Message.Detail.LOW;
        text = "Dice roll: " + getValue();
        messages.add(new Message(text, detail, categories));

        text = "(";
        detail = Message.Detail.HIGH;
        for (int i = 0; i < singleValues.length - 1; i++) {
            text += singleValues[i] + ", ";
        }
        text += singleValues[singleValues.length - 1] + ")";
        messages.add(new Message(text, detail, categories));

        return messages.toArray(new Message[messages.size()]);
    }
}
