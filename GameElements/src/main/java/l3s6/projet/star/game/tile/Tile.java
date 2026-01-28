package l3s6.projet.star.game.tile;

import l3s6.projet.star.game.edge.Edge;

import java.util.HashMap;
import java.util.HashSet;

public class Tile {
    private Edge topEdge;
    private Edge rightEdge;
    private Edge bottomEdge;
    private Edge leftEdge;

    private final HashMap<Location, Location> roadConnections;
    private final HashSet<Location> finishingRoads;

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
        this.finishingRoads = new HashSet<>();
    }


    public HashMap<Location, Location> getRoadConnections() {
        return roadConnections;
    }


    public Direction getDirection() {
        return direction;
    }

    public void changeDirection(Direction direction){
        this.direction = direction;
    }

    /**
     * @param location
     * @return the tile's edge based on the given location.
     */
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

    public boolean isRoadFinished(Location location){
        return this.finishingRoads.contains(location);
    }

    public Location getExitRoadLocation(Location enterLocation) throws NoExitRoadException{
        if(!this.getEdge(enterLocation).hasRoad()){
            throw new NoExitRoadException("There is no road at the enterLocation");
        }
        if(this.isRoadFinished(enterLocation)){
            throw new NoExitRoadException("The road at the enterLocation is not connected");
        }
        return this.roadConnections.get(enterLocation);
    }

    /**
     * Creates a connection between the two given edges, based on the given locations.
     * Creates 2 entries in the roadConnections dictionary, in both ways.
     * @param location1
     * @param location2
     * @throws ConnectionRoadToEdgeWithNoRoadException If one of the given locations gives an edge without a road.
     */
    public void connectRoad(Location location1, Location location2) throws ConnectionRoadToEdgeWithNoRoadException {
        if (!this.getEdge(location1).hasRoad() || !this.getEdge(location2).hasRoad()){
            throw new ConnectionRoadToEdgeWithNoRoadException("Tried to connect a road with an edge without a road.");
        }

        this.roadConnections.put(location1, location2);
        this.roadConnections.put(location2, location1);
    }

    /**
     * Terminates a road's edge based on the location.
     * @param location
     * @throws ConnectionRoadToEdgeWithNoRoadException If the edge based on the location doesn't have a road.
     */
    public void terminateRoad(Location location) throws ConnectionRoadToEdgeWithNoRoadException {
        if (!this.getEdge(location).hasRoad()){
            throw new ConnectionRoadToEdgeWithNoRoadException("Tried to connect a road with an edge without a road.");
        }

        this.finishingRoads.add(location);
    }

    /**
     * @param other the other tile
     * @param location the location where you
     * @return true if the other tile is compatible to the edge location of this tile based on their direction
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
