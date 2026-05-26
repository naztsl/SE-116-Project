package io;

import model.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MapLoader {

    public static CityMap loadMap(String filePath) {
        List<String> lines = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    lines.add(line.trim());
                }
            }
        } catch (IOException e) {
            throw new MapFormatException("Failed to read map file: " + filePath, e);
        }

        if (lines.isEmpty()) {
            throw new MapFormatException("Map file is empty.");
        }

        int height = lines.size();
        int width = lines.get(0).length();

        CityMap cityMap = new CityMap(height, width);

        for (int row = 0; row < height; row++) {
            String currentLine = lines.get(row);

            if (currentLine.length() != width) {
                throw new MapFormatException("Inconsistent row length at row " + (row + 1) + ". Expected " + width + " columns.");
            }

            for (int col = 0; col < width; col++) {
                char symbol = currentLine.charAt(col);
                try {
                    Cell cell = createCellFromSymbol(symbol, row, col);
                    cityMap.setCell(row, col, cell);
                } catch (MapFormatException e) {
                    throw new MapFormatException("Parse error at row " + (row + 1) + ", column " + (col + 1) + ": " + e.getMessage());
                }
            }
        }
        return cityMap;
    }

    /
    private static Cell createCellFromSymbol(char symbol, int row, int col) {
        switch (Character.toUpperCase(symbol)) {
            case 'H': return new Housing(row, col);
            case 'I': return new Industrial(row, col);
            case 'C': return new Commercial(row, col);
            case 'P': return new PowerPlant(row, col);
            case 'W': return new WaterPump(row, col);
            case 'T': return new InternetHub(row, col);
            case 'F': return new PoliceStation(row, col);
            case 'D': return new Hospital(row, col);
            case 'S': return new School(row, col);
            case 'R': return new Road(row, col);
            case 'E':
            case ' ': return new EmptyCell(row, col);
            default:
                throw new MapFormatException("Unknown cell symbol in map: '" + symbol + "'");
        }
    }
}