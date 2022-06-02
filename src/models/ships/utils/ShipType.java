package models.ships.utils;

public enum ShipType {
    RECONNAISSANCE(4, 1),
    DESTROYER(3, 2),
    WAR(2, 3);

    private final int numShips;
    private final int shipLength;

    ShipType (int numShips, int shipLength) {
        this.numShips = numShips;
        this.shipLength = shipLength;
    }

    public int getShipLength() {
        return shipLength;
    }
    public int getNumShips() {
        return numShips;
    }

    public static int lengthAllShips(){
        int sum = 0;
        for (ShipType type: ShipType.values()) sum += type.shipLength * type.numShips;
        return sum;
    }
    public static int sizeAllShips(){
        int sum = 0;
        for (ShipType type: ShipType.values()) sum += type.numShips;
        return sum;
    }
    public static String toSpanishNameShip(ShipType type){
        return switch (type) {
            case RECONNAISSANCE -> "Lancha de reconocimiento";
            case DESTROYER -> "Barco destructor";
            case WAR -> "Buque de guerra";
        };
    }
}
