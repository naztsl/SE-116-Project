public class Main {
    public static void main(String[] args) {
        System.out.println("test");
import io.MapLoader;
import io.MapFormatException;
import model.CityMap;

        public class Main {
            public static void main(String[] args) {
                if (args.length != 2) {
                    System.err.println("Usage: java -jar ObjectVilleGame.jar <map_file.txt> <ticks>");
                    System.exit(1);
                }

                String mapFilePath = args[0];
                int ticks = 0;

                try {
                    ticks = Integer.parseInt(args[1]);
                    if (ticks <= 0) {
                        throw new MapFormatException("Tick count must be a positive integer.");
                    }
                } catch (NumberFormatException e) {
                    System.err.println("Error: Tick count must be a valid integer.");
                    System.exit(1);
                }

                try {
                    CityMap cityMap = MapLoader.loadMap(mapFilePath);
                    System.out.println("Map loaded successfully. Dimensions: " + cityMap.getWidth() + "x" + cityMap.getHeight());
                    cityMap.printMap();

                } catch (MapFormatException e) {
                    System.err.println("Configuration Error: " + e.getMessage());
                    System.exit(1);
                } catch (Exception e) {
                    System.err.println("An unexpected error occurred: " + e.getMessage());
                    e.printStackTrace();
                    System.exit(1);
                }
            }
        }
