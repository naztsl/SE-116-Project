package model;

public class Hospital extends ServiceBuilding {

    public Hospital(int row, int col, char symbol) {
        super(row, col, 'D', 3, "health");
    }

    @Override
    public void applyService(Zone zone) {
        if(isInRange(zone)) {
            zone.setHealth(true);
        }
    }
}
