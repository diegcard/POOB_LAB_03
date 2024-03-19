package domain;

import java.awt.Color;

/**
 * Write a description of class Carnivoras here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Carnivorous extends Flower{
    private static final Color CARNIVOROUS_COLOR = Color.BLUE;

    /**
     * Constructor for objects of class Carnivorous
     *
     * @param garden The garden
     * @param row    The row
     * @param column The column
     */
    public Carnivorous(Garden garden, int row, int column, String name) {
        super(garden, row, column, name);
        this.color = CARNIVOROUS_COLOR;
    }

    /**
     * Act
     */
    @Override
    public void act(){
        moveToNearestFlower();

    }

    private int[] findNearestFlower(){
        int minDistance = Integer.MAX_VALUE;
        int[] nearestFlower = null;
        for (int r = 0; r < garden.getLength(); r++) {
            for (int c = 0; c < garden.getLength(); c++) {
                Thing thing = garden.getThing(r, c);
                if (thing instanceof Flower) {
                    int distance = calculateDistance(row, column, r, c);
                    if (distance < minDistance) {
                        minDistance = distance;
                        nearestFlower = new int[]{r, c};
                    }
                }
            }
        }
        return nearestFlower;
    }

    /**
     * Move the carnivorous to the nearest flower
     */
    public void moveToNearestFlower(){
        int[] nearestFlower = findNearestFlower();
        if (nearestFlower != null) {
            int r = nearestFlower[0];
            int c = nearestFlower[1];
            garden.setThing(this.row, this.column, null);
            this.row = r;
            this.column = c;
            super.moveTo(r, c);
        }
    }
}
