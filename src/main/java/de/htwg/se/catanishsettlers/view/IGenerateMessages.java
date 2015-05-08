package de.htwg.se.catanishsettlers.view;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Stephan on 09.04.2015.
 */
public interface IGenerateMessages {
    List<Message.Category> categories = new ArrayList<Message.Category>();
    abstract Message[] getMessages();
}
