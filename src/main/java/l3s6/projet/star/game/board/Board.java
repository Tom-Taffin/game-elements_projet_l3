package l3s6.projet.star.game.board;

import l3s6.projet.star.game.tile.*;
import org.javatuples.Pair;

import java.util.HashMap;
import java.util.Map;

public class Board {
    private final Map<Pair<Integer, Integer>, Tile> tiles;

    public Board(){
        this.tiles = new HashMap<>();
    }

    /**
     * Puts the given tile at the given coordinates.
     * @param tile
     * @param coord
     */
    public void putTileAt(Tile tile, Coordinates coord){
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

    /**
     * Remove the tile at the given coordinates, then returns it.
     * @param coord
     * @return the removed tile
     */
    public Tile removeTileFrom(Coordinates coord){
        return this.tiles.remove(new Pair<>(coord.getX(), coord.getY()));
    }
}
