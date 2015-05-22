package de.htwg.se.catanishsettlers.view;

import de.htwg.se.catanishsettlers.model.constructions.Construction;
import de.htwg.se.catanishsettlers.model.map.Field;
import de.htwg.se.catanishsettlers.model.map.Map;
import de.htwg.se.catanishsettlers.model.map.Vertex;
import de.htwg.se.catanishsettlers.model.mechanic.DiceRoll;
import de.htwg.se.catanishsettlers.model.mechanic.Player;
import de.htwg.se.catanishsettlers.model.map.Edge;

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
        Field[][] fields = map.getFields();

        categories.add(Message.Category.MAP);

        text = "Map overview:";
        messages.add(new Message(text, detail, categories));

        text = map.getFieldsCount() + " tiles\n\n";
        messages.add(new Message(text, detail, categories));

        for (int row = 0; row < fields.length; row++) {
            Field[] fieldRows = fields[row];
            if (row % 2 == 1) messages.add(new Message(" ", detail, categories));
            for (int col = 0; col < fieldRows.length; col++) {
                Field field = fields[row][col];
                if (field == null) {
                    text = "   ";
                } else {
                    Edge[] edges = map.getEdges(field);
                    Vertex[] vertices = map.getVertices(field);

                    for (int y = 0; y < 3; y++) {
                        // TODO: build a string consisting of edges, vertices and fields to be shown as one single line
                    }

                    text = " " + field.getType().toString().charAt(0) + " ";
                }
                messages.add(new Message(text, detail, categories));
            }
            messages.add(new Message("\n\n", detail, categories));
        }

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
