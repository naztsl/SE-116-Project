package model;

public class Industrial extends Zone {
    public Industrial(int row, int col) {
        super(row, col, 'I');
    }

    @Override
    public void updateLevel() {
        if (!hasPopulation() || !hasElectricityAndWater()) {
            level = 0;
        } else if (!security) {
            level = 1;
        } else if (population <= getMinimumUtility()) {
            level = 2;
        } else {
            level = 3;
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
