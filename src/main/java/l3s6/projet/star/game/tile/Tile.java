package l3s6.projet.star.game.tile;

import java.util.HashMap;
import java.util.Map;

import l3s6.projet.star.game.edge.Edge;
import l3s6.projet.star.game.edge.Topology;
import l3s6.projet.star.game.edge.Zone;

public class Tile {
    private Edge[] edges;

    private Orientation orientation;
    // By default, the orientation of this tile it's on NORTH.

    public Tile(Edge topEdge, Edge rightEdge, Edge bottomEdge, Edge leftEdge){
        this(topEdge, rightEdge, bottomEdge, leftEdge, Orientation.NORTH);
    }

    public Tile(Edge topEdge, Edge rightEdge, Edge bottomEdge, Edge leftEdge, Orientation orientation) {
        this.edges = new Edge[4];
        this.edges[0] = topEdge;
        this.edges[1] = rightEdge;
        this.edges[2] = bottomEdge;
        this.edges[3] = leftEdge;
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
                return this.edges[0];
            case RIGHT:
                return this.edges[1];
            case BOTTOM:
                return this.edges[2];
            default:
                return this.edges[3];
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

    /**
     * @return the i-th zone of the edge at the direction
     */
    public Zone getZonesAt(Direction direction, int i){
        return this.getEdge(direction).getZoneAt(i);
    }

    public String toString(){
        return this.tileStringRepresentation();
    }

    /**
     * Creates the simplest String representation of the tile which takes into account zone connections.
     * @return the String representation of the tile
     */
    private String tileStringRepresentation(){
        String tileRes = this.orientation.toString();
        Map<Topology,Integer> nextIndicesForEachTopology = new HashMap<>();
        Map<Zone, String> visitedZones = new HashMap<>();
        int edgeIndex;
        for (edgeIndex = 0; edgeIndex < this.edges.length; edgeIndex++) {
            tileRes += edgeStringRepresentation(this.edges[edgeIndex], nextIndicesForEachTopology, visitedZones);
            if (edgeIndex < this.edges.length-1){
                tileRes += "-";
            }
        }   
        return tileRes;
    }

    /**
     * Creates the simplest String representation of an edge which takes into account zone connections from previous edges.
     * @param edge the edge
     * @param nextIndicesForEachTopology contains the next index of each topology type
     * @param visitedZones contains for each zone the String representation of the zone to which it's belong
     * @return the String representation of the edge
     */
    private String edgeStringRepresentation(Edge edge, Map<Topology,Integer> nextIndicesForEachTopology, Map<Zone, String> visitedZones){
        String edgeRes = "";
        for(Zone zone : edge.getZones()){
            edgeRes += zoneStringRepresentation(zone, nextIndicesForEachTopology, visitedZones);
        }
        return edgeRes;
    }

    /**
     * Creates the simplest String representation of a zone which takes into account zone connections from previous edges.
     * @param zone the zone
     * @param nextIndicesForEachTopology contains the next index of each topology type
     * @param visitedZones contains for each zone the String representation of the zone to which it's belong
     * @return the String representation of the zone
     */
    private String zoneStringRepresentation(Zone zone, Map<Topology,Integer> nextIndicesForEachTopology, Map<Zone, String> visitedZones){
        String zoneRes = "";
        Zone[] connectedZones = zone.getConnectingZones().toArray(Zone[]::new);
        int i;
        for(i = 0; i < connectedZones.length; i++){
            if (visitedZones.containsKey(connectedZones[i])){
                // zone was visited before
                zoneRes += visitedZones.get(connectedZones[i]);
                break;
            }
        }
        
        if (i == connectedZones.length){
            // zone wasn't visited before
            if (!(nextIndicesForEachTopology.containsKey(zone.getTopology()))){
                // if it's the first time seeing this type of topology, add it to nextIndicesForEachTopology
                nextIndicesForEachTopology.put(zone.getTopology(), 1);
            }
            visitedZones.put(zone, zone.toString() + nextIndicesForEachTopology.get(zone.getTopology()).toString());
            nextIndicesForEachTopology.replace(zone.getTopology(), nextIndicesForEachTopology.get(zone.getTopology())+1);
            zoneRes += visitedZones.get(zone);
        }
        return zoneRes;
    }
}
