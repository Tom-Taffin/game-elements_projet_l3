package carcassonne.edge;

public class EdgeWithRoad extends Edge {
    private Zone zone1;
    private Zone zone2;

    public EdgeWithRoad(Zone zone1, Zone zone2) {
        this.zone1 = zone1;
        this.zone2 = zone2;
    }

    public Zone getZone1() {
        return zone1;
    }

    public Zone getZone2() {
        return zone2;
    }
}
