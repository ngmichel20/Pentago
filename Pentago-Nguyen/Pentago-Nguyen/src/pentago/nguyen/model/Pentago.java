package pentago.nguyen.model;

import java.util.ArrayList;
import java.util.List;
import pentago.nguyen.util.*;

/**
 * This is the facade of the model.
 *
 * @author g48962
 */
public class Pentago implements Observable {

    private Player player1;
    private Player player2;
    private List<Player> listPlayers;
    private Player currentPlayer;
    private List<Observer> observers = new ArrayList<>();
    private final Board board;
    private GameStatus gameStatus;

    /**
     * This is the constructor of Pentago.
     */
    public Pentago() {
        this.board = new Board();
        this.listPlayers = new ArrayList<>();
    }

    /**
     * This method allows to create the players.
     *
     * @param namePlayer1 is the name of the player 1.
     * @param namePlayer2 is the name of the player 2.
     */
    public void createPlayer(String namePlayer1, String namePlayer2) {
        this.player1 = new Player(BallColor.WHITE, namePlayer1);
        this.player2 = new Player(BallColor.BLACK, namePlayer2);
        gameStatus = GameStatus.PLACING_BALL;
        this.currentPlayer = player1;
    }
    
    public void addPlayer(String name){
        if(listPlayers.isEmpty()){
            listPlayers.add(new Player(BallColor.WHITE, name));
        }else{
            listPlayers.add(new Player(BallColor.BLACK, name));
        }
    }

    /**
     * This method allows to choose the next player to play.
     *
     * @return the currentPlayer.
     */
    public Player nextPlayer() {
        if (gameStatus == GameStatus.QUADRANT_ROTATION) {
            throw new IllegalStateException("The game status is not in "
                    + "PlacingBall status (checkIfCanPlaceBall)");
        } else {
            if (this.currentPlayer.equals(player1)) {
                return currentPlayer = player2;
            } else {
                return currentPlayer = player1;
            }
        }
    }

    /**
     * This methdo allows to know the winner of the game.
     *
     * @return true if there is a winner.
     */
    public boolean winner() {
        if (gameStatus == GameStatus.END_GAME) {
            throw new IllegalStateException("The status is on END_GAME but the "
                    + "game is not over yet !");
        }
        boolean hasWinner = false;
        if (board.checkWinner(player1, player2) != null) {
            gameStatus = GameStatus.END_GAME;
            hasWinner = true;
        }
        return hasWinner;
    }

    /**
     * This method allows to know if the board is full or not.
     *
     * @return true if the board is full.
     */
    public boolean isFull() {
        boolean isNotFull = false;
        int k = 0;
        while (k < 4 && !isNotFull) {
            for (int row = 0; row < board.getBoard()[k].getQuadrantSize(); row++) {
                for (int column = 0; column < board.getBoard()[k].getQuadrantSize(); column++) {
                    if (this.board.getBoard()[k].getQuadrant()[row][column].getBallColor() == BallColor.EMPTY) {
                        isNotFull = true;
                    }
                }
            }
            k++;
        }
        return isNotFull;
    }

    /**
     * This method allows to put a ball on the board.
     *
     * @param row is the row of the board.
     * @param column is the column of the board.
     */
    public void putBallOnBoard(int row, int column) {
        if (gameStatus != GameStatus.PLACING_BALL) {
            throw new IllegalStateException("The game status is not in "
                    + "PlacingBall status");
        } else {
            if (currentPlayer.getColorPlayer() == BallColor.BLACK) {
                this.board.addBallOnBoard(row, column, currentPlayer);
            } else {
                this.board.addBallOnBoard(row, column, currentPlayer);
            }
            gameStatus = GameStatus.QUADRANT_ROTATION;
            notifyObservers();
        }
    }

    /**
     * This method allows to check if the player can place a ball on the board.
     *
     * @param row is the row.
     * @param column is the column.
     * @return true if the player can put a ball.
     */
    public boolean checkIfCanPlaceBall(int row, int column) {
        boolean can = true;
        if (gameStatus != GameStatus.PLACING_BALL) {
            throw new IllegalStateException("The game status is not in "
                    + "PlacingBall status (checkIfCanPlaceBall)");
        } else {
            if (this.board.getBoard()[((row / 3) * 2) + (column / 3)].getQuadrant()[row % 3][column % 3].getBallColor() != BallColor.EMPTY) {
                can = false;
            }
        }
        return can;
    }

    /**
     * This method allows to know which quadrant the player wants to rotate.
     *
     * @param numQuadrant is the quadrant chosen by the player.
     * @param direction is the direction chosen by the player.
     */
    public void askQuadrantToRotate(int numQuadrant, Direction direction) {
        if (gameStatus != GameStatus.QUADRANT_ROTATION) {
            throw new IllegalStateException("The game status is not in "
                    + "PlacingBall status (askQuadrantToRotate)");
        } else {
            board.getBoard()[numQuadrant - 1].rotate(direction);
            gameStatus = GameStatus.PLACING_BALL;
            nextPlayer();
            notifyObservers();
        }
    }

    /**
     * This method allows to have the direction that the player
     *
     * @param direction is the direction.
     * @return the direction.
     */
    public Direction quadrantDirection(String direction) {
        if (direction == null) {
            throw new IllegalArgumentException("The direction is null !");
        }
        Direction dir = null;
        if (direction.equalsIgnoreCase("left")) {
            dir = Direction.LEFT;
        } else if (direction.equalsIgnoreCase("right")) {
            dir = Direction.RIGHT;
        }
        return dir;
    }

    /**
     * This method allows to check if there is a empty ball on the quadrant.
     *
     * @param row is the row.
     * @param column is the column.
     * @param k is the quadrant number.
     * @return true if there a ball on the quadrant.
     */
    public boolean getColorEmpty(int row, int column, int k) {
        boolean isEmpty = false;
        if (this.board.getBoard()[k].getQuadrant()[row][column].getBallColor() == BallColor.EMPTY) {
            isEmpty = true;
        }
        return isEmpty;
    }

    /**
     * This method allows to check if there is a black ball on the quadrant.
     *
     * @param row is the row.
     * @param column is the column.
     * @param k is the quadrant number.
     * @return true if there a black ball on the quadrant.
     */
    public boolean getColorBlack(int row, int column, int k) {
        boolean isBlack = false;
        if (this.board.getBoard()[k].getQuadrant()[row][column].getBallColor() == BallColor.BLACK) {
            isBlack = true;
        }
        return isBlack;
    }

    /**
     * This method allows to check if there is a white ball on the quadrant.
     *
     * @param row is the row.
     * @param column is the column.
     * @param k is the quadrant number.
     * @return true if there a white ball on the quadrant.
     */
    public boolean getColorWhite(int row, int column, int k) {
        boolean isWhite = false;
        if (this.board.getBoard()[k].getQuadrant()[row][column].getBallColor() == BallColor.WHITE) {
            isWhite = true;
        }
        return isWhite;
    }

    /**
     * This is the getter of PLACING_BALL.
     *
     * @return the PLACING_BALL game status.
     */
    public GameStatus getPlacingBallState() {
        return gameStatus.PLACING_BALL;
    }

    /**
     * This is the getter of QUADRANT_ROTATION.
     *
     * @return the QUADRANT_ROTATION game status.
     */
    public GameStatus getRotatingState() {
        return gameStatus.QUADRANT_ROTATION;
    }

    /**
     * This is the getter of galeStatus.
     *
     * @return the galeStatus.
     */
    public GameStatus getGameStatus() {
        return gameStatus;
    }

    /**
     * This is the getter of currentPlayer.
     *
     * @return the currentPlayer.
     */
    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    /**
     * This method allows to get the name of the current player.
     *
     * @return the name of the current player.
     */
    public String getCurrentPlayerName() {
        return currentPlayer.getName();
    }

    /**
     * This is the getter of the player2.
     *
     * @return the player 2.
     */
    public Player getPlayer2() {
        return player2;
    }
    
    /**
     * This is the getter of the player1.
     * 
     * @return the player 1. 
     */
    public Player getPlayer1() {
        return player1;
    }

    /**
     * This method allows to have the winner's name.
     *
     * @return the winner's name.
     */
    public String getWinnerName() {
        return board.checkWinner(player1, player2).getName();
    }

    /**
     * This method allows to notify all the Observers.
     */
    private void notifyObservers() {
        for (Observer observer : observers) {
            observer.update();
        }
    }

    /**
     * This method allows to add an Observer.
     *
     * @param obs is an Observer.
     */
    public void addObserver(Observer obs) {
        observers.add(obs);
    }

    /**
     * This method allows to remove an Observer.
     *
     * @param obs is an Observer.
     */
    public void removeObserver(Observer obs) {
        observers.remove(obs);
    }

    /**
     * This method allows to notify when a quadrant is selected with the mouse.
     */
    public void qNotify() {
        notifyObservers();
    }
}
