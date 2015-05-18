package de.htwg.se.catanishsettlers.view;

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

    public static enum Usecase {
        MAP_OVERVIEW,
        PLAYER,
        DICE_ROLL,
        CONSTRUCTION
    }

    public static Message[] generate(Usecase usecase, Object object) {
        List<Message> messages = new ArrayList<Message>();
        List<Message.Category> categories = new ArrayList<Message.Category>();
        Message.Detail detail = Message.Detail.LOW;
        String text;

        switch(usecase) {
            case MAP_OVERVIEW:
                Map map = (Map)object;
                categories.add(Message.Category.MAP);

                text = "Map overview:\n";
                messages.add(new Message(text, detail, categories));

                text = map.getFields().size() + " tiles";
                messages.add(new Message(text, detail, categories));
                break;

            case DICE_ROLL:
                DiceRoll diceRoll = (DiceRoll)object;
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
                break;

            case PLAYER:
                Player player = (Player)object;
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
                break;

            case CONSTRUCTION:
                Construction construction = (Construction)object;
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
        }

        return messages.toArray(new Message[messages.size()]);
    }
}
