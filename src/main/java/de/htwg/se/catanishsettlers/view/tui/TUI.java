package de.htwg.se.catanishsettlers.view.tui;

import de.htwg.se.catanishsettlers.controller.Game;
import de.htwg.se.catanishsettlers.model.mechanic.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Jonathan on 19.06.2015.
 */
public class TUI {
    Game game;

    boolean end;
    Scanner input;

    public TUI() {
        end = false;
        input = Input.getInstance();

        TUIPreparationPhase preparationPhase = new TUIPreparationPhase();
        game = preparationPhase.getGame();
        while (!end) {
            end = true;
        }
    }


    public static void main(String[] args) {
        TUI tui = new TUI();
    }
}
