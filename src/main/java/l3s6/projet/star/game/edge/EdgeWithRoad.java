package l3s6.projet.star.game.edge;

import java.util.Set;

public class EdgeWithRoad implements Edge {
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

    @Override
    public boolean isCompatibleWith(Edge other) {
        return other.accept(new EdgeWithRoadVisitor(this));
    }

    @Override
    public boolean accept(EdgeVisitor visitor) {
        return visitor.visit(this);
    }

    @Override
    public boolean hasRoad() {
        return true;
    }

    public Topology getZone1Topology(){
        return this.zone1.getTopology();
    }

    public Topology getZone2Topology(){
        return this.zone2.getTopology();
    }

    public Set<Zone> getZone1ConnectingZones(){
        return this.zone1.getConnectingZones();
    }

    public Set<Zone> getZone2ConnectingZones(){
        return this.zone2.getConnectingZones();
    }

    public boolean isZone1Finished(){
        return this.zone1.isFinished();
    }

    public boolean isZone2Finished(){
        return this.zone2.isFinished();
    }
}
