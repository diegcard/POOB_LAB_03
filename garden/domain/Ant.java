package domain;


import java.awt.*;

/**
 * Write a description of class Ant here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Ant extends Agent implements Thing{
    protected char nextState;
    protected Color color;
    protected Garden garden;
    protected int row,column;
    protected int ticTacCount;
    protected String name;

    /**
     * Constructs an Ant object with the specified parameters.
     *
     * @param colony The colony to which the Ant belongs.
     * @param row The row in the colony grid.
     * @param column The column in the colony grid.
     * @param name The name of the Ant.
     */
    public Ant(Garden garden, int row, int column, String name) {
        this.garden = garden;
        this.row = row;
        this.column = column;
        this.name = name; 
        nextState = Agent.ALIVE;
        garden.setThing(row, column, (Thing) this);
        color = Color.black;
        ticTacCount = 0;  
    }

    /**
     * Returns the shape
     *
     * @return ANT
     */
    public final int shape() {
        return Thing.ROUND;
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
     * Sets the position of the Ant to the specified row and column.
     *
     * @param newRow The new row for the Ant.
     * @param newColumn The new column for the Ant.
     */
    public void setPosition(int newRow, int newColumn){
        row = newRow;
        column = newColumn;
    }
    
    /**
     * Execute the action
     */
    @Override
    public void act() {
        turn();
        moveToNearestCarnivorous();
    }
    
    
    
    private int calculateDistance(int x1, int y1, int x2, int y2) {
        return (int) Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
    }
    
    private int[] findNearestCarnivorous() {
        int minDistance = Integer.MAX_VALUE;
        int[] nearestStand = null;
        for (int r = 0; r < garden.getLength(); r++) {
            for (int c = 0; c < garden.getLength(); c++) {
                Thing thing = garden.getThing(r, c);
                if (thing instanceof Carnivorous) {
                    int distance = calculateDistance(row, column, r, c);
                    if (distance < minDistance) {
                        minDistance = distance;
                        nearestStand = new int[]{r, c};
                    }
                }
            }
        }
        return nearestStand;
    }
    
    private boolean isValidPosition(int newRow, int newColumn) {
        return newRow >= 0 && newRow < garden.getLength() && newColumn >= 0 && newColumn < garden.getLength();
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
    
    private void moveToNearestCarnivorous() {
        int[] nearestFlower = findNearestCarnivorous();
        if (nearestFlower != null) {
            int newRow = row;
            int newColumn = column;
            double minDistance = Integer.MAX_VALUE;
            for (int r = row - 1; r < row + 2; r++) {
                for (int c = column - 1; c < column + 2; c++) {
                    int distance = calculateDistance(r, c, nearestFlower[0], nearestFlower[1]);
                    boolean flag = isValidPosition(r, c) && ((garden.getThing(r, c) instanceof Carnivorous) || garden.getThing(r, c) == null);
                    if (minDistance > distance && flag) {
                        minDistance = distance;
                        newRow = r;
                        newColumn = c;
                    }
                }
            }
            moveTo(newRow, newColumn);
        }
    }
    
    public void move(){
        
    }
}
