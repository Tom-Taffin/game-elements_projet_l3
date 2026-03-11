package l3s6.projet.star.game.board;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashSet;
import java.util.Set;

public class CoordinatesTest {

    @Test
    public void testEquals(){
        assertEquals(new Coordinates(1, 2), new Coordinates(1, 2));
        assertNotEquals(new Coordinates(1, 1), new Coordinates(1, 2));
    }

    @Test
    public void testGetOutsideFrontierTiles(){
        Coordinates origin = new Coordinates(0, 0);
        Set<Coordinates> exeptedSet = new HashSet<>();
        exeptedSet.add(new Coordinates(0, 1));
        exeptedSet.add(new Coordinates(1, 0));
        exeptedSet.add(new Coordinates(0, -1));
        exeptedSet.add(new Coordinates(-1, 0));
        assertEquals(exeptedSet, origin.getAdjacentCoordinates());
    }
}
