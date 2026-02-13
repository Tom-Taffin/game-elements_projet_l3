package l3s6.projet.star.game.tile;


import java.util.HashMap;
import java.util.HashSet;

import l3s6.projet.star.game.edge.Edge;
import l3s6.projet.star.game.edge.EdgeNoRoad;
import l3s6.projet.star.game.edge.EdgeWithRoad;
import l3s6.projet.star.game.edge.Topology;
import l3s6.projet.star.game.edge.WrongTopologyException;
import l3s6.projet.star.game.edge.Zone;

public class TileBuilder {
    
    public TileBuilder() {
    }

    public Tile build(String string) throws WrongTileSyntaxException{
        HashMap<String,HashSet<Zone>> zoneConnections = new HashMap<>();
        Edge[] edges = new Edge[4];
        
        Orientation orientation = this.getOrientation(string);

        this.analyseString(string.substring(1), edges, zoneConnections);
        
        Tile tile = new Tile(edges[0], edges[1], edges[2], edges[3], orientation);

        this.createConnections(tile,zoneConnections);
        
        return tile;
    }

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

    private void analyseString(String string, Edge[] edges, HashMap<String,HashSet<Zone>> zoneConnections) throws WrongTileSyntaxException{
        String[] stringEdges = string.split("-");
        if(stringEdges.length != 4){
            throw new WrongTileSyntaxException("There is no 3 '-'");
        }
        for(int i = 0 ; i < stringEdges.length ; i++){
            if(stringEdges[i].contains("r")){
                this.analyseEdgeWithRoadString(stringEdges[i], edges, zoneConnections, i);
            }
            else{
                this.analyseEdgeNoRoadString(stringEdges[i], edges, zoneConnections, i);
            }
        }
    }

    private void analyseEdgeNoRoadString(String stringEdge, Edge[] edges, HashMap<String, HashSet<Zone>> zoneConnections, int index) throws WrongTileSyntaxException {
        EdgeNoRoad edge = this.buildEdgeNoRoad(stringEdge);
        edges[index] = edge;
        this.saveConnection(zoneConnections, stringEdge.substring(1), edge.getZone());
    }

    private void analyseEdgeWithRoadString(String stringEdge, Edge[] edges, HashMap<String, HashSet<Zone>> zoneConnections, int index) throws WrongTileSyntaxException {
        String[] stringZones = stringEdge.split("r");
        if(stringZones.length != 2){
            throw new WrongTileSyntaxException("There are too many 'r' between the '-'");
        }
        EdgeWithRoad edge = this.buildEdgeWithRoad(stringZones);
        edges[index] = edge;
        this.saveConnection(zoneConnections, stringZones[0].substring(1), edge.getZone1());
        this.saveConnection(zoneConnections, stringZones[1].substring(1), edge.getZone2());
    }

    private void saveConnection(HashMap<String, HashSet<Zone>> zoneConnections, String id, Zone zone) {
        if(!zoneConnections.containsKey(id)){
            zoneConnections.put(id,new HashSet<Zone>());
        }
        zoneConnections.get(id).add(zone);
    }
    
    private EdgeNoRoad buildEdgeNoRoad(String string) throws WrongTileSyntaxException{
        return new EdgeNoRoad(new Zone(this.getTopology(string)));
    }

    private EdgeWithRoad buildEdgeWithRoad(String[] stringZones) throws WrongTileSyntaxException{
        return new EdgeWithRoad(new Zone(this.getTopology(stringZones[0])),new Zone(this.getTopology(stringZones[1])));
    }

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

    private void createConnections(Tile tile, HashMap<String,HashSet<Zone>> zoneConnections) throws WrongTileSyntaxException {
        for(HashSet<Zone> zonesGroup : zoneConnections.values()){
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
}
