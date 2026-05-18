package model;

public class Commercial extends Zone{
    public Commercial(int row, int col, char symbol) {
        super(row, col, 'C');
    }

    @Override
    public void updateLevel(){

    }

    @Override
    public int calculateOutput(){
        return 0;
    }
}
