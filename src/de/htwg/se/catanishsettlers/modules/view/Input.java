package de.htwg.se.catanishsettlers.modules.view;

import java.util.Scanner;

/**
 * Created by Stephan on 10.04.2015.
 */
public class Input {
    private static Scanner singleton;

    private Input() {
        singleton = new Scanner(System.in);
    }

    public static Scanner getInstance() {
        if (singleton == null) new Input();
        return singleton;
    }
}
