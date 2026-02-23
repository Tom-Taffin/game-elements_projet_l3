package l3s6.projet.star.game.edge;

import java.util.Set;

public class EdgeNoRoad implements Edge {
    private Zone zone;

    public EdgeNoRoad(Zone zone) {
        this.zone = zone;
    }

    public Zone getZone() {
        return this.zone;
    }

    @Override
    public boolean isCompatibleWith(Edge other) {
        return other.accept(new EdgeNoRoadVisitor(this));
    }

    @Override
    public boolean accept(EdgeVisitor visitor) {
        return visitor.visit(this);
    }

    @Override
    public boolean hasRoad() {
        return false;
    }

    public Topology getZoneTopology(){
        return this.zone.getTopology();
    }

    public Set<Zone> getConnectingZones(){
        return this.zone.getConnectingZones();
    }

    public boolean isZoneFinished(){
        return this.zone.isFinished();
    }

}
