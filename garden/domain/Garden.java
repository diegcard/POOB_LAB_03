package domain;

import java.util.*;


public class Garden {
    static public int LENGTH = 40;
    private final Thing[][] garden;

    /**
     * Create a new garden
     */
    public Garden() {
        garden = new Thing[LENGTH][LENGTH];
        for (int r = 0; r < LENGTH; r++) {
            for (int c = 0; c < LENGTH; c++) {
                garden[r][c] = null;
            }
        }
        setThing(0, 0, new Water());
        for (int i = 1; i < 5; i++) {
            for (int j = 1; j < 5; j++) {
                setThing(LENGTH - i, LENGTH - j, new Water());
            }
        }
        someThings();
    }

    /**
     * Get the length
     *
     * @return LENGTH
     */
    public int getLength() {
        return LENGTH;
    }

    /**
     * Get the thing in the garden.
     *
     * @param r Row position.
     * @param c Column Position.
     * @return The Thing at the specified position, or null if nothing is present.
     */
    public Thing getThing(int r, int c) {
        return garden[r][c];
    }

    /**
     * Set the thing in the garden.
     *
     * @param r The row in the Garden.
     * @param c The column in the Garden.
     * @param e The Thing to set at the specified position.
     */
    public void setThing(int r, int c, Thing e) {
        garden[r][c] = e;
    }

    /**
     * Performs a time step (tic-tac) for the entities within the garden grid.
     */
    public void someThings() {
        new Flower(this, 10, 10, "rose");
        new Flower(this, 15, 15, "violet");

        new Carnivorous(this, 20, 20, "venus");
        new Carnivorous(this, 10, 25, "sundeuos");

        new Sand(this, 0, 39);
        new Sand(this, 0, 38);

        //New plant

        new Cane(this, 20, 25, "Diego");
    }

    public void ticTac() {
        for (int r = 0; r < LENGTH; r++) {
            for (int c = 0; c < LENGTH; c++) {
                Thing thing = garden[r][c];
                if (thing != null) {
                    thing.act();
                }
            }
        }
    }

}
