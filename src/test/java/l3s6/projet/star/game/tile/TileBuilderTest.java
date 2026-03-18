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
    public void testSouthOrientationCorrectSyntaxe() throws WrongTileSyntaxException{
        String expString = "SC1-C1f2-c2-f3c2f4";
        Tile tile = this.tileBuilder.build(expString);
        assertEquals(Orientation.SOUTH, tile.getOrientation());
    }

    @Test
    public void testEastOrientationCorrectSyntaxe() throws WrongTileSyntaxException{
        String expString = "EC1-C1f2-c2-f3c2f4";
        Tile tile = this.tileBuilder.build(expString);
        assertEquals(Orientation.EAST, tile.getOrientation());
    }
    
    @Test
    public void testEdgeSizesCorrectSyntaxe() throws WrongTileSyntaxException{
        String expString = "EC1-C1f2-c2-f3c2f4";
        Tile tile = this.tileBuilder.build(expString);

        assertEquals(1, tile.getEdge(Direction.RIGHT).getSize());
        assertEquals(2, tile.getEdge(Direction.BOTTOM).getSize());
        assertEquals(1, tile.getEdge(Direction.LEFT).getSize());
        assertEquals(3, tile.getEdge(Direction.TOP).getSize());
    }

    @Test
    public void testTopologyCorrectSyntaxe() throws WrongTileSyntaxException{
        String expString = "EC1-C1f2-c2-f3c2f4";
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

        assertTrue(tile.getZoneAt(Direction.RIGHT,0).isNotConnected());
        assertTrue(tile.getZoneAt(Direction.BOTTOM,0).isNotConnected());
        assertTrue(tile.getZoneAt(Direction.LEFT,0).isNotConnected());
        assertTrue(tile.getZoneAt(Direction.TOP,0).isNotConnected());
    }

    @Test
    public void testTileWithConnectionsCorrectSyntaxe() throws WrongTileSyntaxException{
        String expString = "Ec1-f1-c2-f1";
        Tile tile = this.tileBuilder.build(expString);

        assertEquals(1, tile.getZoneAt(Direction.BOTTOM,0).getConnectingZones().size());
        assertTrue(tile.getZoneAt(Direction.BOTTOM,0).isConnectedTo(tile.getZoneAt(Direction.TOP,0)));
        assertEquals(1, tile.getZoneAt(Direction.TOP, 0).getConnectingZones().size());
        assertTrue(tile.getZoneAt(Direction.TOP, 0).isConnectedTo(tile.getZoneAt(Direction.BOTTOM,0)));
        assertTrue(tile.getZoneAt(Direction.RIGHT,0).isNotConnected());
        assertTrue(tile.getZoneAt(Direction.LEFT,0).isNotConnected());
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

    @Test
    public void testShieldSetted() throws WrongTileSyntaxException{
        String expString = "NC1-C1f2-c2-f3c2f4";
        Tile tile = this.tileBuilder.build(expString);

        assertTrue(tile.getZoneAt(Direction.TOP, 0).hasShield());
        assertTrue(tile.getZoneAt(Direction.RIGHT, 0).hasShield());
        assertFalse(tile.getZoneAt(Direction.RIGHT, 1).hasShield());
        assertFalse(tile.getZoneAt(Direction.BOTTOM, 0).hasShield());
        assertFalse(tile.getZoneAt(Direction.LEFT, 0).hasShield());
        assertFalse(tile.getZoneAt(Direction.LEFT, 1).hasShield());
        assertFalse(tile.getZoneAt(Direction.LEFT, 2).hasShield());

    }

    @Test
    public void testAbbeySetted() throws WrongTileSyntaxException{
        String expString = "NAC1-C1f2-c2-f3c2f4";
        Tile tile = this.tileBuilder.build(expString);
        assertTrue(tile.hasAbbey());
    }

    @Test
    public void testTopologyCorrectSyntaxeWithAbbey() throws WrongTileSyntaxException{
        String expString = "EAC1-C1f2-c2-f3c2f4";
        Tile tile = this.tileBuilder.build(expString);

        assertEquals(List.of(Topology.CITY), tile.getEdge(Direction.RIGHT).getZoneTopologies());
        assertEquals(List.of(Topology.CITY, Topology.FIELD), tile.getEdge(Direction.BOTTOM).getZoneTopologies());
        assertEquals(List.of(Topology.CITY), tile.getEdge(Direction.LEFT).getZoneTopologies());
        assertEquals(List.of(Topology.FIELD, Topology.CITY, Topology.FIELD), tile.getEdge(Direction.TOP).getZoneTopologies());
    }

    @Test
    public void testTileWithConnectionsCorrectSyntaxeWithAbbey() throws WrongTileSyntaxException{
        String expString = "EAc1-f1-c2-f1";
        Tile tile = this.tileBuilder.build(expString);

        assertEquals(1, tile.getZoneAt(Direction.BOTTOM,0).getConnectingZones().size());
        assertTrue(tile.getZoneAt(Direction.BOTTOM,0).isConnectedTo(tile.getZoneAt(Direction.TOP,0)));
        assertEquals(1, tile.getZoneAt(Direction.TOP, 0).getConnectingZones().size());
        assertTrue(tile.getZoneAt(Direction.TOP, 0).isConnectedTo(tile.getZoneAt(Direction.BOTTOM,0)));
        assertTrue(tile.getZoneAt(Direction.RIGHT,0).isNotConnected());
        assertTrue(tile.getZoneAt(Direction.LEFT,0).isNotConnected());
    }

}
