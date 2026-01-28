package l3s6.projet.star.game.tile;


import l3s6.projet.star.game.edge.Edge;
import l3s6.projet.star.game.edge.EdgeNoRoad;
import l3s6.projet.star.game.edge.EdgeWithRoad;
import l3s6.projet.star.game.edge.Topology;
import l3s6.projet.star.game.edge.Zone;

public class TileBuilder {
    
    public TileBuilder() {
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

    public Tile buildTile(String string) throws WrongTileSyntaxException{
        Edge[] edges = new Edge[4];
        String[] stringEdges = string.split("-");
        if(stringEdges.length != 4){
            throw new WrongTileSyntaxException("There is no 3 '-'");
        }
        for(int i = 0 ; i < 4 ; i++){
            if(stringEdges[i].contains("r")){
                edges[i] = buildEdgeWithRoad(stringEdges[i]);
            }
            else{
                edges[i] = this.buildEdgeNoRoad(stringEdges[i]);
            }
        }
        return new Tile(edges[0], edges[1], edges[2], edges[3]);
    }

    private EdgeNoRoad buildEdgeNoRoad(String string) throws WrongTileSyntaxException{
        return new EdgeNoRoad(new Zone(this.getTopology(string)));
    }

    private EdgeWithRoad buildEdgeWithRoad(String string) throws WrongTileSyntaxException{
        String[] stringZones = string.split("r");
        return new EdgeWithRoad(new Zone(this.getTopology(stringZones[0])),new Zone(this.getTopology(stringZones[1])));
    }
}
