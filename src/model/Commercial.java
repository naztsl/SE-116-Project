package model;

public class Commercial extends Zone{
    public Commercial(int row, int col) {
        super(row, col, 'C');
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
        lifestyle = output;


        return output;
    }
}
