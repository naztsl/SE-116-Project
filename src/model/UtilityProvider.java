package model;

public abstract class UtilityProvider extends Cell {
    protected int capacity;

    public UtilityProvider(int row, int col, char symbol) {
        super(row, col, symbol);
    }
}
