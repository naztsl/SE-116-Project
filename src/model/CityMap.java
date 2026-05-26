package model;

import io.SE116ConfigurationException;

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
            throw new SE116ConfigurationException("Coordinates out of bounds: Row " + row + ", Col " + col);
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