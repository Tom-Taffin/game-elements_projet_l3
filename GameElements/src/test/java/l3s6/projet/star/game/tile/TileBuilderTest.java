package l3s6.projet.star.game.tile;

import org.junit.jupiter.api.Test;

import l3s6.projet.star.game.edge.EdgeNoRoad;
import l3s6.projet.star.game.edge.Topology;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;

public class TileBuilderTest {
    
    private TileBuilder tileBuilder;
    
    @BeforeEach
    public void setup(){
        this.tileBuilder = new TileBuilder();
    }
    
    @Test
    public void testBuildTileWithoutRoadCorrectSyntaxe() throws WrongTileSyntaxException{
        String expString = "c-f-c1-f";
        Tile tile = this.tileBuilder.buildTile(expString);
        assertFalse(tile.getEdge(Direction.TOP).hasRoad());
        assertFalse(tile.getEdge(Direction.RIGHT).hasRoad());
        assertFalse(tile.getEdge(Direction.BOTTOM).hasRoad());
        assertFalse(tile.getEdge(Direction.LEFT).hasRoad());

        assertEquals(Topology.CITY, ((EdgeNoRoad) tile.getEdge(Direction.TOP)).getZoneTopology());
        assertEquals(Topology.FIELD, ((EdgeNoRoad) tile.getEdge(Direction.RIGHT)).getZoneTopology());
        assertEquals(Topology.CITY, ((EdgeNoRoad) tile.getEdge(Direction.BOTTOM)).getZoneTopology());
        assertEquals(Topology.FIELD, ((EdgeNoRoad) tile.getEdge(Direction.LEFT)).getZoneTopology());
    }

}
