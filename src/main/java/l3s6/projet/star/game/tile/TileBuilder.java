package l3s6.projet.star.game.tile;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import l3s6.projet.star.game.edge.Edge;
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
        Edge[] edges = new Edge[4];

        Orientation orientation = this.getOrientation(string);

        this.analyseString(string.substring(1), edges);
        
        Tile tile = new Tile(edges[0], edges[1], edges[2], edges[3], orientation);
        
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
     * Analyzes the string representation of the tile edges, populates the edges array and 
     * create zone connections.
     * @param string the substring containing edge descriptions
     * @param edges the array to store the parsed edges
     * @throws WrongTileSyntaxException if the string format is invalid
     */
    private void analyseString(String string, Edge[] edges) throws WrongTileSyntaxException{
        String[] stringEdges = string.split("-");
        if(stringEdges.length != 4){
            throw new WrongTileSyntaxException("There is no 3 '-'");
        }
        
        // visitedZones associates an id with a list of zones for all visited zones.
        HashMap<String,List<Zone>> visitedZones = new HashMap<>();

        for(int i = 0 ; i < stringEdges.length ; i++){
            this.analyseEdgeString(stringEdges[i], edges, i, visitedZones);
        }
    }

    /**
     * Analyzes a string representation of an edge with a road.
     * Stores the builded edge in edges at the index,
     * populates visitedZones and
     * create  connections.
     * @param stringEdge the string for the edge
     * @param edges the edges array
     * @param index the index in the edges array
     * @param visitedZones a map that associates an id with a list of zones for all visited zones.
     * @throws WrongTileSyntaxException if the format is invalid
     */
    private void analyseEdgeString(String stringEdge, Edge[] edges, int index, HashMap<String,List<Zone>> visitedZones) throws WrongTileSyntaxException {
        List<Zone> zonesPerEdges = new ArrayList<>();
        // each stringZones element is a letter with the id number
        String[] stringZones = stringEdge.split("(?=[a-zA-Z])");
        for(int j = 0; j < stringZones.length; j++){
            Zone zone = new Zone(this.getTopology(stringZones[j]));
            if (visitedZones.containsKey(stringZones[j])){
                createConnections(visitedZones.get(stringZones[j]), zone);
            }
            else {
                visitedZones.put(stringZones[j], new ArrayList<>());
            }
            visitedZones.get(stringZones[j]).add(zone);
            zonesPerEdges.add(zone);
        }
        edges[index] = new Edge(zonesPerEdges.get(0), zonesPerEdges.subList(1, zonesPerEdges.size()));
    }

    /** create zone connections of the zone based on visitedZones
     * @param visitedZones zones that need to be connected to zone
     */
    private void createConnections(List<Zone> visitedZones, Zone zone)
            throws WrongTileSyntaxException {
        for (Zone visitedZone : visitedZones){
            try{
                zone.connectTo(visitedZone);
            } catch (WrongTopologyException e){
                throw new WrongTileSyntaxException(zone.toString() + " can't connected to previously visited zone");
            }
        }
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
        else if(string.startsWith("r")){
            return Topology.ROAD;
        }
        else{
            throw new WrongTileSyntaxException("Topololy " + string + " is not recognized");
        }
    }

}
