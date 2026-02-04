package l3s6.projet.star.game.tile;


import java.util.HashMap;

import l3s6.projet.star.game.edge.Edge;
import l3s6.projet.star.game.edge.EdgeNoRoad;
import l3s6.projet.star.game.edge.EdgeWithRoad;
import l3s6.projet.star.game.edge.Topology;
import l3s6.projet.star.game.edge.Zone;

public class TileBuilder {
    
    public TileBuilder() {
    }

    public Tile build(String string) throws WrongTileSyntaxException{
        HashMap<String,Zone> zoneConnections = new HashMap<>();
        Edge[] edges = new Edge[4];
        this.analyseString(string, edges, zoneConnections);
        Tile tile = new Tile(edges[0], edges[1], edges[2], edges[3]);
        return tile;
    }

    private void analyseString(String string, Edge[] edges, HashMap<String,Zone> zoneConnections) throws WrongTileSyntaxException{
        String[] stringEdges = string.split("-");
        if(stringEdges.length != 4){
            throw new WrongTileSyntaxException("There is no 3 '-'");
        }
        for(int i = 0 ; i < stringEdges.length ; i++){
            if(stringEdges[i].contains("r")){
                String[] stringZones = stringEdges[i].split("r");
                EdgeWithRoad edge = this.buildEdgeWithRoad(stringZones);
                edges[i] = edge;
                zoneConnections.put(stringZones[0].substring(1),edge.getZone1());
                zoneConnections.put(stringZones[1].substring(1),edge.getZone2());
                
            }
            else{
                EdgeNoRoad edge = this.buildEdgeNoRoad(stringEdges[i]);
                edges[i] = edge;
                zoneConnections.put(stringEdges[i].substring(1),edge.getZone());
                
            }
        }
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
            throw new WrongTileSyntaxException(string + " is not recognized");
        }
    }
}
