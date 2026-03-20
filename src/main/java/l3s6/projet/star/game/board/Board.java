package l3s6.projet.star.game.board;

import l3s6.projet.star.game.tile.*;
import org.javatuples.Pair;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Board {

    private final static String FIRST_TILE = "Nc1-f2r3f4-f4-f4r3f2";

    protected Map<Pair<Integer, Integer>, Tile> tiles;
    protected int minX;
    protected int maxX;
    protected int minY;
    protected int maxY;

    public Board(){
        this.minX = 0;
        this.maxX = 0;
        this.minY = 0;
        this.maxY = 0;

        this.tiles = new HashMap<>();
        Tile firstTile;
        try {
            firstTile = new TileBuilder().build(FIRST_TILE);
        } catch (WrongTileSyntaxException e) {
            throw new RuntimeException("Wrong first tile syntax");
        }
        this.putTileAt(firstTile, new Coordinates(0, 0));
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

    public Collection<Tile> getTiles(){
        return this.tiles.values();
    }

    /**
     * Updates min and max coordinates.
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

        for (Direction direction : Direction.values()){
            this.setAdjacentZones(tile, coord, direction);
        }

        this.tiles.put(new Pair<>(coord.getX(), coord.getY()), tile);
    }

    /**
     * remove the tile at the given coordinates.
     */
    public void removeTileAt(Coordinates coord){
        Tile tile = this.getTileAt(coord);
        for (Direction direction : Direction.values()){
            this.removeAdjacentZones(tile, coord, direction);
        }
        this.tiles.remove(new Pair<>(coord.getX(), coord.getY()));
    }

    /**
     * set adjacent zone for all the zone at the direction edge of this tile located at coord
     */
    private void setAdjacentZones(Tile tile, Coordinates coord, Direction direction) {
        if (this.hasTile(coord.getAdjacent(direction))){
            Tile otherTile = this.getTileAt(coord.getAdjacent(direction));
            int nbZones = tile.getEdge(direction).getSize();
            for (int i = 0 ; i < nbZones ; i++){
                tile.getZoneAt(direction, i).setAdjacentZone(otherTile.getZoneAt(direction.toOpposite(), nbZones-i-1));
            }
        }
    }

    /**
     * remove adjacent zone for all the zone at the direction edge of this tile located at coord
     */
    private void removeAdjacentZones(Tile tile, Coordinates coord, Direction direction) {
        if (this.hasTile(coord.getAdjacent(direction))){
            Tile otherTile = this.getTileAt(coord.getAdjacent(direction));
            int nbZones = tile.getEdge(direction).getSize();
            for (int i = 0 ; i < nbZones ; i++){
                tile.getZoneAt(direction, i).removeAdjacentZone(otherTile.getZoneAt(direction.toOpposite(), nbZones-i-1));
            }
        }
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
     * @return all possible location coordinates for a new tile
     */
    public Set<Coordinates> getOutsideFrontierTiles(){
        Set<Coordinates> res = new HashSet<>();
        for (Pair<Integer, Integer> coordPair : this.tiles.keySet()){
            for (Coordinates coord : new Coordinates(coordPair).getAdjacentCoordinates()){
                if (!this.hasTile(coord)){
                    res.add(coord);
                }
            }
        }
        return res;
    }
}
