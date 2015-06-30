package de.htwg.se.catanishsettlers.view.gui.MainFrame;

import de.htwg.se.catanishsettlers.CatanishSettlers;
import de.htwg.se.catanishsettlers.model.mechanic.Player;

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
            public void removeUpdate(DocumentEvent e) {}
            public void changedUpdate(DocumentEvent e) {}
        });

        addButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                removeButton.setEnabled(true);
                addButton.setEnabled(false);
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
                CatanishSettlers.game.getPlayerContainer().remove(player);

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
        if (player != null) return;
        if (textField.getText().isEmpty()) {
            addButton.setEnabled(false);
            System.out.println("hi");
        } else {
            addButton.setEnabled(true);
        }
    }

    public JTextField getTextField() {
        return textField;
    }
}
