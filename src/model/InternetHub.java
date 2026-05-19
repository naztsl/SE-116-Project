package model;
public class InternetHub extends UtilityProvider {

    public InternetHub(int row, int col, char symbol) {
        super(row, col, 'T', 100, "internet");
    }

    @Override
    public int produceUtility() {
        return capacity;
    }
}
