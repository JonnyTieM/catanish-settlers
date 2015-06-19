package de.htwg.se.catanishsettlers.controller;

import de.htwg.se.catanishsettlers.model.mechanic.Player;

import java.util.LinkedList;
import java.util.List;
import java.util.Observable;

/**
 * Created by Stephan on 15.06.2015.
 */
public class Players extends Observable {
    private List<Player> players = new LinkedList<Player>();
    private int activePlayerIndex = 0;

    public Players(List<Player> players) {
        this.players = players;
    }

    public Player next() {
        activePlayerIndex++;
        activePlayerIndex %= players.size();
        setChanged();
        notifyObservers();
        return getActivePlayer();
    }

    public Player getActivePlayer() {
        return players.get(activePlayerIndex);
    }

    public List<Player> getPlayers() {
        return players;
    }
}
