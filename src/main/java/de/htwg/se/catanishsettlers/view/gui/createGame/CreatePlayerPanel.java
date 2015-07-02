package de.htwg.se.catanishsettlers.view.gui.createGame;

import de.htwg.se.catanishsettlers.CatanishSettlers;
import de.htwg.se.catanishsettlers.controller.impl.PlayerContainer;
import de.htwg.se.catanishsettlers.model.mechanic.Player;
import de.htwg.se.catanishsettlers.view.gui.statusPanel.StatusPanel;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Stephan on 25.06.2015.
 */
public class CreatePlayerPanel extends JPanel {

    private final JButton addButton, removeButton;
    private final CreatePlayerPanel thisPanel;
    private final JTextField textField;
    private final ColorChoosePanel colorChoosePanel;
    private Player player;
    private final Color defaultColor = Color.BLACK;

    public CreatePlayerPanel(final CreateGamePanel createGamePanel) {
        thisPanel = this;

        setLayout(new FlowLayout());
        textField = new JTextField();
        textField.setPreferredSize(new Dimension(130, 25));

        final JButton changeColorButton = new JButton("change Color");
        changeColorButton.setBackground(defaultColor);
        final JPanel openColorPanelCardPanel = new JPanel();
        final CardLayout cardLayout = new CardLayout();

        addButton = new JButton("add to game");
        addButton.setEnabled(false);
        removeButton = new JButton("remove from game");
        removeButton.setEnabled(false);

        openColorPanelCardPanel.setLayout(cardLayout);

        openColorPanelCardPanel.add(changeColorButton);
        colorChoosePanel = new ColorChoosePanel(changeColorButton, cardLayout);
        openColorPanelCardPanel.add(colorChoosePanel);

        changeColorButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardLayout.next(openColorPanelCardPanel);
                openColorPanelCardPanel.revalidate();
                openColorPanelCardPanel.repaint();
                revalidate();
                repaint();
            }
        });

        textField.getDocument().addDocumentListener(new DocumentListener() {
            public void insertUpdate(DocumentEvent e) {
                textFieldUpdated(textField);
            }
            public void removeUpdate(DocumentEvent e) { textFieldUpdated(textField); }
            public void changedUpdate(DocumentEvent e) { textFieldUpdated(textField); }
        });

        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                removeButton.setEnabled(true);
                addButton.setEnabled(false);
                changeColorButton.setEnabled(false);
                StatusPanel.switchButton.setEnabled(true);
                CreatePlayerPanel newCreatePlayerPanel = createGamePanel.addCreatePlayerPanel();
                newCreatePlayerPanel.getTextField().requestFocus();

                player = new Player(textField.getText());
                player.setColor(changeColorButton.getBackground());
                CatanishSettlers.game.getPlayerContainer().add(player);
                textField.setEnabled(false);

                createGamePanel.revalidate();
                createGamePanel.repaint();
            }
        });

        removeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                PlayerContainer pc = CatanishSettlers.game.getPlayerContainer();
                pc.remove(player);
                if(pc.getPlayers().size() == 0) StatusPanel.switchButton.setEnabled(false);

                createGamePanel.removeCreatePlayerPanel(thisPanel);
                createGamePanel.getYoungestCreatePlayerPanel().getTextField().requestFocus();
                createGamePanel.revalidate();
                createGamePanel.repaint();
            }
        });

        add(new JLabel("Name: "));
        add(textField);
        add(openColorPanelCardPanel);
        add(addButton);
        add(removeButton);
    }

    private void textFieldUpdated(JTextField textField) {
        String name = textField.getText();
        if (name.isEmpty() || nameIsAlreadyInUse(name)) {
            addButton.setEnabled(false);
        } else {
            addButton.setEnabled(true);
        }
    }

    private boolean nameIsAlreadyInUse(String name) {
        for (Player player : CatanishSettlers.game.getPlayerContainer().getPlayers()) {
            if (player.getName().equals(name)) return true;
        }
        return false;
    }

    public JTextField getTextField() {
        return textField;
    }
}
