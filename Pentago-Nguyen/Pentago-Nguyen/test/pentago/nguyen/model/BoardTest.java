package pentago.nguyen.model;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Michel
 */
public class BoardTest {

    /**
     * Test to create a table and fails when the board is null.
     */
    @Test
    public void testCreateBoardNull(){
        System.out.println("testCreateBoardNull");
        Board board = new Board();
        int boardSize = board.getBoardSize();
        assertNotEquals(boardSize, 0);
    }

    /**
     * Test of addBallOnBoard method, of class Board.
     */
    @Test
    public void testAddBallOnBoard() {
        System.out.println("addBallOnBoard");
        int row = 0;
        int column = 0;
        Player player = new Player(BallColor.BLACK, "Michel");
        Board instance = new Board();
        instance.addBallOnBoard(row, column, player);
    }

    /**
     * Test of checkWinner method, of class Board.
     */
    @Test
    public void testCheckWinner() {
        System.out.println("checkWinner");
        Player player1 = new Player(BallColor.BLACK, "Michel");
        Player player2 = new Player(BallColor.WHITE, "Alan");
        Board instance = new Board();
        Player expResult = null;
        Player result = instance.checkWinner(player1, player2);
        assertEquals(expResult, result);
    }
}
