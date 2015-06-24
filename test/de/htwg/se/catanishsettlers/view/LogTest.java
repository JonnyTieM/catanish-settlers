package de.htwg.se.catanishsettlers.view;

import de.htwg.se.catanishsettlers.model.constructions.City;
import de.htwg.se.catanishsettlers.model.constructions.Road;
import de.htwg.se.catanishsettlers.model.constructions.Settlement;
import de.htwg.se.catanishsettlers.model.mechanic.Dice;
import de.htwg.se.catanishsettlers.model.mechanic.Player;
import de.htwg.se.catanishsettlers.view.tui.Log;
import de.htwg.se.catanishsettlers.view.tui.Message;
import org.junit.Test;

public class LogTest {

    @Test
    public void test() throws Exception {
        Log.categories.add(Message.Category.VICTORY);
        Log.categories.add(Message.Category.CONSTRUCTION);
        Log.detailLevel = Message.Detail.HIGH;
        Settlement settlement = new Settlement(new Player("Hans"));
        City city = new City(new Player("Peter"));
        Road road = new Road(new Player("Susi"));

        Player player = new Player("Test Heinrich");

        Dice roll = new Dice(5);

        Log.display(roll.getMessages());

        Log.display(player.getMessages());

        Log.display(settlement.getMessages());
        Log.display(city.getMessages());
        Log.display(road.getMessages());

        System.out.println("------------------------------------------");

        Log.detailLevel = Message.Detail.MEDIUM;


        Log.display(roll.getMessages());

        Log.display(player.getMessages());

        Log.display(settlement.getMessages());
        Log.display(city.getMessages());
        Log.display(road.getMessages());

        System.out.println("------------------------------------------");

        Log.detailLevel = Message.Detail.LOW;


        Log.display(roll.getMessages());

        Log.display(player.getMessages());

        Log.display(settlement.getMessages());
        Log.display(city.getMessages());
        Log.display(road.getMessages());
    }
}