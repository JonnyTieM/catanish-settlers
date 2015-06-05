package de.htwg.se.catanishsettlers.view;

/**
 * Created by Stephan on 05.06.2015.
 */
public interface IDisplay {
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
            return "*";
        }
    }

    private String printField(int x, int y) {
        Field field = map.getField(x, y);
        if (field == null) {
            return " ";
        }
        if (field.getType() == null) {
            return "F";
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
