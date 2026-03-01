package l3s6.projet.star.game.edge;

import java.util.HashSet;
import java.util.Set;

public class Zone {
    private Topology topology;
    private Set<Zone> connectingZones;

    public Zone(Topology topology) {
        this.topology = topology;
        connectingZones = new HashSet<>();
    }

    public Topology getTopology() {
        return topology;
    }

    public Set<Zone> getConnectingZones() {
        return connectingZones;
    }

    /** add an other connected zone to this zone
     * @param zone
     * @throws WrongTopologyException if the topology of the other zone is incompatible
     */
    public void addConnectedZone(Zone zone) throws WrongTopologyException {
        if(this.getTopology()!=zone.getTopology()){
            throw new WrongTopologyException("There are incompatible topologies");
        }
        this.connectingZones.add(zone);
    }

    public boolean isConnectedTo(Zone zone){
        return this.connectingZones.contains(zone);
    }

    /**
     * @return true if this zone is not connected to anything
     */
    public boolean isFinished() {
        return this.connectingZones.isEmpty();
    }
    
}
