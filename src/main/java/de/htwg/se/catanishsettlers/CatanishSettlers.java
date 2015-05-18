package de.htwg.se.catanishsettlers;

import de.htwg.se.catanishsettlers.controller.Game;
import de.htwg.se.catanishsettlers.model.mechanic.Player;
import de.htwg.se.catanishsettlers.view.Log;
import de.htwg.se.catanishsettlers.view.Message;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sttrube on 27.03.2015.
 */
public class CatanishSettlers {

    public static void main(String[] args) {
        List<Player> players = new ArrayList<Player>();
        players.add(new Player("Hans"));
        players.add(new Player("Susi"));
        players.add(new Player("John"));

        Game game = new Game(players);
        Log.categories.add(Message.Category.MAP);
        Log.display(game.getMap().getMessages());
    }
}
