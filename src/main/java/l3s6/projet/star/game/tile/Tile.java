package l3s6.projet.star.game.tile;

import l3s6.projet.star.game.edge.Edge;

public class Tile {
    private Edge topEdge;
    private Edge rightEdge;
    private Edge bottomEdge;
    private Edge leftEdge;

    private Orientation orientation;
    // By default, the orientation of this tile it's on NORTH.

    public Tile(Edge topEdge, Edge rightEdge, Edge bottomEdge, Edge leftEdge){
        this(topEdge, rightEdge, bottomEdge, leftEdge, Orientation.NORTH);
    }

    public Tile(Edge topEdge, Edge rightEdge, Edge bottomEdge, Edge leftEdge, Orientation orientation) {
        this.topEdge = topEdge;
        this.rightEdge = rightEdge;
        this.bottomEdge = bottomEdge;
        this.leftEdge = leftEdge;
        this.orientation = orientation;
    }

    public Orientation getOrientation() {
        return orientation;
    }

    /**
     * @param direction
     * @return the tile's edge based on the given direction based on orientation.
     */
    public Edge getEdge(Direction direction) {
        switch (direction.getOldDirection(this.orientation)) {
            case TOP:
                return topEdge;
            case RIGHT:
                return rightEdge;
            case BOTTOM:
                return bottomEdge;
            default:
                return leftEdge;
        }
    }

    /**
     * @param other the other tile
     * @param direction the direction where you connect the other tile
     * @return true if the other tile can be connected to the edge direction of this tile based on their orientation
     */
    public boolean isCompatibleWith(Tile other, Direction direction){
        return this.getEdge(direction).isCompatibleWith(other.getEdge(direction.toOpposite()));
    }
}
