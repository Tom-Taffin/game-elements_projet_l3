package carcassonne.edge;

public class EdgeNoRoad extends Edge {
    private Zone zone;

    public EdgeNoRoad(Zone zone) {
        this.zone = zone;
    }

    public Zone getZone() {
        return zone;
    }
}
