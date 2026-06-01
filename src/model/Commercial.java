package model;

public class Commercial extends Zone {
    public Commercial(int row, int col) {
        super(row, col, 'C');
    }

    @Override
    public void updateLevel() {
        if (!hasPopulation() || !hasGoods() || !hasAllUtilities()) {
            level = 0;
        } else if (!security) {
            level = 1;
        } else if (population <= getMinimumUtility() || goods <= getMinimumUtility()) {
            level = 2;
        } else {
            level = 3;
        }
    }

    @Override
    public int calculateOutput() {
        if (level == 3) {
            output = 2 * getMinimumUtility() + getMinimumPopulationGoods();
        } else {
            output = level * getMinimumUtility();
        }

        lifestyle = output;
        return output;
    }
}
