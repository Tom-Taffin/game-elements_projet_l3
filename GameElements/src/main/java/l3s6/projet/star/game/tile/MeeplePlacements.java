package l3s6.projet.star.game.tile;

import l3s6.projet.star.game.edge.Edge;
import l3s6.projet.star.game.meeple.Meeple;

import java.util.HashMap;
import java.util.Map;

public class MeeplePlacements {
    private final Map<Edge, Meeple> meeplePlacements;

    public MeeplePlacements(){
        this.meeplePlacements = new HashMap<>();
    }

    public void placeMeepleOnZone(Meeple meeple, Edge zone){
        this.meeplePlacements.put(zone, meeple);
    }

    public void placeMeepleOnRoad(Meeple meeple, Edge zone){

    }

    public Meeple getMeepleOnZone(Edge zone) {
        return this.meeplePlacements.get(zone);
    }

    public boolean hasMeepleOnZone(Edge zone) {
        return this.meeplePlacements.containsKey(zone);
    }
}
