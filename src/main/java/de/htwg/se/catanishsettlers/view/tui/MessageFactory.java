package de.htwg.se.catanishsettlers.view.tui;

import de.htwg.se.catanishsettlers.model.constructions.Construction;
import de.htwg.se.catanishsettlers.model.map.Map;
import de.htwg.se.catanishsettlers.model.mechanic.DiceRoll;
import de.htwg.se.catanishsettlers.model.mechanic.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Stephan on 18.05.2015.
 */
public class MessageFactory {

    private static List<Message> messages;
    private static List<Message.Category> categories;
    private static Message.Detail detail;
    private static String text;

    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_BLUE = "\u001B[34m";
    private static final String ANSI_MAGENTA = "\u001B[35m";
    private static final String ANSI_OLIVE = "\u001B[36m";

    private static void initialize() {
        messages = new ArrayList<Message>();
        categories = new ArrayList<Message.Category>();
        detail = Message.Detail.LOW;
        text = "";
    }

    public static Message[] map_overview(Map map) {
        initialize();

        categories.add(Message.Category.MAP);

        text = "Map overview:";
        messages.add(new Message(text, detail, categories));

        text = map.getFieldsCount() + " tiles\n\n";
        messages.add(new Message(text, detail, categories));

        MapTUI mapTUI = new MapTUI(map);

        messages.add(new Message(mapTUI.printMap(), detail, categories));

        return messages.toArray(new Message[messages.size()]);
    }

    public static Message[] dice_roll(DiceRoll diceRoll) {
        initialize();
        categories.add(Message.Category.DICE_ROLL);

        int[] singleValues = diceRoll.getSingleValues();
        text = "Dice roll:\n";
        messages.add(new Message(text, detail, categories));

        text = String.valueOf(diceRoll.getValue());
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

    public static Message[] player(Player player) {
        initialize();
        categories.add(Message.Category.PLAYER);

        detail = Message.Detail.MEDIUM;
        text = "Player";
        messages.add(new Message(text, detail, categories));

        detail = Message.Detail.LOW;
        text = player.getName();
        messages.add(new Message(text, detail, categories));

        detail = Message.Detail.MEDIUM;
        text = "(" + player.getScore() + " points)";
        messages.add(new Message(text, detail, categories));

        detail = Message.Detail.HIGH;
        text = System.lineSeparator() + player.getResources().toString();
        messages.add(new Message(text, detail, categories));

        return messages.toArray(new Message[messages.size()]);
    }

    public static Message[] construction(Construction construction) {
        initialize();
        categories.add(Message.Category.CONSTRUCTION);

        detail = Message.Detail.LOW;
        text = construction.getClass().getSimpleName();
        messages.add(new Message(text, detail, categories));

        detail = Message.Detail.MEDIUM;
        text = "(TODO: COORDINATES)";
        messages.add(new Message(text, detail, categories));

        detail = Message.Detail.HIGH;
        text = "owned by " + construction.getPlayer().getName();
        messages.add(new Message(text, detail, categories));

        return messages.toArray(new Message[messages.size()]);
    }
}
