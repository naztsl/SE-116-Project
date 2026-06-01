package engine;

import model.Commercial;
import model.Housing;
import model.Industrial;
import model.Zone;
import model.CityMap;

import java.util.List;

public class ResourceManager {
    private int totalPopulation;
    private int totalGoods;
    private int totalLifestyle;

    public void distributePreviousProduction(CityMap cityMap) {
        List<Zone> zones = cityMap.getZones();

        int housingCount = 0;
        int industrialCount = 0;
        int commercialCount = 0;

        for (Zone zone : zones) {
            if (zone instanceof Housing) {
                housingCount++;
            } else if (zone instanceof Industrial) {
                industrialCount++;
            } else if (zone instanceof Commercial) {
                commercialCount++;
            }
        }

        for (Zone zone : zones) {
            if (zone instanceof Housing) {
                if (housingCount > 0) {
                    zone.setLifestyle(divideResource(totalLifestyle, housingCount));
                }
            } else if (zone instanceof Industrial) {
                if (industrialCount > 0) {
                    zone.setPopulation(divideResource(totalPopulation, industrialCount));
                }
            } else if (zone instanceof Commercial) {
                if (commercialCount > 0) {
                    zone.setPopulation(divideResource(totalPopulation, commercialCount));
                    zone.setGoods(divideResource(totalGoods, commercialCount));
                }
            }
        }
    }

    public void collectNewProduction(CityMap cityMap) {
        totalPopulation = 0;
        totalGoods = 0;
        totalLifestyle = 0;

        for (Zone zone : cityMap.getZones()) {
            int output = zone.calculateOutput();

            if (zone instanceof Housing) {
                totalPopulation += output;
                System.out.println(getZoneTypeName(zone) + " at (" + zone.getRow() + "," + zone.getCol() + ") generated " + output + " population");
            } else if (zone instanceof Industrial) {
                totalGoods += output;
                System.out.println(getZoneTypeName(zone) + " at (" + zone.getRow() + "," + zone.getCol() + ") generated " + output + " goods");
            } else if (zone instanceof Commercial) {
                totalLifestyle += output;
                System.out.println(getZoneTypeName(zone) + " at (" + zone.getRow() + "," + zone.getCol() + ") generated " + output + " lifestyle");
            }
        }
    }

    public int getTotalPopulation() {
        return totalPopulation;
    }

    public int getTotalGoods() {
        return totalGoods;
    }

    public int getTotalLifestyle() {
        return totalLifestyle;
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

    private int divideResource(int totalResource, int zoneCount) {
        if (zoneCount <= 0) {
            return 0;
        }
        return totalResource / zoneCount;
    }
}