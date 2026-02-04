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

    public void connectTo(Zone zone) throws WrongTopologyException {
        if(this.getTopology()!=zone.getTopology()){
            throw new WrongTopologyException("There are incompatible topologies");
        }
        this.connectingZones.add(zone);
        zone.getConnectingZones().add(this);
    }

    public boolean equals(Object o){ 
  
        if (!(o instanceof Zone)) {
            return false; 
        }
        Zone zone = (Zone) o;
        return zone.getTopology() == this.getTopology();
    }
    
}
