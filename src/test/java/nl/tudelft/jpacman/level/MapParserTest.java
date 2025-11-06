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

    final int EXPECTED_GHOSTS = 1;
    final int EXPECTED_WALLS = 26;  // 12 + 2 + 12
    final int EXPECTED_GROUNDS = 10; // P, G, and 8 spaces
    final int EXPECTED_PELLETS = 0;  // '.' not present

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

        // Verification for Ghost creation (requested by user)
        Mockito.verify(levelFactory, Mockito.times(1)).createGhost();

        // Verify Wall creation ('#' is present 12 + 2 + 12 = 26 times)
        Mockito.verify(boardFactory, Mockito.times(26)).createWall();

        // Verify Ground creation (10 non-wall tiles: P, G, and 8 spaces)
        Mockito.verify(boardFactory, Mockito.times(10)).createGround();

        // Verify Pellet creation (Pellet '.' is not present)
        Mockito.verify(levelFactory, Mockito.times(0)).createPellet();
    }

}
