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

    public Edge(Zone firstZone, List<Zone> zones){
        this.zones = new ArrayList<>();
        this.zones.add(firstZone);
        for (Zone z : zones){
            this.zones.add(z);
        }
    }

    public List<Zone> getZones(){
        return this.zones;
    }

    public Zone getZoneAt(int i){
        return this.zones.get(i);
    }

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

    public void connectTwoZones(int i, Zone z) throws WrongTopologyException{
        this.getZoneAt(i).addConnectedZone(z);
        z.addConnectedZone(this.getZoneAt(i));
    }

    public String toString(){
        String res = "";
        for (Zone z : this.zones){
            res += z.toString();
        }
        return res;
    }

}
