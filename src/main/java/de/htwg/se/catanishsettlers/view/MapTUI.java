package de.htwg.se.catanishsettlers.view;

import de.htwg.se.catanishsettlers.model.Config;
import de.htwg.se.catanishsettlers.model.map.Map;

/**
 * Created by Jonathan on 22.05.2015.
 */
public class MapTUI {
    Map map;
    public MapTUI(Map map) {
        this.map = map;
    }

    public String printMap() {
        String s = "";
        for (int i = 0; i < Config.FIELDS_HEIGHT; i++) {
            s += printFirstLine(i);
            s += printSecondLine(i);
            s += printThirdLine(i);
            s += printFourthLine(i);
        }
        s += printFirstLine(Config.FIELDS_HEIGHT);
        return s;
    }

    private String printFirstLine(int depth) {
        String s = "";
        return s;
    }

    private String printSecondLine(int depth) {
        String s = "";
        return s;
    }

    private String printThirdLine(int depth) {
        String s = "";
        return s;
    }

    private String printFourthLine(int depth) {
        String s = "";
        return s;
    }
}
