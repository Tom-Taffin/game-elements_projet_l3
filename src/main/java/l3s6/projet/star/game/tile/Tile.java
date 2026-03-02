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

    public String stringRepresentation(){
        String res = this.orientation.toString();
        Map<Topology,Integer> nextIndicesForEachTopology = new HashMap<>();
        Map<Zone, String> visitedZones = new HashMap<>();
        int edgeIndex;
        for (edgeIndex = 0; edgeIndex < this.edges.length; edgeIndex++) {
            for(Zone zone : this.edges[edgeIndex].getZones()){
                Zone[] connectedZones = zone.getConnectingZones().toArray(Zone[]::new);
                int i;
                for(i = 0; i < connectedZones.length; i++){
                    if (visitedZones.containsKey(connectedZones[i])){
                        res += visitedZones.get(connectedZones[i]);
                        break;
                    }
                }
                // zone wasn't visited before
                if (i == connectedZones.length){
                    if (nextIndicesForEachTopology.containsKey(zone.getTopology())){
                        visitedZones.put(zone, zone.toString() + nextIndicesForEachTopology.get(zone.getTopology()).toString());
                        nextIndicesForEachTopology.replace(zone.getTopology(), nextIndicesForEachTopology.get(zone.getTopology())+1);
                    } else {
                        visitedZones.put(zone, zone.toString() + "1");
                        nextIndicesForEachTopology.put(zone.getTopology(), 2);
                    }
                    res += visitedZones.get(zone);
                }
            }
            // no - after the last edge
            if (edgeIndex < this.edges.length-1){
                res += "-";
            }
        }   
        return res;
    }

    public String toString(){
        return this.stringRepresentation();
    }
}
