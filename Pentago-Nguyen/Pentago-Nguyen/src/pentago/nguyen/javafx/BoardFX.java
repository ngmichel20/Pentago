package pentago.nguyen.javafx;

import java.util.ArrayList;
import java.util.List;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.effect.Glow;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import pentago.nguyen.model.Pentago;
import pentago.nguyen.util.Observer;

/**
 * This is the BoardFX class.
 *
 * @author Michel
 */
class BoardFX extends GridPane implements Observer{

    private final int nbTotalQuadrant = 4;
    private Pentago pentago;
    private List<QuadrantFX> allQuadrants;
    private GridPane boardGP;
    private int numQuadrantSelected;
    private boolean isQuadrantSelected;

    /**
     * This is the contructor of BoardFX.
     *
     * @param pentago is the model.
     */
    BoardFX(Pentago pentago) {
        this.boardGP = new GridPane();
        this.allQuadrants = new ArrayList<>();
        this.isQuadrantSelected = false;
        this.pentago = pentago;
        initAllQuadrants();
        addQuadrantOnGP();
        add(boardGP, 0, 0);
        setAlignment(Pos.CENTER);
        this.pentago.addObserver(this);
    }

    /**
     * This method allows to initializate all the quadrants of the game.
     */
    private void initAllQuadrants() {
        for (int i = 0; i < nbTotalQuadrant; i++) {
            allQuadrants.add(new QuadrantFX(i, this.pentago));
        }
    }

    /**
     * This method allows to add a quadrant on the boardGP.
     */
    private void addQuadrantOnGP() {
        boardGP.add(allQuadrants.get(0), 0, 0);
        boardGP.add(allQuadrants.get(1), 1, 0);
        boardGP.add(allQuadrants.get(2), 0, 1);
        boardGP.add(allQuadrants.get(3), 1, 1);
        boardGP.setAlignment(Pos.CENTER);
        boardGP.setHgap(2);
        boardGP.setVgap(2);
    }

    /**
     * This method allows to display the board (in fact it refresh every
     * quadrants one by one).
     */
    private void displayBoard() {
        for (int i = 0; i < allQuadrants.size(); i++) {
            allQuadrants.get(i).refreshAllQuadrants(i);
        }
    }

    /**
     * This method allows to do the mouse event on the quadrant which we select
     * or go in with the mouse.
     */
    private void mouseQuadrantsSelection() {
        Glow glow = new Glow(0.9);
        for (int i = 0; i < nbTotalQuadrant; i++) {
            final int j = i;
            allQuadrants.get(i).setOnMouseEntered((MouseEvent event) -> {
                allQuadrants.get(j).setEffect(glow);
            });
            allQuadrants.get(i).setOnMouseExited((MouseEvent event) -> {
                allQuadrants.get(j).setEffect(null);
            });
            allQuadrants.get(i).setOnMouseClicked((MouseEvent event) -> {
                numQuadrantSelected = allQuadrants.get(j).getnQuandrant();
                isQuadrantSelected = true;
                pentago.qNotify();
            });
        }
    }

    /**
     * This method allows to desactivate all the mouseEvent of the quadrants.
     */
    private void desactivateMouseEvent() {
        isQuadrantSelected = false;
        for (int i = 0; i < nbTotalQuadrant; i++) {
            allQuadrants.get(i).setOnMouseClicked(null);
            allQuadrants.get(i).setOnMouseEntered(null);
            allQuadrants.get(i).setOnMouseExited(null);
        }
    }

    /**
     * This method is the getter of the quadrant selected.
     *
     * @return the number of the quadrant selected.
     */
    int getNumQuadrantSelected() {
        return numQuadrantSelected;
    }
   
    /**
     * This method allows to know if a quadrant has been selected.
     *
     * @return true if it has been selected.
     */
    boolean isIsQuadrantSelected() {
        return isQuadrantSelected;
    }

    /**
     * This method does an alert when there is a winner.
     *
     * @return the alert.
     */
    private Alert alertWinner() {
        Alert alertWinner = new Alert(Alert.AlertType.CONFIRMATION);
        alertWinner.setTitle("End of the game !");
        alertWinner.setHeaderText("The game is over !");
        alertWinner.setContentText("The winner is : " + pentago.getWinnerName());
        alertWinner.showAndWait();
        return alertWinner;
    }

    /**
     * This method does an alert when there is a draw in the game.
     *
     * @return the alert.
     */
    private Alert alertDraw() {
        Alert alertDraw = new Alert(Alert.AlertType.CONFIRMATION);
        alertDraw.setTitle("End of the game !");
        alertDraw.setHeaderText("The game is over !");
        alertDraw.setContentText("It's a draw !");
        alertDraw.showAndWait();
        return alertDraw;
    }

    @Override
    public void update() {
        displayBoard();
        if (!pentago.isFull()) {
            alertDraw();
            boardGP.setDisable(true);
        } else if (pentago.winner()) {
            alertWinner();
            boardGP.setDisable(true);
        }
        if (pentago.getGameStatus() == pentago.getPlacingBallState()) {
            desactivateMouseEvent();
        } else if (pentago.getGameStatus() == pentago.getRotatingState()) {
            mouseQuadrantsSelection();
        }
    }
}
