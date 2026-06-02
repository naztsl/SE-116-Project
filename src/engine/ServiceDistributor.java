package engine;

import model.*;

public class ServiceDistributor {

    public void distributeServices(Cell[][] grid) {

        for(int i = 0; i < grid.length; i++) {

            for(int j = 0; j < grid[i].length; j++) {

                Cell current = grid[i][j];

                if(current instanceof ServiceBuilding) {

                    ServiceBuilding service =
                            (ServiceBuilding) current;

                    applyServiceToZones(service, grid);
                }
            }
        }
    }

    private void applyServiceToZones(ServiceBuilding service,
                                     Cell[][] grid) {

        for(int i = 0; i < grid.length; i++) {

            for(int j = 0; j < grid[i].length; j++) {

                Cell current = grid[i][j];

                if(current instanceof Zone) {

                    Zone zone = (Zone) current;

                    service.applyService(zone);
                }
            }
        }
    }
}