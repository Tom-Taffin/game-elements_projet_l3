package l3s6.projet.star.game.edge;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ZoneTest {
    @Test
    public void testConnectZone() throws WrongTopologyException{
        
        Zone zone1 = new Zone(Topology.FIELD);
        Zone zone2 = new Zone(Topology.FIELD);

        assertTrue(zone1.isFinished());

        zone1.addConnectedZone(zone2);

        assertTrue(zone1.getConnectingZones().contains(zone2));
        assertFalse(zone1.isFinished());
        assertTrue(zone2.isFinished());

    }

    @Test
    public void testConnectZoneWithDifferentTopologies(){
        Zone zone1 = new Zone(Topology.CITY);
        Zone zone2 = new Zone(Topology.FIELD);

        assertThrows(WrongTopologyException.class, ()->{
            zone1.addConnectedZone(zone2);
        });
    }
}
