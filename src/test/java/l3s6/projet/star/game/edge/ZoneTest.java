package l3s6.projet.star.game.edge;

import org.junit.jupiter.api.Test;

import l3s6.projet.star.game.board.Board;
import l3s6.projet.star.game.board.Coordinates;
import l3s6.projet.star.game.meeple.AlreadyHaveMeepleException;
import l3s6.projet.star.game.meeple.Meeple;
import l3s6.projet.star.game.meeple.NoMeepleException;
import l3s6.projet.star.game.player.Player;
import l3s6.projet.star.game.tile.Direction;
import l3s6.projet.star.game.tile.Tile;
import l3s6.projet.star.game.tile.TileBuilder;
import l3s6.projet.star.game.tile.WrongTileSyntaxException;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashSet;
import java.util.Set;

public class ZoneTest {
    @Test
    public void testConnectZone() throws WrongTopologyException{
        
        Zone zone1 = new Zone(Topology.FIELD);
        Zone zone2 = new Zone(Topology.FIELD);

        assertTrue(zone1.isNotConnected());
        assertTrue(zone2.isNotConnected());

        zone1.connectTo(zone2);

        assertTrue(zone1.isConnectedTo(zone2));
        assertTrue(zone2.isConnectedTo(zone1));
        assertFalse(zone1.isNotConnected());
        assertFalse(zone2.isNotConnected());
    }

    @Test
    public void testConnectZoneWithDifferentTopologies(){
        Zone zone1 = new Zone(Topology.CITY);
        Zone zone2 = new Zone(Topology.FIELD);

        assertThrows(WrongTopologyException.class, ()->{
            zone1.connectTo(zone2);
        });
    }

    @Test
    public void testGetAllBoardConnectingZones() throws WrongTileSyntaxException{
        Board board = new Board();
        TileBuilder tileBuilder = new TileBuilder();
        Tile tile1 = tileBuilder.build("Nc1-f0r0c1-f0-f0");
        Tile tile2 = tileBuilder.build("Nf1-f0-f0-c1");
        Tile tile3 = tileBuilder.build("Nf0-f0-f1-c1");
        Tile tile4 = tileBuilder.build("Nf0-f0-f0-f0");
        Tile tile5 = tileBuilder.build("Nc0-c0-c0-c0r0f1");
        Tile tile6 = tileBuilder.build("Nc1-c0-c0-c1");

        board.putTileAt(tile1, new Coordinates(10,10));
        board.putTileAt(tile2, new Coordinates(10,10).leftCoordinates());
        board.putTileAt(tile3, new Coordinates(10,10).leftCoordinates().downCoordinates());
        board.putTileAt(tile4, new Coordinates(10,10).downCoordinates());
        board.putTileAt(tile5, new Coordinates(10,10).rightCoordinates());
        board.putTileAt(tile6, new Coordinates(10,10).upCoordinates());

        Set<Zone> visitedZones = tile1.getZoneAt(Direction.LEFT, 0).getAllBoardConnectingZones();

        Set<Zone> expectedZones = new HashSet<>();
        expectedZones.add(tile1.getZoneAt(Direction.RIGHT, 0));
        expectedZones.add(tile1.getZoneAt(Direction.BOTTOM, 0));
        expectedZones.add(tile1.getZoneAt(Direction.LEFT, 0));
        expectedZones.add(tile2.getZoneAt(Direction.RIGHT, 0));
        expectedZones.add(tile2.getZoneAt(Direction.BOTTOM, 0));
        expectedZones.add(tile3.getZoneAt(Direction.TOP, 0));
        expectedZones.add(tile3.getZoneAt(Direction.RIGHT, 0));
        expectedZones.add(tile4.getZoneAt(Direction.TOP, 0));
        expectedZones.add(tile4.getZoneAt(Direction.RIGHT, 0));
        expectedZones.add(tile4.getZoneAt(Direction.BOTTOM, 0));
        expectedZones.add(tile4.getZoneAt(Direction.LEFT, 0));
        expectedZones.add(tile5.getZoneAt(Direction.LEFT, 2));

        assertEquals(expectedZones.size(), visitedZones.size());
        assertTrue(visitedZones.containsAll(expectedZones));
        
    }

    @Test
    public void testGiveBackMeeple() throws NoMeepleException, WrongTopologyException, AlreadyHaveMeepleException{
        Zone zone = new Zone(Topology.CITY);
        Player player = new Player("Sam", 2);
        Meeple meeple = new Meeple(player, new Coordinates(0,0));
        
        assertFalse(zone.hasMeeple());
        assertEquals(2, player.getNbMeeples());
        zone.setMeeple(meeple);
        assertEquals(1, player.getNbMeeples());
        assertTrue(zone.hasMeeple());
        zone.giveBackMeeple();
        assertEquals(2, player.getNbMeeples());
        assertFalse(zone.hasMeeple());
        
    }

    @Test
    public void testPlaceMeepleOnField(){
        Zone zone = new Zone(Topology.FIELD);
        Player player = new Player("Sam", 2);
        Meeple meeple = new Meeple(player,new Coordinates(0,0));
        assertThrows(WrongTopologyException.class, () -> {
            zone.setMeeple(meeple);
        });
    }

    @Test
    public void testSetMeepleOnZoneWithMeeple() throws WrongTopologyException, AlreadyHaveMeepleException{
        Zone zone = new Zone(Topology.CITY);
        Player player = new Player("Sam", 2);
        Meeple meeple = new Meeple(player,new Coordinates(0,0));
        zone.setMeeple(meeple);
        assertThrows(AlreadyHaveMeepleException.class, () -> {
            zone.setMeeple(meeple);
        });
    }

    @Test
    public void testGiveBackNoMeeple(){
        Zone zone = new Zone(Topology.CITY);
        assertThrows(NoMeepleException.class, () -> {
            zone.giveBackMeeple();
        });
    }
}
