package l3s6.projet.star.game.meeple;

import l3s6.projet.star.game.edge.Topology;
import l3s6.projet.star.game.edge.Zone;

public class Meeple {
    private final Zone zone;
    private final Color color;

    public Meeple(Zone zone, Color color) {
        this.zone = zone;
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    public Zone getZone() {
        return zone;
    }

    public Topology getTopology(){
        return zone.getTopology();
    }
}
