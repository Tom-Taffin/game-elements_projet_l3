package l3s6.projet.star.game.edge;

import java.util.ArrayList;
import java.util.List;

public class Edge {

    protected List<Zone> zones;

    public Edge(Zone firstZone, List<Zone> zones){
        this.zones = new ArrayList<>();
        this.zones.add(firstZone);
        for (Zone z : zones){
            this.zones.add(z);
        }
    }
    
    public Edge(Zone firstZone, Zone... zones){
        this.zones = new ArrayList<>();
        this.zones.add(firstZone);
        for (Zone z : zones){
            this.zones.add(z);
        }
    }

    public Edge(Topology firstTopology, Topology... topologies){
        this.zones = new ArrayList<>();
        this.zones.add(new Zone(firstTopology));
        for (Topology t : topologies){
            this.zones.add(new Zone(t));
        }
    }

    public List<Zone> getZones(){
        return this.zones;
    }

    public List<Topology> getZoneTopologies(){
        List<Topology> topologies = new ArrayList<>();
        for (Zone z : this.zones){
            topologies.add(z.getTopology());
        }
        return topologies;
    }

    public Zone getZoneAt(int i){
        return this.zones.get(i);
    }

    /**
     * @return the number of zones
     */
    public int getSize(){
        return this.zones.size();
    }

    public boolean isCompatibleWith(Edge otherEdge){
        if (this.getSize() != otherEdge.getSize()){
            return false;
        }
        int size = this.getSize();
        for (int i = 0; i < size; i++){
            if (this.getZoneAt(i).getTopology() != otherEdge.getZoneAt(size-i-1).getTopology()){
                return false;
            }
        }
        return true;
    }

    public String toString(){
        String res = "";
        for (Zone z : this.zones){
            res += z.toString();
        }
        return res;
    }

}
