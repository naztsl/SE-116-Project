package model;

public class Industrial extends Zone {
    public Industrial(int row, int col, char symbol) {
        super(row, col, 'I');
    }

    @Override
    public void updateLevel(){

    }

    @Override
    public int calculateOutput(){
        return 0;
    }
}
