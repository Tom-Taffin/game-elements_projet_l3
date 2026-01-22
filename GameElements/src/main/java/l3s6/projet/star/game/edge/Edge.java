package l3s6.projet.star.game.edge;

public interface Edge {
    boolean isCompatibleWith(Edge other);
    boolean accept(EdgeVisitor visitor);
    boolean hasRoad();
}
