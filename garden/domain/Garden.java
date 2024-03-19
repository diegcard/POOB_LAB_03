package domain;

import java.util.*;


public class Garden {
    static public int LENGTH = 40;
    private Thing[][] garden;

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
     * Get the thing in the garden
     *
     * @param r
     * @param c
     * @return
     */
    public Thing getThing(int r, int c) {
        return garden[r][c];
    }

    /**
     * Set the thing in the garden
     *
     * @param r
     * @param c
     * @param e
     */
    public void setThing(int r, int c, Thing e) {
        garden[r][c] = e;
    }

    /**
     * Get the things in the garden
     */
    public void someThings() {
        Flower rose = new Flower(this, 10, 10, "rose");
        Flower violet = new Flower(this, 15, 15, "violet");

        Carnivorous venus = new Carnivorous(this, 20, 20, "venus");
        Carnivorous sundeuos = new Carnivorous(this, 25, 25, "sundeuos");

        Sand sand = new Sand(this, 0, 39);
        Sand sand2 = new Sand(this, 0, 38);
    }

    /**
     * Get the things in the garden
     */
    public void ticTac() {
        for (int r = 0; r < LENGTH; r++) {
            for (int c = 0; c < LENGTH; c++) {
                if (garden[r][c] != null) {
                    garden[r][c].act();
                }
            }
        }
    }

}
