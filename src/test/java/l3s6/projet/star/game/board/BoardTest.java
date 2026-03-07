package l3s6.projet.star.game.board;

import l3s6.projet.star.game.edge.Edge;
import l3s6.projet.star.game.edge.Topology; // Ajout probable nécessaire si non importé via *
import l3s6.projet.star.game.tile.Tile;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BoardTest {
    @Test
    public void testPutTileAt(){
        Board board = new Board();
        Tile tile = new Tile(new Edge(Topology.FIELD), new Edge(Topology.FIELD), new Edge(Topology.FIELD), new Edge(Topology.FIELD));
        Coordinates coordinates = new Coordinates(0, 0);

        assertFalse(board.hasTile(coordinates));

        board.putTileAt(tile, coordinates);

        assertTrue(board.hasTile(coordinates));

        assertEquals(tile, board.getTileAt(coordinates));
    }

    @Test
    public void testCoordinates(){
        Board board = new Board();
        Tile tile = new Tile(new Edge(Topology.FIELD), new Edge(Topology.FIELD), new Edge(Topology.FIELD), new Edge(Topology.FIELD));
        Coordinates origin = new Coordinates(0, 0);

        board.putTileAt(tile, origin);

        assertEquals(new Coordinates(0, 0), origin);

        assertTrue(board.hasTile(new Coordinates(0, 0)));
    }
}