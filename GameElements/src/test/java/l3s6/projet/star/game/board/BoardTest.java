package l3s6.projet.star.game.board;

import l3s6.projet.star.game.edge.EdgeNoRoad;
import l3s6.projet.star.game.edge.Zone;
import l3s6.projet.star.game.edge.Topology; // Ajout probable nécessaire si non importé via *
import l3s6.projet.star.game.tile.Tile;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BoardTest {
    @Test
    public void testPutTileAt(){
        Board board = new Board();
        Tile tile = new Tile(new EdgeNoRoad(new Zone(Topology.FIELD)), new EdgeNoRoad(new Zone(Topology.FIELD)), new EdgeNoRoad(new Zone(Topology.FIELD)), new EdgeNoRoad(new Zone(Topology.FIELD)));
        Coordinates coordinates = new Coordinates(0, 0);

        assertFalse(board.hasTile(coordinates));

        board.putTileAt(tile, coordinates);

        assertTrue(board.hasTile(coordinates));

        assertEquals(tile, board.getTileAt(coordinates));
    }

    @Test
    public void testRemoveTileFrom(){
        Board board = new Board();
        Tile tile = new Tile(new EdgeNoRoad(new Zone(Topology.FIELD)), new EdgeNoRoad(new Zone(Topology.FIELD)), new EdgeNoRoad(new Zone(Topology.FIELD)), new EdgeNoRoad(new Zone(Topology.FIELD)));
        Coordinates coordinates = new Coordinates(0, 0);

        board.putTileAt(tile, coordinates);

        Tile removedTile = board.removeTileFrom(coordinates);

        assertFalse(board.hasTile(coordinates));

        assertEquals(tile, removedTile);
    }
}