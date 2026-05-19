package model;
public class WaterPump extends UtilityProvider {

    public WaterPump(int row, int col, char symbol) {
        super(row, col, 'W', 100, "water");
    }

    @Override
    public int produceUtility() {
        return capacity;
    }
}
