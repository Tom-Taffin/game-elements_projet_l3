package l3s6.projet.star.game.meeple;

import l3s6.projet.star.game.edge.*;
import l3s6.projet.star.game.tile.Direction;
import l3s6.projet.star.game.tile.Tile;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class MeepleTest {
    @Test
    public void testMeeplePlacedOnTileFullCity(){
        Meeple meeple = new Meeple();
        Tile tile = new Tile(new EdgeNoRoad(new Zone(Topology.CITY)), new EdgeNoRoad(new Zone(Topology.CITY)), new EdgeNoRoad(new Zone(Topology.CITY)), new EdgeNoRoad(new Zone(Topology.CITY)));

        tile.placeMeepleOnZone(meeple, Direction.TOP);

        assertTrue(tile.hasMeepleOnZone(Direction.TOP));
        assertEquals(meeple, tile.getMeepleOnZone(Direction.TOP));
    }
}
