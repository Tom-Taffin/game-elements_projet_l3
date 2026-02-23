package l3s6.projet.star.game.edge;

public interface EdgeVisitor {

    public boolean visit(EdgeNoRoad edge);

    public boolean visit(EdgeWithRoad edge);
    
}