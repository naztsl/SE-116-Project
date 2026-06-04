package model;

public class Housing extends Zone {
    public Housing(int row, int col) {
        super(row, col, 'H');
    }

    @Override
    public void updateLevel() {
        int targetLevel;

        if (!hasAllUtilities()) {
            targetLevel = 0;
        } else if (!hasAllServices()) {
            targetLevel = 1;
        } else if (!hasLifestyle()) {
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
            output = 2 * getMinimumUtility() + lifestyle;
        } else {
            output = level * getMinimumUtility();
        }
        population = output;
        return output;
    }
}
