package l3s6.projet.star.game.edge;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ZoneTest {
    @Test
    public void testConnectZone() throws WrongTopologyException{
        Zone zone1 = new Zone(Topology.FIELD);
        Zone zone2 = new Zone(Topology.FIELD);

        zone1.connectTo(zone2);

        assertTrue(zone1.getConnectingZones().contains(zone2));
        assertTrue(zone2.getConnectingZones().contains(zone1));
    }
}
