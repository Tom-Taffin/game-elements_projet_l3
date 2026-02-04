package l3s6.projet.star.game.tile;

import l3s6.projet.star.game.edge.Edge;
import l3s6.projet.star.game.meeple.Meeple;

import java.util.HashMap;
import java.util.HashSet;

public class Tile {
    private Edge topEdge;
    private Edge rightEdge;
    private Edge bottomEdge;
    private Edge leftEdge;

    private final HashMap<Direction, Direction> roadConnections;
    private final HashSet<Direction> finishingRoads;
    private final HashMap<Direction, HashSet<Direction>> zoneConnections;

    private MeeplePlacements meeples;


    private Orientation orientation;
    // Orientation where the TOP Edge of the tile is.
    // By default, it's on NORTH.

    public Tile(Edge topEdge, Edge rightEdge, Edge bottomEdge, Edge leftEdge) {
        this.topEdge = topEdge;
        this.rightEdge = rightEdge;
        this.bottomEdge = bottomEdge;
        this.leftEdge = leftEdge;
        this.orientation = Orientation.NORTH;
        this.roadConnections = new HashMap<>();
        this.finishingRoads = new HashSet<>();
        this.zoneConnections = new HashMap<>();

        this.meeples = new MeeplePlacements();

        initializeZoneConnections();
    }

    private void initializeZoneConnections(){
        this.zoneConnections.put(Direction.TOP, new HashSet<>());
        this.zoneConnections.put(Direction.RIGHT, new HashSet<>());
        this.zoneConnections.put(Direction.BOTTOM, new HashSet<>());
        this.zoneConnections.put(Direction.LEFT, new HashSet<>());
    }


    public HashMap<Direction, Direction> getRoadConnections() {
        return roadConnections;
    }


    public Orientation getOrientation() {
        return orientation;
    }

    public void changeOrientation(Orientation orientation){
        this.orientation = orientation;
    }

    /**
     * @param direction
     * @return the tile's edge based on the given direction.
     */
    public Edge getEdge(Direction direction) {
        switch (direction) {
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

    public boolean isRoadFinished(Direction direction){
        return this.finishingRoads.contains(direction);
    }

    public Direction getExitRoadDirection(Direction enterDirection) throws NoExitRoadException{
        if(!this.getEdge(enterDirection).hasRoad()){
            throw new NoExitRoadException("There is no road at the enterDirection");
        }
        if(this.isRoadFinished(enterDirection)){
            throw new NoExitRoadException("The road at the enterDirection is not connected");
        }
        return this.roadConnections.get(enterDirection);
    }

    /**
     * Creates a connection between the two given edges, based on the given directions.
     * Creates 2 entries in the roadConnections dictionary, in both ways.
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
     * @param direction the direction where you
     * @return true if the other tile is compatible to the edge direction of this tile based on their orientation
     */
    public boolean isCompatibleWith(Tile other, Direction direction){
        if (this.orientation == other.getOrientation()){
            return this.getEdge(direction).isCompatibleWith(other.getEdge(direction.toOpposite()));
        }
        else if (this.orientation == other.getOrientation().rotateLeft()){
            return this.getEdge(direction).isCompatibleWith(other.getEdge(direction.toRigth()));
        }
        else if (this.orientation == other.getOrientation().rotateRight()){
            return this.getEdge(direction).isCompatibleWith(other.getEdge(direction.toLeft()));
        }
        else {
            return this.getEdge(direction).isCompatibleWith(other.getEdge(direction));
        }
    }

    public Meeple getMeepleOnZone(Direction direction){
        return meeples.getMeepleOnZone(this.getEdge(direction));
    }

    public void placeMeepleOnZone(Meeple meeple, Direction direction){
        meeples.placeMeepleOnZone(meeple, this.getEdge(direction));
    }

    public boolean hasMeepleOnZone(Direction direction){
        return this.meeples.hasMeepleOnZone(this.getEdge(direction));
    }
}
