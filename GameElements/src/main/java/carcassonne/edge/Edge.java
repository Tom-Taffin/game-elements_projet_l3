package carcassonne.edge;

public interface Edge {
    boolean isCompatibleWith(Edge other);
    boolean accept(EdgeVisitor visitor);
}
