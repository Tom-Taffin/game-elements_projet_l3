package l3s6.projet.star.game.tile;

import l3s6.projet.star.game.edge.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TileTest {
    @Test
    public void testCompatibilityOfTwoCompatibleTilesNoRoads(){
        Tile tile1 = new Tile(new EdgeNoRoad(Zone.CITY), new EdgeNoRoad(Zone.FIELD), new EdgeNoRoad(Zone.FIELD), new EdgeNoRoad(Zone.FIELD));
        Tile tile2 = new Tile(new EdgeNoRoad(Zone.FIELD), new EdgeNoRoad(Zone.FIELD), new EdgeNoRoad(Zone.CITY), new EdgeNoRoad(Zone.FIELD));

        assertTrue(tile1.isCompatibleWith(tile2, Location.TOP));
    }

    @Test
    public void testCompatibilityOfTwoIncompatibleTilesNoRoads(){
        Tile tile1 = new Tile(new EdgeNoRoad(Zone.CITY), new EdgeNoRoad(Zone.FIELD), new EdgeNoRoad(Zone.FIELD), new EdgeNoRoad(Zone.FIELD));
        Tile tile2 = new Tile(new EdgeNoRoad(Zone.CITY), new EdgeNoRoad(Zone.CITY), new EdgeNoRoad(Zone.FIELD), new EdgeNoRoad(Zone.CITY));

        assertFalse(tile1.isCompatibleWith(tile2, Location.TOP));
    }

    @Test
    public void testCompatibilityOfTwoCompatibleTilesWithRoads(){
        Tile tile1 = new Tile(new EdgeWithRoad(Zone.FIELD, Zone.FIELD), new EdgeWithRoad(Zone.FIELD, Zone.CITY),
                              new EdgeWithRoad(Zone.FIELD, Zone.FIELD), new EdgeWithRoad(Zone.FIELD, Zone.FIELD));

        Tile tile2 = new Tile(new EdgeWithRoad(Zone.FIELD, Zone.FIELD), new EdgeWithRoad(Zone.FIELD, Zone.FIELD),
                              new EdgeWithRoad(Zone.FIELD, Zone.FIELD), new EdgeWithRoad(Zone.CITY, Zone.FIELD));

        assertTrue(tile1.isCompatibleWith(tile2, Location.RIGHT));
    }

    @Test
    public void testCompatibilityOfTwoIncompatibleTilesWithRoads(){
        Tile tile1 = new Tile(new EdgeWithRoad(Zone.FIELD, Zone.FIELD), new EdgeWithRoad(Zone.FIELD, Zone.CITY),
                              new EdgeWithRoad(Zone.FIELD, Zone.FIELD), new EdgeWithRoad(Zone.FIELD, Zone.FIELD));

        Tile tile2 = new Tile(new EdgeWithRoad(Zone.FIELD, Zone.FIELD), new EdgeWithRoad(Zone.FIELD, Zone.FIELD),
                              new EdgeWithRoad(Zone.FIELD, Zone.FIELD), new EdgeWithRoad(Zone.FIELD, Zone.FIELD));

        assertFalse(tile1.isCompatibleWith(tile2, Location.RIGHT));
    }

    @Test
    public void testCompatibilityOfTwoIncompatibleTilesWithAndWithoutRoad(){
        Tile tile1 = new Tile(new EdgeWithRoad(Zone.FIELD, Zone.FIELD), new EdgeWithRoad(Zone.FIELD, Zone.CITY),
                new EdgeWithRoad(Zone.FIELD, Zone.FIELD), new EdgeWithRoad(Zone.FIELD, Zone.FIELD));

        Tile tile2 = new Tile(new EdgeWithRoad(Zone.FIELD, Zone.FIELD), new EdgeNoRoad(Zone.FIELD),
                new EdgeWithRoad(Zone.FIELD, Zone.FIELD), new EdgeWithRoad(Zone.FIELD, Zone.FIELD));

        assertFalse(tile1.isCompatibleWith(tile2, Location.LEFT));
    }

    @Test
    public void testConnectTwoRoadsEdgesWithRoads() throws ConnectionRoadToEdgeWithNoRoadException, NoExitRoadException{
        Tile tile = new Tile(new EdgeWithRoad(Zone.FIELD, Zone.FIELD), new EdgeWithRoad(Zone.FIELD, Zone.FIELD),
                new EdgeWithRoad(Zone.FIELD, Zone.FIELD), new EdgeWithRoad(Zone.FIELD, Zone.FIELD));

        tile.connectRoad(Location.TOP, Location.BOTTOM);

        assertFalse(tile.isRoadFinished(Location.TOP));
        assertFalse(tile.isRoadFinished(Location.BOTTOM));
        assertEquals(Location.BOTTOM, tile.getExitRoadLocation(Location.TOP));
        assertEquals(Location.TOP, tile.getExitRoadLocation(Location.BOTTOM));

    }

    @Test
    public void testConnectTwoRoadsWithEdgesWithoutRoads() throws ConnectionRoadToEdgeWithNoRoadException {
        Tile tile = new Tile(new EdgeNoRoad(Zone.CITY), new EdgeNoRoad(Zone.FIELD), new EdgeNoRoad(Zone.FIELD), new EdgeNoRoad(Zone.FIELD));

        assertThrows(ConnectionRoadToEdgeWithNoRoadException.class, () -> {tile.connectRoad(Location.TOP, Location.BOTTOM);});

    }

    @Test
    public void testTerminateRoadWithEdgeWithRoad() throws ConnectionRoadToEdgeWithNoRoadException{
        Tile tile = new Tile(new EdgeWithRoad(Zone.FIELD, Zone.FIELD), new EdgeWithRoad(Zone.FIELD, Zone.FIELD),
                new EdgeWithRoad(Zone.FIELD, Zone.FIELD), new EdgeWithRoad(Zone.FIELD, Zone.FIELD));

        tile.terminateRoad(Location.TOP);

        assertTrue(tile.isRoadFinished(Location.TOP));
        assertThrows(NoExitRoadException.class, () -> {
            tile.getExitRoadLocation(Location.TOP);
        });
    }

    @Test
    public void testTerminateRoadWithEdgeWithoutRoad() throws ConnectionRoadToEdgeWithNoRoadException {
        Tile tile = new Tile(new EdgeNoRoad(Zone.CITY), new EdgeNoRoad(Zone.FIELD), new EdgeNoRoad(Zone.FIELD), new EdgeNoRoad(Zone.FIELD));

        assertThrows(ConnectionRoadToEdgeWithNoRoadException.class, () -> {tile.terminateRoad(Location.TOP);});

    }

    
}
