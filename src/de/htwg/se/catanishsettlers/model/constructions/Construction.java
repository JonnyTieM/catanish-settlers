package de.htwg.se.catanishsettlers.model.constructions;

import de.htwg.se.catanishsettlers.model.map.MapObject;
import de.htwg.se.catanishsettlers.model.mechanic.Investment;
import de.htwg.se.catanishsettlers.model.mechanic.Player;
import de.htwg.se.catanishsettlers.view.IGenerateMessages;
import de.htwg.se.catanishsettlers.view.Message;

import java.util.ArrayList;

/**
 * Created by Stephan on 01.04.2015.
 */
public abstract class Construction extends Investment implements IGenerateMessages{
    protected Player owner;

    public Construction(Player player) {
        categories.add(Message.Category.CONSTRUCTION);
        owner = player;
    }

    public Player getPlayer() {
        return owner;
    }

    public Message[] getMessages() {
        ArrayList<Message> messages = new ArrayList<Message>();

        String text;
        Message.Detail detail;

        detail = Message.Detail.LOW;
        text = getClass().getSimpleName();
        messages.add(new Message(text, detail, categories));

        detail = Message.Detail.MEDIUM;
        text = "(TODO: COORDINATES)";
        messages.add(new Message(text, detail, categories));

        detail = Message.Detail.HIGH;
        text = "owned by " + getPlayer().getName();
        messages.add(new Message(text, detail, categories));

        return messages.toArray(new Message[messages.size()]);
    }
}
