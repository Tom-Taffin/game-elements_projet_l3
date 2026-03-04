package l3s6.projet.star.game.tile;

import l3s6.projet.star.game.edge.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TileTest {
    @Test
    public void testGetEdgeWithNorthOrientation(){
        Edge topEdge = new Edge(Topology.CITY);
        Tile tile1 = new Tile(topEdge, 
                    new Edge(Topology.FIELD), 
                    new Edge(Topology.FIELD), 
                    new Edge(Topology.FIELD),
                    Orientation.NORTH);
        assertSame(topEdge, tile1.getEdge(Direction.TOP));
    }

    @Test
    public void testGetEdgeWithEstOrientation(){
        Edge topEdge = new Edge(Topology.CITY);
        Tile tile1 = new Tile(topEdge, 
                            new Edge(Topology.FIELD), 
                            new Edge(Topology.FIELD), 
                            new Edge(Topology.FIELD),
                            Orientation.EAST);
        assertSame(topEdge, tile1.getEdge(Direction.RIGHT));
    }

    @Test
    public void testGetEdgeWithSouthOrientation(){
        Edge topEdge = new Edge(Topology.CITY);
        Tile tile1 = new Tile(topEdge, 
                            new Edge(Topology.FIELD), 
                            new Edge(Topology.FIELD), 
                            new Edge(Topology.FIELD),
                            Orientation.SOUTH);
        assertSame(topEdge, tile1.getEdge(Direction.BOTTOM));
    }

    @Test
    public void testGetEdgeWithWestOrientation(){
        Edge topEdge = new Edge(Topology.CITY);
        Tile tile1 = new Tile(topEdge, 
                            new Edge(Topology.FIELD), 
                            new Edge(Topology.FIELD), 
                            new Edge(Topology.FIELD),
                            Orientation.WEST);
        assertSame(topEdge, tile1.getEdge(Direction.LEFT));
    }
    
    @Test
    public void testCompatibilityOfTwoCompatibleTilesNoRoads(){
        Tile tile1 = new Tile(new Edge(Topology.CITY), 
                            new Edge(Topology.FIELD), 
                            new Edge(Topology.FIELD), 
                            new Edge(Topology.FIELD));

        Tile tile2 = new Tile(new Edge(Topology.FIELD), 
                            new Edge(Topology.FIELD), 
                            new Edge(Topology.CITY), 
                            new Edge(Topology.FIELD));

        assertTrue(tile1.isCompatibleWith(tile2, Direction.TOP));
    }

    @Test
    public void testCompatibilityOfTwoIncompatibleTilesNoRoads(){
        Tile tile1 = new Tile(new Edge(Topology.CITY), 
                            new Edge(Topology.FIELD), 
                            new Edge(Topology.FIELD), 
                            new Edge(Topology.FIELD));

        Tile tile2 = new Tile(new Edge(Topology.FIELD), 
                            new Edge(Topology.FIELD), 
                            new Edge(Topology.FIELD), 
                            new Edge(Topology.FIELD));

        assertFalse(tile1.isCompatibleWith(tile2, Direction.TOP));
    }

    @Test
    public void testCompatibilityOfTwoCompatibleTilesWithRoads(){
        Tile tile1 = new Tile(new Edge(Topology.FIELD, Topology.FIELD), 
                            new Edge(Topology.FIELD, Topology.CITY),
                            new Edge(Topology.FIELD, Topology.FIELD), 
                            new Edge(Topology.CITY, Topology.FIELD));

        Tile tile2 = new Tile(new Edge(Topology.CITY, Topology.CITY), 
                            new Edge(Topology.FIELD, Topology.FIELD),
                            new Edge(Topology.FIELD, Topology.CITY), 
                            new Edge(Topology.CITY, Topology.FIELD));

        assertTrue(tile1.isCompatibleWith(tile2, Direction.RIGHT));
    }

    @Test
    public void testCompatibilityOfTwoIncompatibleTilesWithRoads(){
        Tile tile1 = new Tile(new Edge(Topology.FIELD, Topology.FIELD), 
                            new Edge(Topology.CITY, Topology.FIELD),
                            new Edge(Topology.FIELD, Topology.FIELD), 
                            new Edge(Topology.CITY, Topology.FIELD));

        Tile tile2 = new Tile(new Edge(Topology.CITY, Topology.CITY), 
                            new Edge(Topology.FIELD, Topology.FIELD),
                            new Edge(Topology.FIELD, Topology.CITY), 
                            new Edge(Topology.CITY, Topology.FIELD));

        assertFalse(tile1.isCompatibleWith(tile2, Direction.RIGHT));
    }

    @Test
    public void testCompatibilityOfTwoIncompatibleTilesWithAndWithoutRoad(){
        Tile tile1 = new Tile(new Edge(Topology.FIELD, Topology.FIELD), 
                            new Edge(Topology.FIELD, Topology.CITY),
                            new Edge(Topology.FIELD), 
                            new Edge(Topology.FIELD, Topology.FIELD));

        Tile tile2 = new Tile(new Edge(Topology.FIELD, Topology.FIELD), 
                            new Edge(Topology.FIELD),
                            new Edge(Topology.FIELD, Topology.FIELD), 
                            new Edge(Topology.FIELD, Topology.FIELD));

        assertFalse(tile1.isCompatibleWith(tile2, Direction.LEFT));
    }

    @Test
    public void testCompatibilityOfTwoCompatibleTilesWithAndWithoutRoad(){
        Tile tile1 = new Tile(new Edge(Topology.FIELD, Topology.FIELD), 
                            new Edge(Topology.FIELD, Topology.CITY),
                            new Edge(Topology.FIELD), 
                            new Edge(Topology.FIELD));

        Tile tile2 = new Tile(new Edge(Topology.FIELD, Topology.FIELD), 
                            new Edge(Topology.FIELD),
                            new Edge(Topology.FIELD, Topology.FIELD), 
                            new Edge(Topology.FIELD, Topology.FIELD));

        assertTrue(tile1.isCompatibleWith(tile2, Direction.LEFT));
    }

    @Test
    public void testCompatibilityOfTwoCompatibleTilesWithRotation(){
        Tile tile1 = new Tile(new Edge(Topology.FIELD), 
                            new Edge(Topology.FIELD), 
                            new Edge(Topology.CITY), 
                            new Edge(Topology.FIELD),
                            Orientation.SOUTH);

        Tile tile2 = new Tile(new Edge(Topology.FIELD), 
                            new Edge(Topology.CITY),  
                            new Edge(Topology.FIELD),
                            new Edge(Topology.FIELD),
                            Orientation.EAST);

        assertTrue(tile1.isCompatibleWith(tile2, Direction.TOP));
    }

    @Test
    public void testCompatibilityOfTwoIncompatibleTilesWithRotation(){
        Tile tile1 = new Tile(new Edge(Topology.FIELD), 
                            new Edge(Topology.FIELD), 
                            new Edge(Topology.CITY), 
                            new Edge(Topology.FIELD),
                            Orientation.SOUTH);

        Tile tile2 = new Tile(new Edge(Topology.FIELD), 
                            new Edge(Topology.FIELD),  
                            new Edge(Topology.FIELD),
                            new Edge(Topology.FIELD),
                            Orientation.EAST);

        assertFalse(tile1.isCompatibleWith(tile2, Direction.TOP));
    }

    @Test
    public void testCompatibilityOfTwoIncompatibleTilesWithAndWithoutRoadWithRotation(){
        Tile tile1 = new Tile(new Edge(Topology.FIELD, Topology.FIELD), 
                            new Edge(Topology.FIELD, Topology.CITY),
                            new Edge(Topology.FIELD), 
                            new Edge(Topology.FIELD, Topology.FIELD),
                            Orientation.WEST);

        Tile tile2 = new Tile(new Edge(Topology.FIELD, Topology.FIELD), 
                            new Edge(Topology.FIELD),
                            new Edge(Topology.FIELD, Topology.FIELD), 
                            new Edge(Topology.FIELD, Topology.FIELD));

        assertFalse(tile1.isCompatibleWith(tile2, Direction.LEFT));
    }

    @Test
    public void testCompatibilityOfTwoCompatibleTilesWithAndWithoutRoadWithRotation(){
        Tile tile1 = new Tile(new Edge(Topology.FIELD), 
                            new Edge(Topology.FIELD, Topology.CITY),
                            new Edge(Topology.FIELD), 
                            new Edge(Topology.FIELD, Topology.FIELD),
                            Orientation.WEST);

        Tile tile2 = new Tile(new Edge(Topology.FIELD, Topology.FIELD), 
                            new Edge(Topology.FIELD),
                            new Edge(Topology.FIELD, Topology.FIELD), 
                            new Edge(Topology.FIELD, Topology.FIELD));
        
        assertTrue(tile1.isCompatibleWith(tile2, Direction.LEFT));
    }
     
    @Test
    public void testConnectTwoRoadsEdgesWithRoads() throws NoRoadException{
        Tile tile = new Tile(new Edge(Topology.CITY, Topology.FIELD), 
                            new Edge(Topology.FIELD, Topology.FIELD),
                            new Edge(Topology.FIELD, Topology.FIELD), 
                            new Edge(Topology.FIELD, Topology.FIELD));

        assertThrows(NoRoadException.class, () -> {tile.getExitRoadDirection(Direction.TOP);});

        tile.connectRoad(Direction.TOP, Direction.BOTTOM);

        assertEquals(Direction.BOTTOM, tile.getExitRoadDirection(Direction.TOP));
        assertEquals(Direction.TOP, tile.getExitRoadDirection(Direction.BOTTOM));

    }

    @Test
    public void testConnectTwoRoadsWithEdgesWithoutRoads() {
        Tile tile = new Tile(new Edge(Topology.FIELD), 
                            new Edge(Topology.FIELD), 
                            new Edge(Topology.FIELD), 
                            new Edge(Topology.FIELD));

        assertThrows(NoRoadException.class, () -> {
            tile.connectRoad(Direction.TOP, Direction.BOTTOM);
        });

    }

    @Test
    public void testConnectTwoRoadsWithOneEdgeWithoutRoad() {
        Tile tile = new Tile(new Edge(Topology.FIELD), 
                            new Edge(Topology.FIELD, Topology.FIELD), 
                            new Edge(Topology.FIELD, Topology.FIELD), 
                            new Edge(Topology.FIELD, Topology.FIELD));

        assertThrows(NoRoadException.class, () -> {
            tile.connectRoad(Direction.TOP, Direction.BOTTOM);
        });

    }

    @Test
    public void testConnectTwoRoadsEdgesWithRoadsWithRotation() throws NoRoadException{
        Tile tile = new Tile(new Edge(Topology.CITY, Topology.FIELD), 
                            new Edge(Topology.FIELD, Topology.FIELD),
                            new Edge(Topology.FIELD, Topology.FIELD), 
                            new Edge(Topology.FIELD, Topology.FIELD),
                            Orientation.EAST);
        
        assertThrows(NoRoadException.class, () -> {tile.getExitRoadDirection(Direction.TOP);});

        tile.connectRoad(Direction.TOP, Direction.BOTTOM);

        assertEquals(Direction.BOTTOM, tile.getExitRoadDirection(Direction.TOP));
        assertEquals(Direction.TOP, tile.getExitRoadDirection(Direction.BOTTOM));

    }

    @Test
    public void testConnectTwoRoadsWithOneEdgeWithoutRoadWithRotation() {
        Tile tile = new Tile(new Edge(Topology.CITY, Topology.FIELD), 
                            new Edge(Topology.FIELD), 
                            new Edge(Topology.FIELD, Topology.FIELD), 
                            new Edge(Topology.FIELD, Topology.FIELD),
                            Orientation.EAST);

        assertThrows(NoRoadException.class, () -> {
            tile.connectRoad(Direction.TOP, Direction.BOTTOM);
        });

    }

    @Test
    public void testGetExitRoadWithEdgeWithoutRoad() {
        Tile tile = new Tile(new Edge(Topology.CITY), 
                            new Edge(Topology.FIELD), 
                            new Edge(Topology.FIELD), 
                            new Edge(Topology.FIELD));

        assertThrows(NoRoadException.class, () -> {tile.getExitRoadDirection(Direction.TOP);});

    }

    @Test
    public void testTerminateRoadWithEdge() throws NoRoadException{
        Tile tile = new Tile(new Edge(Topology.FIELD, Topology.FIELD), 
                            new Edge(Topology.FIELD, Topology.FIELD),
                            new Edge(Topology.FIELD, Topology.FIELD), 
                            new Edge(Topology.FIELD, Topology.FIELD));
        
        assertFalse(tile.isRoadTerminated(Direction.TOP));
        
        tile.terminateRoad(Direction.TOP);

        assertTrue(tile.isRoadTerminated(Direction.TOP));
    }

    @Test
    public void testTerminateRoadWithEdgeWithoutRoad() {
        Tile tile = new Tile(new Edge(Topology.CITY), 
                            new Edge(Topology.FIELD), 
                            new Edge(Topology.FIELD), 
                            new Edge(Topology.FIELD));

        assertThrows(NoRoadException.class, () -> {tile.terminateRoad(Direction.TOP);});

    }
}