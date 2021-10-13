package pentago.nguyen.model;

import org.junit.Test;

/**
 *
 * @author Michel
 */
public class PlayerTest {

    /**
     * ========================== Tests Constructor Of Player ======================
     */
    
    /**
     * Test to create a player but with no name.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testCreateAPlayerNameFail(){
        System.out.println("nameEqualsNull");
        Player p1 = new Player(BallColor.BLACK, null);
    }
    
    /**
     * Test to create a player but with color.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testCreateAPlayerColorFail(){
        System.out.println("colorEqualsNull");
        Player p1 = new Player(null, "Michel");
    }
}
