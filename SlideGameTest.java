import static org.junit.Assert.*;
import org.junit.*;

// A class to test all of the slide methods in the SlideGame class
// The tests are done on a 4 x 4 board
public class SlideGameTest {

    // A boolean array passed into all slide methods (unnecessary for this testing but necessary for the SlideGame class so it is used here)
    boolean[][] merges = {{false,false,false,false}, {false,false,false,false}, {false,false,false,false}, {false,false,false,false}};

    // testing the slide left method
    @Test
    public void leftSlideTest() {
        SlideGame slideGame = new SlideGame();
        // testing 0 for a slide left
        int[][] actualBoard = {{0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}};
        int[][] expectedBoard = {{0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}};
        slideGame.slideLeft(actualBoard, merges);
        assertEquals("Testing a left slide with an empty board", expectedBoard, actualBoard);

        // testing 1 for a slide left
        actualBoard = new int[][]{{0, 0, 0, 1}, {0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}};
        expectedBoard = new int[][]{{1, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}};
        slideGame.slideLeft(actualBoard, merges);
        assertEquals("Testing a left slide with a value in one tile", expectedBoard, actualBoard);

        // testing many for a slide left
        actualBoard = new int[][]{{8, 2, 16, 1}, {0, 0, 0, 0}, {1, 0, 2, 1}, {0, 2, 0, 2}};
        expectedBoard = new int[][]{{8, 2, 16, 1}, {0, 0, 0, 0}, {1, 2, 1, 0}, {4, 0, 0, 0}};
        slideGame.slideLeft(actualBoard, merges);
        assertEquals("Testing a left slide with a value in one tile", expectedBoard, actualBoard);

        // testing first for a slide left
        actualBoard = new int[][]{{1, 0, 0, 0}, {1, 0, 0, 0}, {1, 0, 0, 0}, {1, 0, 0, 0}};
        expectedBoard = new int[][]{{1, 0, 0, 0}, {1, 0, 0, 0}, {1, 0, 0, 0}, {1, 0, 0, 0}};
        slideGame.slideLeft(actualBoard, merges);
        assertEquals("Testing the first value for a left slide", expectedBoard, actualBoard);

        // testing middle for a slide left
        actualBoard = new int[][]{{0, 0, 1, 0}, {0, 1, 0, 0}, {0, 0, 1, 0}, {0, 1, 0, 0}};
        expectedBoard = new int[][]{{1, 0, 0, 0}, {1, 0, 0, 0}, {1, 0, 0, 0}, {1, 0, 0, 0}};
        slideGame.slideLeft(actualBoard, merges);
        assertEquals("Testing the middle value for a left slide", expectedBoard, actualBoard);

        // testing last for a slide left
        actualBoard = new int[][]{{0, 0, 0, 1}, {0, 0, 0, 1}, {0, 0, 0, 1}, {0, 0, 0, 1}};
        expectedBoard = new int[][]{{1, 0, 0, 0}, {1, 0, 0, 0}, {1, 0, 0, 0}, {1, 0, 0, 0}};
        slideGame.slideLeft(actualBoard, merges);
        assertEquals("Testing the last value for a left slide", expectedBoard, actualBoard);

        // testing one merge for a left slide
        actualBoard = new int[][]{{1, 0, 0, 1}, {0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}};
        expectedBoard = new int[][]{{2, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}};
        slideGame.slideLeft(actualBoard, merges);
        assertEquals("Testing a merge for a left slide", expectedBoard, actualBoard);

        // testing many merges for a left slide
        actualBoard = new int[][]{{1, 0, 0, 1}, {2, 0, 0, 2}, {1, 1, 0, 1}, {8, 4, 2, 2}};
        expectedBoard = new int[][]{{2, 0, 0, 0}, {4, 0, 0, 0}, {2, 1, 0, 0}, {16, 0, 0, 0}};
        slideGame.slideLeft(actualBoard, merges);
        assertEquals("Testing many merges for a left slide", expectedBoard, actualBoard);
    }

    // testing the slide right method
    @Test
    public void rightSlideTest() {
        SlideGame slideGame = new SlideGame();
        // testing 0 for a slide right
        int[][] actualBoard = {{0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}};
        int[][] expectedBoard = {{0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}};
        slideGame.slideRight(actualBoard, merges);
        assertEquals("Testing a right slide with an empty board", expectedBoard, actualBoard);

        // testing 1 for a slide right
        actualBoard = new int[][]{{1, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}};
        expectedBoard = new int[][]{{0, 0, 0, 1}, {0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}};
        slideGame.slideRight(actualBoard, merges);
        assertEquals("Testing a right slide with a value in one tile", expectedBoard, actualBoard);

        // testing many for a slide right
        actualBoard = new int[][]{{8, 2, 16, 1}, {0, 0, 0, 0}, {1, 2, 0, 1}, {0, 2, 0, 2}};
        expectedBoard = new int[][]{{8, 2, 16, 1}, {0, 0, 0, 0}, {0, 1, 2, 1}, {0, 0, 0, 4}};
        slideGame.slideRight(actualBoard, merges);
        assertEquals("Testing a right slide with a value in one tile", expectedBoard, actualBoard);

        // testing first for a slide right
        actualBoard = new int[][]{{0, 0, 0, 1}, {0, 0, 0, 1}, {0, 0, 0, 1}, {0, 0, 0, 1}};
        expectedBoard = new int[][]{{0, 0, 0, 1}, {0, 0, 0, 1}, {0, 0, 0, 1}, {0, 0, 0, 1}};
        slideGame.slideRight(actualBoard, merges);
        assertEquals("Testing the first value for a right slide", expectedBoard, actualBoard);

        // testing middle for a slide right
        actualBoard = new int[][]{{0, 1, 0, 0}, {0, 0, 1, 0}, {0, 1, 0, 0}, {0, 0, 1, 0}};
        expectedBoard = new int[][]{{0, 0, 0, 1}, {0, 0, 0, 1}, {0, 0, 0, 1}, {0, 0, 0, 1}};
        slideGame.slideRight(actualBoard, merges);
        assertEquals("Testing the middle value for a right slide", expectedBoard, actualBoard);

        // testing last for a slide right
        actualBoard = new int[][]{{1, 0, 0, 0}, {1, 0, 0, 0}, {1, 0, 0, 0}, {1, 0, 0, 0}};
        expectedBoard = new int[][]{{0, 0, 0, 1}, {0, 0, 0, 1}, {0, 0, 0, 1}, {0, 0, 0, 1}};
        slideGame.slideRight(actualBoard, merges);
        assertEquals("Testing the last value for a right slide", expectedBoard, actualBoard);

        // testing one merge for a right slide
        actualBoard = new int[][]{{1, 0, 0, 1}, {0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}};
        expectedBoard = new int[][]{{0, 0, 0, 2}, {0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}};
        slideGame.slideRight(actualBoard, merges);
        assertEquals("Testing a merge for a right slide", expectedBoard, actualBoard);

        // testing many merges for a right slide
        actualBoard = new int[][]{{1, 0, 0, 1}, {2, 0, 0, 2}, {1, 0, 1, 1}, {2, 2, 4, 8}};
        expectedBoard = new int[][]{{0, 0, 0, 2}, {0, 0, 0, 4}, {0, 0, 1, 2}, {0, 0, 0, 16}};
        slideGame.slideRight(actualBoard, merges);
        assertEquals("Testing many merges for a right slide", expectedBoard, actualBoard);
    }

    // testing the slide up method
    @Test
    public void upSlideTest() {
        SlideGame slideGame = new SlideGame();
        // testing 0 for a slide up
        int[][] actualBoard = {{0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}};
        int[][] expectedBoard = {{0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}};
        slideGame.slideUp(actualBoard, merges);
        assertEquals("Testing an up slide with an empty board", expectedBoard, actualBoard);

        // testing 1 for a slide up
        actualBoard = new int[][]{{0, 0, 0, 0}, {1, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}};
        expectedBoard = new int[][]{{1, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}};
        slideGame.slideUp(actualBoard, merges);
        assertEquals("Testing an up slide with a value in one tile", expectedBoard, actualBoard);

        // testing many for a slide up
        actualBoard = new int[][]{{0, 0, 0, 0}, {8, 2, 16, 1}, {1, 2, 0, 1}, {0, 2, 0, 2}};
        expectedBoard = new int[][]{{8, 4, 16, 4}, {1, 2, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}};
        slideGame.slideUp(actualBoard, merges);
        assertEquals("Testing an up slide with a value in one tile", expectedBoard, actualBoard);

        // testing first for a slide up
        actualBoard = new int[][]{{1, 1, 1, 1}, {0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}};
        expectedBoard = new int[][]{{1, 1, 1, 1}, {0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}};
        slideGame.slideUp(actualBoard, merges);
        assertEquals("Testing the first value for an up slide", expectedBoard, actualBoard);

        // testing middle for a slide up
        actualBoard = new int[][]{{0, 0, 0, 0}, {1, 0, 1, 0}, {0, 1, 0, 1}, {0, 0, 0, 0}};
        expectedBoard = new int[][]{{1, 1, 1, 1}, {0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}};
        slideGame.slideUp(actualBoard, merges);
        assertEquals("Testing the middle value for an up slide", expectedBoard, actualBoard);

        // testing last for a slide up
        actualBoard = new int[][]{{0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}, {1, 1, 1, 1}};
        expectedBoard = new int[][]{{1, 1, 1, 1}, {0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}};
        slideGame.slideUp(actualBoard, merges);
        assertEquals("Testing the last value for an up slide", expectedBoard, actualBoard);

        // testing one merge for an up slide
        actualBoard = new int[][]{{1, 0, 0, 0}, {1, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}};
        expectedBoard = new int[][]{{2, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}};
        slideGame.slideUp(actualBoard, merges);
        assertEquals("Testing a merge for an up slide", expectedBoard, actualBoard);

        // testing many merges for an up slide
        actualBoard = new int[][]{{1, 8, 0, 2}, {1, 4, 0, 2}, {1, 2, 1, 1}, {2, 2, 1, 2}};
        expectedBoard = new int[][]{{2, 16, 2, 4}, {1, 0, 0, 1}, {2, 0, 0, 2}, {0, 0, 0, 0}};
        slideGame.slideUp(actualBoard, merges);
        assertEquals("Testing many merges for an up slide", expectedBoard, actualBoard);
    }

    // testing the slide down method
    @Test
    public void downSlideTest() {
        SlideGame slideGame = new SlideGame();
        // testing 0 for a slide down
        int[][] actualBoard = {{0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}};
        int[][] expectedBoard = {{0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}};
        slideGame.slideDown(actualBoard, merges);
        assertEquals("Testing a down slide with an empty board", expectedBoard, actualBoard);

        // testing 1 for a slide down
        actualBoard = new int[][]{{0, 0, 0, 0}, {0, 0, 0, 0}, {1, 0, 0, 0}, {0, 0, 0, 0}};
        expectedBoard = new int[][]{{0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}, {1, 0, 0, 0}};
        slideGame.slideDown(actualBoard, merges);
        assertEquals("Testing a down slide with a value in one tile", expectedBoard, actualBoard);

        // testing many for a slide down
        actualBoard = new int[][]{{0, 2, 0, 2}, {1, 2, 0, 1}, {8, 2, 16, 1}, {0, 0, 0, 0}};
        expectedBoard = new int[][]{{0, 0, 0, 0}, {0, 0, 0, 0}, {1, 2, 0, 0}, {8, 4, 16, 4}};
        slideGame.slideDown(actualBoard, merges);
        assertEquals("Testing a down slide with a value in one tile", expectedBoard, actualBoard);

        // testing first for a slide down
        actualBoard = new int[][]{{0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}, {1, 1, 1, 1}};
        expectedBoard = new int[][]{{0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}, {1, 1, 1, 1}};
        slideGame.slideDown(actualBoard, merges);
        assertEquals("Testing the first value for a down slide", expectedBoard, actualBoard);

        // testing middle for a slide down
        actualBoard = new int[][]{{0, 0, 0, 0}, {1, 0, 1, 0}, {0, 1, 0, 1}, {0, 0, 0, 0}};
        expectedBoard = new int[][]{{0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}, {1, 1, 1, 1}};
        slideGame.slideDown(actualBoard, merges);
        assertEquals("Testing the middle value for a down slide", expectedBoard, actualBoard);

        // testing last for a slide down
        actualBoard = new int[][]{{1, 1, 1, 1}, {0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}};
        expectedBoard = new int[][]{{0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}, {1, 1, 1, 1}};
        slideGame.slideDown(actualBoard, merges);
        assertEquals("Testing the last value for a down slide", expectedBoard, actualBoard);

        // testing one merge for a down slide
        actualBoard = new int[][]{{1, 0, 0, 0}, {1, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}};
        expectedBoard = new int[][]{{0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}, {2, 0, 0, 0}};
        slideGame.slideDown(actualBoard, merges);
        assertEquals("Testing a merge for a down slide", expectedBoard, actualBoard);

        // testing many merges for a down slide
        actualBoard = new int[][]{{2, 2, 1, 2}, {1, 2, 1, 1}, {1, 4, 0, 2}, {1, 8, 0, 2}};
        expectedBoard = new int[][]{{0, 0, 0, 0}, {2, 0, 0, 2}, {1, 0, 0, 1}, {2, 16, 2, 4}};
        slideGame.slideDown(actualBoard, merges);
        assertEquals("Testing many merges for a down slide", expectedBoard, actualBoard);
    }

    // testing the slide diagonal up left method
    @Test
    public void diagonalSlideUpLeftTest() {
        SlideGame slideGame = new SlideGame();
        // testing 0 for a diagonal slide up left
        int[][] actualBoard = {{0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}};
        int[][] expectedBoard = {{0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}};
        slideGame.diagonalSlideUpLeft(actualBoard, merges);
        assertEquals("Testing a diagonal slide up left on an empty board", expectedBoard, actualBoard);

        // testing 1 for a diagonal slide up left
        actualBoard = new int[][]{{0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 1}};
        expectedBoard = new int[][]{{1, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}};
        slideGame.diagonalSlideUpLeft(actualBoard, merges);
        assertEquals("Testing a diagonal slide up left with a value in one tile", expectedBoard, actualBoard);

        // testing many for a diagonal slide up left
        actualBoard = new int[][]{{4, 0, 2, 0}, {2, 1, 2, 4}, {0, 1, 0, 2}, {1, 1, 1, 1}};
        expectedBoard = new int[][]{{4, 4, 2, 0}, {4, 2, 0, 4}, {1, 0, 0, 0}, {1, 0, 0, 0}};
        slideGame.diagonalSlideUpLeft(actualBoard, merges);
        assertEquals("Testing a diagonal slide up left with a value in many tiles", expectedBoard, actualBoard);

        // testing first for a diagonal slide up left
        actualBoard = new int[][]{{1, 1, 1, 1}, {1, 0, 0, 0}, {1, 0, 0, 0}, {1, 0, 0, 0}};
        expectedBoard = new int[][]{{1, 1, 1, 1}, {1, 0, 0, 0}, {1, 0, 0, 0}, {1, 0, 0, 0}};
        slideGame.diagonalSlideUpLeft(actualBoard, merges);
        assertEquals("Testing a diagonal slide up left for the first value", expectedBoard, actualBoard);

        // testing middle for a diagonal slide up left
        actualBoard = new int[][]{{0, 0, 0, 0}, {0, 1, 1, 0}, {0, 1, 1, 0}, {0, 0, 0, 0}};
        expectedBoard = new int[][]{{2, 1, 0, 0}, {1, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}};
        slideGame.diagonalSlideUpLeft(actualBoard, merges);
        assertEquals("Testing a diagonal slide up left for the middle value", expectedBoard, actualBoard);

        // testing last for a diagonal slide up left
        actualBoard = new int[][]{{0, 0, 0, 1}, {0, 0, 0, 1}, {0, 0, 0, 1}, {1, 1, 1, 1}};
        expectedBoard = new int[][]{{1, 1, 1, 1}, {1, 0, 0, 0}, {1, 0, 0, 0}, {1, 0, 0, 0}};
        slideGame.diagonalSlideUpLeft(actualBoard, merges);
        assertEquals("Testing a diagonal slide up left for the last value", expectedBoard, actualBoard);

        // testing one merge for a diagonal slide up left
        actualBoard = new int[][]{{1, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 1}};
        expectedBoard = new int[][]{{2, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}};
        slideGame.diagonalSlideUpLeft(actualBoard, merges);
        assertEquals("Testing a merge for a diagonal slide up left", expectedBoard, actualBoard);

        // testing many merges for a diagonal slide up left
        actualBoard = new int[][]{{16, 2, 1, 1}, {2, 8, 0, 1}, {1, 0, 4, 2}, {1, 1, 2, 4}};
        expectedBoard = new int[][]{{32, 4, 2, 1}, {4, 0, 0, 0}, {2, 0, 0, 0}, {1, 0, 0, 0}};
        slideGame.diagonalSlideUpLeft(actualBoard, merges);
        assertEquals("Testing many merges for a diagonal slide up left", expectedBoard, actualBoard);
    }

    // testing the slide diagonal up left method
    @Test
    public void diagonalSlideUpRightest() {
        SlideGame slideGame = new SlideGame();
        // testing 0 for a diagonal slide up right
        int[][] actualBoard = {{0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}};
        int[][] expectedBoard = {{0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}};
        slideGame.diagonalSlideUpRight(actualBoard, merges);
        assertEquals("Testing a diagonal slide up right on an empty board", expectedBoard, actualBoard);

        // testing 1 for a diagonal slide up right
        actualBoard = new int[][]{{0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}, {1, 0, 0, 0}};
        expectedBoard = new int[][]{{0, 0, 0, 1}, {0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}};
        slideGame.diagonalSlideUpRight(actualBoard, merges);
        assertEquals("Testing a diagonal slide up right with a value in one tile", expectedBoard, actualBoard);

        // testing many for a diagonal slide up right
        actualBoard = new int[][]{{0, 2, 0, 4}, {4, 2, 1, 2}, {2, 0, 1, 0}, {1, 1, 1, 1}};
        expectedBoard = new int[][]{{0, 2, 4, 4}, {4, 0, 2, 4}, {0, 0, 0, 1}, {0, 0, 0, 1}};
        slideGame.diagonalSlideUpRight(actualBoard, merges);
        assertEquals("Testing a diagonal slide up right with a value in many tiles", expectedBoard, actualBoard);

        // testing first for a diagonal slide up right
        actualBoard = new int[][]{{1, 1, 1, 1}, {1, 0, 0, 0}, {1, 0, 0, 0}, {1, 0, 0, 0}};
        expectedBoard = new int[][]{{1, 2, 2, 2}, {0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}};
        slideGame.diagonalSlideUpRight(actualBoard, merges);
        assertEquals("Testing a diagonal slide up right for the first value", expectedBoard, actualBoard);

        // testing middle for a diagonal slide up right
        actualBoard = new int[][]{{0, 0, 0, 0}, {0, 1, 1, 0}, {0, 1, 1, 0}, {0, 0, 0, 0}};
        expectedBoard = new int[][]{{0, 0, 1, 2}, {0, 0, 0, 1}, {0, 0, 0, 0}, {0, 0, 0, 0}};
        slideGame.diagonalSlideUpRight(actualBoard, merges);
        assertEquals("Testing a diagonal slide up right for the middle value", expectedBoard, actualBoard);

        // testing last for a diagonal slide up right
        actualBoard = new int[][]{{1, 0, 0, 0}, {1, 0, 0, 0}, {1, 0, 0, 0}, {1, 1, 1, 1}};
        expectedBoard = new int[][]{{1, 1, 1, 1}, {0, 0, 0, 1}, {0, 0, 0, 1}, {0, 0, 0, 1}};
        slideGame.diagonalSlideUpRight(actualBoard, merges);
        assertEquals("Testing a diagonal slide up right for the last value", expectedBoard, actualBoard);

        // testing one merge for a diagonal slide up right
        actualBoard = new int[][]{{0, 0, 0, 1}, {0, 0, 0, 0}, {0, 0, 0, 0}, {1, 0, 0, 0}};
        expectedBoard = new int[][]{{0, 0, 0, 2}, {0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}};
        slideGame.diagonalSlideUpRight(actualBoard, merges);
        assertEquals("Testing a merge for a diagonal slide up right", expectedBoard, actualBoard);

        // testing many merges for a diagonal slide up right
        actualBoard = new int[][]{{1, 1, 2, 16}, {1, 0, 8, 2}, {2, 4, 0, 1}, {4, 2, 1, 1}};
        expectedBoard = new int[][]{{1, 2, 4, 32}, {0, 0, 0, 4}, {0, 0, 0, 2}, {0, 0, 0, 1}};
        slideGame.diagonalSlideUpRight(actualBoard, merges);
        assertEquals("Testing many merges for a diagonal slide up right", expectedBoard, actualBoard);
    }

    // testing the slide diagonal down left method
    @Test
    public void diagonalSlideDownLeftest() {
        SlideGame slideGame = new SlideGame();
        // testing 0 for a diagonal slide down left
        int[][] actualBoard = {{0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}};
        int[][] expectedBoard = {{0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}};
        slideGame.diagonalSlideDownLeft(actualBoard, merges);
        assertEquals("Testing a diagonal slide down left on an empty board", expectedBoard, actualBoard);

        // testing 1 for a diagonal slide down left
        actualBoard = new int[][]{{0, 0, 0, 1}, {0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}};
        expectedBoard = new int[][]{{0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}, {1, 0, 0, 0}};
        slideGame.diagonalSlideDownLeft(actualBoard, merges);
        assertEquals("Testing a diagonal slide down left with a value in one tile", expectedBoard, actualBoard);

        // testing many for a diagonal slide down left
        actualBoard = new int[][]{{0, 2, 0, 4}, {4, 2, 1, 2}, {2, 0, 1, 0}, {1, 1, 1, 1}};
        expectedBoard = new int[][]{{0, 2, 0, 0}, {4, 0, 0, 0}, {4, 4, 0, 0}, {2, 4, 1, 1}};
        slideGame.diagonalSlideDownLeft(actualBoard, merges);
        assertEquals("Testing a diagonal slide down left with a value in many tiles", expectedBoard, actualBoard);

        // testing first for a diagonal slide down left
        actualBoard = new int[][]{{0, 0, 0, 1}, {0, 0, 0, 1}, {0, 0, 0, 1}, {1, 1, 1, 1}};
        expectedBoard = new int[][]{{0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}, {2, 2, 2, 1}};
        slideGame.diagonalSlideDownLeft(actualBoard, merges);
        assertEquals("Testing a diagonal slide down left for the first value", expectedBoard, actualBoard);

        // testing middle for a diagonal slide down left
        actualBoard = new int[][]{{0, 0, 0, 0}, {0, 1, 1, 0}, {0, 1, 1, 0}, {0, 0, 0, 0}};
        expectedBoard = new int[][]{{0, 0, 0, 0}, {0, 0, 0, 0}, {1, 0, 0, 0}, {2, 1, 0, 0}};
        slideGame.diagonalSlideDownLeft(actualBoard, merges);
        assertEquals("Testing a diagonal slide down left for the middle value", expectedBoard, actualBoard);

        // testing last for a diagonal slide down left
        actualBoard = new int[][]{{1, 1, 1, 1}, {0, 0, 0, 1}, {0, 0, 0, 1}, {0, 0, 0, 1}};
        expectedBoard = new int[][]{{1, 0, 0, 0}, {1, 0, 0, 0}, {1, 0, 0, 0}, {1, 1, 1, 1}};
        slideGame.diagonalSlideDownLeft(actualBoard, merges);
        assertEquals("Testing a diagonal slide down left for the last value", expectedBoard, actualBoard);

        // testing one merge for a diagonal slide down left
        actualBoard = new int[][]{{0, 0, 0, 1}, {0, 0, 0, 0}, {0, 0, 0, 0}, {1, 0, 0, 0}};
        expectedBoard = new int[][]{{0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}, {2, 0, 0, 0}};
        slideGame.diagonalSlideDownLeft(actualBoard, merges);
        assertEquals("Testing a merge for a diagonal slide down left", expectedBoard, actualBoard);

        // testing many merges for a diagonal slide down left
        actualBoard = new int[][]{{1, 1, 2, 4}, {1, 0, 4, 2}, {2, 8, 0, 1}, {16, 2, 1, 1}};
        expectedBoard = new int[][]{{1, 0, 0, 0}, {2, 0, 0, 0}, {4, 0, 0, 0}, {32, 4, 2, 1}};
        slideGame.diagonalSlideDownLeft(actualBoard, merges);
        assertEquals("Testing many merges for a diagonal slide down left", expectedBoard, actualBoard);
    }

    // testing the slide diagonal down right method
    @Test
    public void diagonalSlideDownRightest() {
        SlideGame slideGame = new SlideGame();
        // testing 0 for a diagonal slide down right
        int[][] actualBoard = {{0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}};
        int[][] expectedBoard = {{0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}};
        slideGame.diagonalSlideDownRight(actualBoard, merges);
        assertEquals("Testing a diagonal slide down right on an empty board", expectedBoard, actualBoard);

        // testing 1 for a diagonal slide down right
        actualBoard = new int[][]{{1, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}};
        expectedBoard = new int[][]{{0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 1}};
        slideGame.diagonalSlideDownRight(actualBoard, merges);
        assertEquals("Testing a diagonal slide down right with a value in one tile", expectedBoard, actualBoard);

        // testing many for a diagonal slide down right
        actualBoard = new int[][]{{4, 0, 2, 0}, {2, 1, 2, 4}, {0, 1, 0, 2}, {1, 1, 1, 1}};
        expectedBoard = new int[][]{{0, 0, 2, 0}, {0, 0, 0, 4}, {0, 0, 4, 4}, {1, 1, 4, 2}};
        slideGame.diagonalSlideDownRight(actualBoard, merges);
        assertEquals("Testing a diagonal slide down right with a value in many tiles", expectedBoard, actualBoard);

        // testing first for a diagonal slide down right
        actualBoard = new int[][]{{1, 0, 0, 0}, {1, 0, 0, 0}, {1, 0, 0, 0}, {1, 1, 1, 1}};
        expectedBoard = new int[][]{{0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}, {1, 2, 2, 2}};
        slideGame.diagonalSlideDownRight(actualBoard, merges);
        assertEquals("Testing a diagonal slide down right for the first value", expectedBoard, actualBoard);

        // testing middle for a diagonal slide down right
        actualBoard = new int[][]{{0, 0, 0, 0}, {0, 1, 1, 0}, {0, 1, 1, 0}, {0, 0, 0, 0}};
        expectedBoard = new int[][]{{0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 1}, {0, 0, 1, 2}};
        slideGame.diagonalSlideDownRight(actualBoard, merges);
        assertEquals("Testing a diagonal slide down right for the middle value", expectedBoard, actualBoard);

        // testing last for a diagonal slide down right
        actualBoard = new int[][]{{1, 1, 1, 1}, {1, 0, 0, 0}, {1, 0, 0, 0}, {1, 0, 0, 0}};
        expectedBoard = new int[][]{{0, 0, 0, 1}, {0, 0, 0, 1}, {0, 0, 0, 1}, {1, 1, 1, 1}};
        slideGame.diagonalSlideDownRight(actualBoard, merges);
        assertEquals("Testing a diagonal slide down right for the last value", expectedBoard, actualBoard);

        // testing one merge for a diagonal slide down right
        actualBoard = new int[][]{{1, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 1}};
        expectedBoard = new int[][]{{0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 2}};
        slideGame.diagonalSlideDownRight(actualBoard, merges);
        assertEquals("Testing a merge for a diagonal slide down right", expectedBoard, actualBoard);

        // testing many merges for a diagonal slide down right
        actualBoard = new int[][]{{4, 2, 1, 1}, {2, 4, 0, 1}, {1, 0, 8, 2}, {1, 1, 2, 16}};
        expectedBoard = new int[][]{{0, 0, 0, 1}, {0, 0, 0, 2}, {0, 0, 0, 4}, {1, 2, 4, 32}};
        slideGame.diagonalSlideDownRight(actualBoard, merges);
        assertEquals("Testing many merges for a diagonal slide down right", expectedBoard, actualBoard);
    }
}