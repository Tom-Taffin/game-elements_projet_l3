package l3s6.projet.star.game.meeple;

import l3s6.projet.star.game.edge.Edge;
import l3s6.projet.star.game.edge.Topology;
import l3s6.projet.star.game.tile.Direction;
import l3s6.projet.star.game.tile.Tile;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MeepleTest {
    @Test
    public void testGetZone(){
        Tile tile = new Tile(new Edge(Topology.FIELD), new Edge(Topology.FIELD), new Edge(Topology.FIELD), new Edge(Topology.FIELD));
        Meeple meeple = new Meeple(tile.getZoneAt(Direction.TOP, 0));

        assertEquals(tile.getZoneAt(Direction.TOP, 0), meeple.getZone());
    }

    @Test
    public void testGetTopology(){
        Tile tile = new Tile(new Edge(Topology.FIELD), new Edge(Topology.FIELD), new Edge(Topology.FIELD), new Edge(Topology.FIELD));
        Meeple meeple = new Meeple(tile.getZoneAt(Direction.TOP, 0));

        assertEquals(Topology.FIELD, meeple.getTopology());
    }
}
