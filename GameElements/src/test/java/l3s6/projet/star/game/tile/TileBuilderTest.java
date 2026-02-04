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
    public void testEdgdesOfBuildTileWithoutRoadCorrectSyntaxe() throws WrongTileSyntaxException{
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
    public void testConnectionsOfBuildTileWithoutRoadCorrectSyntaxe() throws WrongTileSyntaxException{
        String expString = "c1-f3-c2-f3";
        Tile tile = this.tileBuilder.build(expString);

        assertTrue(((EdgeNoRoad) tile.getEdge(Direction.TOP)).getZone().isFinished());
        assertTrue(((EdgeNoRoad) tile.getEdge(Direction.RIGHT)).getZone().getConnectingZones().contains(((EdgeNoRoad) tile.getEdge(Direction.LEFT)).getZone()));
        assertTrue(((EdgeNoRoad) tile.getEdge(Direction.BOTTOM)).getZone().isFinished());
        assertTrue(((EdgeNoRoad) tile.getEdge(Direction.LEFT)).getZone().getConnectingZones().contains(((EdgeNoRoad) tile.getEdge(Direction.RIGHT)).getZone()));

    }

    @Test
    public void testEdgesofBuildTileWithRoadCorrectSyntaxe() throws WrongTileSyntaxException{
        String expString = "c1-f2rf3-f0rf2-c4";
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
    public void testConnectionsOfBuildTileWithRoadCorrectSyntaxe() throws WrongTileSyntaxException{
        String expString = "c1-f2rf3-f0rf2-c4";
        Tile tile = this.tileBuilder.build(expString);

        assertTrue(((EdgeNoRoad) tile.getEdge(Direction.TOP)).getZone().isFinished());
        assertTrue(((EdgeWithRoad) tile.getEdge(Direction.RIGHT)).getZone1().getConnectingZones().contains(((EdgeWithRoad) tile.getEdge(Direction.BOTTOM)).getZone2()));
        assertTrue(((EdgeWithRoad) tile.getEdge(Direction.BOTTOM)).getZone2().getConnectingZones().contains(((EdgeWithRoad) tile.getEdge(Direction.RIGHT)).getZone1()));
        assertTrue(((EdgeWithRoad) tile.getEdge(Direction.RIGHT)).getZone2().isFinished());
        assertTrue(((EdgeWithRoad) tile.getEdge(Direction.BOTTOM)).getZone1().isFinished());
        assertTrue(((EdgeNoRoad) tile.getEdge(Direction.LEFT)).getZone().isFinished());

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
