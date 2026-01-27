package l3s6.projet.star.game.board;

import l3s6.projet.star.game.edge.EdgeNoRoad;
import l3s6.projet.star.game.edge.Zone;
import l3s6.projet.star.game.tile.Tile;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BoardTest {
    @Test
    public void testPutTileAt(){
        Board board = new Board();
        Tile tile = new Tile(new EdgeNoRoad(Zone.FIELD), new EdgeNoRoad(Zone.FIELD), new EdgeNoRoad(Zone.FIELD), new EdgeNoRoad(Zone.FIELD));
        Coordinates coordinates = new Coordinates();

        assertFalse(board.hasTile(coordinates));

        board.putTileAt(tile, coordinates);

        assertTrue(board.hasTile(coordinates));

        assertEquals(tile, board.getTileAt(coordinates));
    }
}
