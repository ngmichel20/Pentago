package pentago.nguyen.model;

/**
 * This is the class Quadrants.
 *
 * @author Michel
 */
class Quadrants {

    private final int quadrantSize = 3;
    private Ball quadrant[][] = new Ball[quadrantSize][quadrantSize];

    /**
     * This is the constructor of Quadrants.
     */
    Quadrants() {
        if (quadrant == null) {
            throw new NullPointerException("The quadrant has not benn created.");
        } else {
            for (int i = 0; i < quadrantSize; i++) {
                for (int j = 0; j < quadrantSize; j++) {
                    quadrant[i][j] = new Ball(BallColor.EMPTY);
                }
            }
        }
    }

    /**
     * This method allows to add a ball on the quadrant.
     *
     * @param row is the row.
     * @param column is the column.
     * @param player is the current player.
     */
    void addBallOnQuadrant(int row, int column, Player player) {
        if (ballEmpty(row, column)) {
            quadrant[row % 3][column % 3].setBallColor(player.getColorPlayer());
        }
    }

    /**
     * This method allows to rotate the quadrant chosen.
     *
     * @param direction is the direction chosen.
     */
    void rotate(Direction direction) {
        Ball newQuadrant[][] = new Ball[quadrantSize][quadrantSize];
        if (direction == Direction.RIGHT) {
            newQuadrant = rotateToRight(newQuadrant);
        } else if (direction == Direction.LEFT) {
            newQuadrant = rotateToLeft(newQuadrant);
        }
        quadrant = newQuadrant;
    }

    /**
     * This method allows to rotate the quadrant to the left.
     *
     * @param newQuadrant the new quadrant rotated.
     * @return the new quadrant.
     */
    private Ball[][] rotateToRight(Ball newQuadrant[][]) {
        for (int i = 0; i < quadrantSize; i++) {
            for (int j = 0; j < quadrantSize; j++) {
                newQuadrant[i][j] = quadrant[2 - j][i];
                //newQuadrant[i][j] = quadrant[i][2-j];
            }
        }
        return newQuadrant;
    }

    /**
     * This method allows to rotate the quadrant to the right.
     *
     * @param newQuadrant the new quadrant rotated.
     * @return the new quadrant.
     */
    private Ball[][] rotateToLeft(Ball newQuadrant[][]) {
        for (int i = 0; i < quadrantSize; i++) {
            for (int j = 0; j < quadrantSize; j++) {
                newQuadrant[i][j] = quadrant[j][2 - i];
                //newQuadrant[i][j] = quadrant[2-i][j];
            }
        }
        return newQuadrant;
    }

    /**
     * This method allows to check if there is a empty ball on the row and the
     * column chosen.
     *
     * @param row is the row.
     * @param column is the column.
     * @return true if there is a empty ball on the row and the column chosen.
     */
    private boolean ballEmpty(int row, int column) {
        return quadrant[row % 3][column % 3].getBallColor() == BallColor.EMPTY;
    }

    /**
     * This is the getter of Quadrant.
     *
     * @return the quadrant.
     */
    public Ball[][] getQuadrant() {
        return quadrant;
    }

    /**
     * This is the quadrant size.
     *
     * @return the quadrant size.
     */
    int getQuadrantSize() {
        return quadrantSize;
    }
}
