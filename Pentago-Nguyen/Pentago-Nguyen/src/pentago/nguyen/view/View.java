package pentago.nguyen.view;

import java.util.Scanner;
import pentago.nguyen.model.Pentago;

/**
 * This it he view of Pentago.
 *
 * @author Michel
 */
public class View {

    private Pentago pentago;
    private Scanner scanner;

    /**
     * This is the View constructor.
     *
     * @param pentago is the model.
     */
    public View(Pentago pentago) {
        if (pentago == null) {
            throw new IllegalArgumentException("The model has not been created.");
        } else {
            this.pentago = pentago;
            this.scanner = new Scanner(System.in);
        }
    }

    /**
     * This method displays to the player that it's his turn to place the ball.
     */
    public void askCurrentPlayerPlaceBall() {
        System.out.println(pentago.getCurrentPlayerName() + " it's your turn to place your ball !");
    }

    /**
     * This method allows to askEnterNumber to the player to enter a number.
     *
     * @param msg is the message to enter the row or the column.
     * @return the row or the column.
     */
    private int askEnterNumber(String msg) {
        System.out.println(msg);
        while (!scanner.hasNextInt()) {
            System.out.println("Enter a number please :");
            scanner.next();
        }
        return scanner.nextInt() - 1;
    }

    /**
     * This method allows to ask a row.
     *
     * @return the row.
     */
    public int askRow() {
        String msgRow = "Enter the row (1-6) :";
        int row = askEnterNumber(msgRow);
        while (row < 0 || row > 5) {
            System.out.println("You have to enter a number between 1 and 6 :");
            row = askEnterNumber(msgRow);
        }
        return row;
    }

    /**
     * This method allows to ask a column.
     *
     * @return the column.
     */
    public int askColumn() {
        String msgColumn = "Enter the column (1-6) :";
        int column = askEnterNumber(msgColumn);
        while (column < 0 || column > 5) {
            System.out.println("You have to enter a number between 1 and 6 :");
            column = askEnterNumber(msgColumn);
        }
        return column;
    }

    public void displayInitBoard2() {
        int startQ = 0;
        for (int i = 0; i < 2; i++) {
            System.out.print(display2Q(startQ, ++startQ));
            startQ++;
        }
    }

    public String display2Q(int q1, int q2) {
        String concat = "";
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 6; j++) {
                if (j < 3) {
                    concat += placeEmptyBall2(i, j, q1);
                    concat += placePlayer2BallBlack2(i, j, q1);
                    concat += placePlayer1BallWhite2(i, j, q1);
                } else {
                    concat += placeEmptyBall2(i, j % 3, q2);
                    concat += placePlayer2BallBlack2(i, j % 3, q2);
                    concat += placePlayer1BallWhite2(i, j % 3, q2);
                }
            }
            concat += "\n";
        }
        return concat;
    }

    private String placeEmptyBall2(int row, int column, int k) {
        String emptyBall = "";
        if (pentago.getColorEmpty(row, column, k)) {
            emptyBall += " . ";
        }
        return emptyBall;
    }

    private String placePlayer2BallBlack2(int row, int column, int k) {
        String blackBall = "";
        if (pentago.getColorBlack(row, column, k)) {
            blackBall += " B ";
        }
        return blackBall;
    }

    private String placePlayer1BallWhite2(int row, int column, int k) {
        String concat = "";
        if (pentago.getColorWhite(row, column, k)) {
            concat += " W ";
        }
        return concat;
    }

    /**
     * This method allows to ask the name of the player.
     */
    public void askNamePlayer() {
        System.out.println("Player 1 : What's your name ?");
        String namePlayer1 = scanner.next();
        System.out.println("Player 2 : What's your name ?");
        String namePlayer2 = scanner.next();
        while (namePlayer1.equals(namePlayer2)) {
            System.out.println("Please enter a different name : ");
            namePlayer2 = scanner.next();
        }
        pentago.createPlayer(namePlayer1, namePlayer2);
    }

    /**
     * This method allows to ask to the player the quadrant he want to move.
     *
     * @return the quadrant.
     */
    private int askPlayerQuadrantToMove() {
        System.out.println("Which Quadrant do you want to move ? (1-4)");
        while (!scanner.hasNextInt()) {
            System.out.println("You have to enter a Quadrant between 1 and 4 :");
            scanner.next();
        }
        return scanner.nextInt();
    }

    /**
     * This method allows to check the quadrant if it is between 1 and 4.
     *
     * @return the quadrant.
     */
    public int checkQuadrant() {
        int numQuadrant = askPlayerQuadrantToMove();
        while (numQuadrant < 1 || numQuadrant > 4) {
            System.out.println("Please enter a Quadrant between 1 and 4 :");
            numQuadrant = askPlayerQuadrantToMove();
        }
        return numQuadrant;
    }

    /**
     * This method allows to ask to the player the direction he want to do on
     * the quadrant.
     *
     * @return the direction left or right.
     */
    public String askPlayerDirection() {
        String direction = "";
        System.out.println("Which direction do you want to apply on the Quadrant ? (left/right)");
        direction = scanner.next();
        while (!direction.equalsIgnoreCase("left") && !direction.equalsIgnoreCase("right")) {
            System.out.println("Please enter left or right :");
            direction = scanner.next();
        }
        return direction;
    }

    /**
     * This method allows to display the rotation message.
     *
     * @param quadrant is the quadrant.
     * @param direction is the direction.
     */
    public void displayRotationMessage(int quadrant, String direction) {
        System.out.println("Rotation of the Quadrant " + quadrant + " on the " + direction + " !");
    }

    /**
     * This method allows to display the winner.
     */
    public void displayWinner() {
        System.out.println("The winner is " + pentago.getCurrentPlayerName() + " !");
    }

    /**
     * This method allows to display if there is already a ball.
     */
    public void displayAlreadyBall() {
        System.out.println("There is already a Ball :( ");
        System.out.println("Enter another coordinate !");
    }
}
