package model;

import io.MapFormatException;

import java.util.ArrayList;
import java.util.List;

public class CityMap {
    private final Cell[][] grid;
    private final int width;
    private final int height;

    public CityMap(int height, int width) {
        this.height = height;
        this.width = width;
        this.grid = new Cell[height][width];
    }

    public void setCell(int row, int col, Cell cell) {
        if (row < 0 || row >= height || col < 0 || col >= width) {
            throw new MapFormatException("Coordinates out of bounds: Row " + row + ", Col " + col);
        }
        grid[row][col] = cell;
    }

    public Cell getCell(int row, int col) {
        if (row < 0 || row >= height || col < 0 || col >= width) {
            return null;
        }
        return grid[row][col];
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public List<Zone> getZones() {
        List<Zone> zones = new ArrayList<>();

        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                Cell cell = grid[row][col];
                if (cell instanceof Zone) {
                    zones.add((Zone) cell);
                }
            }
        }

        return zones;
    }

    public List<UtilityProvider> getUtilityProviders() {
        List<UtilityProvider> utilityProviders = new ArrayList<>();

        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                Cell cell = grid[row][col];
                if (cell instanceof UtilityProvider) {
                    utilityProviders.add((UtilityProvider) cell);
                }
            }
        }

        return utilityProviders;
    }

    public List<ServiceBuilding> getServiceBuildings() {
        List<ServiceBuilding> serviceBuildings = new ArrayList<>();

        for (int row = 0; row < height; row++) {
            for (int col = 0; col < width; col++) {
                Cell cell = grid[row][col];
                if (cell instanceof ServiceBuilding) {
                    serviceBuildings.add((ServiceBuilding) cell);
                }
            }
        }

        return serviceBuildings;
    }

    public void printMap() {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                if (grid[i][j] != null) {

                    System.out.print(grid[i][j].toString() + " ");
                } else {
                    System.out.print("E ");
                }
            }
            System.out.println();
        }
    }
}