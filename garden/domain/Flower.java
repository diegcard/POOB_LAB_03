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
    protected int ticTacCount1;

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
        color = Color.red;
        this.name = name;
        garden.setThing(row, column,(Thing) this);
    }

    /**Create a new Flower (<b>row,column</b>) in the garden <b>garden</b>.
     * Every new Ant is going to be alive in the following state.
     * @param garden 
     * @param row 
     * @param column 
     */
    public Flower(Garden garden,int row, int column){
        this.garden = garden;
        this.row=row;
        this.column=column;
        nextState=Agent.ALIVE;
        garden.setThing(row,column,(Thing)this);  
        color=Color.red;
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


    /**
     * The flower turns one life span old
     */
    public void act() {
        switch (ticTacCount1) {
            case 1:
                color = Color.orange;
                break;
            case 3:
                color = Color.BLACK;
                state = Agent.DEAD;
                break;
            case 5:
                state = Agent.ALIVE;
                color = Color.red;
                ticTacCount1 = 0;
                break;
            default:
                break;
        }
        ticTacCount1++;
    }

    public Garden getGarden() {
        return garden;
    }

    public void moveTo(int newRow, int newColumn) {
        garden.setThing(row, column, null);
        if (garden.getThing(newRow,newColumn) instanceof Water){
            this.state = Agent.DEAD;
        }else{
            garden.setThing(newRow, newColumn, this);
            row = newRow;
            column = newColumn;  
        }
    }

    /**
     * Calculate the position between two points.
     *
     * @param x1 The x-coordinate of the first point.
     * @param y1 The y-coordinate of the first point.
     * @param x2 The x-coordinate of the second point.
     * @param y2 The y-coordinate of the second point.
     * @return The calculated distance.
     */
    protected static int calculateDistance(int x1, int y1, int x2, int y2) {
        return (int) Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
    }

    /**
     * Check  if a position (row and column) within the garden grid is valid.
     *
     * @param newRow    The row to check
     * @param newColumn The Column to check
     * @return true if the position is valid, otherwise false
     */
    protected boolean isValidPosition(int newRow, int newColumn) {
        return newRow >= 0 && newRow < garden.getLength() && newColumn >= 0 && newColumn < garden.getLength();
    }

    private int randomMove(){
        return (int) (Math.random() * 3) - 1;
    }
    
    /**
     * Sets the state of the Flower to DEAD.
     */
    public void kill(){
        state = Agent.DEAD;
    }
    
    public void move() {
        int newRow = row + randomMove();
        int newColumn = column + randomMove();

        newRow = Math.max(0, Math.min(newRow, garden.getLength() - 1));
        newColumn = Math.max(0, Math.min(newColumn, garden.getLength() - 1));
        
        if (!(garden.getThing(newRow,newColumn) instanceof Water || garden.getThing(newRow,newColumn) == null)){
            newRow = row; 
            newColumn = column;
        } 
        garden.setThing(row, column, null);
        if (garden.getThing(newRow,newColumn) instanceof Water){
            kill();
        }
        else{
            garden.setThing(newRow, newColumn, this);
            setPosition(newRow,newColumn);
        }
    }
    
    /**
     * Sets the position of the Flower to the specified row and column.
     *
     * @param newRow The new row for the Ant.
     * @param newColumn The new column for the Ant.
     */
    public void setPosition(int newRow, int newColumn){
        row = newRow;
        column = newColumn;
    }
}
