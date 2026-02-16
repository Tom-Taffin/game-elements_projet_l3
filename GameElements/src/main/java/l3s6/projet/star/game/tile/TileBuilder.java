package l3s6.projet.star.game.tile;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import l3s6.projet.star.game.edge.Edge;
import l3s6.projet.star.game.edge.EdgeNoRoad;
import l3s6.projet.star.game.edge.EdgeWithRoad;
import l3s6.projet.star.game.edge.Topology;
import l3s6.projet.star.game.edge.WrongTopologyException;
import l3s6.projet.star.game.edge.Zone;

/**
 * Builder class for creating Tile objects from string representations.
 * This class parses a specific string format to construct tiles with edges, zones, and road connections
 * for a tile-based game.
 */
public class TileBuilder {
    
    public TileBuilder() {
    }

    /**
     * Builds a Tile object from the given string representation.
     * The string format starts with an orientation character (N, E, S, W) followed by four edge descriptions separated by '-'.
     * @param string the string representation of the tile
     * @return the constructed Tile object
     * @throws WrongTileSyntaxException if the string format is invalid
     */
    public Tile build(String string) throws WrongTileSyntaxException{
        HashMap<String,List<Zone>> zoneConnections = new HashMap<>();
        HashMap<String,List<Direction>> roadConnections = new HashMap<>();
        Edge[] edges = new Edge[4];
        
        Orientation orientation = this.getOrientation(string);

        this.analyseString(string.substring(1), edges, zoneConnections, roadConnections);
        
        Tile tile = new Tile(edges[0], edges[1], edges[2], edges[3], orientation);

        this.createZoneConnections(tile,zoneConnections);
        this.createRoadConnections(tile,roadConnections);
        
        return tile;
    }

    /**
     * Parses the orientation from the first character of the string.
     * @param string the input string
     * @return the corresponding Orientation enum value
     * @throws WrongTileSyntaxException if the orientation character is invalid
     */
    private Orientation getOrientation(String string) throws WrongTileSyntaxException {
        switch (string.charAt(0)) {
            case 'N':
                return Orientation.NORTH;
            case 'E':
                return Orientation.EAST;
            case 'S':
                return Orientation.SOUTH;
            case 'W':
                return Orientation.WEST;
            default:
                throw new WrongTileSyntaxException("Orientation " + string.charAt(0) + " is not recognized");
        }
    }

    /**
     * Analyzes the string representation of the tile edges and populates the edges array,
     * zone connections, and road connections.
     * @param string the substring containing edge descriptions
     * @param edges the array to store the parsed edges
     * @param zoneConnections map to store zone connections by ID
     * @param roadConnections map to store road connections by ID
     * @throws WrongTileSyntaxException if the string format is invalid
     */
    private void analyseString(String string, Edge[] edges, HashMap<String,List<Zone>> zoneConnections, HashMap<String,List<Direction>> roadConnections) throws WrongTileSyntaxException{
        String[] stringEdges = string.split("-");
        if(stringEdges.length != 4){
            throw new WrongTileSyntaxException("There is no 3 '-'");
        }
        for(int i = 0 ; i < stringEdges.length ; i++){
            if(stringEdges[i].contains("r")){
                this.analyseEdgeWithRoadString(stringEdges[i], edges, zoneConnections, roadConnections, i);
            }
            else{
                this.analyseEdgeNoRoadString(stringEdges[i], edges, zoneConnections, i);
            }
        }
    }

    /**
     * Analyzes a string representation of an edge without a road. 
     * Stores the builded edge in edges at the index
     * and saves zones connections in zoneConnections.
     * @param stringEdge the string for the edge
     * @param edges the edges array
     * @param zoneConnections map for zone connections
     * @param index the index in the edges array
     * @throws WrongTileSyntaxException if the format is invalid
     */
    private void analyseEdgeNoRoadString(String stringEdge, Edge[] edges, HashMap<String, List<Zone>> zoneConnections, int index) throws WrongTileSyntaxException {
        EdgeNoRoad edge = this.buildEdgeNoRoad(stringEdge);
        edges[index] = edge;
        this.saveConnection(zoneConnections, stringEdge.substring(1), edge.getZone());
    }

    /**
     * Analyzes a string representation of an edge with a road.
     * Stores the builded edge in edges at the index
     * and saves connections in zoneConnections and roadConnections.
     * @param stringEdge the string for the edge
     * @param edges the edges array
     * @param zoneConnections map for zone connections
     * @param roadConnections map for road connections
     * @param index the index in the edges array
     * @throws WrongTileSyntaxException if the format is invalid
     */
    private void analyseEdgeWithRoadString(String stringEdge, Edge[] edges, HashMap<String, List<Zone>> zoneConnections, HashMap<String,List<Direction>> roadConnections, int index) throws WrongTileSyntaxException {
        String[] stringZones = stringEdge.split("r");
        if(stringZones.length != 2){
            throw new WrongTileSyntaxException("There are too many 'r' between the '-'");
        }
        this.saveConnection(roadConnections, stringZones[1].substring(0,1), this.getDirection(index));
        stringZones[1] = stringZones[1].substring(1);
        EdgeWithRoad edge = this.buildEdgeWithRoad(stringZones);
        edges[index] = edge;
        this.saveConnection(zoneConnections, stringZones[0].substring(1), edge.getZone1());
        this.saveConnection(zoneConnections, stringZones[1].substring(1), edge.getZone2());
    }

    /**
     * Gets the Direction enum corresponding to the edge index.
     * @param index the edge index (0-3)
     * @return the Direction (TOP, RIGHT, BOTTOM, LEFT)
     */
    private Direction getDirection(int index) {
        switch (index) {
            case 0:
                return Direction.TOP;
            case 1:
                return Direction.RIGHT;
            case 2:
                return Direction.BOTTOM;
            default:
                return Direction.LEFT;
        }
    }

    /**
     * Saves an element to the connections map under the given ID.
     * @param <Elt> the type of element
     * @param connections the map to store connections
     * @param id the connection ID
     * @param elt the element to add
     */
    private <Elt> void saveConnection(HashMap<String, List<Elt>> connections, String id, Elt elt) {
        if(!connections.containsKey(id)){
            connections.put(id,new ArrayList<Elt>());
        }
        connections.get(id).add(elt);
    }
    
    /**
     * Builds an EdgeNoRoad from the string representation.
     * @param string the string for the edge
     * @return the EdgeNoRoad object
     * @throws WrongTileSyntaxException if the topology is invalid
     */
    private EdgeNoRoad buildEdgeNoRoad(String string) throws WrongTileSyntaxException{
        return new EdgeNoRoad(new Zone(this.getTopology(string)));
    }

    /**
     * Builds an EdgeWithRoad from the string zones.
     * @param stringZones array of strings for the two zones
     * @return the EdgeWithRoad object
     * @throws WrongTileSyntaxException if the topologies are invalid
     */
    private EdgeWithRoad buildEdgeWithRoad(String[] stringZones) throws WrongTileSyntaxException{
        return new EdgeWithRoad(new Zone(this.getTopology(stringZones[0])),new Zone(this.getTopology(stringZones[1])));
    }

    /**
     * Parses the Topology from the string prefix.
     * @param string the string starting with 'f' or 'c'
     * @return the Topology enum value
     * @throws WrongTileSyntaxException if the prefix is invalid
     */
    private Topology getTopology(String string) throws WrongTileSyntaxException{
        if(string.startsWith("f")){
            return Topology.FIELD;
        }
        else if(string.startsWith("c")){
            return Topology.CITY;
        }
        else{
            throw new WrongTileSyntaxException("Topololy " + string + " is not recognized");
        }
    }

    /**
     * Creates connections between zones that share the same ID.
     * @param tile the tile being built
     * @param zoneConnections map of zone connections
     * @throws WrongTileSyntaxException if zones of different topologies are connected
     */
    private void createZoneConnections(Tile tile, HashMap<String,List<Zone>> zoneConnections) throws WrongTileSyntaxException {
        for(List<Zone> zonesGroup : zoneConnections.values()){
            for(Zone zone : zonesGroup){
                for(Zone otherZone : zonesGroup){
                    if(zone != otherZone){
                        try {
                            zone.addConnectedZone(otherZone);
                        } catch (WrongTopologyException e) {
                            throw new WrongTileSyntaxException("There is a connection of different topologies");
                        }
                    }
                }
            }
        }
    }

    /**
     * Creates road connections or terminations based on the road connections map.
     * @param tile the tile being built
     * @param roadConnections map of road connections
     * @throws WrongTileSyntaxException if road connections are invalid
     */
    private void createRoadConnections(Tile tile, HashMap<String,List<Direction>> roadConnections) throws WrongTileSyntaxException {
        for(List<Direction> roadGroup : roadConnections.values()){
            if(roadGroup.size() > 2){
                throw new WrongTileSyntaxException("More than 2 road can't be connected");
            }
            else if(roadGroup.size() == 2){
                try {
                    tile.connectRoad(roadGroup.get(0).getNewDirection(tile.getOrientation()), roadGroup.get(1).getNewDirection(tile.getOrientation()));
                } catch (NoRoadException e) {
                    throw new WrongTileSyntaxException("No road for connection");
                }
            }
            else{
                try {
                    tile.terminateRoad(roadGroup.get(0).getNewDirection(tile.getOrientation()));
                } catch (NoRoadException e) {
                    throw new WrongTileSyntaxException("No road for terminate");
                }
            }
        }
    }
}
