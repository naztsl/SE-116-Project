package model;

public class Housing extends Zone{
    public Housing(int row, int col, char symbol) {
        super(row, col, 'H');
    }

    @Override
    public void updateLevel(){

    }

    @Override
    public int calculateOutput(){
        return 0;
    }
}
