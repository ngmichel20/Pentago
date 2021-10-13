package pentago.nguyen.model;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Michel
 */
public class PentagoTest {

    /**
     * =====================================    Tests on createPlayer()   ===========================================
     */
    
    /**
     * Test of createPlayer method, of class Pentago when names are different.
     */
    @Test
    public void testCreatePlayerWhenNamesDifferent() {
        System.out.println("createPlayerWhenNamesDifferent");
        String namePlayer1 = "Michel";
        String namePlayer2 = "Younes";
        Pentago instance = new Pentago();
        instance.createPlayer(namePlayer1, namePlayer2);
        assertNotEquals(namePlayer1, namePlayer2);
    }

    /**
     * Test of createPlayer method, of class Pentago if the 2 players have both
     * the same name.
     */
    @Test
    public void testCreatePlayerWhenSameNames() {
        System.out.println("createPlayerWhenSameName");
        String namePlayer1 = "Michel";
        String namePlayer2 = "Michel";
        Pentago instance = new Pentago();
        instance.createPlayer(namePlayer1, namePlayer2);
        assertEquals(namePlayer1, namePlayer2);
    }

    /**
     * Test of createPlayer method, of class Pentago if the second player name
     * doesn't exist.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testCreatePlayerNoSecondPlayer() {
        System.out.println("testCreatePlayerNoSecondPlayer");
        String namePlayer1 = "Michel";
        String namePlayer2 = null;
        Pentago instance = new Pentago();
        instance.createPlayer(namePlayer1, namePlayer2);
    }

    /**
     * Test of createPlayer method, of class Pentago if the first player name
     * doesn't exist.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testCreatePlayerNoFirstPlayer() {
        System.out.println("testCreatePlayerNoFirstPlayer");
        String namePlayer1 = null;
        String namePlayer2 = "Michel";
        Pentago instance = new Pentago();
        instance.createPlayer(namePlayer1, namePlayer2);
    }

    /**
     * Test of createPlayer method, of class Pentago if the two names of the
     * players are null.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testCreatePlayerNamesNull() {
        System.out.println("testCreatePlayerNoFirstPlayer");
        String namePlayer1 = null;
        String namePlayer2 = null;
        Pentago instance = new Pentago();
        instance.createPlayer(namePlayer1, namePlayer2);
    }

    /**
     * Test of createPlayer when the black color is null.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testCreatePlayerBlackColorNull() {
        System.out.println("testCreatePlayerBlackColorNull");
        Player player1 = new Player(null, "Michel");
        Player player2 = new Player(BallColor.WHITE, "Younes");
        Pentago instance = new Pentago();
        instance.createPlayer(player1.getName(), player2.getName());
    }

    /**
     * =====================================    Tests on nextPlayer()   ===========================================
     */
    
    /**
     * Test of nextPlayer method, of class Pentago with the Exception.
     */
    @Test(expected = IllegalStateException.class)
    public void testNextPlayerException() {
        System.out.println("testNextPlayerException");
        Pentago instance = new Pentago();
        String name1 = "Michel";
        String name2 = "Younes";
        instance.createPlayer(name1, name2);
        instance.putBallOnBoard(0, 0);
        instance.nextPlayer();
    }
    
    /**
     * Test of nextPlayer method, of class Pentago when it's good.
     */
    @Test
    public void testNextPlayerGood() {
        System.out.println("testNextPlayerGood");
        Pentago instance = new Pentago();
        String name1 = "Michel";
        String name2 = "Younes";
        instance.createPlayer(name1, name2);
        Player result = instance.nextPlayer();
        assertEquals(instance.getPlayer2(), result);
    }
    
    /**
     * Test of nextPlayer method, of class Pentago when nextPlayer cannot do it.
     */
    @Test
    public void testNextPlayerNotGood() {
        System.out.println("testNextPlayerNotGood");
        Pentago instance = new Pentago();
        String name1 = "Michel";
        String name2 = "Younes";
        instance.createPlayer(name1, name2);
        Player result = instance.nextPlayer();
        assertNotEquals(instance.getPlayer1(), result);
    }

    /**
     * =====================================    Tests on winner()   ===========================================
     */
    
    /**
     * Test of winner method, of class Pentago with exception.
     */
    @Test(expected = IllegalStateException.class)
    public void testWinnerOnColumnPossibilityOneexce() {
        System.out.println("testWinnerOnColumnPossibilityOneTYTYTYTYTYT");
        Pentago instance = new Pentago();
        instance.createPlayer("Michel", "Younes");
        int nbSameBalls = 0;
        for (int i = 0; i < 9; i++) {
            if (instance.getCurrentPlayerName().equals("Michel")) {
                instance.putBallOnBoard(nbSameBalls, 0);
                nbSameBalls++;
                instance.askQuadrantToRotate(2, Direction.LEFT);
            } else {
                instance.putBallOnBoard(nbSameBalls, 4);
                instance.askQuadrantToRotate(2, Direction.LEFT);
            }
        }
        instance.winner();
        instance.winner();
    }
    

    /**
     * Test of winner method, of class Pentago on a row (possibility one)
     */
    @Test
    public void testWinnerOnRowPossibilityOne() {
        System.out.println("testWinnerOnRowPossibilityOne");
        Pentago instance = new Pentago();
        instance.createPlayer("Michel", "Younes");
        int nbSameBalls = 0;
        for (int i = 0; i < 9; i++) {
            if (instance.getCurrentPlayerName().equals("Michel")) {
                instance.putBallOnBoard(0, nbSameBalls);
                nbSameBalls++;
                instance.askQuadrantToRotate(3, Direction.LEFT);
            } else {
                instance.putBallOnBoard(5, nbSameBalls);
                instance.askQuadrantToRotate(3, Direction.LEFT);
            }
        }
        boolean expResult = true;
        boolean result = instance.winner();
        assertEquals(expResult, result);
    }

    /**
     * Test of winner method, of class Pentago on a row (possibility two)
     */
    @Test
    public void testWinnerOnRowPossibilityTwo() {
        System.out.println("testWinnerOnRowPossibilityTwo");
        Pentago instance = new Pentago();
        instance.createPlayer("Michel", "Younes");
        int nbSameBalls = 0;
        for (int i = 0; i < 9; i++) {
            if (instance.getCurrentPlayerName().equals("Michel")) {
                instance.putBallOnBoard(1, nbSameBalls);
                nbSameBalls++;
                instance.askQuadrantToRotate(3, Direction.LEFT);
            } else {
                instance.putBallOnBoard(5, nbSameBalls);
                instance.askQuadrantToRotate(3, Direction.LEFT);
            }
        }
        boolean expResult = true;
        boolean result = instance.winner();
        assertEquals(expResult, result);
    }

    /**
     * Test of winner method, of class Pentago on a column (possibility one)
     */
    @Test
    public void testWinnerOnColumnPossibilityOne() {
        System.out.println("testWinnerOnColumnPossibilityOne");
        Pentago instance = new Pentago();
        instance.createPlayer("Michel", "Younes");
        int nbSameBalls = 0;
        for (int i = 0; i < 9; i++) {
            if (instance.getCurrentPlayerName().equals("Michel")) {
                instance.putBallOnBoard(nbSameBalls, 0);
                nbSameBalls++;
                instance.askQuadrantToRotate(2, Direction.LEFT);
            } else {
                instance.putBallOnBoard(nbSameBalls, 4);
                instance.askQuadrantToRotate(2, Direction.LEFT);
            }
        }
        boolean expResult = true;
        boolean result = instance.winner();
        assertEquals(expResult, result);
    }

    /**
     * Test of winner method, of class Pentago on a column (possibility two)
     */
    @Test
    public void testWinnerOnColumnPossibilityTwo() {
        System.out.println("testWinnerOnColumnPossibilityTwo");
        Pentago instance = new Pentago();
        instance.createPlayer("Michel", "Younes");
        int nbSameBalls = 0;
        for (int i = 0; i < 9; i++) {
            if (instance.getCurrentPlayerName().equals("Michel")) {
                instance.putBallOnBoard(nbSameBalls, 1);
                nbSameBalls++;
                instance.askQuadrantToRotate(2, Direction.LEFT);
            } else {
                instance.putBallOnBoard(nbSameBalls, 4);
                instance.askQuadrantToRotate(2, Direction.LEFT);
            }
        }
        boolean expResult = true;
        boolean result = instance.winner();
        assertEquals(expResult, result);
    }

    /**
     * Test of winner method, of class Pentago on the diagonal from the left
     * (possibility 1)
     */
    @Test
    public void testWinnerOnDiagFromLeftPossibilityOne() {
        System.out.println("testWinnerOnDiagFromLeftPossibilityOne");
        Pentago instance = new Pentago();
        instance.createPlayer("Michel", "Younes");
        int nbSameBalls = 1;
        for (int i = 0; i < 9; i++) {
            if (instance.getCurrentPlayerName().equals("Michel")) {
                instance.putBallOnBoard(nbSameBalls, nbSameBalls);
                nbSameBalls++;
                instance.askQuadrantToRotate(2, Direction.LEFT);
            } else {
                instance.putBallOnBoard(nbSameBalls, 0);
                instance.askQuadrantToRotate(2, Direction.LEFT);
            }
        }
        boolean expResult = true;
        boolean result = instance.winner();
        assertEquals(expResult, result);
    }

    /**
     * Test of winner method, of class Pentago on the diagonal from the left
     * (possibility 2)
     */
    @Test
    public void testWinnerOnDiagFromLeftPossibilityTwo() {
        System.out.println("testWinnerOnDiagFromLeftPossibilityTwo");
        Pentago instance = new Pentago();
        instance.createPlayer("Michel", "Younes");
        int nbSameBalls = 1;
        for (int i = 0; i < 9; i++) {
            if (instance.getCurrentPlayerName().equals("Michel")) {
                instance.putBallOnBoard(nbSameBalls, nbSameBalls);
                nbSameBalls++;
                instance.askQuadrantToRotate(2, Direction.LEFT);
            } else {
                instance.putBallOnBoard(nbSameBalls, 0);
                instance.askQuadrantToRotate(2, Direction.LEFT);
            }
        }
        boolean expResult = true;
        boolean result = instance.winner();
        assertEquals(expResult, result);
    }

    /**
     * Test of winner method, of class Pentago on the diagonal from the left
     * (possibility 3)
     */
    @Test
    public void testWinnerOnDiagFromLeftPossibilityThree() {
        System.out.println("testWinnerOnDiagFromLeftPossibilityThree");
        Pentago instance = new Pentago();
        instance.createPlayer("Michel", "Younes");
        int nbSameBalls = 0;
        int row = 1;
        for (int i = 0; i < 9; i++) {
            if (instance.getCurrentPlayerName().equals("Michel")) {
                instance.putBallOnBoard(row, nbSameBalls);
                nbSameBalls++;
                row++;
                instance.askQuadrantToRotate(2, Direction.LEFT);
            } else {
                instance.putBallOnBoard(nbSameBalls, 5);
                instance.askQuadrantToRotate(2, Direction.LEFT);
            }
        }
        boolean expResult = true;
        boolean result = instance.winner();
        assertEquals(expResult, result);
    }

    /**
     * Test of winner method, of class Pentago on the diagonal from the left
     * (possibility 4)
     */
    @Test
    public void testWinnerOnDiagFromLeftPossibilityFour() {
        System.out.println("testWinnerOnDiagFromLeftPossibilityFour");
        Pentago instance = new Pentago();
        instance.createPlayer("Michel", "Younes");
        int nbSameBalls = 0;
        int column = 1;
        for (int i = 0; i < 9; i++) {
            if (instance.getCurrentPlayerName().equals("Michel")) {
                instance.putBallOnBoard(nbSameBalls, column);
                nbSameBalls++;
                column++;
                instance.askQuadrantToRotate(3, Direction.LEFT);
            } else {
                instance.putBallOnBoard(nbSameBalls, 1);
                instance.askQuadrantToRotate(3, Direction.LEFT);
            }
        }
        boolean expResult = true;
        boolean result = instance.winner();
        assertEquals(expResult, result);
    }

    /**
     * Test of winner method, of class Pentago on the diagonal from the right
     * (possibility 1)
     */
    @Test
    public void testWinnerOnDiagFromRightPossibilityOne() {
        System.out.println("testWinnerOnDiagFromRightPossibilityOne");
        Pentago instance = new Pentago();
        instance.createPlayer("Michel", "Younes");
        int nbSameBalls = 0;
        int column = 5;
        for (int i = 0; i < 9; i++) {
            if (instance.getCurrentPlayerName().equals("Michel")) {
                instance.putBallOnBoard(nbSameBalls, column);
                nbSameBalls++;
                column--;
                instance.askQuadrantToRotate(1, Direction.LEFT);
            } else {
                instance.putBallOnBoard(nbSameBalls, 5);
                instance.askQuadrantToRotate(1, Direction.LEFT);
            }
        }
        boolean expResult = true;
        boolean result = instance.winner();
        assertEquals(expResult, result);
    }
    
    /**
     * Test of winner method, of class Pentago on the diagonal from the right
     * (possibility 2)
     */
    @Test
    public void testWinnerOnDiagFromRightPossibilityTwo() {
        System.out.println("testWinnerOnDiagFromRightPossibilityTwo");
        Pentago instance = new Pentago();
        instance.createPlayer("Michel", "Younes");
        int nbSameBalls = 1;
        int nbSameBall2 = 0;
        int column = 4;
        for (int i = 0; i < 9; i++) {
            if (instance.getCurrentPlayerName().equals("Michel")) {
                instance.putBallOnBoard(nbSameBalls, column);
                nbSameBalls++;
                column--;
                instance.askQuadrantToRotate(1, Direction.LEFT);
            } else {
                instance.putBallOnBoard(nbSameBall2, 5);
                nbSameBall2++;
                instance.askQuadrantToRotate(1, Direction.LEFT);
            }
        }
        boolean expResult = true;
        boolean result = instance.winner();
        assertEquals(expResult, result);
    }
    
    /**
     * Test of winner method, of class Pentago on the diagonal from the right
     * (possibility 3)
     */
    @Test
    public void testWinnerOnDiagFromRightPossibilityThree() {
        System.out.println("testWinnerOnDiagFromRightPossibilityThree");
        Pentago instance = new Pentago();
        instance.createPlayer("Michel", "Younes");
        int nbSameBalls = 0;
        int nbSameBall2 = 0;
        int column = 4;
        for (int i = 0; i < 9; i++) {
            if (instance.getCurrentPlayerName().equals("Michel")) {
                instance.putBallOnBoard(nbSameBalls, column);
                nbSameBalls++;
                column--;
                instance.askQuadrantToRotate(4, Direction.LEFT);
            } else {
                instance.putBallOnBoard(nbSameBall2, 5);
                nbSameBall2++;
                instance.askQuadrantToRotate(4, Direction.LEFT);
            }
        }
        boolean expResult = true;
        boolean result = instance.winner();
        assertEquals(expResult, result);
    }
    
    /**
     * Test of winner method, of class Pentago on the diagonal from the right
     * (possibility 4)
     */
    @Test
    public void testWinnerOnDiagFromRightPossibilityFour() {
        System.out.println("testWinnerOnDiagFromRightPossibilityThree");
        Pentago instance = new Pentago();
        instance.createPlayer("Michel", "Younes");
        int nbSameBalls = 1;
        int nbSameBall2 = 0;
        int column = 5;
        for (int i = 0; i < 9; i++) {
            if (instance.getCurrentPlayerName().equals("Michel")) {
                instance.putBallOnBoard(nbSameBalls, column);
                nbSameBalls++;
                column--;
                instance.askQuadrantToRotate(1, Direction.LEFT);
            } else {
                instance.putBallOnBoard(nbSameBall2, 0);
                nbSameBall2++;
                instance.askQuadrantToRotate(1, Direction.LEFT);
            }
        }
        boolean expResult = true;
        boolean result = instance.winner();
        assertEquals(expResult, result);
    }  
    
    /**
     * ========== Here is the tests for "winner" but when the winner win the game with a quadrant rotation=======
     *              By using the method askQuadrantToRotate and proves that 
     *              the method askQuadrantToRotate is working.
     */
    
    
    /**
     * Test of winner on the row with the quadrantRotation (possibility1)
     */
    @Test
    public void testWinnerOnRowPossibilityOneWithQuadrantRotation() {
        System.out.println("testWinnerOnRowPossibilityOneWithQuadrantRotation");
        Pentago instance = new Pentago();
        instance.createPlayer("Michel", "Younes");
        int columnP2 = 0;
        int row = 0;
        int column = 0;
        int row2 = 1;
        int column2 = 3;

        for (int i = 0; i < 9; i++) {
            if (instance.getCurrentPlayerName().equals("Michel")) {
                if (i >= 6) {
                    instance.putBallOnBoard(row2, column2);
                    instance.askQuadrantToRotate(4, Direction.LEFT);
                    row2++;
                } else {
                    instance.putBallOnBoard(row, column);
                    column++;
                    instance.askQuadrantToRotate(4, Direction.LEFT);
                }
            } else {
                instance.putBallOnBoard(columnP2, 5);
                instance.askQuadrantToRotate(4, Direction.LEFT);
                columnP2++;
            }
        }
        instance.putBallOnBoard(3, 3);
        instance.askQuadrantToRotate(4, Direction.LEFT);
        instance.putBallOnBoard(4, 3);
        instance.askQuadrantToRotate(2, Direction.RIGHT);//Here is the rotation that gives the win
        boolean expResult = true;
        boolean result = instance.winner();
        assertEquals(expResult, result);
    }
    
    /**
     * Test of winner on the row with the quadrantRotation (possibility2)
     */
    @Test
    public void testWinnerOnRowPossibilityTwoWithQuadrantRotation() {
        System.out.println("testWinnerOnRowPossibilityTwoWithQuadrantRotation");
        Pentago instance = new Pentago();
        instance.createPlayer("Michel", "Younes");
        int columnP2 = 0;
        int row = 0;
        int column = 1;
        int row2 = 0;
        int column2 = 3;

        for (int i = 0; i < 9; i++) {
            if (instance.getCurrentPlayerName().equals("Michel")) {
                if (i >= 4) {
                    instance.putBallOnBoard(row2, column2);
                    instance.askQuadrantToRotate(4, Direction.LEFT);
                    row2++;
                } else {
                    instance.putBallOnBoard(row, column);
                    column++;
                    instance.askQuadrantToRotate(4, Direction.LEFT);
                }
            } else {
                instance.putBallOnBoard(columnP2, 5);
                instance.askQuadrantToRotate(4, Direction.LEFT);
                columnP2++;
            }
        }
        instance.putBallOnBoard(3, 3);
        instance.askQuadrantToRotate(4, Direction.LEFT);
        instance.putBallOnBoard(4, 3);
        instance.askQuadrantToRotate(2, Direction.RIGHT);//Here is the rotation that gives the win
        boolean expResult = true;
        boolean result = instance.winner();
        assertEquals(expResult, result);
    }
    
    /**
     * Test of winner on the column with the quadrantRotation (possibility1)
     */
    @Test
    public void testWinnerOnColumnPossibilityOneWithQuadrantRotation() {
        System.out.println("testWinnerOnColumnPossibilityOneWithQuadrantRotation");
        Pentago instance = new Pentago();
        instance.createPlayer("Michel", "Younes");
        int row = 0;
        int column = 0;
        int row2 = 5;
        int column2 = 0;
        int columnP2 = 0;

        for (int i = 0; i < 9; i++) {
            if (instance.getCurrentPlayerName().equals("Michel")) {
                if (i >= 6) {
                    instance.putBallOnBoard(row2, column2);
                    instance.askQuadrantToRotate(4, Direction.LEFT);
                    column2++;
                } else {
                    instance.putBallOnBoard(row, column);
                    row++;
                    instance.askQuadrantToRotate(4, Direction.LEFT);
                }
            } else {
                instance.putBallOnBoard(columnP2, 5);
                instance.askQuadrantToRotate(4, Direction.LEFT);
                columnP2++;
            }
        }
        instance.putBallOnBoard(2, 3);
        instance.askQuadrantToRotate(4, Direction.LEFT);
        instance.putBallOnBoard(2, 4);
        instance.askQuadrantToRotate(3, Direction.RIGHT);//Here is the rotation that gives the win
        boolean expResult = true;
        boolean result = instance.winner();
        assertEquals(expResult, result);
    }
    
    /**
     * Test of winner on the column with the quadrantRotation (possibility2)
     */
    @Test
    public void testWinnerOnColumnPossibilityTwoWithQuadrantRotation() {
        System.out.println("testWinnerOnColumnPossibilityTwoWithQuadrantRotation");
        Pentago instance = new Pentago();
        instance.createPlayer("Michel", "Younes");
        int row = 1;
        int column = 0;
        int row2 = 5;
        int column2 = 0;
        int columnP2 = 0;

        for (int i = 0; i < 9; i++) {
            if (instance.getCurrentPlayerName().equals("Michel")) {
                if (i >= 4) {
                    instance.putBallOnBoard(row2, column2);
                    instance.askQuadrantToRotate(4, Direction.LEFT);
                    column2++;
                } else {
                    instance.putBallOnBoard(row, column);
                    row++;
                    instance.askQuadrantToRotate(4, Direction.LEFT);
                }
            } else {
                instance.putBallOnBoard(columnP2, 5);
                instance.askQuadrantToRotate(4, Direction.LEFT);
                columnP2++;
            }
        }
        instance.putBallOnBoard(2, 3);
        instance.askQuadrantToRotate(4, Direction.LEFT);
        instance.putBallOnBoard(2, 4);
        instance.askQuadrantToRotate(3, Direction.RIGHT);//Here is the rotation that gives the win
        boolean expResult = true;
        boolean result = instance.winner();
        assertEquals(expResult, result);
    }
    
    
    /**
     * Test of winner on the diagonal from the left with the quadrantRotation (possibility1)
     */
    @Test
    public void testWinnerOnDiagFromLeftPossibilityOneWithRotationQuadrant() {
        System.out.println("testWinnerOnDiagFromLeftPossibilityOneWithRotationQuadrant");
        Pentago instance = new Pentago();
        instance.createPlayer("Michel", "Younes");
        int columnP2 = 0;
        int row = 0;
        int row2 = 5;
        int column2 = 3;
        for (int i = 0; i < 9; i++) {
            if (instance.getCurrentPlayerName().equals("Michel")) {
                if (i >= 6) {
                    instance.putBallOnBoard(row2, column2);
                    instance.askQuadrantToRotate(2, Direction.LEFT);
                    row2--;
                    column2++;
                } else {
                    instance.putBallOnBoard(row, row);
                    row++;
                    instance.askQuadrantToRotate(2, Direction.LEFT);
                }
            } else {
                instance.putBallOnBoard(4, columnP2);
                instance.askQuadrantToRotate(2, Direction.LEFT);
                columnP2++;
            }
        }
        instance.putBallOnBoard(2, 3);
        instance.askQuadrantToRotate(4, Direction.RIGHT);//Here is the rotation that gives the win
        boolean expResult = true;
        boolean result = instance.winner();
        assertEquals(expResult, result);
    }
    
    /**
     * Test of winner on the diagonal from the left with the quadrantRotation (possibility2)
     */
    @Test
    public void testWinnerOnDiagFromLeftPossibilityTwoWithRotationQuadrant() {
        System.out.println("testWinnerOnDiagFromLeftPossibilityThreeWithRotationQuadrant");
        Pentago instance = new Pentago();
        instance.createPlayer("Michel", "Younes");
        int columnP2 = 0;
        int row = 1;
        int row2 = 5;
        int column2 = 3;
        for (int i = 0; i < 9; i++) {
            if (instance.getCurrentPlayerName().equals("Michel")) {
                if (i >= 4) {
                    instance.putBallOnBoard(row2, column2);
                    instance.askQuadrantToRotate(2, Direction.LEFT);
                    row2--;
                    column2++;
                } else {
                    instance.putBallOnBoard(row, row);
                    row++;
                    instance.askQuadrantToRotate(2, Direction.LEFT);
                }
            } else {
                instance.putBallOnBoard(4, columnP2);
                instance.askQuadrantToRotate(2, Direction.LEFT);
                columnP2++;
            }
        }
        instance.putBallOnBoard(2, 3);
        instance.askQuadrantToRotate(4, Direction.RIGHT);//Here is the rotation that gives the win
        boolean expResult = true;
        boolean result = instance.winner();
        assertEquals(expResult, result);
    }
    
    
    /**
     * Test of winner on the diagonal from the left with the quadrantRotation (possibility3)
     */
    @Test
    public void testWinnerOnDiagFromLeftPossibilityThreeWithRotationQuadrant() {
        System.out.println("testWinnerOnDiagFromLeftPossibilityTwoWithRotationQuadrant");
        Pentago instance = new Pentago();
        instance.createPlayer("Michel", "Younes");
        int columnP2 = 0;
        int row = 1;
        int column = 0;
        int row2 = 5;
        int column2 = 4;
        for (int i = 0; i < 9; i++) {
            if (instance.getCurrentPlayerName().equals("Michel")) {
                if (i >= 6) {
                    instance.putBallOnBoard(row2, column2);
                    instance.askQuadrantToRotate(2, Direction.LEFT);
                    row2--;
                    column2++;
                } else {
                    instance.putBallOnBoard(row, column);
                    row++;
                    column++;
                    instance.askQuadrantToRotate(2, Direction.LEFT);
                }
            } else {
                instance.putBallOnBoard(4, columnP2);
                instance.askQuadrantToRotate(2, Direction.LEFT);
                columnP2++;
            }
        }
        instance.putBallOnBoard(2, 3);
        instance.askQuadrantToRotate(4, Direction.RIGHT);//Here is the rotation that gives the win
        boolean expResult = true;
        boolean result = instance.winner();
        assertEquals(expResult, result);
    }
    
    /**
     * Test of winner on the diagonal from the left with the quadrantRotation (possibility4)
     */
    @Test
    public void testWinnerOnDiagFromLeftPossibilityFourWithRotationQuadrant() {
        System.out.println("testWinnerOnDiagFromLeftPossibilityFourWithRotationQuadrant");
        Pentago instance = new Pentago();
        instance.createPlayer("Michel", "Younes");
        int columnP2 = 0;
        int row = 0;
        int column = 1;
        int row2 = 5;
        int column2 = 4;
        for (int i = 0; i < 9; i++) {
            if (instance.getCurrentPlayerName().equals("Michel")) {
                if (i >= 6) {
                    instance.putBallOnBoard(row2, column2);
                    instance.askQuadrantToRotate(3, Direction.LEFT);
                    row2--;
                    column2++;
                } else {
                    instance.putBallOnBoard(row, column);
                    row++;
                    column++;
                    instance.askQuadrantToRotate(3, Direction.LEFT);
                }
            } else {
                instance.putBallOnBoard(4, columnP2);
                instance.askQuadrantToRotate(3, Direction.LEFT);
                columnP2++;
            }
        }
        instance.putBallOnBoard(5, 0);
        instance.askQuadrantToRotate(4, Direction.LEFT);//Here is the rotation that gives the win
        boolean expResult = true;
        boolean result = instance.winner();
        assertEquals(expResult, result);
    }
    
    
    /**
     * Test of winner on the diagonal from the right with the quadrantRotation (possibility1)
     */
    @Test
    public void testWinnerOnDiagFromRightPossibilityOneWithRotationQuadrant() {
        System.out.println("testWinnerOnDiagFromRightPossibilityOneWithRotationQuadrant");
        Pentago instance = new Pentago();
        instance.createPlayer("Michel", "Younes");
        int columnP2 = 0;
        int row = 0;
        int column = 5;
        int row2 = 5;
        int column2 = 2;
        for (int i = 0; i < 9; i++) {
            if (instance.getCurrentPlayerName().equals("Michel")) {
                if (i >= 6) {
                    instance.putBallOnBoard(row2, column2);
                    instance.askQuadrantToRotate(1, Direction.LEFT);
                    row2--;
                    column2--;
                } else {
                    instance.putBallOnBoard(row, column);
                    row++;
                    column--;
                    instance.askQuadrantToRotate(1, Direction.LEFT);
                }
            } else {
                instance.putBallOnBoard(0, columnP2);
                instance.askQuadrantToRotate(1, Direction.LEFT);
                columnP2++;
            }
        }
        instance.putBallOnBoard(2, 2);
        instance.askQuadrantToRotate(3, Direction.LEFT);//Here is the rotation that gives the win
        boolean expResult = true;
        boolean result = instance.winner();
        assertEquals(expResult, result);
    }
    
    
    /**
     * Test of winner on the diagonal from the right with the quadrantRotation (possibility2)
     */
    @Test
    public void testWinnerOnDiagFromRightPossibilityTwoWithRotationQuadrant() {
        System.out.println("testWinnerOnDiagFromRightPossibilityTwoWithRotationQuadrant");
        Pentago instance = new Pentago();
        instance.createPlayer("Michel", "Younes");
        int columnP2 = 0;
        int row = 1;
        int column = 4;
        int row2 = 5;
        int column2 = 2;
        for (int i = 0; i < 9; i++) {
            if (instance.getCurrentPlayerName().equals("Michel")) {
                if (i >= 4) {
                    instance.putBallOnBoard(row2, column2);
                    instance.askQuadrantToRotate(1, Direction.LEFT);
                    row2--;
                    column2--;
                } else {
                    instance.putBallOnBoard(row, column);
                    row++;
                    column--;
                    instance.askQuadrantToRotate(1, Direction.LEFT);
                }
            } else {
                instance.putBallOnBoard(0, columnP2);
                instance.askQuadrantToRotate(1, Direction.LEFT);
                columnP2++;
            }
        }
        instance.putBallOnBoard(2, 2);
        instance.askQuadrantToRotate(3, Direction.LEFT);//Here is the rotation that gives the win
        boolean expResult = true;
        boolean result = instance.winner();
        assertEquals(expResult, result);
    }
    
    
    /**
     * Test of winner on the diagonal from the right with the quadrantRotation (possibility3)
     */
    @Test
    public void testWinnerOnDiagFromRightPossibilityThreeWithRotationQuadrant() {
        System.out.println("testWinnerOnDiagFromRightPossibilityThreeWithRotationQuadrant");
        Pentago instance = new Pentago();
        instance.createPlayer("Michel", "Younes");
        int columnP2 = 0;
        int row = 1;
        int column = 5;
        int row2 = 5;
        int column2 = 1;
        for (int i = 0; i < 9; i++) {
            if (instance.getCurrentPlayerName().equals("Michel")) {
                if (i >= 6) {
                    instance.putBallOnBoard(row2, column2);
                    instance.askQuadrantToRotate(1, Direction.LEFT);
                    row2--;
                    column2--;
                } else {
                    instance.putBallOnBoard(row, column);
                    row++;
                    column--;
                    instance.askQuadrantToRotate(1, Direction.LEFT);
                }
            } else {
                instance.putBallOnBoard(0, columnP2);
                instance.askQuadrantToRotate(1, Direction.LEFT);
                columnP2++;
            }
        }
        instance.putBallOnBoard(2, 2);
        instance.askQuadrantToRotate(3, Direction.LEFT);//Here is the rotation that gives the win
        boolean expResult = true;
        boolean result = instance.winner();
        assertEquals(expResult, result);
    }
    
    
    /**
     * Test of winner on the diagonal from the right with the quadrantRotation (possibility4)
     */
    @Test
    public void testWinnerOnDiagFromRightPossibilityFourWithRotationQuadrant() {
        System.out.println("testWinnerOnDiagFromRightPossibilityFourWithRotationQuadrant");
        Pentago instance = new Pentago();
        instance.createPlayer("Michel", "Younes");
        int columnP2 = 0;
        int row = 0;
        int column = 4;
        int row2 = 5;
        int column2 = 1;
        for (int i = 0; i < 9; i++) {
            if (instance.getCurrentPlayerName().equals("Michel")) {
                if (i >= 6) {
                    instance.putBallOnBoard(row2, column2);
                    instance.askQuadrantToRotate(4, Direction.LEFT);
                    row2--;
                    column2--;
                } else {
                    instance.putBallOnBoard(row, column);
                    row++;
                    column--;
                    instance.askQuadrantToRotate(4, Direction.LEFT);
                }
            } else {
                instance.putBallOnBoard(0, columnP2);
                instance.askQuadrantToRotate(4, Direction.LEFT);
                columnP2++;
            }
        }
        instance.putBallOnBoard(2, 2);
        instance.askQuadrantToRotate(3, Direction.RIGHT);//Here is the rotation that gives the win
        boolean expResult = true;
        boolean result = instance.winner();
        assertEquals(expResult, result);
    }
    
    
    /**
     * =====================================    Tests on putBallOnBoard()   ===========================================
     */
    
    
    /**
     * Test to throw the Exception when we try to launch putBallOnBoard when
     * it's not the right game status.
     */
    @Test(expected = IllegalStateException.class)
    public void testPutBallOnBoardWhenNotInPlacingBallStatus() {
        Pentago instance = new Pentago();
        instance.createPlayer("Michel", "Younes");
        instance.putBallOnBoard(0, 0);
        instance.putBallOnBoard(4, 4);
    }
    
    /**
     * Test of putBallOnBoard method, of class Pentago.
     */
    @Test
    public void testPutBallOnBoard() {
        System.out.println("putBallOnBoard");
        int row = 0;
        int column = 0;
        Pentago instance = new Pentago();
        instance.createPlayer("Michel", "Younes");
        instance.putBallOnBoard(row, column);
    }

    
    /**
     * =====================================    Tests on checkIfCanPlaceBall()   ===========================================
     */
    
    
    /**
     * Test of checkIfCanPlaceBall method, of class Pentago.
     */
    @Test
    public void testCheckIfCanPlaceBall() {
        System.out.println("checkIfCanPlaceBall");
        int row = 0;
        int column = 0;
        Pentago instance = new Pentago();
        boolean expResult = true;
        instance.createPlayer("Michel", "Younes");
        boolean result = instance.checkIfCanPlaceBall(row, column);
        assertEquals(expResult, result);
    }

    /**
     * Test of checkIfCanPlaceBall method, of class Pentago when it cannot place
     * the ball.
     */
    @Test
    public void testCheckIfCannotPlaceBall() {
        System.out.println("checkIfCannotPlaceBall");
        Pentago instance = new Pentago();
        instance.createPlayer("Michel", "Younes");
        boolean expResult = false;
        instance.putBallOnBoard(0, 0);
        instance.askQuadrantToRotate(2, Direction.LEFT);
        boolean result = instance.checkIfCanPlaceBall(0, 0);
        assertEquals(expResult, result);
    }

    /**
     * Test of checkIfCanPlaceBall when it is not in PlacingBall Status.
     */
    @Test(expected = IllegalStateException.class)
    public void testCheckIfCanPlaceBallWhenNotInPlacingBallStatus() {
        System.out.println("testCheckIfCanPlaceBallWhenNotInPlacingBallStatus");
        Pentago instance = new Pentago();
        instance.createPlayer("Michel", "Younes");
        instance.putBallOnBoard(0, 0);
        instance.checkIfCanPlaceBall(0, 0);
    }

    /**
     * =====================================    Tests on quadrantDirection()   ===========================================
     */
    
    /**
     * Test of quadrantDirection method, of class Pentago when throw the exception
     */
    @Test(expected = IllegalArgumentException.class)
    public void testQuadrantDirectionWhenDirectionIsNull() {
        System.out.println("testQuadrantDirectionWhenDirectionIsNull");
        String direction = null;
        Pentago instance = new Pentago();
        instance.quadrantDirection(direction);
    }
    
    
    /**
     * Test of quadrantDirection method, of class Pentago for the left direction
     */
    @Test
    public void testQuadrantDirectionLeft() {
        System.out.println("quadrantDirectionLeft");
        String direction = "left";
        Pentago instance = new Pentago();
        Direction expResult = Direction.LEFT;
        Direction result = instance.quadrantDirection(direction);
        assertEquals(expResult, result);
    }
    
    /**
     * Test of quadrantDirection method, of class Pentago for the right direction
     */
    @Test
    public void testQuadrantDirectionRight() {
        System.out.println("quadrantDirectionRight");
        String direction = "right";
        Pentago instance = new Pentago();
        Direction expResult = Direction.RIGHT;
        Direction result = instance.quadrantDirection(direction);
        assertEquals(expResult, result);
    }
    
    /**
     * =====================================    Tests on isFull()   ===========================================
     */
    
    /**
     * Test of isFull.
     */
    @Test
    public void testIsFull() {
        System.out.println("testIsFull");
        Pentago instance = new Pentago();
        instance.createPlayer("Michel", "Younes");
        int quadrant = 4; 
        int row = 0;
        int column = 0;
        while (!instance.winner()) {
            for (int i = 0; i < 36; i++) {
                if(column == 5){
                    column = column%5;
                    row++;
                    if(row==4){
                        quadrant=1;
                    }
                }
                if (instance.getCurrentPlayerName().equals("Michel")) {
                    instance.putBallOnBoard(row, column);
                    column++;
                    instance.askQuadrantToRotate(quadrant, Direction.LEFT);
                } else {
                    instance.putBallOnBoard(row, column);
                    instance.askQuadrantToRotate(quadrant, Direction.LEFT);
                }
            }
        }
        boolean expResult = true;
        boolean result = instance.isFull();
        assertEquals(expResult, result);
    }
    
    /**
     * Test of isFull method, of class Pentago when it's not full.
     */
    @Test
    public void testIsNotFull() {
        System.out.println("isNotFull");
        Pentago instance = new Pentago();
        instance.createPlayer("Michel", "Alan");
        instance.putBallOnBoard(0, 0);
        boolean expResult = false;
        boolean result = instance.isFull();
        assertNotEquals(expResult, result);
    }
    
    /**
     * =====================================    Tests on askQuadrantToRotate()   ===========================================
     * 
     * 
     *      /!\ THE REAL METHOD "ROTATE" IS IN THE CLASS "QUADRANTS" /!\
     *          askQuadrantToRotate only calls the metod "rotate" that is in the
     *          Quadrants Class. But the quadrant rotation is also used in the
     *          winner tests section above. (When the winner win when he turns the
     *          quadrant).
     * 
     * 
     * /
    
    /**
     * Test of askQuadrantToRotate method, of class Pentago when throw the
     * Exception.
     */
    @Test(expected = IllegalStateException.class)
    public void testAskQuadrantToRotateException() {
        System.out.println("askQuadrantToRotateException");
        Pentago instance = new Pentago();
        instance.createPlayer("Michel", "Alan");
        instance.askQuadrantToRotate(1, Direction.RIGHT);
    }
    
    /**
     * Test of askQuadrantToRotate method, of class Pentago, rotate to right.
     */
    @Test
    public void testAskQuadrantToRotateRight() {
        System.out.println("askQuadrantToRotateRight");
        Pentago instance = new Pentago();
        instance.createPlayer("Michel", "Alan");
        instance.putBallOnBoard(0, 0);
        instance.askQuadrantToRotate(1, Direction.RIGHT);
    }
    
    /**
     * Test of askQuadrantToRotate method, of class Pentago, rotate to left.
     */
    @Test
    public void testAskQuadrantToRotateLeft() {
        System.out.println("askQuadrantToRotateLeft");
        Pentago instance = new Pentago();
        instance.createPlayer("Michel", "Alan");
        instance.putBallOnBoard(0, 0);
        instance.askQuadrantToRotate(1, Direction.LEFT);
    }

    
    /**
     *
     * =====================================    Tests on getColorEmpty()   ===========================================
     * 
     */
    
    
    /**
     * Test of getColorEmpty method, when there is an emptyball on the posisiton.
     */
    @Test
    public void testGetColorEmptyTrue() {
        System.out.println("getColorEmptyTrue");
        Pentago instance = new Pentago();
        boolean expResult = true;
        boolean result = instance.getColorEmpty(0, 0, 0);
        instance.createPlayer("Michel", "Alan");
        instance.putBallOnBoard(1, 0);
        assertEquals(expResult, result);
    }

    /**
     * Test of getColorEmpty method, when there isn't an emptyball on the posisiton.
     */
    @Test
    public void testGetColorEmptyFalse() {
        System.out.println("getColorEmptyFalse");
        Pentago instance = new Pentago();
        boolean expResult = false;
        instance.createPlayer("Michel", "Younes");
        instance.putBallOnBoard(0, 0);
        boolean result = instance.getColorEmpty(0, 0, 0);
        assertEquals(expResult, result);
    }

    
    /**
     *
     * =====================================    Tests on getColorBlack()   ===========================================
     * 
     */
    
    /**
     * Test of getColorBlack method, when there is an blackBall on the posisiton.
     */
    @Test
    public void testGetColorBlackTrue() {
        System.out.println("getColorBlackTrue");
        Pentago instance = new Pentago();
        boolean expResult = true;
        instance.createPlayer("Michel", "Younes");
        instance.putBallOnBoard(0, 0);
        instance.askQuadrantToRotate(4, Direction.LEFT);
        instance.putBallOnBoard(1, 0);
        boolean result = instance.getColorBlack(1, 0, 0);
        assertEquals(expResult, result);
    }

    /**
     * Test of getColorBlack method, when there isn't an blackBall on the posisiton.
     */
    @Test
    public void testGetColorBlackFalse() {
        System.out.println("getColorBlackFalse");
        Pentago instance = new Pentago();
        boolean expResult = false;
        instance.createPlayer("Michel", "Younes");
        instance.putBallOnBoard(0, 0);
        instance.askQuadrantToRotate(2, Direction.LEFT);
        instance.putBallOnBoard(1, 0);
        boolean result = instance.getColorBlack(2, 1, 1);
        assertEquals(expResult, result);
    }

    /**
     *
     * =====================================    Tests on getWhiteBlack()   ===========================================
     * 
     */
    
    
    /**
     * Test of getColorWhite method, when there is an whiteball on the posisiton.
     */
    @Test
    public void testGetColorWhiteTrue() {
        System.out.println("getColorWhiteTrue");
        int row = 0;
        int column = 0;
        Pentago instance = new Pentago();
        boolean expResult = true;
        instance.createPlayer("Michel", "Younes");
        instance.putBallOnBoard(row, column);
        boolean result = instance.getColorWhite(row, column, 0);
        assertEquals(expResult, result);
    }

    /**
     * Test of getColorWhite method, when there isn't an whiteball on the posisiton.
     */
    @Test
    public void testGetColorWhiteFalse() {
        System.out.println("getColorWhiteFalse");
        int row = 0;
        int column = 0;
        Pentago instance = new Pentago();
        boolean expResult = false;
        instance.createPlayer("Michel", "Younes");
        instance.putBallOnBoard(row, column);
        boolean result = instance.getColorWhite(row, column, 1);
        assertEquals(expResult, result);
    }

    
    /**
     *
     * =====================================    Tests on getWinnerName()   ===========================================
     * 
     */

    /**
     * Test of getWinnerName method, of class Pentago.
     */
    @Test
    public void testGetWinnerName() {
        System.out.println("testWinnerName");
        Pentago instance = new Pentago();
        instance.createPlayer("Michel", "Younes");
        int nbSameBalls = 0;
        for (int i = 0; i < 9; i++) {
            if (instance.getCurrentPlayerName().equals("Michel")) {
                instance.putBallOnBoard(0, nbSameBalls);
                nbSameBalls++;
                instance.askQuadrantToRotate(3, Direction.LEFT);
            } else {
                instance.putBallOnBoard(5, nbSameBalls);
                instance.askQuadrantToRotate(3, Direction.LEFT);
            }
        }
        String expResult = "Michel";
        String result = instance.getWinnerName();
        assertEquals(expResult, result);
    }
}
