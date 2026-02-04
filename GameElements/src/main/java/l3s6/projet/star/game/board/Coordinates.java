package l3s6.projet.star.game.board;

import l3s6.projet.star.game.edge.Zone;

public class Coordinates {
    /**
     * The y coordinate is positive when it goes up, and negative when it goes down.
     */
    private final int x;
    private final int y;

    public Coordinates(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Coordinates rightCoordinates(){
        return new Coordinates(this.x + 1, this.y);
    }

    public Coordinates leftCoordinates(){
        return new Coordinates(this.x - 1, this.y);
    }

    public Coordinates upCoordinates(){
        return new Coordinates(this.x, this.y + 1);
    }

    public Coordinates downCoordinates(){
        return new Coordinates(this.x, this.y - 1);
    }

    public int getY() {
        return y;
    }

    public int getX() {
        return x;
    }

    public boolean equals(Object o){

        if (!(o instanceof Coordinates)) {
            return false;
        }
        Coordinates coordinates = (Coordinates) o;
        return (coordinates.getX() == this.x) && (coordinates.getY() == this.y);
    }
}
