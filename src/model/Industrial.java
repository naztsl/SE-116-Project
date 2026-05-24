package model;

public class Industrial extends Zone {
    public Industrial(int row, int col) {
        super(row, col, 'I');
    }

    @Override
    public void updateLevel() {

        if (!hasAllUtilities()) {
            level = 0;
            return;
        }

        if (level < 3) {
            level++;
        }
    }


    @Override
    public int calculateOutput() {

        output = level * getMinimumUtility();
        goods = output;

        return output;
    }
}
