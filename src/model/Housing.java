package model;

public class Housing extends Zone{
    public Housing(int row, int col) {
        super(row, col, 'H');
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

        return output;
    }


}
