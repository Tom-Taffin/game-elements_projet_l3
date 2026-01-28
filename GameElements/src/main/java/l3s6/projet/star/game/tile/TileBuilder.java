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
        for(int i = 0 ; i < 4 ; i++){
            if(stringEdges[i].contains("r")){
                String[] stringZones = stringEdges[i].split("r");
                edges[i] = new EdgeWithRoad(new Zone(this.getTopology(stringZones[0])),new Zone(this.getTopology(stringZones[1])));
            }
            else{
                edges[i] = new EdgeNoRoad(new Zone(this.getTopology(stringEdges[i])));
            }
        }
        return new Tile(edges[0], edges[1], edges[2], edges[3]);
    }
}
