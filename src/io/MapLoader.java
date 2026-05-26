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
            throw new SE116ConfigurationException("Failed to read map file: " + filePath, e);
        }

        if (lines.isEmpty()) {
            throw new SE116ConfigurationException("Map file is empty.");
        }

        int height = lines.size();
        int width = lines.get(0).length();

        CityMap cityMap = new CityMap(height, width);

        for (int row = 0; row < height; row++) {
            String currentLine = lines.get(row);

            if (currentLine.length() != width) {
                throw new SE116ConfigurationException("Inconsistent row length at row " + (row + 1) + ". Expected " + width + " columns.");
            }

            for (int col = 0; col < width; col++) {
                char symbol = currentLine.charAt(col);
                try {
                    Cell cell = createCellFromSymbol(symbol);
                    cityMap.setCell(row, col, cell);
                } catch (SE116ConfigurationException e) {
                    throw new SE116ConfigurationException("Parse error at row " + (row + 1) + ", column " + (col + 1) + ": " + e.getMessage());
                }
            }
        }
        return cityMap;
    }

    private static Cell createCellFromSymbol(char symbol) {
        switch (Character.toUpperCase(symbol)) {
            case 'H': return new Housing();
            case 'I': return new Industrial();
            case 'C': return new Commercial();
            case 'P': return new PowerPlant();
            case 'W': return new WaterPump();
            case 'T': return new InternetHub();
            case 'F': return new PoliceStation();
            case 'D': return new Hospital();
            case 'S': return new School();
            case 'R': return new Road();
            case 'E':
            case ' ': return new EmptyCell();
            default:
                throw new SE116ConfigurationException("Unknown cell symbol in map: '" + symbol + "'");
        }
    }
}