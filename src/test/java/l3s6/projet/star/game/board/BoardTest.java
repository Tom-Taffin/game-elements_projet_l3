package l3s6.projet.star.game.board;

import l3s6.projet.star.game.edge.Edge;
import l3s6.projet.star.game.edge.Topology; // Ajout probable nécessaire si non importé via *
import l3s6.projet.star.game.tile.Direction;
import l3s6.projet.star.game.tile.Tile;
import l3s6.projet.star.game.tile.WrongTileSyntaxException;

import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;

public class BoardTest {

    private Board board;
    private Tile tile;
    private Coordinates coordinates;

    @BeforeEach
    public void init() throws WrongTileSyntaxException{
        board = new Board();
        tile = new Tile(new Edge(Topology.FIELD), new Edge(Topology.FIELD), new Edge(Topology.FIELD), new Edge(Topology.FIELD));
        coordinates = new Coordinates(2, 1);
    }

    @Test
    public void testCorrectCreation(){
        assertEquals(0, board.getMaxX());
        assertEquals(0, board.getMinX());
        assertEquals(0, board.getMaxY());
        assertEquals(0, board.getMinY());

        assertTrue(board.hasTile(new Coordinates(0, 0)));
    }

    @Test
    public void testGetOutsideFrontierTiles(){
        Set<Coordinates> frontier = board.getOutsideFrontierTiles();
        
        assertEquals(4, frontier.size());
        assertTrue(frontier.contains(new Coordinates(0, 1)));
        assertTrue(frontier.contains(new Coordinates(1, 0)));
        assertTrue(frontier.contains(new Coordinates(0, -1)));
        assertTrue(frontier.contains(new Coordinates(-1, 0)));

        board.putTileAt(tile, new Coordinates(1, 0));
        Set<Coordinates> frontierAfter = board.getOutsideFrontierTiles();

        assertEquals(6, frontierAfter.size());
        assertTrue(frontier.contains(new Coordinates(0, 1)));
        assertFalse(frontierAfter.contains(new Coordinates(1, 0)));
        assertTrue(frontier.contains(new Coordinates(0, -1)));
        assertTrue(frontier.contains(new Coordinates(-1, 0)));
        assertTrue(frontierAfter.contains(new Coordinates(1, 1)));
        assertTrue(frontierAfter.contains(new Coordinates(2, 0)));
        assertTrue(frontierAfter.contains(new Coordinates(1, -1)));
        assertFalse(frontierAfter.contains(new Coordinates(0, 0)));
    }

    @Test
    public void testPutTileAt(){
        assertFalse(board.hasTile(coordinates));

        board.putTileAt(tile, coordinates);

        assertTrue(board.hasTile(coordinates));
        assertEquals(tile, board.getTileAt(coordinates));
    }

    @Test
    public void testDifferentCoordinatesInstances(){
        Coordinates origin = new Coordinates(2, 1);

        assertFalse(board.hasTile(new Coordinates(2, 1)));

        board.putTileAt(tile, origin);

        assertTrue(board.hasTile(new Coordinates(2, 1)));
    }

    @Test
    public void testMinAndMaxCoordinatesUpdated(){
        assertEquals(0, board.getMaxX());
        assertEquals(0, board.getMinX());
        assertEquals(0, board.getMaxY());
        assertEquals(0, board.getMinY());

        board.putTileAt(tile, new Coordinates(2, 1));
        board.putTileAt(tile, new Coordinates(-3, -6));

        assertEquals(2, board.getMaxX());
        assertEquals(-3, board.getMinX());
        assertEquals(1, board.getMaxY());
        assertEquals(-6, board.getMinY());

        board.putTileAt(tile, new Coordinates(10, -10));

        assertEquals(10, board.getMaxX());
        assertEquals(-3, board.getMinX());
        assertEquals(1, board.getMaxY());
        assertEquals(-10, board.getMinY());
    }

    @Test
    public void testAdjacentZoneSetted(){
        Tile tile1 = new Tile(new Edge(Topology.FIELD, Topology.CITY), new Edge(Topology.FIELD), new Edge(Topology.FIELD), new Edge(Topology.FIELD));
        Tile tile2 = new Tile(new Edge(Topology.FIELD), new Edge(Topology.FIELD), new Edge(Topology.FIELD), new Edge(Topology.FIELD));
        Tile tile3 = new Tile(new Edge(Topology.FIELD), new Edge(Topology.FIELD), new Edge(Topology.CITY, Topology.FIELD), new Edge(Topology.FIELD));

        board.putTileAt(tile1, new Coordinates(2, 2));
        board.putTileAt(tile2, new Coordinates(1, 3));
        board.putTileAt(tile3, new Coordinates(2, 3));

        assertFalse(tile3.getZoneAt(Direction.TOP, 0).hasAdjacentZone());
        assertFalse(tile3.getZoneAt(Direction.RIGHT, 0).hasAdjacentZone());
        assertTrue(tile3.getZoneAt(Direction.BOTTOM, 0).hasAdjacentZone());
        assertSame(tile1.getZoneAt(Direction.TOP, 1), tile3.getZoneAt(Direction.BOTTOM, 0).getAdjacentZone());
        assertSame(tile1.getZoneAt(Direction.TOP, 0), tile3.getZoneAt(Direction.BOTTOM, 1).getAdjacentZone());
        assertSame(tile2.getZoneAt(Direction.RIGHT, 0), tile3.getZoneAt(Direction.LEFT, 0).getAdjacentZone());
    }

    @Test
    public void testRemoveTileAt(){

        board.putTileAt(tile, coordinates);
        assertTrue(board.hasTile(coordinates));
        assertEquals(tile, board.getTileAt(coordinates));

        board.removeTileAt(coordinates);
        assertFalse(board.hasTile(coordinates));
    }

    @Test
    public void testAdjacentZoneRemoved(){

        Tile tile1 = new Tile(new Edge(Topology.FIELD, Topology.CITY), new Edge(Topology.FIELD), new Edge(Topology.FIELD), new Edge(Topology.FIELD));
        Tile tile2 = new Tile(new Edge(Topology.FIELD), new Edge(Topology.FIELD), new Edge(Topology.FIELD), new Edge(Topology.FIELD));
        Tile tile3 = new Tile(new Edge(Topology.FIELD), new Edge(Topology.FIELD), new Edge(Topology.CITY, Topology.FIELD), new Edge(Topology.FIELD));

        board.putTileAt(tile1, new Coordinates(2, 2));
        board.putTileAt(tile2, new Coordinates(1, 3));
        board.putTileAt(tile3, new Coordinates(2, 3));
        board.removeTileAt(new Coordinates(2, 3));

        assertFalse(tile1.getZoneAt(Direction.TOP, 0).hasAdjacentZone());
        assertFalse(tile1.getZoneAt(Direction.TOP, 1).hasAdjacentZone());
        assertFalse(tile1.getZoneAt(Direction.RIGHT, 0).hasAdjacentZone());
        assertFalse(tile1.getZoneAt(Direction.BOTTOM, 0).hasAdjacentZone());
        assertFalse(tile1.getZoneAt(Direction.LEFT, 0).hasAdjacentZone());

        assertFalse(tile2.getZoneAt(Direction.TOP, 0).hasAdjacentZone());
        assertFalse(tile2.getZoneAt(Direction.RIGHT, 0).hasAdjacentZone());
        assertFalse(tile2.getZoneAt(Direction.BOTTOM, 0).hasAdjacentZone());
        assertFalse(tile2.getZoneAt(Direction.LEFT, 0).hasAdjacentZone());

        assertFalse(tile3.getZoneAt(Direction.TOP, 0).hasAdjacentZone());
        assertFalse(tile3.getZoneAt(Direction.RIGHT, 0).hasAdjacentZone());
        assertFalse(tile3.getZoneAt(Direction.BOTTOM, 0).hasAdjacentZone());
        assertFalse(tile3.getZoneAt(Direction.BOTTOM, 1).hasAdjacentZone());
        assertFalse(tile3.getZoneAt(Direction.LEFT, 0).hasAdjacentZone());

    }
}