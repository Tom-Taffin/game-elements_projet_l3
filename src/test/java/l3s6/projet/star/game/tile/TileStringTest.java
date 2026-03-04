package l3s6.projet.star.game.tile;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import l3s6.projet.star.game.edge.*;

public class TileStringTest {

    @Test
    public void testTileNoConnectionsRepresentation() {
        Tile tileScratch = new Tile(new Edge(new Zone(Topology.FIELD)), 
                                    new Edge(new Zone(Topology.FIELD), new Zone(Topology.ROAD), new Zone(Topology.CITY)),
                                    new Edge(new Zone(Topology.CITY), new Zone(Topology.FIELD)), 
                                    new Edge(new Zone(Topology.FIELD), new Zone(Topology.ROAD), new Zone(Topology.FIELD)),
                                    Orientation.WEST);
        assertEquals("Wf1-f2r1c1-c2f3-f4r2f5", tileScratch.toString());
    }

    @Test
    public void testTileWithConnectionsRepresentation() throws WrongTileSyntaxException {
        String tileRepresentation = "Wf1-f1r1c1-c1f2-f2r1f1";
        Tile tileBuilt = new TileBuilder().build(tileRepresentation);
        assertEquals(tileRepresentation, tileBuilt.toString());
    }

}
