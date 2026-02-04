package l3s6.projet.star.game.tile;

import org.junit.jupiter.api.Test;

import l3s6.projet.star.game.edge.EdgeNoRoad;
import l3s6.projet.star.game.edge.EdgeWithRoad;
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
        String expString = "c1-f3-c2-f3";
        Tile tile = this.tileBuilder.build(expString);
        assertFalse(tile.getEdge(Direction.TOP).hasRoad());
        assertFalse(tile.getEdge(Direction.RIGHT).hasRoad());
        assertFalse(tile.getEdge(Direction.BOTTOM).hasRoad());
        assertFalse(tile.getEdge(Direction.LEFT).hasRoad());

        assertEquals(Topology.CITY, ((EdgeNoRoad) tile.getEdge(Direction.TOP)).getZoneTopology());
        assertEquals(Topology.FIELD, ((EdgeNoRoad) tile.getEdge(Direction.RIGHT)).getZoneTopology());
        assertEquals(Topology.CITY, ((EdgeNoRoad) tile.getEdge(Direction.BOTTOM)).getZoneTopology());
        assertEquals(Topology.FIELD, ((EdgeNoRoad) tile.getEdge(Direction.LEFT)).getZoneTopology());
    }

    @Test
    public void testBuildTileWithRoadCorrectSyntaxe() throws WrongTileSyntaxException{
        String expString = "c1-f2rf0-f0rf2-c1 ";
        Tile tile = this.tileBuilder.build(expString);
        assertFalse(tile.getEdge(Direction.TOP).hasRoad());
        assertTrue(tile.getEdge(Direction.RIGHT).hasRoad());
        assertTrue(tile.getEdge(Direction.BOTTOM).hasRoad());
        assertFalse(tile.getEdge(Direction.LEFT).hasRoad());

        assertEquals(Topology.CITY, ((EdgeNoRoad) tile.getEdge(Direction.TOP)).getZoneTopology());
        assertEquals(Topology.FIELD, ((EdgeWithRoad) tile.getEdge(Direction.RIGHT)).getZone1Topology());
        assertEquals(Topology.FIELD, ((EdgeWithRoad) tile.getEdge(Direction.RIGHT)).getZone2Topology());
        assertEquals(Topology.FIELD, ((EdgeWithRoad) tile.getEdge(Direction.BOTTOM)).getZone1Topology());
        assertEquals(Topology.FIELD, ((EdgeWithRoad) tile.getEdge(Direction.BOTTOM)).getZone2Topology());
        assertEquals(Topology.CITY, ((EdgeNoRoad) tile.getEdge(Direction.LEFT)).getZoneTopology());
    }

    @Test
    public void testBuildTileInCorrectTopologySyntaxe(){
        String expString = "c0-t2-c1-f3";
        assertThrows(WrongTileSyntaxException.class, () -> {
            this.tileBuilder.build(expString);
        });
    }

    @Test
    public void testBuildTileInCorrectFormSyntaxe(){
        String expString = "c0-fc1-f2";
        assertThrows(WrongTileSyntaxException.class, () -> {
            this.tileBuilder.build(expString);
        });
    }
    

}
