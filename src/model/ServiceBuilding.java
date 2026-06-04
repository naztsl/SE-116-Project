package model;

public abstract class ServiceBuilding extends Cell {

    protected int radius;
    protected String serviceType;

    public ServiceBuilding(int row, int col, char symbol, int radius, String serviceType) {
        super(row, col, symbol);
        this.radius = radius;
        this.serviceType = serviceType;
    }

    public int getRadius() {
        return radius;
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }
    public boolean isInRange(Zone zone) {
        int rowDistance = this.row - zone.getRow();
        int colDistance = this.col - zone.getCol();
        double distance = Math.sqrt(rowDistance * rowDistance + colDistance * colDistance);

        return distance <= radius;
    }

    public abstract void applyService(Zone zone);
}
