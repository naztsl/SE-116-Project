package model;

public class Housing extends Zone {
    public Housing(int row, int col) {
        super(row, col, 'H');
    }

    @Override
    public void updateLevel() {
        if (!hasAllUtilities()) {
            level = 0;
        } else if (!hasAllServices()) {
            level = 1;
        } else if (!hasLifestyle()) {
            level = 2;
        } else {
            level = 3;
        }
    }

    @Override
    public int calculateOutput() {
        if (level == 3) {
            output = 2 * getMinimumUtility() + lifestyle;
        } else {
            output = level * getMinimumUtility();
        }
        population = output;
        return output;
    }
}
