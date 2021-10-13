package pentago.nguyen.model;

/**
 * This is the class Ball.
 *
 * @author g48962
 */
class Ball {

    private BallColor ballColor;

    /**
     * This is the Ball constructor.
     *
     * @param ballColor is the color of the ball.
     */
    Ball(BallColor ballColor) {
        if(ballColor == null){
            throw new NullPointerException("The ball color has not been created.");
        }
        this.ballColor = ballColor;
    }

    /**
     * This is the getter of Ball.
     *
     * @return the color of the ball.
     */
    BallColor getBallColor() {
        return ballColor;
    }

    /**
     * This is the setter of Ball.
     *
     * @param ballColor is the color of the ball who will set.
     */
    void setBallColor(BallColor ballColor) {
        this.ballColor = ballColor;
    }
}
