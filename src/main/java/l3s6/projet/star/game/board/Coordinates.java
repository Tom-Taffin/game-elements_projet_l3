package l3s6.projet.star.game.board;

import java.util.HashSet;
import java.util.Set;

import org.javatuples.Pair;

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

    public Coordinates(Pair<Integer,Integer> pair){
        this(pair.getValue0(), pair.getValue1());
    }

    /**
     * @return the coordinates that are to the right
     */
    public Coordinates rightCoordinates(){
        return new Coordinates(this.x + 1, this.y);
    }

    /**
     * @return the coordinates that are to the left
     */
    public Coordinates leftCoordinates(){
        return new Coordinates(this.x - 1, this.y);
    }

    /**
     * @return the coordinates that are above
     */
    public Coordinates upCoordinates(){
        return new Coordinates(this.x, this.y + 1);
    }

    /**
     * @return the coordinates that are below
     */
    public Coordinates downCoordinates(){
        return new Coordinates(this.x, this.y - 1);
    }

    /**
     * @return an set of all the coordinates that are adjacent
     */
    public Set<Coordinates> getAdjacentCoordinates(){
        Set<Coordinates> res = new HashSet<>();
        res.add(this.upCoordinates());
        res.add(this.rightCoordinates());
        res.add(this.downCoordinates());
        res.add(this.leftCoordinates());
        return res;
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
