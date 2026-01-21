package carcassonne.edge;

public interface Edge {
    boolean isCompatible(Edge other);
    boolean accept(EdgeVisitor visitor);
}
