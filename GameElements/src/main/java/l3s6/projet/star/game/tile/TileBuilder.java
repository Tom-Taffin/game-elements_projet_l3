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

public class TileBuilder {
    
    public TileBuilder() {
    }

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

    private void analyseEdgeNoRoadString(String stringEdge, Edge[] edges, HashMap<String, List<Zone>> zoneConnections, int index) throws WrongTileSyntaxException {
        EdgeNoRoad edge = this.buildEdgeNoRoad(stringEdge);
        edges[index] = edge;
        this.saveConnection(zoneConnections, stringEdge.substring(1), edge.getZone());
    }

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

    private <Elt> void saveConnection(HashMap<String, List<Elt>> connections, String id, Elt elt) {
        if(!connections.containsKey(id)){
            connections.put(id,new ArrayList<Elt>());
        }
        connections.get(id).add(elt);
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
