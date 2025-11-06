package nl.tudelft.jpacman.board;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * test class for board
 */
public class BoardTest {
    private Board board;
    private BasicSquare square;

    /**
     * setup
     */
    @BeforeEach
    public void setup() {
        square = new BasicSquare();
        Square[][] grid = new Square[][] { { square } };
        board = new Board(grid);
    }

    /**
     * test the board creation validity
     */
    @Test
    void testBoardSizeOne() {
        // Board should not be null
        assertThat(board).isNotNull();

        // Board should return the same square we created
        assertThat(board.squareAt(0, 0)).isEqualTo(square);

        // The square should not be null
        assertThat(board.squareAt(0, 0)).isNotNull();

        // The width and height should both  be 1
        assertThat(board.getWidth()).isEqualTo(1);
        assertThat(board.getHeight()).isEqualTo(1);
    }

    /**
     * test null square
     * @throws AssertionError
     */
    @Test
    void testBoardWithNullSquare() {
        // Create a board grid with a null square
        Square[][] invalidGrid = new Square[][] { { null } };

        assertThrows(AssertionError.class, () -> {
            new Board(invalidGrid);
        });
    }
}
