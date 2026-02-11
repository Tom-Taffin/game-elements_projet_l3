package l3s6.projet.star.game.tile;

import l3s6.projet.star.game.edge.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TileTest {
    @Test
    public void testCompatibilityOfTwoCompatibleTilesNoRoads(){
        Tile tile1 = new Tile(new EdgeNoRoad(new Zone(Topology.CITY)), new EdgeNoRoad(new Zone(Topology.FIELD)), new EdgeNoRoad(new Zone(Topology.FIELD)), new EdgeNoRoad(new Zone(Topology.FIELD)));
        Tile tile2 = new Tile(new EdgeNoRoad(new Zone(Topology.FIELD)), new EdgeNoRoad(new Zone(Topology.FIELD)), new EdgeNoRoad(new Zone(Topology.CITY)), new EdgeNoRoad(new Zone(Topology.FIELD)));

        assertTrue(tile1.isCompatibleWith(tile2, Direction.TOP));
    }

    @Test
    public void testCompatibilityOfTwoIncompatibleTilesNoRoads(){
        Tile tile1 = new Tile(new EdgeNoRoad(new Zone(Topology.CITY)), new EdgeNoRoad(new Zone(Topology.FIELD)), new EdgeNoRoad(new Zone(Topology.FIELD)), new EdgeNoRoad(new Zone(Topology.FIELD)));
        Tile tile2 = new Tile(new EdgeNoRoad(new Zone(Topology.CITY)), new EdgeNoRoad(new Zone(Topology.CITY)), new EdgeNoRoad(new Zone(Topology.FIELD)), new EdgeNoRoad(new Zone(Topology.CITY)));

        assertFalse(tile1.isCompatibleWith(tile2, Direction.TOP));
    }

    @Test
    public void testCompatibilityOfTwoCompatibleTilesWithRoads(){
        Tile tile1 = new Tile(new EdgeWithRoad(new Zone(Topology.FIELD), new Zone(Topology.FIELD)), new EdgeWithRoad(new Zone(Topology.FIELD), new Zone(Topology.CITY)),
                              new EdgeWithRoad(new Zone(Topology.FIELD), new Zone(Topology.FIELD)), new EdgeWithRoad(new Zone(Topology.FIELD), new Zone(Topology.FIELD)));

        Tile tile2 = new Tile(new EdgeWithRoad(new Zone(Topology.FIELD), new Zone(Topology.FIELD)), new EdgeWithRoad(new Zone(Topology.FIELD), new Zone(Topology.FIELD)),
                              new EdgeWithRoad(new Zone(Topology.FIELD), new Zone(Topology.FIELD)), new EdgeWithRoad(new Zone(Topology.CITY), new Zone(Topology.FIELD)));

        assertTrue(tile1.isCompatibleWith(tile2, Direction.RIGHT));
    }

    @Test
    public void testCompatibilityOfTwoIncompatibleTilesWithRoads(){
        Tile tile1 = new Tile(new EdgeWithRoad(new Zone(Topology.FIELD), new Zone(Topology.FIELD)), new EdgeWithRoad(new Zone(Topology.FIELD), new Zone(Topology.CITY)),
                              new EdgeWithRoad(new Zone(Topology.FIELD), new Zone(Topology.FIELD)), new EdgeWithRoad(new Zone(Topology.FIELD), new Zone(Topology.FIELD)));

        Tile tile2 = new Tile(new EdgeWithRoad(new Zone(Topology.FIELD), new Zone(Topology.FIELD)), new EdgeWithRoad(new Zone(Topology.FIELD), new Zone(Topology.FIELD)),
                              new EdgeWithRoad(new Zone(Topology.FIELD), new Zone(Topology.FIELD)), new EdgeWithRoad(new Zone(Topology.FIELD), new Zone(Topology.FIELD)));

        assertFalse(tile1.isCompatibleWith(tile2, Direction.RIGHT));
    }

    @Test
    public void testCompatibilityOfTwoIncompatibleTilesWithAndWithoutRoad(){
        Tile tile1 = new Tile(new EdgeWithRoad(new Zone(Topology.FIELD), new Zone(Topology.FIELD)), new EdgeWithRoad(new Zone(Topology.FIELD), new Zone(Topology.CITY)),
                new EdgeWithRoad(new Zone(Topology.FIELD), new Zone(Topology.FIELD)), new EdgeWithRoad(new Zone(Topology.FIELD), new Zone(Topology.FIELD)));

        Tile tile2 = new Tile(new EdgeWithRoad(new Zone(Topology.FIELD), new Zone(Topology.FIELD)), new EdgeNoRoad(new Zone(Topology.FIELD)),
                new EdgeWithRoad(new Zone(Topology.FIELD), new Zone(Topology.FIELD)), new EdgeWithRoad(new Zone(Topology.FIELD), new Zone(Topology.FIELD)));

        assertFalse(tile1.isCompatibleWith(tile2, Direction.LEFT));
    }

    @Test
    public void testConnectTwoRoadsEdgesWithRoads() throws ConnectionRoadToEdgeWithNoRoadException, NoExitRoadException{
        Tile tile = new Tile(new EdgeWithRoad(new Zone(Topology.FIELD), new Zone(Topology.FIELD)), new EdgeWithRoad(new Zone(Topology.FIELD), new Zone(Topology.FIELD)),
                new EdgeWithRoad(new Zone(Topology.FIELD), new Zone(Topology.FIELD)), new EdgeWithRoad(new Zone(Topology.FIELD), new Zone(Topology.FIELD)));

        tile.connectRoad(Direction.TOP, Direction.BOTTOM);

        assertFalse(tile.isRoadTerminated(Direction.TOP));
        assertFalse(tile.isRoadTerminated(Direction.BOTTOM));
        assertEquals(Direction.BOTTOM, tile.getExitRoadDirection(Direction.TOP));
        assertEquals(Direction.TOP, tile.getExitRoadDirection(Direction.BOTTOM));

    }

    @Test
    public void testConnectTwoRoadsWithEdgesWithoutRoads() throws ConnectionRoadToEdgeWithNoRoadException {
        Tile tile = new Tile(new EdgeNoRoad(new Zone(Topology.CITY)), new EdgeNoRoad(new Zone(Topology.FIELD)), new EdgeNoRoad(new Zone(Topology.FIELD)), new EdgeNoRoad(new Zone(Topology.FIELD)));

        assertThrows(ConnectionRoadToEdgeWithNoRoadException.class, () -> {tile.connectRoad(Direction.TOP, Direction.BOTTOM);});

    }

    @Test
    public void testTerminateRoadWithEdgeWithRoad() throws ConnectionRoadToEdgeWithNoRoadException{
        Tile tile = new Tile(new EdgeWithRoad(new Zone(Topology.FIELD), new Zone(Topology.FIELD)), new EdgeWithRoad(new Zone(Topology.FIELD), new Zone(Topology.FIELD)),
                new EdgeWithRoad(new Zone(Topology.FIELD), new Zone(Topology.FIELD)), new EdgeWithRoad(new Zone(Topology.FIELD), new Zone(Topology.FIELD)));

        tile.terminateRoad(Direction.TOP);

        assertTrue(tile.isRoadTerminated(Direction.TOP));
        assertThrows(NoExitRoadException.class, () -> {
            tile.getExitRoadDirection(Direction.TOP);
        });
    }

    @Test
    public void testTerminateRoadWithEdgeWithoutRoad() throws ConnectionRoadToEdgeWithNoRoadException {
        Tile tile = new Tile(new EdgeNoRoad(new Zone(Topology.CITY)), new EdgeNoRoad(new Zone(Topology.FIELD)), new EdgeNoRoad(new Zone(Topology.FIELD)), new EdgeNoRoad(new Zone(Topology.FIELD)));

        assertThrows(ConnectionRoadToEdgeWithNoRoadException.class, () -> {tile.terminateRoad(Direction.TOP);});

    }
}