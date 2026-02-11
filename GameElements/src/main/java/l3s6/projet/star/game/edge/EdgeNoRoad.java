package l3s6.projet.star.game.edge;

public class EdgeNoRoad implements Edge {
    private Zone zone;

    public EdgeNoRoad(Zone zone) {
        this.zone = zone;
    }

    public Zone getZone() {
        return this.zone;
    }

    public Topology getZoneTopology(){
        return this.zone.getTopology();
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

    /** 
     * add a connected zone to the zone of this edge
     */
    public void addConnectZone(Zone zone) throws WrongTopologyException{
        this.zone.addConnectedZone(zone);
    }

}
