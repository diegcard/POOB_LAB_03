package domain;

import java.awt.Color;
import java.util.Arrays;

public class Sand extends Agent implements Thing {
    private Color color;
    private Garden garden;
    private int row, column;

    /**
     * Create a new sand (<b>row,column</b>) in the garden <b>garden</b>.
     *
     * @param garden The garden
     * @param row    The row
     * @param column The column
     */
    public Sand(Garden garden, int row, int column) {
        this.garden = garden;
        this.row = row;
        this.column = column;
        this.color = Color.GRAY;
        garden.setThing(row, column, this);
    }

    public Thing[] getNeighbors() {
    Thing[] neighbors = new Thing[8];
    int counter = 0;
    int gardenLength = garden.getLength();
    for (int i = row - 1; i <= row + 1; i++) {
        for (int j = column - 1; j <= column + 1; j++) {
            if (i == row && j == column) {
                continue;
            }
            if (i >= 0 && i < gardenLength && j >= 0 && j < gardenLength) {
                if (garden.getThing(i, j) instanceof Cane) {
                    Thing cane = garden.getThing(i, j);
                    neighbors[counter] = cane;
                }
            } else {
                neighbors[counter] = null;
            }
            counter++;
        }
    }
    return neighbors;
}

    @Override
    public void act() {
        boolean hasCaneNeighbor = Arrays.stream(getNeighbors()).anyMatch(neighbor -> neighbor instanceof Cane);
        if (color.getRed() < 245 && hasCaneNeighbor) {
            color = new Color(Math.min(color.getRed() + 5, 255), Math.min(color.getGreen() + 5, 255), Math.min(color.getBlue() + 5, 255));
        }
    }

    @Override
    public int getColumn() {
        return column;
    }

    public int getRow() {
        return row;
    }

    public Color getColor() {
        return color;
    }

    public void move() {

    }
}