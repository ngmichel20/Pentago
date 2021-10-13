package pentago.nguyen.model;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Michel
 */
public class QuadrantsTest {
    
    /**
     * Test of addBallOnQuadrant method, of class Quadrants.
     */
    @Test
    public void testAddBallOnQuadrant() {
        System.out.println("addBallOnQuadrant");
        int row = 0;
        int column = 0;
        Player player = new Player(BallColor.BLACK, "Michel");
        Quadrants instance = new Quadrants();
        instance.addBallOnQuadrant(row, column, player);
    }

    /**
     * Test of rotate method, of class Quadrants.
     */
    @Test
    public void testRotateLeft() {
        System.out.println("rotateLeft");
        Direction direction = Direction.LEFT;
        Quadrants instance = new Quadrants();
        instance.rotate(direction);
    }
    
    /**
     * Test of rotate method, of class Quadrants.
     */
    @Test
    public void testRotateRight() {
        System.out.println("rotateRight");
        Direction direction = Direction.RIGHT;
        Quadrants instance = new Quadrants();
        instance.rotate(direction);
    }
}
