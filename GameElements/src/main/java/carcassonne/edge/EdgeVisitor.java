package carcassonne.edge;

public interface EdgeVisitor {

    public boolean visit(EdgeNoRoad edge);

    public boolean visit(EdgeWithRoad edge);
    
}