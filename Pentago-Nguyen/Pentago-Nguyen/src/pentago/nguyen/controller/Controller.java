package pentago.nguyen.controller;

import pentago.nguyen.model.Pentago;
import pentago.nguyen.view.View;

/**
 * This class is the controller.
 *
 * @author Michel
 */
public class Controller {

    private View view;
    private Pentago pentago;

    /**
     * This is the controller constructor.
     *
     * @param pentago is the model pentago.
     * @param view is the view.
     */
    public Controller(Pentago pentago, View view) {
        this.view = view;
        this.pentago = pentago;
    }

    /**
     * This is the start method of the game.
     */
    public void start() {
        view.askNamePlayer();
        view.displayInitBoard2();
        while (!pentago.winner()|| !pentago.isFull()) {
            view.askCurrentPlayerPlaceBall();
            int row = view.askRow();
            int column = view.askColumn();
            while (!pentago.checkIfCanPlaceBall(row, column)) {
                view.displayAlreadyBall();
                view.askCurrentPlayerPlaceBall();
                row = view.askRow();
                column = view.askColumn();
            }
            pentago.putBallOnBoard(row, column);
            view.displayInitBoard2();
            gameContinueOrNot();
        }
    }

    /**
     * This method allows to check the quadrant and the direction.
     */
    private void quadrantAndDirection() {
        int numQuadrantAnswer = view.checkQuadrant();
        String s = view.askPlayerDirection();
        pentago.askQuadrantToRotate(numQuadrantAnswer, pentago.quadrantDirection(s));
        view.displayRotationMessage(numQuadrantAnswer, s);
        view.displayInitBoard2();
        if (pentago.winner()) {
            view.displayWinner();
        }
    }

    /**
     * This method allows to know if the game continues or not.
     */
    private void gameContinueOrNot() {
        if (pentago.winner()) {
            view.displayWinner();
        } else {
            quadrantAndDirection();
        }
    }
}
