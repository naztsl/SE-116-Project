package model;

public class Commercial extends Zone {
    public Commercial(int row, int col) {
        super(row, col, 'C');
    }

    @Override
    public void updateLevel() {
        int targetLevel;

        if (!hasPopulation() || !hasGoods() || !hasAllUtilities()) {
            targetLevel = 0;
        } else if (!security) {
            targetLevel = 1;
        } else if (population <= getMinimumUtility() || goods <= getMinimumUtility()) {
            targetLevel = 2;
        } else {
            targetLevel = 3;
        }

        if (targetLevel > level) {
            level++;
        } else if (targetLevel < level) {
            level--;
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
