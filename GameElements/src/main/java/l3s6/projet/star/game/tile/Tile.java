package l3s6.projet.star.game.tile;

import l3s6.projet.star.game.edge.Edge;

import java.util.HashMap;
import java.util.HashSet;

public class Tile {
    private Edge topEdge;
    private Edge rightEdge;
    private Edge bottomEdge;
    private Edge leftEdge;

    private final HashMap<Direction, Direction> roadConnections;
    private final HashSet<Direction> finishingRoads;

    private Orientation orientation;
    // By default, the orientation of this tile it's on NORTH.

    public Tile(Edge topEdge, Edge rightEdge, Edge bottomEdge, Edge leftEdge) {
        this.topEdge = topEdge;
        this.rightEdge = rightEdge;
        this.bottomEdge = bottomEdge;
        this.leftEdge = leftEdge;
        this.orientation = Orientation.NORTH;
        this.roadConnections = new HashMap<>();
        this.finishingRoads = new HashSet<>();
    }

    public Orientation getOrientation() {
        return orientation;
    }

    public void changeOrientation(Orientation orientation){
        this.orientation = orientation;
    }

    /**
     * @param direction
     * @return the tile's edge based on the given direction based on orientation.
     */
    public Edge getEdge(Direction direction) {
        Direction edgeDirection = direction;
        switch (this.orientation) {
            case EAST:
                edgeDirection = direction.toLeft();
                break;
            case SOUTH:
                edgeDirection = direction.toOpposite();
                break;
            case WEST:
                edgeDirection = direction.toRigth();
                break;
            default:
                break;
        }
        switch (edgeDirection) {
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
     * @param direction the direction of the road
     * @return true if the road at the direction is terminated.
     */
    public boolean isRoadTerminated(Direction direction){
        return this.finishingRoads.contains(direction);
    }

    /**
     * @param enterDirection the direction of the enter road
     * @return the direction of the road connected to the road at the enter direction
     * @throws NoExitRoadException if there is no exit road
     */
    public Direction getExitRoadDirection(Direction enterDirection) throws NoExitRoadException{
        if(!this.getEdge(enterDirection).hasRoad()){
            throw new NoExitRoadException("There is no road at the enterDirection");
        }
        if(this.isRoadTerminated(enterDirection)){
            throw new NoExitRoadException("The road at the enterDirection is not connected");
        }
        return this.roadConnections.get(enterDirection);
    }

    /**
     * Creates a road connection between the two given edges, based on the given directions.
     * @param direction1
     * @param direction2
     * @throws ConnectionRoadToEdgeWithNoRoadException If one of the given directions gives an edge without a road.
     */
    public void connectRoad(Direction direction1, Direction direction2) throws ConnectionRoadToEdgeWithNoRoadException {
        if (!this.getEdge(direction1).hasRoad() || !this.getEdge(direction2).hasRoad()){
            throw new ConnectionRoadToEdgeWithNoRoadException("Tried to connect a road with an edge without a road.");
        }

        this.roadConnections.put(direction1, direction2);
        this.roadConnections.put(direction2, direction1);
    }

    /**
     * Terminates a road's edge based on the direction.
     * @param direction
     * @throws ConnectionRoadToEdgeWithNoRoadException If the edge based on the direction doesn't have a road.
     */
    public void terminateRoad(Direction direction) throws ConnectionRoadToEdgeWithNoRoadException {
        if (!this.getEdge(direction).hasRoad()){
            throw new ConnectionRoadToEdgeWithNoRoadException("Tried to connect a road with an edge without a road.");
        }

        this.finishingRoads.add(direction);
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
