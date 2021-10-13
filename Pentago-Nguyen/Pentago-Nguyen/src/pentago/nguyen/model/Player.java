package pentago.nguyen.model;

/**
 * This is the class player.
 *
 * @author g48962
 */
class Player {

    private final String name;
    private final BallColor colorPlayer;

    /**
     * This is the Player constructor.
     *
     * @param ballColor is the color of the ball which is the color of the
     * player.
     * @param name is the name of the player.
     */
    Player(BallColor ballColor, String name) {
        if (ballColor == null || name == null) {
            throw new IllegalArgumentException("A player has not been created !");
        }
        this.colorPlayer = ballColor;
        this.name = name;
    }

    /**
     * This is the getter of colorPlayer.
     *
     * @return the color of the player.
     */
    BallColor getColorPlayer() {
        return colorPlayer;
    }

    /**
     * This is the getter of name.
     *
     * @return the name of the player.
     */
    String getName() {
        return name;
    }
    
    
}
