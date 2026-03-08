package l3s6.projet.star.game.board;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

public class CoordinatesTest {

    @Test
    public void testEquals(){
        assertEquals(new Coordinates(1, 2), new Coordinates(1, 2));
        assertNotEquals(new Coordinates(1, 1), new Coordinates(1, 2));
    }

    @Test
    public void testGetOutsideFrontierTiles(){
        Coordinates origin = new Coordinates(0, 0);
        ArrayList<Coordinates> exeptedList = new ArrayList<>();
        exeptedList.add(new Coordinates(0, 1));
        exeptedList.add(new Coordinates(1, 0));
        exeptedList.add(new Coordinates(0, -1));
        exeptedList.add(new Coordinates(-1, 0));
        assertEquals(exeptedList, origin.getOutsideFrontierTiles());
    }
}
