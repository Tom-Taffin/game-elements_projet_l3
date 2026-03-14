package l3s6.projet.star.game.tile;

import l3s6.projet.star.game.edge.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Set;

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
        Tile tile1 = new Tile(new Edge(Topology.FIELD, Topology.ROAD, Topology.FIELD), 
                            new Edge(Topology.FIELD, Topology.ROAD, Topology.CITY),
                            new Edge(Topology.FIELD, Topology.ROAD, Topology.FIELD), 
                            new Edge(Topology.CITY, Topology.ROAD, Topology.FIELD));

        Tile tile2 = new Tile(new Edge(Topology.CITY, Topology.ROAD, Topology.CITY), 
                            new Edge(Topology.FIELD, Topology.ROAD, Topology.FIELD),
                            new Edge(Topology.FIELD, Topology.ROAD, Topology.CITY), 
                            new Edge(Topology.CITY, Topology.ROAD, Topology.FIELD));

        assertTrue(tile1.isCompatibleWith(tile2, Direction.RIGHT));
    }

    @Test
    public void testCompatibilityOfTwoIncompatibleTilesWithRoads(){
        Tile tile1 = new Tile(new Edge(Topology.FIELD, Topology.ROAD, Topology.FIELD), 
                            new Edge(Topology.CITY, Topology.ROAD, Topology.FIELD),
                            new Edge(Topology.FIELD, Topology.ROAD, Topology.FIELD), 
                            new Edge(Topology.CITY, Topology.ROAD, Topology.FIELD));

        Tile tile2 = new Tile(new Edge(Topology.CITY, Topology.ROAD, Topology.CITY), 
                            new Edge(Topology.FIELD, Topology.ROAD, Topology.FIELD),
                            new Edge(Topology.FIELD, Topology.ROAD, Topology.CITY), 
                            new Edge(Topology.CITY, Topology.ROAD, Topology.FIELD));

        assertFalse(tile1.isCompatibleWith(tile2, Direction.RIGHT));
    }

    @Test
    public void testCompatibilityOfTwoIncompatibleTilesWithAndWithoutRoad(){
        Tile tile1 = new Tile(new Edge(Topology.FIELD, Topology.ROAD, Topology.FIELD), 
                            new Edge(Topology.FIELD, Topology.ROAD, Topology.CITY),
                            new Edge(Topology.FIELD), 
                            new Edge(Topology.FIELD, Topology.ROAD, Topology.FIELD));

        Tile tile2 = new Tile(new Edge(Topology.FIELD, Topology.ROAD, Topology.FIELD), 
                            new Edge(Topology.FIELD),
                            new Edge(Topology.FIELD, Topology.ROAD, Topology.FIELD), 
                            new Edge(Topology.FIELD, Topology.ROAD, Topology.FIELD));

        assertFalse(tile1.isCompatibleWith(tile2, Direction.LEFT));
    }

    @Test
    public void testCompatibilityOfTwoCompatibleTilesWithAndWithoutRoad(){
        Tile tile1 = new Tile(new Edge(Topology.FIELD, Topology.ROAD, Topology.FIELD), 
                            new Edge(Topology.FIELD, Topology.ROAD, Topology.CITY),
                            new Edge(Topology.FIELD), 
                            new Edge(Topology.FIELD));

        Tile tile2 = new Tile(new Edge(Topology.FIELD, Topology.ROAD, Topology.FIELD), 
                            new Edge(Topology.FIELD),
                            new Edge(Topology.FIELD, Topology.ROAD, Topology.FIELD), 
                            new Edge(Topology.FIELD, Topology.ROAD, Topology.FIELD));

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
        Tile tile1 = new Tile(new Edge(Topology.FIELD, Topology.ROAD, Topology.FIELD), 
                            new Edge(Topology.FIELD, Topology.ROAD, Topology.CITY),
                            new Edge(Topology.FIELD), 
                            new Edge(Topology.FIELD, Topology.ROAD, Topology.FIELD),
                            Orientation.WEST);

        Tile tile2 = new Tile(new Edge(Topology.FIELD, Topology.ROAD, Topology.FIELD), 
                            new Edge(Topology.FIELD),
                            new Edge(Topology.FIELD, Topology.ROAD, Topology.FIELD), 
                            new Edge(Topology.FIELD, Topology.ROAD, Topology.FIELD));

        assertFalse(tile1.isCompatibleWith(tile2, Direction.LEFT));
    }

    @Test
    public void testCompatibilityOfTwoCompatibleTilesWithAndWithoutRoadWithRotation(){
        Tile tile1 = new Tile(new Edge(Topology.FIELD), 
                            new Edge(Topology.FIELD, Topology.ROAD, Topology.CITY),
                            new Edge(Topology.FIELD), 
                            new Edge(Topology.FIELD, Topology.ROAD, Topology.FIELD),
                            Orientation.WEST);

        Tile tile2 = new Tile(new Edge(Topology.FIELD, Topology.ROAD, Topology.FIELD), 
                            new Edge(Topology.FIELD),
                            new Edge(Topology.FIELD, Topology.ROAD, Topology.FIELD), 
                            new Edge(Topology.FIELD, Topology.ROAD, Topology.FIELD));
        
        assertTrue(tile1.isCompatibleWith(tile2, Direction.LEFT));
    }

    @Test
    public void testGetEdgeAfterRotate(){
        Edge topEdge = new Edge(Topology.CITY);
        Tile tile1 = new Tile(topEdge, 
                    new Edge(Topology.FIELD), 
                    new Edge(Topology.FIELD), 
                    new Edge(Topology.FIELD),
                    Orientation.NORTH);
        assertSame(topEdge, tile1.getEdge(Direction.TOP));
        tile1.rotateRight();
        assertSame(topEdge, tile1.getEdge(Direction.RIGHT));
        assertEquals(Orientation.EAST, tile1.getOrientation());

    }

    @Test
    public void testCompatibilityAfterRotation(){
        Tile tile1 = new Tile(new Edge(Topology.FIELD), 
                            new Edge(Topology.FIELD), 
                            new Edge(Topology.CITY), 
                            new Edge(Topology.FIELD),
                            Orientation.WEST);

        Tile tile2 = new Tile(new Edge(Topology.FIELD), 
                            new Edge(Topology.CITY),  
                            new Edge(Topology.FIELD),
                            new Edge(Topology.FIELD),
                            Orientation.EAST);

        assertFalse(tile1.isCompatibleWith(tile2, Direction.TOP));

        tile1.rotateLeft();

        assertTrue(tile1.isCompatibleWith(tile2, Direction.TOP));
    }

    @Test
    public void testGetDistinctZone() throws WrongTopologyException{
        Tile tile = new Tile(new Edge(Topology.FIELD,Topology.ROAD, Topology.FIELD), 
                            new Edge(Topology.FIELD,Topology.ROAD, Topology.FIELD),  
                            new Edge(Topology.CITY),
                            new Edge(Topology.FIELD));
        
        tile.getZoneAt(Direction.TOP, 0).connectTo(tile.getZoneAt(Direction.LEFT, 0));
        tile.getZoneAt(Direction.TOP, 0).connectTo(tile.getZoneAt(Direction.RIGHT, 2));
        tile.getZoneAt(Direction.LEFT, 0).connectTo(tile.getZoneAt(Direction.RIGHT, 2));
        tile.getZoneAt(Direction.TOP, 2).connectTo(tile.getZoneAt(Direction.RIGHT, 0));
        tile.getZoneAt(Direction.TOP, 1).connectTo(tile.getZoneAt(Direction.RIGHT, 1));
        
        Set<Zone> distinctZones = tile.getDistinctZone();
        assertFalse(distinctZones.contains(tile.getZoneAt(Direction.TOP, 0)) && 
                    distinctZones.contains(tile.getZoneAt(Direction.LEFT, 0)));
        
        assertFalse(distinctZones.contains(tile.getZoneAt(Direction.TOP, 0)) && 
                    distinctZones.contains(tile.getZoneAt(Direction.RIGHT, 2)));
        
        assertFalse(distinctZones.contains(tile.getZoneAt(Direction.RIGHT, 2)) && 
                    distinctZones.contains(tile.getZoneAt(Direction.LEFT, 0)));
    
        assertTrue(distinctZones.contains(tile.getZoneAt(Direction.RIGHT, 2)) ||
                    distinctZones.contains(tile.getZoneAt(Direction.LEFT, 0)) ||
                    distinctZones.contains(tile.getZoneAt(Direction.TOP, 0)));

        assertTrue(distinctZones.contains(tile.getZoneAt(Direction.BOTTOM, 0)));
        
        assertEquals(4, distinctZones.size());
    }
}