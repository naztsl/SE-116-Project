package engine;

import model.*;

public class ServiceDistributor {

    public void distributeServices(Cell[][] grid) {

        for (int i = 0; i < grid.length; i++) {

            for (int j = 0; j < grid[i].length; j++) {

                Cell current = grid[i][j];

                if (current instanceof ServiceBuilding) {

                    ServiceBuilding service =
                            (ServiceBuilding) current;

                    applyServiceToZones(service, grid);
                }
            }
        }
    }

    private void applyServiceToZones(ServiceBuilding service,
                                     Cell[][] grid) {

        for (int i = 0; i < grid.length; i++) {

            for (int j = 0; j < grid[i].length; j++) {

                Cell current = grid[i][j];

                if (current instanceof Zone) {

                    Zone zone = (Zone) current;

                    if (isWithinRadius(service, zone) && canReceiveServiceByRadius(service, zone)) {
                        service.applyService(zone);
                    }
                }
            }
        }
    }
    private boolean isWithinRadius(ServiceBuilding service, Zone zone) {
        int rowDistance = service.getRow() - zone.getRow();
        int colDistance = service.getCol() - zone.getCol();
        double distance = Math.sqrt(rowDistance * rowDistance + colDistance * colDistance);

        return distance <= service.getRadius();
    }

    private boolean canReceiveServiceByRadius(ServiceBuilding service, Zone zone) {
        int radius = service.getRadius();

        if (radius == 3 || radius == 4) {
            return isHouseZone(zone);
        }

        return true;
    }

    private boolean isHouseZone(Zone zone) {
        String zoneName = zone.getClass().getSimpleName().toLowerCase();
        return zoneName.contains("house") || zoneName.contains("housing") || zoneName.contains("residential");
    }
}