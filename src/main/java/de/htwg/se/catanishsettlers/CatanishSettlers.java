package de.htwg.se.catanishsettlers;

import de.htwg.se.catanishsettlers.controller.Game;
import de.htwg.se.catanishsettlers.model.constructions.Road;
import de.htwg.se.catanishsettlers.model.mechanic.DiceRoll;
import de.htwg.se.catanishsettlers.model.mechanic.Player;
import de.htwg.se.catanishsettlers.view.Log;
import de.htwg.se.catanishsettlers.view.Message;
import de.htwg.se.catanishsettlers.view.MessageFactory;

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
        Log.display(MessageFactory.generate(MessageFactory.Usecase.MAP_OVERVIEW, game.getMap()));
        Log.categories.add(Message.Category.DICE_ROLL);
        Log.display(MessageFactory.generate(MessageFactory.Usecase.DICE_ROLL, new DiceRoll(3)));
        Log.categories.add(Message.Category.PLAYER);
        Log.display(MessageFactory.generate(MessageFactory.Usecase.PLAYER, players.get(0)));
        Log.categories.add(Message.Category.CONSTRUCTION);
        Log.display(MessageFactory.generate(MessageFactory.Usecase.CONSTRUCTION, new Road(players.get(1))));
    }
}
