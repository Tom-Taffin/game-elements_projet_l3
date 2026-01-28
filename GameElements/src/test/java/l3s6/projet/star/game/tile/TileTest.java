package l3s6.projet.star.game.tile;

import l3s6.projet.star.game.edge.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TileTest {
    @Test
    public void testCompatibilityOfTwoCompatibleTilesNoRoads(){
        Tile tile1 = new Tile(new EdgeNoRoad(Zone.CITY), new EdgeNoRoad(Zone.FIELD), new EdgeNoRoad(Zone.FIELD), new EdgeNoRoad(Zone.FIELD));
        Tile tile2 = new Tile(new EdgeNoRoad(Zone.FIELD), new EdgeNoRoad(Zone.FIELD), new EdgeNoRoad(Zone.CITY), new EdgeNoRoad(Zone.FIELD));

        assertTrue(tile1.isCompatibleWith(tile2, Direction.TOP));
    }

    @Test
    public void testCompatibilityOfTwoIncompatibleTilesNoRoads(){
        Tile tile1 = new Tile(new EdgeNoRoad(Zone.CITY), new EdgeNoRoad(Zone.FIELD), new EdgeNoRoad(Zone.FIELD), new EdgeNoRoad(Zone.FIELD));
        Tile tile2 = new Tile(new EdgeNoRoad(Zone.CITY), new EdgeNoRoad(Zone.CITY), new EdgeNoRoad(Zone.FIELD), new EdgeNoRoad(Zone.CITY));

        assertFalse(tile1.isCompatibleWith(tile2, Direction.TOP));
    }

    @Test
    public void testCompatibilityOfTwoCompatibleTilesWithRoads(){
        Tile tile1 = new Tile(new EdgeWithRoad(Zone.FIELD, Zone.FIELD), new EdgeWithRoad(Zone.FIELD, Zone.CITY),
                              new EdgeWithRoad(Zone.FIELD, Zone.FIELD), new EdgeWithRoad(Zone.FIELD, Zone.FIELD));

        Tile tile2 = new Tile(new EdgeWithRoad(Zone.FIELD, Zone.FIELD), new EdgeWithRoad(Zone.FIELD, Zone.FIELD),
                              new EdgeWithRoad(Zone.FIELD, Zone.FIELD), new EdgeWithRoad(Zone.CITY, Zone.FIELD));

        assertTrue(tile1.isCompatibleWith(tile2, Direction.RIGHT));
    }

    @Test
    public void testCompatibilityOfTwoIncompatibleTilesWithRoads(){
        Tile tile1 = new Tile(new EdgeWithRoad(Zone.FIELD, Zone.FIELD), new EdgeWithRoad(Zone.FIELD, Zone.CITY),
                              new EdgeWithRoad(Zone.FIELD, Zone.FIELD), new EdgeWithRoad(Zone.FIELD, Zone.FIELD));

        Tile tile2 = new Tile(new EdgeWithRoad(Zone.FIELD, Zone.FIELD), new EdgeWithRoad(Zone.FIELD, Zone.FIELD),
                              new EdgeWithRoad(Zone.FIELD, Zone.FIELD), new EdgeWithRoad(Zone.FIELD, Zone.FIELD));

        assertFalse(tile1.isCompatibleWith(tile2, Direction.RIGHT));
    }

    @Test
    public void testCompatibilityOfTwoIncompatibleTilesWithAndWithoutRoad(){
        Tile tile1 = new Tile(new EdgeWithRoad(Zone.FIELD, Zone.FIELD), new EdgeWithRoad(Zone.FIELD, Zone.CITY),
                new EdgeWithRoad(Zone.FIELD, Zone.FIELD), new EdgeWithRoad(Zone.FIELD, Zone.FIELD));

        Tile tile2 = new Tile(new EdgeWithRoad(Zone.FIELD, Zone.FIELD), new EdgeNoRoad(Zone.FIELD),
                new EdgeWithRoad(Zone.FIELD, Zone.FIELD), new EdgeWithRoad(Zone.FIELD, Zone.FIELD));

        assertFalse(tile1.isCompatibleWith(tile2, Direction.LEFT));
    }

    @Test
    public void testConnectTwoRoadsEdgesWithRoads() throws ConnectionRoadToEdgeWithNoRoadException, NoExitRoadException{
        Tile tile = new Tile(new EdgeWithRoad(Zone.FIELD, Zone.FIELD), new EdgeWithRoad(Zone.FIELD, Zone.FIELD),
                new EdgeWithRoad(Zone.FIELD, Zone.FIELD), new EdgeWithRoad(Zone.FIELD, Zone.FIELD));

        tile.connectRoad(Direction.TOP, Direction.BOTTOM);

        assertFalse(tile.isRoadFinished(Direction.TOP));
        assertFalse(tile.isRoadFinished(Direction.BOTTOM));
        assertEquals(Direction.BOTTOM, tile.getExitRoadDirection(Direction.TOP));
        assertEquals(Direction.TOP, tile.getExitRoadDirection(Direction.BOTTOM));

    }

    @Test
    public void testConnectTwoRoadsWithEdgesWithoutRoads() throws ConnectionRoadToEdgeWithNoRoadException {
        Tile tile = new Tile(new EdgeNoRoad(Zone.CITY), new EdgeNoRoad(Zone.FIELD), new EdgeNoRoad(Zone.FIELD), new EdgeNoRoad(Zone.FIELD));

        assertThrows(ConnectionRoadToEdgeWithNoRoadException.class, () -> {tile.connectRoad(Direction.TOP, Direction.BOTTOM);});

    }

    @Test
    public void testTerminateRoadWithEdgeWithRoad() throws ConnectionRoadToEdgeWithNoRoadException{
        Tile tile = new Tile(new EdgeWithRoad(Zone.FIELD, Zone.FIELD), new EdgeWithRoad(Zone.FIELD, Zone.FIELD),
                new EdgeWithRoad(Zone.FIELD, Zone.FIELD), new EdgeWithRoad(Zone.FIELD, Zone.FIELD));

        tile.terminateRoad(Direction.TOP);

        assertTrue(tile.isRoadFinished(Direction.TOP));
        assertThrows(NoExitRoadException.class, () -> {
            tile.getExitRoadDirection(Direction.TOP);
        });
    }

    @Test
    public void testTerminateRoadWithEdgeWithoutRoad() throws ConnectionRoadToEdgeWithNoRoadException {
        Tile tile = new Tile(new EdgeNoRoad(Zone.CITY), new EdgeNoRoad(Zone.FIELD), new EdgeNoRoad(Zone.FIELD), new EdgeNoRoad(Zone.FIELD));

        assertThrows(ConnectionRoadToEdgeWithNoRoadException.class, () -> {tile.terminateRoad(Direction.TOP);});

    }


}
