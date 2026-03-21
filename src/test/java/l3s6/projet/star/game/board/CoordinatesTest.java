package l3s6.projet.star.game.board;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Set;

public class CoordinatesTest {

    @Test
    public void testEquals(){
        assertEquals(new Coordinates(1, 2), new Coordinates(1, 2));
        assertNotEquals(new Coordinates(1, 1), new Coordinates(1, 2));
    }

    @Test
    public void testGetAdjacentCoordinates(){
        Coordinates origin = new Coordinates(0, 0);
        ArrayList<Coordinates> exeptedList = new ArrayList<>();
        exeptedList.add(new Coordinates(0, 1));
        exeptedList.add(new Coordinates(1, 0));
        exeptedList.add(new Coordinates(0, -1));
        exeptedList.add(new Coordinates(-1, 0));
        assertEquals(exeptedList, origin.getAdjacentCoordinates());
    }

    @Test
    public void testGetAdjacentAndCornerCoordinates(){
        Coordinates origin = new Coordinates(0, 0);
        Set<Coordinates> adjacentAndCorner = origin.getAdjacentAndCornerCoordinates();
        
        assertEquals(8, adjacentAndCorner.size());
        assertTrue(adjacentAndCorner.contains(new Coordinates(-1, -1)));
        assertTrue(adjacentAndCorner.contains(new Coordinates(-1, 0)));
        assertTrue(adjacentAndCorner.contains(new Coordinates(-1, 1)));
        assertTrue(adjacentAndCorner.contains(new Coordinates(0, -1)));
        assertTrue(adjacentAndCorner.contains(new Coordinates(0, 1)));
        assertTrue(adjacentAndCorner.contains(new Coordinates(1, -1)));
        assertTrue(adjacentAndCorner.contains(new Coordinates(1, 0)));
        assertTrue(adjacentAndCorner.contains(new Coordinates(1, 1)));

        assertFalse(adjacentAndCorner.contains(new Coordinates(0, 0)));
    }
}
