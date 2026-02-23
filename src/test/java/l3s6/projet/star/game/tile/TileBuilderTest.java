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
        String expString = "Ec1-f3-c2-f3";
        Tile tile = this.tileBuilder.build(expString);

        assertEquals(Orientation.EAST, tile.getOrientation());

        assertFalse(tile.getEdge(Direction.TOP).hasRoad());
        assertFalse(tile.getEdge(Direction.RIGHT).hasRoad());
        assertFalse(tile.getEdge(Direction.BOTTOM).hasRoad());
        assertFalse(tile.getEdge(Direction.LEFT).hasRoad());

        assertEquals(Topology.CITY, ((EdgeNoRoad) tile.getEdge(Direction.TOP.toRight())).getZoneTopology());
        assertEquals(Topology.FIELD, ((EdgeNoRoad) tile.getEdge(Direction.RIGHT.toRight())).getZoneTopology());
        assertEquals(Topology.CITY, ((EdgeNoRoad) tile.getEdge(Direction.BOTTOM.toRight())).getZoneTopology());
        assertEquals(Topology.FIELD, ((EdgeNoRoad) tile.getEdge(Direction.LEFT.toRight())).getZoneTopology());
    }

    @Test
    public void testConnectionsOfBuildTileWithoutRoadCorrectSyntaxe() throws WrongTileSyntaxException{
        String expString = "Ec1-f3-c2-f3";
        Tile tile = this.tileBuilder.build(expString);

        assertEquals(Orientation.EAST, tile.getOrientation());

        assertTrue(((EdgeNoRoad) tile.getEdge(Direction.TOP.toRight())).isZoneFinished());
        assertTrue(((EdgeNoRoad) tile.getEdge(Direction.RIGHT.toRight())).getConnectingZones().contains(((EdgeNoRoad) tile.getEdge(Direction.LEFT.toRight())).getZone()));
        assertTrue(((EdgeNoRoad) tile.getEdge(Direction.BOTTOM.toRight())).isZoneFinished());
        assertTrue(((EdgeNoRoad) tile.getEdge(Direction.LEFT.toRight())).getConnectingZones().contains(((EdgeNoRoad) tile.getEdge(Direction.RIGHT.toRight())).getZone()));

    }

    @Test
    public void testEdgesofBuildTileWithRoadCorrectSyntaxe() throws WrongTileSyntaxException{
        String expString = "Sc1-f2r1f3-f0r1f2-c4";
        Tile tile = this.tileBuilder.build(expString);

        assertEquals(Orientation.SOUTH, tile.getOrientation());

        assertFalse(tile.getEdge(Direction.TOP.toOpposite()).hasRoad());
        assertTrue(tile.getEdge(Direction.RIGHT.toOpposite()).hasRoad());
        assertTrue(tile.getEdge(Direction.BOTTOM.toOpposite()).hasRoad());
        assertFalse(tile.getEdge(Direction.LEFT.toOpposite()).hasRoad());

        assertEquals(Topology.CITY, ((EdgeNoRoad) tile.getEdge(Direction.TOP.toOpposite())).getZoneTopology());
        assertEquals(Topology.FIELD, ((EdgeWithRoad) tile.getEdge(Direction.RIGHT.toOpposite())).getZone1Topology());
        assertEquals(Topology.FIELD, ((EdgeWithRoad) tile.getEdge(Direction.RIGHT.toOpposite())).getZone2Topology());
        assertEquals(Topology.FIELD, ((EdgeWithRoad) tile.getEdge(Direction.BOTTOM.toOpposite())).getZone1Topology());
        assertEquals(Topology.FIELD, ((EdgeWithRoad) tile.getEdge(Direction.BOTTOM.toOpposite())).getZone2Topology());
        assertEquals(Topology.CITY, ((EdgeNoRoad) tile.getEdge(Direction.LEFT.toOpposite())).getZoneTopology());
    }

    @Test
    public void testZoneConnectionsOfBuildTileWithRoadCorrectSyntaxe() throws WrongTileSyntaxException{
        String expString = "Sc1-f2r1f3-f0r1f2-c4";
        Tile tile = this.tileBuilder.build(expString);

        assertEquals(Orientation.SOUTH, tile.getOrientation());

        assertTrue(((EdgeNoRoad) tile.getEdge(Direction.TOP.toOpposite())).isZoneFinished());
        assertTrue(((EdgeWithRoad) tile.getEdge(Direction.RIGHT.toOpposite())).getZone1ConnectingZones().contains(((EdgeWithRoad) tile.getEdge(Direction.BOTTOM.toOpposite())).getZone2()));
        assertTrue(((EdgeWithRoad) tile.getEdge(Direction.BOTTOM.toOpposite())).getZone2ConnectingZones().contains(((EdgeWithRoad) tile.getEdge(Direction.RIGHT.toOpposite())).getZone1()));
        assertTrue(((EdgeWithRoad) tile.getEdge(Direction.RIGHT.toOpposite())).isZone2Finished());
        assertTrue(((EdgeWithRoad) tile.getEdge(Direction.BOTTOM.toOpposite())).isZone1Finished());
        assertTrue(((EdgeNoRoad) tile.getEdge(Direction.LEFT.toOpposite())).isZoneFinished());
    }

    @Test
    public void testRoadConnectionsOfBuildTileWithRoadCorrectSyntaxe() throws WrongTileSyntaxException, NoRoadException{
        String expString = "Sc1r2c5-f2r1f3-f0r1f2-c4";
        Tile tile = this.tileBuilder.build(expString);

        assertEquals(Orientation.SOUTH, tile.getOrientation());

        assertTrue(tile.isRoadTerminated(Direction.TOP.getNewDirection(tile.getOrientation())));
        assertFalse(tile.isRoadTerminated(Direction.RIGHT.getNewDirection(tile.getOrientation())));
        assertFalse(tile.isRoadTerminated(Direction.BOTTOM.getNewDirection(tile.getOrientation())));
        assertEquals(Direction.BOTTOM.getNewDirection(tile.getOrientation()),
                    tile.getExitRoadDirection(Direction.RIGHT.getNewDirection(tile.getOrientation())));
        assertEquals(Direction.RIGHT.getNewDirection(tile.getOrientation()),
                    tile.getExitRoadDirection(Direction.BOTTOM.getNewDirection(tile.getOrientation())));
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
    public void testBuildTileWith2RoadsOnEdge(){
        String expString = "Ec1-f2r1f3r2f6-f0r3f2-c4";
        assertThrows(WrongTileSyntaxException.class, () -> {
            this.tileBuilder.build(expString);
        });
    }

    @Test
    public void testBuildTileWithDifferentsTopologiesConnections(){
        String expString = "Ec1-c3-c2-f3";
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
    public void testBuildTileWith3RoadConnected(){
        String expString = "Sc1r1c5-f2r1f3-f0r1f2-c4\"";
        assertThrows(WrongTileSyntaxException.class, () -> {
            this.tileBuilder.build(expString);
        });
    }
    

}
