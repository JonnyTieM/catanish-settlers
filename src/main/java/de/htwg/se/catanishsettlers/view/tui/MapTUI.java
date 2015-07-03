package de.htwg.se.catanishsettlers.view.tui;

import de.htwg.se.catanishsettlers.model.Config;
import de.htwg.se.catanishsettlers.model.map.Edge;
import de.htwg.se.catanishsettlers.model.map.Field;
import de.htwg.se.catanishsettlers.model.map.Map;
import de.htwg.se.catanishsettlers.model.map.Vertex;
import de.htwg.se.catanishsettlers.model.mechanic.Player;

/**
 * This class can convert a map to a printable text output.
 * Created by Jonathan on 22.05.2015.
 */
class MapTUI {

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    private final Map map;
    private Vertex markedVertex;
    private Edge markedEdge;
    private Player activePlayer;

    public MapTUI(Map map) {
        this.map = map;
    }

    //just for testing the output
    public static void main(String[] args) {
        Map map = new Map();
        MapTUI m = new MapTUI(map);
        m.markVertex(2, 4);
        m.markEdge(4,4);
        m.printMap();
    }

    public void printMap() {
        String s = "";
        for (int i = 0; i < Config.FIELDS_HEIGHT; i++) {
            s += printFirstLine(i);
            s += printSecondLine(i);
            s += printThirdLine(i);
            s += printFourthLine(i);
        }
        s += printFirstLine(Config.FIELDS_HEIGHT);
        System.out.println(s);
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
        return printStreetLine(3 * depth + 1);
    }

    private String printThirdLine(int depth) {
        String s = "";
        s += printVertex(0, 2 * depth + 1);
        s += "  ";
        s += printField(0, depth);
        s += "  ";
        s += printVertex(1, 2 * depth + 1);
        s += printEdge(1, 3 * depth);
        s += printVertex(2, 2 * depth + 1);
        s += "  ";
        s += printField(2, depth);
        s += "  ";
        s += printVertex(3, 2 * depth + 1);
        s += printEdge(3, 3 * depth);
        s += printVertex(4, 2 * depth + 1);
        s += "  ";
        s += printField(4, depth);
        s += "  ";
        s += printVertex(5, 2 * depth + 1);
        s += "\n";
        return s;
    }

    private String printFourthLine(int depth) {
        return printStreetLine(3 * depth + 2);
    }

    /**
     * this can print the lines where are only streets in it (which is the second and the fourth line)
     *
     * @param y y-value of the given streets
     * @return printed pure street line
     */
    private String printStreetLine(int y) {
        String s = "";
        s += " ";
        for (int i = 0; i < Config.EDGES_WIDTH; i++) {
            s += printEdge(i, y);
            s += "   ";
        }
        s += "\n";
        return s;
    }

    public void markVertex(int x, int y) {
        Vertex vertex = map.getVertex(x, y);
        if (vertex != null) markedVertex = vertex;
    }

    public void unmarkVertex() {
        markedVertex = null;
    }

    public boolean markEdge(int x, int y) {
        Edge edge = map.getEdge(x, y);
        if (edge == null) {
            return false;
        } else {
            markedEdge = edge;
            return true;
        }
    }

    public void unmarkEdge() {
        markedEdge = null;
    }

    public void setActivePlayer(Player player) {
        activePlayer = player;
    }

    private String printVertex(int x, int y) {
        String s;
        Vertex vertex = map.getVertex(x, y);
        if (vertex == null) {
            return " ";
        }
        if (vertex.hasBuilding()) {
            if (vertex.hasSettlement()) {
                s = "S";
            } else {
                s = "C";
            }
        } else {
            s = "X";
        }
        return checkVertexColor(s, vertex);
    }

    private String checkVertexColor(String s, Vertex vertex) {
        if (vertex == markedVertex) {
            s = ANSI_RED + s + ANSI_RESET;
        } else if (vertex.hasBuilding() && vertex.getBuilding().getPlayer() == activePlayer) {
            s = ANSI_GREEN + s + ANSI_RESET;
        }
        return s;
    }

    private String printEdge(int x, int y) {
        String s;
        Edge edge = map.getEdge(x, y);
        if (edge == null) {
            return " ";
        }
        if (edge.hasRoad()) {
            s = "-";
        } else {
            s = "*";
        }
        return checkEdgeColor(s, edge);
    }

    private String checkEdgeColor(String s, Edge edge) {
        if (edge == markedEdge) {
            s = ANSI_RED + s + ANSI_RESET;
        } else if (edge.hasRoad() && edge.getRoad().getPlayer() == activePlayer) {
            s = ANSI_GREEN + s + ANSI_RESET;
        }
        return s;
    }

    private String printField(int x, int y) {
        Field field = map.getField(x, y);
        if (field == null) {
            return " ";
        }
        if (field.getResourceType() == null) {
            return "F";
        }
        switch (field.getResourceType()) {
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
