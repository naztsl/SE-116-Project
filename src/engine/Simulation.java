package engine;

import model.ServiceBuilding;
import model.UtilityProvider;
import model.Zone;
import model.CityMap;

public class Simulation {
    private final CityMap cityMap;
    private final int ticks;
    private final BFSHandler bfsHandler;
    private final ResourceManager resourceManager;


    public Simulation(CityMap cityMap, int ticks) {
        this.cityMap = cityMap;
        this.ticks = ticks;
        this.bfsHandler = new BFSHandler();
        this.resourceManager = new ResourceManager();
    }

    public void run() {
        for (int tick = 1; tick <= ticks; tick++) {
            System.out.println("Tick " + tick);
            runOneTick();
        }
    }

    private void runOneTick() {
        resetZonesForTick();
        provideServices();
        distributeUtilities();
        resourceManager.distributePreviousProduction(cityMap);
        updateZones();
        resourceManager.collectNewProduction(cityMap);
    }

    private void resetZonesForTick() {
        for (Zone zone : cityMap.getZones()) {
            zone.resetTickData();
        }
    }

     private void provideServices() {
         for (ServiceBuilding serviceBuilding : cityMap.getServiceBuildings()) {
             for (Zone zone : cityMap.getZones()) {
                boolean hadService = hasService(zone, serviceBuilding.getServiceType());
                serviceBuilding.applyService(zone);
                boolean hasServiceNow = hasService(zone, serviceBuilding.getServiceType());

                if (!hadService && hasServiceNow) {
                    System.out.println(getZoneTypeName(zone) + " at (" + zone.getRow() + "," + zone.getCol() + ") received " + serviceBuilding.getServiceType() + " service");
                }
            }
        }
    }

    private void distributeUtilities() {
        for (UtilityProvider utilityProvider : cityMap.getUtilityProviders()) {
            bfsHandler.distributeUtility(cityMap, utilityProvider);
        }
    }

    private void updateZones() {
        for (Zone zone : cityMap.getZones()) {
            int oldLevel = zone.getLevel();
            zone.updateLevel();
            int newLevel = zone.getLevel();

            if (newLevel > oldLevel) {
                System.out.println(getZoneTypeName(zone) + " at (" + zone.getRow() + "," + zone.getCol() + ") levels up from " + oldLevel + " to " + newLevel);
            }
        }
    }
    private boolean hasService(Zone zone, String serviceType) {
         if (serviceType.equals("security")) {
            return zone.hasSecurity();
        }
        if (serviceType.equals("health")) {
            return zone.hasHealth();
        }
        if (serviceType.equals("education")) {
            return zone.hasEducation();
        }
        return false;
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
}
