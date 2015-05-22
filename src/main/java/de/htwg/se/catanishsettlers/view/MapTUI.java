package de.htwg.se.catanishsettlers.view;

import de.htwg.se.catanishsettlers.model.Config;
import de.htwg.se.catanishsettlers.model.map.Edge;
import de.htwg.se.catanishsettlers.model.map.Field;
import de.htwg.se.catanishsettlers.model.map.Map;
import de.htwg.se.catanishsettlers.model.map.Vertex;
import de.htwg.se.catanishsettlers.model.resources.EResource;

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
        s += "  ";
        s += printVertex(0, 2 * depth);
        s += printEdge(0, 3 * depth);
        s += printVertex(1, 2 * depth);
        s += "  ";
        s += printField(1, depth - 1);
        s += "  ";
        s += printVertex(2, 2 * depth);
        s += printEdge(2, 3 * depth);
        s += printVertex(3, 2 * depth);
        s += "  ";
        s += printField(3, depth - 1);
        s += "  ";
        s += printVertex(4, 2 * depth);
        s += printEdge(4, 3 * depth);
        s += printVertex(5, 2 * depth);
        s += "\n";
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

    private String printVertex(int x, int y) {
        Vertex vertex = map.getVertex(x, y);
        if (vertex == null) {
            return " ";
        }
        if (vertex.hasBuilding()) {
            if (vertex.hasSettlement()) {
                return "S";
            }
            return "C";
        } else {
            return "X";
        }
    }

    private String printEdge(int x, int y) {
        Edge edge = map.getEdge(x, y);
        if (edge == null) {
            return " ";
        }
        if (edge.hasRoad()) {
            return "-";
        } else {
            return ".";
        }
    }

    private String printField(int x, int y) {
        Field field = map.getField(x, y);
        if (field == null) {
            return " ";
        }
        switch (field.getType()) {
            case BRICK:
                return "B";
            case GRAIN:
                return "G";
            case LUMBER:
                return "L";
            case ORE:
                return "O";
            case WOOL:
                return "W";
            default:
                return "F";
        }
    }
}
