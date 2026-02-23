package l3s6.projet.star.game.edge;

public class EdgeNoRoadVisitor implements EdgeVisitor{

    private final EdgeNoRoad origin;

    public EdgeNoRoadVisitor(EdgeNoRoad origin) {
        this.origin = origin;
    }

    @Override
    public boolean visit(EdgeNoRoad other) {
        return origin.getZone().getTopology() == other.getZone().getTopology();
    }

    @Override
    public boolean visit(EdgeWithRoad other) {
        return false;
    }
    
}
