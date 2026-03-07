package l3s6.projet.star.game.board;

import l3s6.projet.star.game.tile.*;
import org.javatuples.Pair;

import java.util.HashMap;
import java.util.Map;

public class Board {

    private final Map<Pair<Integer, Integer>, Tile> tiles;
    protected int minX;
    protected int maxX;
    protected int minY;
    protected int maxY;

    public Board(){
        this.tiles = new HashMap<>();
        this.minX = 0;
        this.maxX = 0;
        this.minY = 0;
        this.maxY = 0;
    }

    public int getMinX() {
        return minX;
    }

    public int getMaxX() {
        return maxX;
    }

    public int getMinY() {
        return minY;
    }

    public int getMaxY() {
        return maxY;
    }

    /**
     * Updates min and max width and height.
     */
    private void updateSize(Coordinates coord){
        int x = coord.getX();
        int y = coord.getY();
        if (x < minX) minX = x;
        if (x > maxX) maxX = x;
        if (y < minY) minY = y;
        if (y > maxY) maxY = y;
    }

    /**
     * Puts the given tile at the given coordinates.
     * @param tile
     * @param coord
     */
    public void putTileAt(Tile tile, Coordinates coord){
        this.updateSize(coord);
        this.tiles.put(new Pair<>(coord.getX(), coord.getY()), tile);
    }

    /**
     * Returns true if there is a tile at the given coordinates.
     * @param coord
     * @return true if there is a tile at the given coordinates.
     */
    public boolean hasTile(Coordinates coord){
        return this.tiles.containsKey(new Pair<>(coord.getX(), coord.getY()));
    }

    /**
     * Returns the tile at the given coordinates.
     * @param coord
     * @return
     */
    public Tile getTileAt(Coordinates coord){
        return this.tiles.get(new Pair<>(coord.getX(), coord.getY()));
    }
}
