package domain;

import java.awt.Color;

public class Sand extends Agent implements Thing {
    private Color color;
    private Garden garden;
    private int row, column;

    public Sand(Garden garden, int row, int column) {
        this.garden = garden;
        this.row = row;
        this.column = column;
        this.color = Color.GRAY;
        garden.setThing(row, column, this);
    }

    @Override
    public void act() {
        if (color.getRed() < 245) {
            int red = Math.max(color.getRed() + 10, 0);
            int green = Math.max(color.getGreen() + 10, 0);
            int blue = Math.max(color.getBlue() + 10, 0);
            color = new Color(red, green, blue);
        }

    }

    @Override
    public int getColumn() {
        return column;
    }

    @Override
    public int getRow() {
        return row;
    }

    @Override
    public Color getColor() {
        return color;
    }

    @Override
    public void move() {

    }
}