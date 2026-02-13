package l3s6.projet.star.game.tile;

import l3s6.projet.star.game.edge.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TileTest {
    @Test
    public void testGetEdgeWithNorthOrientation(){
        Edge topEdge = new EdgeNoRoad(new Zone(Topology.CITY));
        Tile tile1 = new Tile(topEdge, new EdgeNoRoad(new Zone(Topology.FIELD)), new EdgeNoRoad(new Zone(Topology.FIELD)), new EdgeNoRoad(new Zone(Topology.FIELD)));
        tile1.changeOrientation(Orientation.NORTH);
        assertSame(topEdge, tile1.getEdge(Direction.TOP));
    }

    @Test
    public void testGetEdgeWithEstOrientation(){
        Edge topEdge = new EdgeNoRoad(new Zone(Topology.CITY));
        Tile tile1 = new Tile(topEdge, new EdgeNoRoad(new Zone(Topology.FIELD)), new EdgeNoRoad(new Zone(Topology.FIELD)), new EdgeNoRoad(new Zone(Topology.FIELD)));
        tile1.changeOrientation(Orientation.EAST);
        assertSame(topEdge, tile1.getEdge(Direction.RIGHT));
    }

    @Test
    public void testGetEdgeWithSouthOrientation(){
        Edge topEdge = new EdgeNoRoad(new Zone(Topology.CITY));
        Tile tile1 = new Tile(topEdge, new EdgeNoRoad(new Zone(Topology.FIELD)), new EdgeNoRoad(new Zone(Topology.FIELD)), new EdgeNoRoad(new Zone(Topology.FIELD)));
        tile1.changeOrientation(Orientation.SOUTH);
        assertSame(topEdge, tile1.getEdge(Direction.BOTTOM));
    }

    @Test
    public void testGetEdgeWithWestOrientation(){
        Edge topEdge = new EdgeNoRoad(new Zone(Topology.CITY));
        Tile tile1 = new Tile(topEdge, new EdgeNoRoad(new Zone(Topology.FIELD)), new EdgeNoRoad(new Zone(Topology.FIELD)), new EdgeNoRoad(new Zone(Topology.FIELD)));
        tile1.changeOrientation(Orientation.WEST);
        assertSame(topEdge, tile1.getEdge(Direction.LEFT));
    }
    
    @Test
    public void testCompatibilityOfTwoCompatibleTilesNoRoads(){
        Tile tile1 = new Tile(new EdgeNoRoad(new Zone(Topology.CITY)), 
                            new EdgeNoRoad(new Zone(Topology.FIELD)), 
                            new EdgeNoRoad(new Zone(Topology.FIELD)), 
                            new EdgeNoRoad(new Zone(Topology.FIELD)));

        Tile tile2 = new Tile(new EdgeNoRoad(new Zone(Topology.FIELD)), 
                            new EdgeNoRoad(new Zone(Topology.FIELD)), 
                            new EdgeNoRoad(new Zone(Topology.CITY)), 
                            new EdgeNoRoad(new Zone(Topology.FIELD)));

        assertTrue(tile1.isCompatibleWith(tile2, Direction.TOP));
    }

    @Test
    public void testCompatibilityOfTwoIncompatibleTilesNoRoads(){
        Tile tile1 = new Tile(new EdgeNoRoad(new Zone(Topology.CITY)), 
                            new EdgeNoRoad(new Zone(Topology.FIELD)), 
                            new EdgeNoRoad(new Zone(Topology.FIELD)), 
                            new EdgeNoRoad(new Zone(Topology.FIELD)));

        Tile tile2 = new Tile(new EdgeNoRoad(new Zone(Topology.FIELD)), 
                            new EdgeNoRoad(new Zone(Topology.FIELD)), 
                            new EdgeNoRoad(new Zone(Topology.FIELD)), 
                            new EdgeNoRoad(new Zone(Topology.FIELD)));

        assertFalse(tile1.isCompatibleWith(tile2, Direction.TOP));
    }

    @Test
    public void testCompatibilityOfTwoCompatibleTilesWithRoads(){
        Tile tile1 = new Tile(new EdgeWithRoad(new Zone(Topology.FIELD), new Zone(Topology.FIELD)), 
                            new EdgeWithRoad(new Zone(Topology.FIELD), new Zone(Topology.CITY)),
                            new EdgeWithRoad(new Zone(Topology.FIELD), new Zone(Topology.FIELD)), 
                            new EdgeWithRoad(new Zone(Topology.CITY), new Zone(Topology.FIELD)));

        Tile tile2 = new Tile(new EdgeWithRoad(new Zone(Topology.CITY), new Zone(Topology.CITY)), 
                            new EdgeWithRoad(new Zone(Topology.FIELD), new Zone(Topology.FIELD)),
                            new EdgeWithRoad(new Zone(Topology.FIELD), new Zone(Topology.CITY)), 
                            new EdgeWithRoad(new Zone(Topology.CITY), new Zone(Topology.FIELD)));

        assertTrue(tile1.isCompatibleWith(tile2, Direction.RIGHT));
    }

    @Test
    public void testCompatibilityOfTwoIncompatibleTilesWithRoads(){
        Tile tile1 = new Tile(new EdgeWithRoad(new Zone(Topology.FIELD), new Zone(Topology.FIELD)), 
                            new EdgeWithRoad(new Zone(Topology.CITY), new Zone(Topology.FIELD)),
                            new EdgeWithRoad(new Zone(Topology.FIELD), new Zone(Topology.FIELD)), 
                            new EdgeWithRoad(new Zone(Topology.CITY), new Zone(Topology.FIELD)));

        Tile tile2 = new Tile(new EdgeWithRoad(new Zone(Topology.CITY), new Zone(Topology.CITY)), 
                            new EdgeWithRoad(new Zone(Topology.FIELD), new Zone(Topology.FIELD)),
                            new EdgeWithRoad(new Zone(Topology.FIELD), new Zone(Topology.CITY)), 
                            new EdgeWithRoad(new Zone(Topology.CITY), new Zone(Topology.FIELD)));

        assertFalse(tile1.isCompatibleWith(tile2, Direction.RIGHT));
    }

    @Test
    public void testCompatibilityOfTwoIncompatibleTilesWithAndWithoutRoad(){
        Tile tile1 = new Tile(new EdgeWithRoad(new Zone(Topology.FIELD), new Zone(Topology.FIELD)), 
                            new EdgeWithRoad(new Zone(Topology.FIELD), new Zone(Topology.CITY)),
                            new EdgeNoRoad(new Zone(Topology.FIELD)), 
                            new EdgeWithRoad(new Zone(Topology.FIELD), new Zone(Topology.FIELD)));

        Tile tile2 = new Tile(new EdgeWithRoad(new Zone(Topology.FIELD), new Zone(Topology.FIELD)), 
                            new EdgeNoRoad(new Zone(Topology.FIELD)),
                            new EdgeWithRoad(new Zone(Topology.FIELD), new Zone(Topology.FIELD)), 
                            new EdgeWithRoad(new Zone(Topology.FIELD), new Zone(Topology.FIELD)));

        assertFalse(tile1.isCompatibleWith(tile2, Direction.LEFT));
    }

    @Test
    public void testCompatibilityOfTwoCompatibleTilesWithAndWithoutRoad(){
        Tile tile1 = new Tile(new EdgeWithRoad(new Zone(Topology.FIELD), new Zone(Topology.FIELD)), 
                            new EdgeWithRoad(new Zone(Topology.FIELD), new Zone(Topology.CITY)),
                            new EdgeNoRoad(new Zone(Topology.FIELD)), 
                            new EdgeNoRoad(new Zone(Topology.FIELD)));

        Tile tile2 = new Tile(new EdgeWithRoad(new Zone(Topology.FIELD), new Zone(Topology.FIELD)), 
                            new EdgeNoRoad(new Zone(Topology.FIELD)),
                            new EdgeWithRoad(new Zone(Topology.FIELD), new Zone(Topology.FIELD)), 
                            new EdgeWithRoad(new Zone(Topology.FIELD), new Zone(Topology.FIELD)));

        assertTrue(tile1.isCompatibleWith(tile2, Direction.LEFT));
    }

    @Test
    public void testCompatibilityOfTwoCompatibleTilesWithRotation(){
        Tile tile1 = new Tile(new EdgeNoRoad(new Zone(Topology.FIELD)), 
                            new EdgeNoRoad(new Zone(Topology.FIELD)), 
                            new EdgeNoRoad(new Zone(Topology.CITY)), 
                            new EdgeNoRoad(new Zone(Topology.FIELD)));

        Tile tile2 = new Tile(new EdgeNoRoad(new Zone(Topology.FIELD)), 
                            new EdgeNoRoad(new Zone(Topology.CITY)),  
                            new EdgeNoRoad(new Zone(Topology.FIELD)),
                            new EdgeNoRoad(new Zone(Topology.FIELD)));

        tile1.changeOrientation(Orientation.SOUTH);
        tile2.changeOrientation(Orientation.EAST);

        assertTrue(tile1.isCompatibleWith(tile2, Direction.TOP));
    }

    @Test
    public void testCompatibilityOfTwoIncompatibleTilesWithRotation(){
        Tile tile1 = new Tile(new EdgeNoRoad(new Zone(Topology.FIELD)), 
                            new EdgeNoRoad(new Zone(Topology.FIELD)), 
                            new EdgeNoRoad(new Zone(Topology.CITY)), 
                            new EdgeNoRoad(new Zone(Topology.FIELD)));

        Tile tile2 = new Tile(new EdgeNoRoad(new Zone(Topology.FIELD)), 
                            new EdgeNoRoad(new Zone(Topology.FIELD)),  
                            new EdgeNoRoad(new Zone(Topology.FIELD)),
                            new EdgeNoRoad(new Zone(Topology.FIELD)));

        tile1.changeOrientation(Orientation.SOUTH);
        tile2.changeOrientation(Orientation.EAST);

        assertFalse(tile1.isCompatibleWith(tile2, Direction.TOP));
    }

    @Test
    public void testConnectTwoRoadsEdgesWithRoads() throws ConnectionRoadToEdgeWithNoRoadException, NoExitRoadException{
        Tile tile = new Tile(new EdgeWithRoad(new Zone(Topology.CITY), new Zone(Topology.FIELD)), 
                            new EdgeWithRoad(new Zone(Topology.FIELD), new Zone(Topology.FIELD)),
                            new EdgeWithRoad(new Zone(Topology.FIELD), new Zone(Topology.FIELD)), 
                            new EdgeWithRoad(new Zone(Topology.FIELD), new Zone(Topology.FIELD)));

        assertThrows(NoExitRoadException.class, () -> {tile.getExitRoadDirection(Direction.TOP);});

        tile.connectRoad(Direction.TOP, Direction.BOTTOM);

        assertEquals(Direction.BOTTOM, tile.getExitRoadDirection(Direction.TOP));
        assertEquals(Direction.TOP, tile.getExitRoadDirection(Direction.BOTTOM));

    }

    @Test
    public void testConnectTwoRoadsWithEdgesWithoutRoads() {
        Tile tile = new Tile(new EdgeNoRoad(new Zone(Topology.FIELD)), 
                            new EdgeNoRoad(new Zone(Topology.FIELD)), 
                            new EdgeNoRoad(new Zone(Topology.FIELD)), 
                            new EdgeNoRoad(new Zone(Topology.FIELD)));

        assertThrows(ConnectionRoadToEdgeWithNoRoadException.class, () -> {
            tile.connectRoad(Direction.TOP, Direction.BOTTOM);
        });

    }

    @Test
    public void testConnectTwoRoadsWithOneEdgeWithoutRoad() {
        Tile tile = new Tile(new EdgeNoRoad(new Zone(Topology.FIELD)), 
                            new EdgeWithRoad(new Zone(Topology.FIELD), new Zone(Topology.FIELD)), 
                            new EdgeWithRoad(new Zone(Topology.FIELD), new Zone(Topology.FIELD)), 
                            new EdgeWithRoad(new Zone(Topology.FIELD), new Zone(Topology.FIELD)));

        assertThrows(ConnectionRoadToEdgeWithNoRoadException.class, () -> {
            tile.connectRoad(Direction.TOP, Direction.BOTTOM);
        });

    }

    @Test
    public void testGetExitRoadWithEdgeWithoutRoad() {
        Tile tile = new Tile(new EdgeNoRoad(new Zone(Topology.CITY)), 
                            new EdgeNoRoad(new Zone(Topology.FIELD)), 
                            new EdgeNoRoad(new Zone(Topology.FIELD)), 
                            new EdgeNoRoad(new Zone(Topology.FIELD)));

        assertThrows(NoExitRoadException.class, () -> {tile.getExitRoadDirection(Direction.TOP);});

    }

    @Test
    public void testTerminateRoadWithEdgeWithRoad() throws ConnectionRoadToEdgeWithNoRoadException{
        Tile tile = new Tile(new EdgeWithRoad(new Zone(Topology.FIELD), new Zone(Topology.FIELD)), 
                            new EdgeWithRoad(new Zone(Topology.FIELD), new Zone(Topology.FIELD)),
                            new EdgeWithRoad(new Zone(Topology.FIELD), new Zone(Topology.FIELD)), 
                            new EdgeWithRoad(new Zone(Topology.FIELD), new Zone(Topology.FIELD)));
        
        assertFalse(tile.isRoadTerminated(Direction.TOP));
        
        tile.terminateRoad(Direction.TOP);

        assertTrue(tile.isRoadTerminated(Direction.TOP));
    }

    @Test
    public void testTerminateRoadWithEdgeWithoutRoad() {
        Tile tile = new Tile(new EdgeNoRoad(new Zone(Topology.CITY)), 
                            new EdgeNoRoad(new Zone(Topology.FIELD)), 
                            new EdgeNoRoad(new Zone(Topology.FIELD)), 
                            new EdgeNoRoad(new Zone(Topology.FIELD)));

        assertThrows(ConnectionRoadToEdgeWithNoRoadException.class, () -> {tile.terminateRoad(Direction.TOP);});

    }
}