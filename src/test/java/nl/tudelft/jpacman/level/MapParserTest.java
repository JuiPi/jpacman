package nl.tudelft.jpacman.level;

import nl.tudelft.jpacman.board.BoardFactory;
import nl.tudelft.jpacman.npc.ghost.Blinky;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertNotNull;


/**
 * This is a test class for MapParser.
 */
@ExtendWith(MockitoExtension.class)
public class MapParserTest {
    @Mock
    private BoardFactory boardFactory;
    @Mock
    private LevelFactory levelFactory;
    @Mock
    private Blinky blinky;

    /**
     * Test for the parseMap method (good map).
     */
    @Test
    public void testParseMapGood() {
        MockitoAnnotations.initMocks(this);
        assertNotNull(boardFactory);
        assertNotNull(levelFactory);
        Mockito.when(levelFactory.createGhost()).thenReturn(blinky);
        MapParser mapParser = new MapParser(levelFactory, boardFactory);
        ArrayList<String> map = new ArrayList<>();
        map.add("############");
        map.add("#P        G#");
        map.add("############");
        mapParser.parseMap(map);

        // Expected number of elements
        final int expectedGhosts = 1;
        final int expectedWalls = 26;  // 12 + 2 + 12
        final int expectedGrounds = 10; // P, G, and 8 spaces
        final int expectedPellets = 0;  // '.' not present


        // Verification
        Mockito.verify(levelFactory, Mockito.times(expectedGhosts)).createGhost();
        Mockito.verify(boardFactory, Mockito.times(expectedWalls)).createWall();
        Mockito.verify(boardFactory, Mockito.times(expectedGrounds)).createGround();
        Mockito.verify(levelFactory, Mockito.times(expectedPellets)).createPellet();
    }

}
