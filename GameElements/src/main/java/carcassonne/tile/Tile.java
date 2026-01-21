package carcassonne.tile;

import carcassonne.edge.Edge;
import carcassonne.edge.EdgeWithRoad;

import java.util.HashMap;

public class Tile {
    private Edge topEdge;
    private Edge rightEdge;
    private Edge bottomEdge;
    private Edge leftEdge;

    private final HashMap<Location, Location> roadConnections;

    private Direction direction;
    // Direction where the TOP Edge of the tile is.
    // By default, it's on NORTH.

    public Tile(Edge topEdge, Edge rightEdge, Edge bottomEdge, Edge leftEdge) {
        this.topEdge = topEdge;
        this.rightEdge = rightEdge;
        this.bottomEdge = bottomEdge;
        this.leftEdge = leftEdge;
        this.direction = Direction.NORTH;
        this.roadConnections = new HashMap<>();
    }

    public HashMap<Location, Location> getRoadConnections() {
        return roadConnections;
    }

    public void connectRoad(Location location1, Location location2) throws ConnectionRoadToEdgeWithNoRoadException {
        if (!this.getEdge(location1).hasRoad() || !this.getEdge(location2).hasRoad()){
            throw new ConnectionRoadToEdgeWithNoRoadException("Tried to connect a road with an edge without a road.");
        }

        this.roadConnections.put(location1, location2);
        this.roadConnections.put(location2, location1);
    }

    public void terminateRoad(Location location) throws ConnectionRoadToEdgeWithNoRoadException {
        if (!this.getEdge(location).hasRoad()){
            throw new ConnectionRoadToEdgeWithNoRoadException("Tried to connect a road with an edge without a road.");
        }

        this.roadConnections.put(location, null);
    }

    public Direction getDirection() {
        return direction;
    }

    public void changeDirection(Direction direction){
        this.direction = direction;
    }

    public Edge getEdge(Location location) {
        switch (location) {
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
     * @param other
     * @param location
     * @return true if the other tile can connect to the edge location of this tile based on their direction
     */
    public boolean isCompatibleWith(Tile other, Location location){
        if (this.direction == other.getDirection()){
            return this.getEdge(location).isCompatibleWith(other.getEdge(location.toOpposite()));
        }
        else if (this.direction == other.getDirection().rotateLeft()){
            return this.getEdge(location).isCompatibleWith(other.getEdge(location.toRigth()));
        }
        else if (this.direction == other.getDirection().rotateRight()){
            return this.getEdge(location).isCompatibleWith(other.getEdge(location.toLeft()));
        }
        else {
            return this.getEdge(location).isCompatibleWith(other.getEdge(location));
        }
    }
}
