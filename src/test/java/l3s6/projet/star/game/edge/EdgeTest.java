package l3s6.projet.star.game.edge;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class EdgeTest {
    @Test
    public void testEdgeOneZoneConnectSameTopology(){
        Edge EdgeOneZone1 = new Edge(new Zone(Topology.FIELD));
        Edge EdgeOneZone2 = new Edge(new Zone(Topology.FIELD));

        assertTrue(EdgeOneZone1.isCompatibleWith(EdgeOneZone2));
    }

    @Test
    public void testEdgeOneZoneConnectDifferentTopologies(){
        Edge EdgeOneZone1 = new Edge(new Zone(Topology.FIELD));
        Edge EdgeOneZone2 = new Edge(new Zone(Topology.CITY));

        assertFalse(EdgeOneZone1.isCompatibleWith(EdgeOneZone2));
    }

    @Test
    public void testEdgeManyZonesConnectSameTopology(){
        Edge EdgeManyZones1 = new Edge(new Zone(Topology.FIELD), new Zone(Topology.ROAD), new Zone(Topology.FIELD));
        Edge EdgeManyZones2 = new Edge(new Zone(Topology.FIELD), new Zone(Topology.ROAD), new Zone(Topology.FIELD));

        assertTrue(EdgeManyZones1.isCompatibleWith(EdgeManyZones2));
    }

    @Test
    public void testEdgeManyZonesConnectDifferentTopologies(){
        Edge EdgeManyZones1 = new Edge(new Zone(Topology.FIELD), new Zone(Topology.ROAD), new Zone(Topology.FIELD));
        Edge EdgeManyZones2 = new Edge(new Zone(Topology.CITY), new Zone(Topology.ROAD), new Zone(Topology.FIELD));

        assertFalse(EdgeManyZones1.isCompatibleWith(EdgeManyZones2));
    }

    @Test
    public void testEdgeWithDifferentZoneSizes(){
        Edge EdgeManyZones1 = new Edge(new Zone(Topology.FIELD), new Zone(Topology.ROAD), new Zone(Topology.FIELD));
        Edge EdgeManyZones2 = new Edge(new Zone(Topology.ROAD), new Zone(Topology.FIELD));

        assertFalse(EdgeManyZones1.isCompatibleWith(EdgeManyZones2));
    }

    @Test
    public void connectTwoZonesSameTopology() throws WrongTopologyException{
        Edge edge1 = new Edge(new Zone(Topology.FIELD), new Zone(Topology.ROAD));
        Zone zone = new Zone(Topology.FIELD);

        int i = 0;
        edge1.connectTwoZones(i, zone);

        assertTrue(edge1.getZoneAt(i).isConnectedTo(zone));
        assertTrue(zone.isConnectedTo(edge1.getZoneAt(i)));
    }

    @Test
    public void connectTwoZonesDifferentTopology() throws WrongTopologyException{
        Edge edge1 = new Edge(new Zone(Topology.FIELD), new Zone(Topology.ROAD));
        Zone zone = new Zone(Topology.ROAD);

        int i = 0;
        assertThrows(WrongTopologyException.class, () -> {edge1.connectTwoZones(i, zone);});

        assertFalse(edge1.getZoneAt(i).isConnectedTo(zone));
        assertFalse(zone.isConnectedTo(edge1.getZoneAt(i)));
    }

}