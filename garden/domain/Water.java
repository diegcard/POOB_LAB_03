package domain;

public final class Water implements Thing {

    private Garden garden;
    private int row, column;


    public void act() {}

    public int getColumn() {
        return column;
    }

    public int getRow() {
        return row;
    }
}
