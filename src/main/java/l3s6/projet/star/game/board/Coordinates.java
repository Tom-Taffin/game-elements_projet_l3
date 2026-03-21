package l3s6.projet.star.game.board;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.javatuples.Pair;

import l3s6.projet.star.game.tile.Direction;

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

    public int getY() {
        return y;
    }

    public int getX() {
        return x;
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
     * @return the adjacent coordinates at the direction
     */
    public Coordinates getAdjacent(Direction direction){
        switch (direction) {
            case TOP:
                return upCoordinates();
            case RIGHT:
                return rightCoordinates();
            case LEFT:
                return leftCoordinates();
            default:
                return downCoordinates();
        }
    }

    /**
     * @return an List of all the coordinates that are adjacent
     */
    public List<Coordinates> getAdjacentCoordinates(){
        List<Coordinates> res = new ArrayList<>();
        res.add(this.upCoordinates());
        res.add(this.rightCoordinates());
        res.add(this.downCoordinates());
        res.add(this.leftCoordinates());
        return res;
    }

    /**
     * @return an Set of all the coordinates that are adjacent and at the corner
     */
    public Set<Coordinates> getAdjacentAndCornerCoordinates(){
        Set<Coordinates> res = new HashSet<>();
        for (int dx = -1; dx <= 1; dx++) {
            for (int dy = -1; dy <= 1; dy++) {
                if (dx == 0 && dy == 0) continue;
                res.add(new Coordinates(this.x + dx, this.y + dy));
            }
        }
        return res;
    }

    public boolean equals(Object o){

        if (!(o instanceof Coordinates)) {
            return false;
        }
        Coordinates coordinates = (Coordinates) o;
        return (coordinates.getX() == this.x) && (coordinates.getY() == this.y);
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(x) + Integer.hashCode(y) * 31;
    }

    public String toString(){
        return "(" + this.x + "," + this.y + ")";
    }
}
