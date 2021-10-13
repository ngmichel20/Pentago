package pentago.nguyen.javafx;

import javafx.scene.effect.Light;
import javafx.scene.effect.Lighting;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import pentago.nguyen.model.Pentago;

/**
 * This is the QuadrantFX class.
 *
 * @author Michel
 */
class QuadrantFX extends GridPane {

    private int quadrantSize = 3;
    private CircleFX quadrant[][] = new CircleFX[quadrantSize][quadrantSize];
    private Pentago pentago;
    private int nQuandrant;

    /**
     * This is the constrcutor of QuadrantFX.
     *
     * @param numQuadrant is the quadrant number.
     * @param pentago is the model.
     */
    QuadrantFX(int numQuadrant, Pentago pentago) {
        this.pentago = pentago;
        this.nQuandrant = numQuadrant;
        initAllQuadrants(numQuadrant);
        setVgap(10);
        setHgap(10);
    }

    /**
     * This method allows to init all the quadrants with the circle balls on it.
     *
     * @param numQuadrant is the number of the quadrant.
     */
    private void initAllQuadrants(int numQuadrant) {
        int startRow = 0;
        int endRow = 0;
        int startColumn = 0;
        int endColumn = 0;
        switch (numQuadrant) {
            case 0:
                initQuadrantOne(startRow, endRow, startColumn, endColumn, numQuadrant);
                break;
            case 1:
                initQuadrantTwo(startRow, endRow, startColumn, endColumn, numQuadrant);
                break;
            case 2:
                initQuadrantThree(startRow, endRow, startColumn, endColumn, numQuadrant);
                break;
            case 3:
                initQuadrantFour(startRow, endRow, startColumn, endColumn, numQuadrant);
                break;
        }
    }

    /**
     * This method allows to initializate the quadrant One.
     *
     * @param startRow is the row start.
     * @param endRow is the row end.
     * @param startColumn is the column start.
     * @param endColumn is the end column.
     * @param numQuadrant is the number of the quadrant.
     */
    private void initQuadrantOne(int startRow, int endRow, int startColumn, int endColumn, int numQuadrant) {
        startRow = 0;
        endRow = quadrantSize;
        startColumn = 0;
        endColumn = quadrantSize;
        Lighting lighting = lightPointAndLighting();
        for (int i = startRow; i < endRow; i++) {
            for (int j = startColumn; j < endColumn; j++) {
                quadrant[i][j] = new CircleFX(30, i, j);
                if (pentago.getColorEmpty(i, j, numQuadrant)) {
                    addEmptyBall(quadrant[i][j], i, j);
                } else if (pentago.getColorWhite(i, j, numQuadrant)) {
                    addColorBall(quadrant[i][j], i, j, lighting, Color.WHITE);
                } else {
                    addColorBall(quadrant[i][j], i, j, lighting, Color.BLACK);
                }
            }
        }
    }

    /**
     * This method allows to initializate the quadrant Two.
     *
     * @param startRow is the row start.
     * @param endRow is the row end.
     * @param startColumn is the column start.
     * @param endColumn is the end column.
     * @param numQuadrant is the number of the quadrant.
     */
    private void initQuadrantTwo(int startRow, int endRow, int startColumn, int endColumn, int numQuadrant) {
        startRow = 0;
        endRow = quadrantSize;
        startColumn = quadrantSize;
        endColumn = quadrantSize * 2;
        Lighting lighting = lightPointAndLighting();
        for (int i = startRow; i < endRow; i++) {
            for (int j = startColumn; j < endColumn; j++) {
                quadrant[i][j % quadrantSize] = new CircleFX(30, i, j);
                if (pentago.getColorEmpty(i, j % quadrantSize, numQuadrant)) {
                    addEmptyBall(quadrant[i][j % quadrantSize], i, j);
                } else if (pentago.getColorWhite(i, j % quadrantSize, numQuadrant)) {
                    addColorBall(quadrant[i][j % quadrantSize], i, j, lighting, Color.WHITE);
                } else {
                    addColorBall(quadrant[i][j % quadrantSize], i, j, lighting, Color.BLACK);
                }
            }
        }
    }

    /**
     * This method allows to initializate the quadrant Three.
     *
     * @param startRow is the row start.
     * @param endRow is the row end.
     * @param startColumn is the column start.
     * @param endColumn is the end column.
     * @param numQuadrant is the number of the quadrant.
     */
    private void initQuadrantThree(int startRow, int endRow, int startColumn, int endColumn, int numQuadrant) {
        startRow = quadrantSize;
        endRow = quadrantSize * 2;
        startColumn = 0;
        endColumn = quadrantSize;
        Lighting lighting = lightPointAndLighting();
        for (int i = startRow; i < endRow; i++) {
            for (int j = startColumn; j < endColumn; j++) {
                quadrant[i % quadrantSize][j] = new CircleFX(30, i, j);
                if (pentago.getColorEmpty(i % quadrantSize, j, numQuadrant)) {
                    addEmptyBall(quadrant[i % quadrantSize][j], i, j);
                } else if (pentago.getColorWhite(i % quadrantSize, j, numQuadrant)) {
                    addColorBall(quadrant[i % quadrantSize][j], i, j, lighting, Color.WHITE);
                } else {
                    addColorBall(quadrant[i % quadrantSize][j], i, j, lighting, Color.BLACK);
                }
            }
        }
    }

    /**
     * This method allows to initializate the quadrant Four.
     *
     * @param startRow is the row start.
     * @param endRow is the row end.
     * @param startColumn is the column start.
     * @param endColumn is the end column.
     * @param numQuadrant is the number of the quadrant.
     */
    private void initQuadrantFour(int startRow, int endRow, int startColumn, int endColumn, int numQuadrant) {
        startRow = quadrantSize;
        endRow = quadrantSize * 2;
        startColumn = quadrantSize;
        endColumn = quadrantSize * 2;
        Lighting lighting = lightPointAndLighting();
        for (int i = startRow; i < endRow; i++) {
            for (int j = startColumn; j < endColumn; j++) {
                quadrant[i % quadrantSize][j % quadrantSize] = new CircleFX(30, i, j);
                if (pentago.getColorEmpty(i % quadrantSize, j % quadrantSize, numQuadrant)) {
                    addEmptyBall(quadrant[i % quadrantSize][j % quadrantSize], i, j);
                } else if (pentago.getColorWhite(i % quadrantSize, j % quadrantSize, numQuadrant)) {
                    addColorBall(quadrant[i % quadrantSize][j % quadrantSize], i, j, lighting, Color.WHITE);
                } else {
                    addColorBall(quadrant[i % quadrantSize][j % quadrantSize], i, j, lighting, Color.BLACK);
                }
            }
        }
    }

    /**
     * This method allows to refresh all the quadrants to have the new one.
     *
     * @param numQuadrant is the number of the quadrant.
     */
    void refreshAllQuadrants(int numQuadrant) {
        getChildren().clear();
        int startRow = 0;
        int endRow = 0;
        int startColumn = 0;
        int endColumn = 0;
        switch (numQuadrant) {
            case 0:
                initQuadrantOne(startRow, endRow, startColumn, endColumn, numQuadrant);
                break;
            case 1:
                initQuadrantTwo(startRow, endRow, startColumn, endColumn, numQuadrant);
                break;
            case 2:
                initQuadrantThree(startRow, endRow, startColumn, endColumn, numQuadrant);
                break;
            case 3:
                initQuadrantFour(startRow, endRow, startColumn, endColumn, numQuadrant);
                break;
        }
    }

    /**
     * This method allows to add an empty Ball on the quadrant (on the GP
     * class).
     *
     * @param circle is the circleFX.
     * @param row is the row.
     * @param column is the column
     */
    private void addEmptyBall(CircleFX circle, int row, int column) {
        circle.setFill(Color.rgb(98, 98, 98));
        circle.setStroke(Color.BROWN);
        circle.addEventFilter(MouseEvent.MOUSE_RELEASED, (event) -> {
            circleClicked(event);
        });
        add(circle, column, row);
    }

    /**
     * This method allows to add the color Ball of the current player on the
     * quadrant (on the GP class).
     *
     * @param circle is the circleFX.
     * @param row is the row.
     * @param column is the column
     */
    private void addColorBall(CircleFX circle, int row, int column, Lighting lighting, Color color) {
        circle = new CircleFX(30, row, column);
        circle.setFill(color);
        circle.setStroke(Color.BROWN);
        circle.setEffect(lighting);
        add(circle, column, row);
    }

    /**
     * This method allows to put a ball on the quadrant when we click on an
     * empty ball. It will have an mouse event.
     *
     * @param event is the MouseEvent.
     */
    private void circleClicked(MouseEvent event) {
        CircleFX circleClicked = (CircleFX) event.getSource();
        if (pentago.getGameStatus() == pentago.getPlacingBallState()) {
            pentago.putBallOnBoard(circleClicked.getRow(), circleClicked.getColumn());
        }
    }

    /**
     * This method allows to put a lighting effect(on the players's balls).
     *
     * @return the lighting effect.
     */
    private Lighting lightPointAndLighting() {
        Light.Point light = new Light.Point();
        Lighting lighting = new Lighting();

        light.setColor(Color.WHITE);
        light.setX(70);
        light.setY(70);
        light.setZ(100);
        lighting.setLight(light);

        return lighting;
    }

    /**
     * This is the getter of the nQudrant.
     *
     * @return
     */
    int getnQuandrant() {
        return nQuandrant;
    }
}
