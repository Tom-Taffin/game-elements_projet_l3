package l3s6.projet.star.game.edge;

public class EdgeWithRoadVisitor implements EdgeVisitor{

    private final EdgeWithRoad origin;

    public EdgeWithRoadVisitor(EdgeWithRoad origin) {
        this.origin = origin;
    }

    @Override
    public boolean visit(EdgeNoRoad other) {
        return false;
    }

    @Override
    public boolean visit(EdgeWithRoad other) {
        return origin.getZone1().getTopology() == other.getZone2().getTopology() && origin.getZone2().getTopology() == other.getZone1().getTopology();
    }
    
}
