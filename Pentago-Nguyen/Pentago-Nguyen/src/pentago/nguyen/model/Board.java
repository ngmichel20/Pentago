package pentago.nguyen.model;

/**
 * This is the class Board.
 *
 * @author g48962
 */
class Board {

    private final int boardSize = 6;
    private final int numberOfQuadrant = 4;
    private Quadrants[] board = new Quadrants[numberOfQuadrant];
    private Ball[][] copiedBoard = new Ball[boardSize][boardSize];

    /**
     * This is the constructor of Board.
     */
    Board() {
        if (boardSize == 0) {
            throw new ArithmeticException("The board has not been created. "
                    + "Maybe it is equals 0.");
        } else {
            for (int i = 0; i < numberOfQuadrant; i++) {
                board[i] = new Quadrants();
            }
        }
    }

    /**
     * This method allows to add a ball on the board and calculates which
     * quadrant it will be, with the row and the column given.
     *
     * @param row is the row.
     * @param column is the column.
     * @param player is the current Player.
     */
    void addBallOnBoard(int row, int column, Player player) {
        board[((row / 3) * 2) + (column / 3)].addBallOnQuadrant(row, column, player);
    }

    /**
     * This method allows to check the winner player.
     *
     * @param player1 is the player one.
     * @param player2 is the player two.
     * @return the winner.
     */
    Player checkWinner(Player player1, Player player2) {
        copiedBoard = copyBoardIn6x6();
        Player winner = null;
        winner = checkColumn(player1, player2, winner);
        if (winner != null) {
            return winner;
        }
        if (winner == null) {
            winner = checkRow(player1, player2, winner);
        }
        if (winner == null) {
            winner = checkDiagDescendingAtLeft(player1, player2, winner);
        }
        if (winner == null) {
            winner = checkDiagDescendingAtRight(player1, player2, winner);
        }
        return winner;
    }

    /**
     * This method allows to see if there is a winner on the rows.
     *
     * @param player1 is the player one.
     * @param player2 is the player two.
     * @param winner is the winner.
     * @return the winner.
     */
    private Player checkRow(Player player1, Player player2, Player winner) {
        int nbBallsSame = 0;
        for (int i = 0; i < copiedBoard.length; i++) {
            nbBallsSame = 0;
            for (int j = 0; j < copiedBoard.length-1; j++) {
                int k = j;
                k++;
                    if ((copiedBoard[i][j].getBallColor() != BallColor.EMPTY) && (copiedBoard[i][j].getBallColor() == copiedBoard[i][k].getBallColor())) {
                        nbBallsSame++;
                        if (nbBallsSame == 4 && copiedBoard[i][j].getBallColor() == player1.getColorPlayer()) {
                            winner = player1;
                        } else if (nbBallsSame == 4 && copiedBoard[i][j].getBallColor() == player2.getColorPlayer()) {
                            winner = player2;
                        }
                    } else {
                        nbBallsSame = 0;
                    }
                }
            
        }
        return winner;
    }

    /**
     * This method allows to see if there is a winner on the columns.
     *
     * @param player1 is the player one.
     * @param player2 is the player two.
     * @param winner is the winner.
     * @return the winner.
     */
    private Player checkColumn(Player player1, Player player2, Player winner) {
        int nbBallsSame = 0;
        for (int j = 0; j < copiedBoard.length; j++) {
            nbBallsSame = 0;
            for (int i = 0; i < copiedBoard.length-1; i++) {
                int k = i;
                k++;
                if ((copiedBoard[i][j].getBallColor() != BallColor.EMPTY) && (copiedBoard[i][j].getBallColor() == copiedBoard[k][j].getBallColor())) {
                    nbBallsSame++;
                    if (nbBallsSame == 4) {
                        if (copiedBoard[i][j].getBallColor() == player1.getColorPlayer()) {
                            winner = player1;
                        } else {
                            winner = player2;
                        }
                    }
                } else {
                    nbBallsSame = 0;
                }
            }
        }
        return winner;
    }

    /**
     * This method allows to see if there is a winner on the diagonals
     * (descending) from the top left of the copy board.
     *
     * @param player1 is the player one.
     * @param player2 is the player two.
     * @param winner is the winner.
     * @return the winner.
     */
    private Player checkDiagDescendingAtLeft(Player player1, Player player2, Player winner) {
        int lengthBoardMinusOne = copiedBoard.length - 1;
        for (int column = 0; column < copiedBoard.length * 2; column++) {
            if (column <= lengthBoardMinusOne) {
                winner = diagonalInverseAtLeft(0, column, player1, player2, winner);
            } else {
                winner = diagonalInverseAtLeft(copiedBoard.length - lengthBoardMinusOne, lengthBoardMinusOne, player1, player2, winner);
            }
        }
        return winner;
    }

    /**
     * This method allows to see if there is a winner on the diagonals
     * (descending) from the top right of the copy board.
     *
     * @param player1 is the player one.
     * @param player2 is the player two.
     * @param winner is the winner.
     * @return the winner.
     */
    private Player checkDiagDescendingAtRight(Player player1, Player player2, Player winner) {
        int lengthBoard = copiedBoard.length;
        for (int column = copiedBoard.length - 1; (lengthBoard * 2) > 0; lengthBoard--) {
            column--;
            if (column >= 0) {
                winner = diagonalInverseAtRight(0, column, player1, player2, winner);
            } else {
                winner = diagonalInverseAtRight(Math.abs(column), 0, player1, player2, winner);
            }
        }
        return winner;
    }

    /**
     * This method allows to browse all the diagonals from the top left of the
     * copied board.
     *
     * @param row is the row.
     * @param column is the column.
     * @param player1 is the player one.
     * @param player2 is the player two.
     * @param winner is the winner.
     * @return the winner.
     */
    private Player diagonalInverseAtLeft(int row, int column, Player player1, Player player2, Player winner) {
        int nbBallsSame = 0;
        while (row < copiedBoard.length - 1 && column > 0) {
            if ((copiedBoard[row][column].getBallColor() != BallColor.EMPTY)
                    && (copiedBoard[row][column].getBallColor() == copiedBoard[row + 1][column - 1].getBallColor())) {
                nbBallsSame++;
                if (nbBallsSame == 4 && copiedBoard[row][column].getBallColor() == player1.getColorPlayer()) {
                    winner = player1;
                } else if (nbBallsSame == 4 && copiedBoard[row][column].getBallColor() == player2.getColorPlayer()) {
                    winner = player2;
                }
            } else {
                nbBallsSame = 0;
            }
            row += 1;
            column -= 1;
        }
        return winner;
    }

    /**
     * This method allows to browse all the diagonals from the top right of the
     * copied board.
     *
     * @param row is the row.
     * @param column is the column.
     * @param player1 is the player one.
     * @param player2 is the player two.
     * @param winner is the winner.
     * @return the winner.
     */
    private Player diagonalInverseAtRight(int row, int column, Player player1, Player player2, Player winner) {
        int nbBallsSame = 0;
        while (row < copiedBoard.length - 1 && column < copiedBoard.length - 1) {
            if ((copiedBoard[row][column].getBallColor() != BallColor.EMPTY)
                    && (copiedBoard[row][column].getBallColor() == copiedBoard[row + 1][column + 1].getBallColor())) {
                nbBallsSame++;
                if (nbBallsSame == 4 && copiedBoard[row][column].getBallColor() == player1.getColorPlayer()) {
                    winner = player1;
                } else if (nbBallsSame == 4 && copiedBoard[row][column].getBallColor() == player2.getColorPlayer()) {
                    winner = player2;
                }
            } else {
                nbBallsSame = 0;
            }
            row += 1;
            column += 1;
        }
        return winner;
    }

    /**
     * This method allows to convert the board of quadrants in a board in 6x6 of
     * Balls and it will be use when there is a winner.
     *
     * @return the board in 6x6 of Balls.
     */
    private Ball[][] copyBoardIn6x6() {
        int k = 0;
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                if (i < 3) {
                    copiedBoard = copyBoardQuadrant1And2(i, j, k);
                } else {
                    copiedBoard = copyBoardQuadrant3And4(i, j, k);
                }
            }
        }
        return copiedBoard;
    }

    /**
     * This method allows to copy the quadrants 1 and 2 of the basic board, on
     * the copied board.
     *
     * @param i is the row.
     * @param j is the column.
     * @param k is the quadrant number.
     * @return the copied board.
     */
    private Ball[][] copyBoardQuadrant1And2(int i, int j, int k) {
        if (j < 3) {
            k = 0;
            copiedBoard[i][j] = board[k].getQuadrant()[i][j];
        } else {
            k = 1;
            copiedBoard[i][j] = board[k].getQuadrant()[i][j % 3];
        }
        return copiedBoard;
    }

    /**
     * This method allows to copy the quadrants 3 and 4 of the basic board, on
     * the copied board.
     *
     * @param i is the row.
     * @param j is the column.
     * @param k is the quadrant number.
     * @return the copied board.
     */
    private Ball[][] copyBoardQuadrant3And4(int i, int j, int k) {
        if (j < 3) {
            k = 2;
            copiedBoard[i][j] = board[k].getQuadrant()[i % 3][j];
        } else {
            k = 3;
            copiedBoard[i][j] = board[k].getQuadrant()[i % 3][j % 3];
        }
        return copiedBoard;
    }

    /**
     * This is the getter of copiedBoard.
     *
     * @return the coped board.
     */
    Ball[][] getCopiedBoard() {
        return copiedBoard;
    }

    /**
     * This is the getter of BoardSize.
     *
     * @return the size of the board.
     */
    int getBoardSize() {
        return boardSize;
    }

    /**
     * This is the getter of board.
     *
     * @return the board of quadrants.
     */
    Quadrants[] getBoard() {
        return board;
    }
}
