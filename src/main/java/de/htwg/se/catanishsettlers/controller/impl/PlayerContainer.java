package de.htwg.se.catanishsettlers.controller.impl;

import de.htwg.se.catanishsettlers.model.mechanic.Player;

import java.awt.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Observable;

/**
 * Created by Stephan on 15.06.2015.
 */
public class PlayerContainer extends Observable {
    private List<Player> players = new LinkedList<Player>();
    private int activePlayerIndex = 0;

    public PlayerContainer(List<Player> players) {
        this.players = players;
    }

    public PlayerContainer() {}

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

    public Player getPlayer(int i) {
        return players.get(i);
    }

    public void add(final Player player) {
        players.add(player);
        setChanged();
        notifyObservers();
    }

    public void remove(final Player player) {
        players.remove(player);
        setChanged();
        notifyObservers();
    }
}
