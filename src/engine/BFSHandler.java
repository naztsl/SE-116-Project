package engine;

import model.Cell;
import model.Road;
import model.UtilityProvider;
import model.Zone;
import model.CityMap;

import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;

public class BFSHandler {

    private static final int[][] DIRECTIONS = {
            {-1, 0},
            {1, 0},
            {0, -1},
            {0, 1}
    };

    public void distributeUtility(CityMap cityMap, UtilityProvider provider) {
        int remainingCapacity = provider.produceUtility();
        String utilityType = provider.getUtilityType();

        Queue<Cell> queue = new ArrayDeque<>();
        Set<String> visited = new HashSet<>();

        queue.add(provider);
        visited.add(createKey(provider.getRow(), provider.getCol()));

        while (!queue.isEmpty() && remainingCapacity > 0) {
            Cell current = queue.poll();

            for (int[] direction : DIRECTIONS) {
                int nextRow = current.getRow() + direction[0];
                int nextCol = current.getCol() + direction[1];
                String nextKey = createKey(nextRow, nextCol);

                if (visited.contains(nextKey)) {
                    continue;
                }

                Cell nextCell = cityMap.getCell(nextRow, nextCol);
                if (nextCell == null) {
                    continue;
                }

                if (nextCell instanceof Road) {
                    queue.add(nextCell);
                    visited.add(nextKey);
                } else if (nextCell instanceof Zone) {
                    Zone zone = (Zone) nextCell;
                    int demand = getUtilityDemand(zone, utilityType);
                    int delivered = Math.min(demand, remainingCapacity);

                    if (delivered > 0) {
                        addUtility(zone, utilityType, delivered);
                        remainingCapacity -= delivered;
                        System.out.println(getZoneTypeName(zone) + " at (" + zone.getRow() + "," + zone.getCol() + ") received " + delivered + " " + utilityType);
                    }

                    visited.add(nextKey);
                }
            }
        }
    }

    private int getUtilityDemand(Zone zone, String utilityType) {
        int demand = Math.max(1, zone.getOutput());

        if (utilityType.equals("electricity")) {
            return Math.max(0, demand - zone.getElectricity());
        }
        if (utilityType.equals("water")) {
            return Math.max(0, demand - zone.getWater());
        }
        if (utilityType.equals("internet")) {
            return Math.max(0, demand - zone.getInternet());
        }

        return 0;
    }

    private void addUtility(Zone zone, String utilityType, int amount) {
        if (utilityType.equals("electricity")) {
            zone.setElectricity(zone.getElectricity() + amount);
        } else if (utilityType.equals("water")) {
            zone.setWater(zone.getWater() + amount);
        } else if (utilityType.equals("internet")) {
            zone.setInternet(zone.getInternet() + amount);
        }
    }

    private String getZoneTypeName(Zone zone) {
        if (zone.getSymbol() == 'H') {
            return "House";
        }
        if (zone.getSymbol() == 'C') {
            return "Commercial";
        }
        if (zone.getSymbol() == 'I') {
            return "Industrial";
        }
        return "Zone";
    }

    private String createKey(int row, int col) {
        return row + "," + col;
    }
}
