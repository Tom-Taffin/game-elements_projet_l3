package carcassonne.tile;

import carcassonne.edge.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TileTest {
    @Test
    public void testConnectTwoCompatibleTilesNoRoads(){
        Tile tile1 = new Tile(new EdgeNoRoad(Zone.CITY), new EdgeNoRoad(Zone.FIELD), new EdgeNoRoad(Zone.FIELD), new EdgeNoRoad(Zone.FIELD));
        Tile tile2 = new Tile(new EdgeNoRoad(Zone.FIELD), new EdgeNoRoad(Zone.FIELD), new EdgeNoRoad(Zone.CITY), new EdgeNoRoad(Zone.FIELD));

        assertTrue(tile1.isCompatibleWith(tile2, Location.TOP));
    }

    @Test
    public void testConnectTwoIncompatibleTilesNoRoads(){
        Tile tile1 = new Tile(new EdgeNoRoad(Zone.CITY), new EdgeNoRoad(Zone.FIELD), new EdgeNoRoad(Zone.FIELD), new EdgeNoRoad(Zone.FIELD));
        Tile tile2 = new Tile(new EdgeNoRoad(Zone.CITY), new EdgeNoRoad(Zone.CITY), new EdgeNoRoad(Zone.FIELD), new EdgeNoRoad(Zone.CITY));

        assertFalse(tile1.isCompatibleWith(tile2, Location.TOP));
    }

    @Test
    public void testConnectTwoCompatibleTilesWithRoads(){
        Tile tile1 = new Tile(new EdgeWithRoad(Zone.FIELD, Zone.FIELD), new EdgeWithRoad(Zone.FIELD, Zone.CITY),
                              new EdgeWithRoad(Zone.FIELD, Zone.FIELD), new EdgeWithRoad(Zone.FIELD, Zone.FIELD));

        Tile tile2 = new Tile(new EdgeWithRoad(Zone.FIELD, Zone.FIELD), new EdgeWithRoad(Zone.FIELD, Zone.FIELD),
                              new EdgeWithRoad(Zone.FIELD, Zone.FIELD), new EdgeWithRoad(Zone.CITY, Zone.FIELD));

        assertTrue(tile1.isCompatibleWith(tile2, Location.RIGHT));
    }

    @Test
    public void testConnectTwoIncompatibleTilesWithRoads(){
        Tile tile1 = new Tile(new EdgeWithRoad(Zone.FIELD, Zone.FIELD), new EdgeWithRoad(Zone.FIELD, Zone.CITY),
                              new EdgeWithRoad(Zone.FIELD, Zone.FIELD), new EdgeWithRoad(Zone.FIELD, Zone.FIELD));

        Tile tile2 = new Tile(new EdgeWithRoad(Zone.FIELD, Zone.FIELD), new EdgeWithRoad(Zone.FIELD, Zone.FIELD),
                              new EdgeWithRoad(Zone.FIELD, Zone.FIELD), new EdgeWithRoad(Zone.FIELD, Zone.FIELD));

        assertFalse(tile1.isCompatibleWith(tile2, Location.RIGHT));
    }

    @Test
    public void testConnectTwoIncompatibleTilesWithAndWithoutRoad(){
        Tile tile1 = new Tile(new EdgeWithRoad(Zone.FIELD, Zone.FIELD), new EdgeWithRoad(Zone.FIELD, Zone.CITY),
                new EdgeWithRoad(Zone.FIELD, Zone.FIELD), new EdgeWithRoad(Zone.FIELD, Zone.FIELD));

        Tile tile2 = new Tile(new EdgeWithRoad(Zone.FIELD, Zone.FIELD), new EdgeNoRoad(Zone.FIELD),
                new EdgeWithRoad(Zone.FIELD, Zone.FIELD), new EdgeWithRoad(Zone.FIELD, Zone.FIELD));

        assertFalse(tile1.isCompatibleWith(tile2, Location.LEFT));
    }

    @Test
    public void testConnectTwoRoadsEdgesWithRoads(){
        Tile tile = new Tile(new EdgeWithRoad(Zone.FIELD, Zone.FIELD), new EdgeWithRoad(Zone.FIELD, Zone.FIELD),
                new EdgeWithRoad(Zone.FIELD, Zone.FIELD), new EdgeWithRoad(Zone.FIELD, Zone.FIELD));

        tile.connectRoad(Location.TOP, Location.BOTTOM);

        assertEquals(Location.BOTTOM, tile.getRoadConnections().get(Location.TOP));
        assertEquals(Location.TOP, tile.getRoadConnections().get(Location.BOTTOM));

    }

    @Test
    public void testConnectTwoRoadsWithEdgesWithoutRoads() throws ConnectionRoadToEdgeWithNoRoadException {
        Tile tile = new Tile(new EdgeNoRoad(Zone.CITY), new EdgeNoRoad(Zone.FIELD), new EdgeNoRoad(Zone.FIELD), new EdgeNoRoad(Zone.FIELD));

        assertThrows(ConnectionRoadToEdgeWithNoRoadException.class, () -> {tile.connectRoad(Location.TOP, Location.BOTTOM);});

    }

    @Test
    public void testTerminateRoadWithEdgeWithRoad(){
        Tile tile = new Tile(new EdgeWithRoad(Zone.FIELD, Zone.FIELD), new EdgeWithRoad(Zone.FIELD, Zone.FIELD),
                new EdgeWithRoad(Zone.FIELD, Zone.FIELD), new EdgeWithRoad(Zone.FIELD, Zone.FIELD));

        tile.terminateRoad(Location.TOP);

        assertNull(tile.getRoadConnections().get(Location.TOP));
    }

    @Test
    public void testTerminateRoadWithEdgeWithoutRoad() throws ConnectionRoadToEdgeWithNoRoadException {
        Tile tile = new Tile(new EdgeNoRoad(Zone.CITY), new EdgeNoRoad(Zone.FIELD), new EdgeNoRoad(Zone.FIELD), new EdgeNoRoad(Zone.FIELD));

        assertThrows(ConnectionRoadToEdgeWithNoRoadException.class, () -> {tile.terminateRoad(Location.TOP);});

    }
}
