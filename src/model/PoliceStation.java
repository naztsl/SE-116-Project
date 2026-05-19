package model;

public class PoliceStation extends ServiceBuilding {

    public PoliceStation(int row, int col, char symbol) {
        super(row, col, 'F', 5, "security");
    }

    @Override
    public void applyService(Zone zone) {
        if(isInRange(zone)) {
            zone.setSecurity(true);
        }
    }
}
