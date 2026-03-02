package l3s6.projet.star.game.edge;

public enum Topology {
    FIELD, CITY, ROAD;

    public String toString(){
        switch (this) {
            case FIELD:
                return "f";
            case CITY:
                return "c";
            case ROAD:
                return "r";
            default:
                return "f";
        }
    }
}
