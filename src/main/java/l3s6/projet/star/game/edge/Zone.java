package l3s6.projet.star.game.edge;

import java.util.HashSet;
import java.util.Set;

import l3s6.projet.star.game.meeple.Meeple;

public class Zone {
    private Topology topology;
    private Meeple meeple = null;

    private Set<Zone> connectingZones;
    private Zone adjacentZone = null;

    public Zone(Topology topology) {
        this.topology = topology;
        connectingZones = new HashSet<>();
    }

    public Meeple getMeeple() {
        return meeple;
    }

    /**
     * set meeple into this zone and decrement amout of player's meeple
     */
    public void setMeeple(Meeple meeple) {
        this.meeple = meeple;
        this.meeple.decrementPlayerMeeple();
    }

    public boolean hasMeeple(){
        return meeple != null;
    }

    /**
     * remove meeple into this zone and increment amout of player's meeple
     */
    public void giveBackMeeple() throws NoMeepleException{
        if(!this.hasMeeple()){
            throw new NoMeepleException("There is no meeple in this zone");
        }
        this.meeple.incrementPlayerMeeple();
        this.meeple = null;
    }

    public Zone getAdjacentZone() {
        return adjacentZone;
    }

    public Boolean hasAdjacentZone(){
        return adjacentZone != null;
    }

    public void setAdjacentZone(Zone zone) {
        this.adjacentZone = zone;
        zone.adjacentZone = this;
    }

    public Topology getTopology() {
        return topology;
    }

    public Set<Zone> getConnectingZones() {
        return connectingZones;
    }

    /** connect this zone to an other zone
     * @throws WrongTopologyException if the topology of the other zone is incompatible
     */
    public void connectTo(Zone zone) throws WrongTopologyException {
        if(this.getTopology()!=zone.getTopology()){
            throw new WrongTopologyException("There are incompatible topologies");
        }
        this.connectingZones.add(zone);
        zone.getConnectingZones().add(this);
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

    /**
     * @return all the connecting zones in the board including this zone
     */
    public Set<Zone> getAllBoardConnectingZones(){
        Set<Zone> visitedZones = new HashSet<>();
        this.getAllBoardConnectingZones(visitedZones);
        return visitedZones;
    }

    public void getAllBoardConnectingZones(Set<Zone> visitedZones) {
        if(!visitedZones.contains(this)){
            visitedZones.add(this);
            for(Zone zone : this.getConnectingZones()){
                zone.getAllBoardConnectingZones(visitedZones);
            }
            if(this.hasAdjacentZone()){
                this.getAdjacentZone().getAllBoardConnectingZones(visitedZones);
            }
        }
    }
    
    public String toString(){
        return this.topology.toString();
    }

}
