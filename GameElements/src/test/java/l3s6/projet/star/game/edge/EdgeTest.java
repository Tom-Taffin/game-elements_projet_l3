package l3s6.projet.star.game.edge;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class EdgeTest {
    @Test
    public void testEdgeNoRoadConnectEdgeNoRoadSameZone(){
        Edge noRoadEdge1 = new EdgeNoRoad(new Zone(Topology.FIELD));
        Edge noRoadEdge2 = new EdgeNoRoad(new Zone(Topology.FIELD));

        assertTrue(noRoadEdge1.isCompatibleWith(noRoadEdge2));
    }

    @Test
    public void testEdgeNoRoadConnectEdgeNoRoadDifferentZone(){
        Edge noRoadEdge1 = new EdgeNoRoad(new Zone(Topology.FIELD));
        Edge noRoadEdge2 = new EdgeNoRoad(new Zone(Topology.CITY));

        assertFalse(noRoadEdge1.isCompatibleWith(noRoadEdge2));
    }

    @Test
    public void testEdgeWithRoadConnectEdgeWithRoadSameZones(){
        Edge edgeWithRoad1 = new EdgeWithRoad(new Zone(Topology.FIELD), new Zone(Topology.CITY));
        Edge edgeWithRoad2 = new EdgeWithRoad(new Zone(Topology.CITY), new Zone(Topology.FIELD));

        assertTrue(edgeWithRoad1.isCompatibleWith(edgeWithRoad2));
    }

    @Test
    public void testEdgeWithRoadConnectEdgeWithRoadDifferentZones(){
        Edge edgeWithRoad1 = new EdgeWithRoad(new Zone(Topology.FIELD), new Zone(Topology.CITY));
        Edge edgeWithRoad2 = new EdgeWithRoad(new Zone(Topology.FIELD), new Zone(Topology.CITY));

        assertFalse(edgeWithRoad1.isCompatibleWith(edgeWithRoad2));
    }

    @Test
    public void testEdgeWithRoadConnectEdgeNoRoad(){
        Edge edgeWithRoad = new EdgeWithRoad(new Zone(Topology.FIELD), new Zone(Topology.FIELD));
        Edge noRoadEdge = new EdgeNoRoad(new Zone(Topology.FIELD));

        assertFalse(edgeWithRoad.isCompatibleWith(noRoadEdge));
    }

    @Test
    public void testEdgeNoRoadConnectEdgeWithRoad(){
        Edge edgeWithRoad = new EdgeWithRoad(new Zone(Topology.FIELD), new Zone(Topology.FIELD));
        Edge noRoadEdge = new EdgeNoRoad(new Zone(Topology.FIELD));

        assertFalse(noRoadEdge.isCompatibleWith(edgeWithRoad));
    }
}