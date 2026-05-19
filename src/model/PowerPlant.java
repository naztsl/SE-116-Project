package model;

public class PowerPlant extends UtilityProvider {

    public PowerPlant(int row, int col, char symbol) {
        super(row, col, 'P', 100, "electricity");
    }

    @Override
    public int produceUtility() {
        return capacity;
    }
}
