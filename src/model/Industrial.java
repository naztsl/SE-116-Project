package model;

public class Industrial extends Zone {
    public Industrial(int row, int col) {
        super(row, col, 'I');
    }

    @Override
    public void updateLevel() {
        int targetLevel;

        if (!hasPopulation() || !hasElectricityAndWater()) {
            targetLevel = 0;
        } else if (!security) {
            targetLevel = 1;
        } else if (population <= getMinimumUtility()) {
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
        int minimumUtility = Math.min(electricity, water);

        if (level == 3) {
            output = 2 * minimumUtility + population;
        } else {
            output = level * minimumUtility;
        }

        goods = output;
        return output;
    }
}
