package model;

public class School extends ServiceBuilding {

    public School(int row, int col, char symbol) {
        super(row, col, 'S',4, "education");
    }

    @Override
    public void applyService(Zone zone) {
        if(isInRange(zone)) {
            zone.setEducation(true);
        }
    }
}
