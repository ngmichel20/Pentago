package pentago.nguyen.javafx;

import javafx.scene.shape.Circle;

/**
 * This is the class CircleFX who extends the Circle from javafx.
 *
 * @author Michel
 */
class CircleFX extends Circle {

    private int row;
    private int column;

    /**
     * This is the constructor of CircleFX.
     *
     * @param radius is the radius.
     * @param row is the row.
     * @param column is the column.
     */
    CircleFX(double radius, int row, int column) {
        super(radius);
        this.row = row;
        this.column = column;
    }

    /**
     * This is the row's getter.
     *
     * @return the row.
     */
    int getRow() {
        return row;
    }

    /**
     * This is the column's getter.
     *
     * @return the column.
     */
    int getColumn() {
        return column;
    }
}
