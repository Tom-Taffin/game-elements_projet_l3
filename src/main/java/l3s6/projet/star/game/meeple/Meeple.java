package l3s6.projet.star.game.meeple;

import l3s6.projet.star.game.edge.Topology;
import l3s6.projet.star.game.edge.Zone;

public class Meeple {
    private final Zone zone;

    public Meeple(Zone zone) {
        this.zone = zone;
    }

    public Zone getZone() {
        return zone;
    }

    public Topology getTopology(){
        return zone.getTopology();
    }
}
