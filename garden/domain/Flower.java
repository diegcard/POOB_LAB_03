package domain;

import java.awt.Color;

/**
 * Information about a Flower<br>
 * <b>(garden,row,column,time,state,nextState, color)</b><br>
 * <br>
 */
public class Flower extends Agent implements Thing {
    protected char nextState;
    protected Color color;
    protected Garden garden;
    protected int row, column;
    protected String name;


    /**
     * Create a new flower (<b>row,column</b>) in the garden <b>garden</b>.
     * Every new flower is going to be alive in the following state.
     *
     * @param garden The garden
     * @param row    The row
     * @param column The column
     */
    public Flower(Garden garden, int row, int column, String name) {
        this.garden = garden;
        this.row = row;
        this.column = column;
        nextState = Agent.ALIVE;
        garden.setThing(row, column, (Thing) this);
        color = Color.red;
        this.name = name;
    }

    /**
     * Returns the shape
     *
     * @return FLOWER
     */
    public final int shape() {
        return Thing.FLOWER;
    }

    /**
     * Returns the row
     *
     * @return row
     */
    public final int getRow() {
        return row;
    }

    /**
     * Returns tha column
     *
     * @return column
     */
    public final int getColumn() {
        return column;
    }


    /**
     * Returns the color
     *
     * @return color
     */
    public final Color getColor() {
        return color;
    }


    public void move() {
    }

    /**
     * The flower turns one life span old
     */
    public void act() {
        turn();
        switch (getTime()) {
            case 1:
                color = Color.orange;
                break;
            case 3:
                state = Agent.DEAD;
                break;
            case 5:
                state = Agent.ALIVE;
                color = Color.red;
                break;
            default:
                break;
        }
    }

    public Garden getGarden() {
        return garden;
    }

    public void moveTo(int newRow, int newColumn) {
        //Mueve la flor a la nueva posición
        garden.setThing(row, column, this);
        row = newRow;
        column = newColumn;

    }

    protected int calculateDistance(int x1, int y1, int x2, int y2){
        return (int) Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
    }
}
