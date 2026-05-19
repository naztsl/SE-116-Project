package model;

public abstract class UtilityProvider extends Cell {
    protected int capacity;
    protected String utilityType;

    public UtilityProvider(int row, int col, char symbol, int capacity, String utilityType) {
        super(row, col, symbol);
        this.capacity = capacity;
        this.utilityType = utilityType;
    }

    public abstract int produceUtility();

    public int getCapacity ( ) {
        return capacity;
    }

    public String getUtilityType() {
        return utilityType;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public void setUtilityType(String utilityType) {
        this.utilityType = utilityType;
    }
}
