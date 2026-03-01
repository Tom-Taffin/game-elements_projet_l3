package l3s6.projet.star.game.edge;

import java.util.ArrayList;
import java.util.List;

public class Edge {

    protected List<Zone> zones;

    public Edge(Zone firstZone, Zone... zones){
        this.zones = new ArrayList<>();
        this.zones.add(firstZone);
        for (Zone z : zones){
            this.zones.add(z);
        }
    }

    public List<Zone> getZones(){
        return this.zones;
    }

    public boolean isCompatibleWith(Edge otherEdge){
        if (this.zones.size() != otherEdge.getZones().size()){
            return false;
        }
        int size = this.zones.size();
        for (int i = 0; i < size; i++){
            if (this.zones.get(i).getTopology() != otherEdge.getZones().get(size-i-1).getTopology()){
                return false;
            }
        }
        return true;
    }

}
