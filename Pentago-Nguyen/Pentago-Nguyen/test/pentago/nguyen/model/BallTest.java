package pentago.nguyen.model;

import org.junit.Test;

/**
 *
 * @author Michel
 */
public class BallTest {
    
    /**
     * Test of the creation of a ball if it is null.
     */
    @Test(expected = NullPointerException.class)
    public void testCreateBallWithNoColor(){
        System.out.println("testCreateBallWithNoColor");
        Ball ball = new Ball(null);
    }

    /**
     * Test of setBallColor method, of class Ball.
     */
    @Test
    public void testSetBallColor() {
        System.out.println("setBallColor");
        BallColor ballColor = BallColor.BLACK;
        Ball instance = new Ball(BallColor.EMPTY);
        instance.setBallColor(ballColor);
    }
}
