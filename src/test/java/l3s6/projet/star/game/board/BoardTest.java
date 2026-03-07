package l3s6.projet.star.game.board;

import l3s6.projet.star.game.edge.Edge;
import l3s6.projet.star.game.edge.Topology; // Ajout probable nécessaire si non importé via *
import l3s6.projet.star.game.tile.Tile;
import l3s6.projet.star.game.tile.WrongTileSyntaxException;

import org.junit.jupiter.api.Test;

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
}