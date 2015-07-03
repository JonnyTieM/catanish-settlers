package de.htwg.se.catanishsettlers.view.gui.createGame;

import de.htwg.se.catanishsettlers.controller.Game;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Stephan on 25.06.2015.
 */
public class CreateGamePanel extends JPanel {

    private final Game game;
    private final JPanel addPlayersPanel = new JPanel();
    private CreatePlayerPanel youngestCreatePlayerPanel;

    public CreateGamePanel(Game game) {
        this.game = game;
        addPlayersPanel.setLayout(new GridLayout(0, 1));
        add(addPlayersPanel);
        addCreatePlayerPanel();
    }

    public CreatePlayerPanel addCreatePlayerPanel() {
        CreatePlayerPanel createPlayerPanel = new CreatePlayerPanel(game, this);
        youngestCreatePlayerPanel = createPlayerPanel;
        addPlayersPanel.add(createPlayerPanel);
        return createPlayerPanel;
    }

    public void removeCreatePlayerPanel(CreatePlayerPanel createPlayerPanel) {
        addPlayersPanel.remove(createPlayerPanel);
    }

    public CreatePlayerPanel getYoungestCreatePlayerPanel() {
        return youngestCreatePlayerPanel;
    }
}
