package l3s6.projet.star.game.tile;

import org.junit.jupiter.api.Test;

import l3s6.projet.star.game.edge.Topology;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;

public class TileBuilderTest {
    
    private TileBuilder tileBuilder;
    
    @BeforeEach
    public void setup(){
        this.tileBuilder = new TileBuilder();
    }
    
    @Test
    public void testEdgeSizesCorrectSyntaxe() throws WrongTileSyntaxException{
        String expString = "Ec1-c1f2-c2-f3c2f4";
        Tile tile = this.tileBuilder.build(expString);

        assertEquals(Orientation.EAST, tile.getOrientation());

        assertEquals(1, tile.getEdge(Direction.RIGHT).getSize());
        assertEquals(2, tile.getEdge(Direction.BOTTOM).getSize());
        assertEquals(1, tile.getEdge(Direction.LEFT).getSize());
        assertEquals(3, tile.getEdge(Direction.TOP).getSize());
    }

    @Test
    public void testTopologyCorrectSyntaxe() throws WrongTileSyntaxException{
        String expString = "Ec1-c1f2-c2-f3c2f4";
        Tile tile = this.tileBuilder.build(expString);

        assertEquals(List.of(Topology.CITY), tile.getEdge(Direction.RIGHT).getZoneTopologies());
        assertEquals(List.of(Topology.CITY, Topology.FIELD), tile.getEdge(Direction.BOTTOM).getZoneTopologies());
        assertEquals(List.of(Topology.CITY), tile.getEdge(Direction.LEFT).getZoneTopologies());
        assertEquals(List.of(Topology.FIELD, Topology.CITY, Topology.FIELD), tile.getEdge(Direction.TOP).getZoneTopologies());
    }

    @Test
    public void testTileWithoutConnectionsCorrectSyntaxe() throws WrongTileSyntaxException{
        String expString = "Ec1-f1-c2-f2";
        Tile tile = this.tileBuilder.build(expString);

        assertTrue(tile.getEdge(Direction.RIGHT).getZoneAt(0).getConnectingZones().isEmpty());
        assertTrue(tile.getEdge(Direction.BOTTOM).getZoneAt(0).getConnectingZones().isEmpty());
        assertTrue(tile.getEdge(Direction.LEFT).getZoneAt(0).getConnectingZones().isEmpty());
        assertTrue(tile.getEdge(Direction.TOP).getZoneAt(0).getConnectingZones().isEmpty());
    }

    @Test
    public void testTileWithConnectionsCorrectSyntaxe() throws WrongTileSyntaxException{
        String expString = "Ec1-f1-c2-f1";
        Tile tile = this.tileBuilder.build(expString);

        assertEquals(1, tile.getEdge(Direction.BOTTOM).getZoneAt(0).getConnectingZones().size());
        assertTrue(tile.getEdge(Direction.BOTTOM).getZoneAt(0).isConnectedTo(tile.getEdge(Direction.TOP).getZoneAt(0)));
        assertEquals(1, tile.getEdge(Direction.TOP).getZoneAt(0).getConnectingZones().size());
        assertTrue(tile.getEdge(Direction.TOP).getZoneAt(0).isConnectedTo(tile.getEdge(Direction.BOTTOM).getZoneAt(0)));
    }

    @Test
    public void testBuildTileInCorrectTopologySyntaxe(){
        String expString = "Ec0-t2-c1-f3";
        assertThrows(WrongTileSyntaxException.class, () -> {
            this.tileBuilder.build(expString);
        });
    }

    @Test
    public void testBuildTileWith3Edges(){
        String expString = "Ec0-fc1-f2";
        assertThrows(WrongTileSyntaxException.class, () -> {
            this.tileBuilder.build(expString);
        });
    }

    @Test
    public void testBuildTileWithWrongOrientation(){
        String expString = "c1-c3-c2-f3";
        assertThrows(WrongTileSyntaxException.class, () -> {
            this.tileBuilder.build(expString);
        });
    }

}
