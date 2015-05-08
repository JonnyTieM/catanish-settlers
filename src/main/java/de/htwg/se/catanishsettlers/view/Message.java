package de.htwg.se.catanishsettlers.view;

import java.util.List;

/**
 * Created by Stephan on 05.04.2015.
 */
public class Message {
    public String text;         // the actual message

    public static enum Detail {
        LOW,            // show only basic information
        MEDIUM,         // show moderate amount of information
        HIGH            // tell us all about it!
    }
    public Detail detail;

    public static enum Category {
        RESOURCE,
        CONSTRUCTION,
        GAMEPHASE,
        GAMESTATE,
        VICTORY,
        ROBBER,
        ROUTE_AND_KNIGHT,
        DEBUG
    }
    public List<Category> categories;

    public Message(String text, Detail detail, List<Category> categories) {
        this.text = text;
        this.detail = detail;
        this.categories = categories;
    }
}
