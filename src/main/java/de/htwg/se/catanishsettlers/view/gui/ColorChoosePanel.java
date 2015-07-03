package de.htwg.se.catanishsettlers.view.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

/**
 * Created by Stephan on 26.06.2015.
 */
class ColorChoosePanel extends JPanel {

    private final java.util.List<JRadioButton> radioButtonList = new LinkedList<JRadioButton>();

    public ColorChoosePanel(final JButton button, final CardLayout cardLayout) {

        ButtonGroup buttonGroup = new ButtonGroup();
        Color[] colors = { Color.RED, Color.BLUE, Color.YELLOW, Color.WHITE };

        for( Color color : colors ) {
            JRadioButton radioButton = new JRadioButton();
            radioButtonList.add(radioButton);
            radioButton.add(new JLabel("    "));
            radioButton.setBackground(color);

            radioButton.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    for (JRadioButton radioButton : radioButtonList) {
                        if (radioButton.isSelected()) {
                            button.setBackground(radioButton.getBackground());
                        }
                    }
                    cardLayout.next(getParent());
                }
            });

            buttonGroup.add(radioButton);
            add(radioButton);
        }
    }
}
