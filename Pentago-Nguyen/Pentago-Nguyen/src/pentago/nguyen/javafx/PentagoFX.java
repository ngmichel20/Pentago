package pentago.nguyen.javafx;

import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.geometry.*;
import javafx.scene.effect.Glow;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import pentago.nguyen.model.Pentago;
import pentago.nguyen.util.*;

/**
 * This is the class PentagoFX.
 *
 * @author Michel
 */
class PentagoFX extends HBox implements Observer {

    private HBox hbox;
    private VBox vbox;
    private Pentago pentago;

    private GridPane currentPlayerGP;
    private HBox directionHBox;
    private BoardFX boardFX;

    private String namePlayer1;
    private String namePlayer2;
    private Label lblCurrentPlayerName;
    private Label lblQudrantChoice;
    private GridPane quadrantChoiceGP;

    private Button leftButton;
    private Button rightButton;

    /**
     * This is the constructor of PentagoFX.
     *
     * @param namePlayer1 is the player's 1 name.
     * @param namePlayer2 is the player's 2 name.
     */
    PentagoFX(String namePlayer1, String namePlayer2) {
        this.namePlayer1 = namePlayer1;
        this.namePlayer2 = namePlayer2;
        mainHBox();
    }

    /**
     * This method is the mainHBox method.
     */
    private void mainHBox() {
        this.pentago = new Pentago();
        this.currentPlayerGP = new GridPane();
        this.leftButton = new Button("LEFT");
        this.rightButton = new Button("RIGHT");
        this.directionHBox = new HBox(leftButton, rightButton);
        this.quadrantChoiceGP = new GridPane();

        this.boardFX = new BoardFX(pentago);
        this.vbox = new VBox(currentPlayerGP, directionHBox, quadrantChoiceGP);
        this.pentago.addObserver(this);
        createHBox();
    }

    /**
     * This is the getter of the hbox.
     *
     * @return the hbox.
     */
    HBox getHbox() {
        return this.hbox;
    }

    /**
     * THis method allows to create the HBox and all the layout needed.
     */
    private void createHBox() {
        hbox = new HBox(boardFX, vbox);
        createPlayersNames();
        hBoxGpAlignementBackground();
        lblCurrentPlayer();
        lblNameCurrentPlayer();
        directionPlacementInGP();
        quadrantChoiseGP();
        lblQuadrantChoice();
    }

    /**
     * This method allows to create the players's name.
     */
    private void createPlayersNames() {
        pentago.createPlayer(namePlayer1, namePlayer2);
    }

    /**
     * This method allows to do the alignement of the HBox, the gap of the board
     * (GridPane) and also the creation of background of the HBox.
     */
    private void hBoxGpAlignementBackground() {
        BackgroundFill background_fill = new BackgroundFill(Color.rgb(128, 128, 128),
                CornerRadii.EMPTY, Insets.EMPTY);
        Background background = new Background(background_fill);

        hbox.setSpacing(10);
        hbox.setPadding(new Insets(150));
        hbox.setAlignment(Pos.CENTER);
        hbox.setBackground(background);
    }

    /**
     * This mehtod allows to display the label of the current player.
     */
    private void lblCurrentPlayer() {
        Label label = new Label("Current Player : ");
        label.setFont(Font.font("Verdana", 20));
        currentPlayerGP.add(label, 0, 0);
        currentPlayerGP.setAlignment(Pos.CENTER);
        currentPlayerGP.setPrefWidth(300);
    }

    /**
     * This method allows to create the lable of the current player's name in
     * taaaaidPane.
     */
    private void lblNameCurrentPlayer() {
        lblCurrentPlayerName = new Label(pentago.getCurrentPlayerName());
        lblCurrentPlayerName.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
        currentPlayerGP.add(lblCurrentPlayerName, 1, 0);
    }

    /**
     * This method allows to refresh the text of the current player's name.
     */
    private void refreshCurrentPlayerName() {
        lblCurrentPlayerName.setText(pentago.getCurrentPlayerName());
    }
    
    private void refreshQuadrantSelection(){
        lblQudrantChoice.setText(Integer.toString(boardFX.getNumQuadrantSelected()+1));
    }

    /**
     * This method allows to know the direcion placement of the GP in the VBox.
     */
    private void directionPlacementInGP() {
        leftButton.setPrefSize(50, 50);
        rightButton.setPrefSize(70, 50);
        vbox.setSpacing(50);
        vbox.setPadding(new Insets(50));
        directionHBox.setAlignment(Pos.CENTER);
        directionHBox.setSpacing(20);
    }

    /**
     * This method allwos to know which quadrant we had selected.
     */
    private void quadrantSelected() {
        Glow glow = new Glow();
        leftButtonClicked(glow);
        rightButtonClicked(glow);
    }

    /**
     * This method allows to click on a right button.
     *
     * @param glow is the glow effect on the rightButton.
     */
    private void rightButtonClicked(Glow glow) {
        rightButton.addEventFilter(MouseEvent.MOUSE_ENTERED, (event) -> {
            rightButton.setEffect(glow);
        });
        rightButton.addEventFilter(MouseEvent.MOUSE_EXITED, (event) -> {
            rightButton.setEffect(null);
        });
        rightButton.setOnMouseClicked((MouseEvent event) -> {
            if (boardFX.isIsQuadrantSelected()) {
                int quadrantSelected = boardFX.getNumQuadrantSelected();
                pentago.askQuadrantToRotate(quadrantSelected + 1, pentago.quadrantDirection("right"));
            }
        });
    }

    /**
     * This method allows to click on the leftButton.
     *
     * @param glow is the glow effecton the button.
     */
    private void leftButtonClicked(Glow glow) {
        leftButton.addEventFilter(MouseEvent.MOUSE_ENTERED, (event) -> {
            leftButton.setEffect(glow);
        });
        leftButton.addEventFilter(MouseEvent.MOUSE_EXITED, (event) -> {
            leftButton.setEffect(null);
        });
        leftButton.setOnMouseClicked((MouseEvent event) -> {
            if (boardFX.isIsQuadrantSelected()) {
                int quadrantSelected = boardFX.getNumQuadrantSelected();
                pentago.askQuadrantToRotate(quadrantSelected + 1, pentago.quadrantDirection("left"));
            }
        });
    }
    
    private void quadrantChoiseGP(){
        Label label = new Label("Quadrant choice : ");
        label.setFont(Font.font("Verdana", 20));
        quadrantChoiceGP.setAlignment(Pos.CENTER);
        quadrantChoiceGP.add(label, 0, 0);
        quadrantChoiceGP.setPrefWidth(300);
    }
    
    private void lblQuadrantChoice(){
        lblQudrantChoice = new Label(Integer.toString(boardFX.getNumQuadrantSelected()));
        lblQudrantChoice.setFont(Font.font("Verdana", 20));
        quadrantChoiceGP.add(lblQudrantChoice, 1, 0);
    }
    
    @Override
    public void update() {
        refreshCurrentPlayerName();
        refreshQuadrantSelection();
        if (pentago.getGameStatus() == pentago.getRotatingState()) {
            leftButton.setDisable(false);
            rightButton.setDisable(false);
            quadrantSelected();
        } else {
            leftButton.setDisable(true);
            rightButton.setDisable(true);
        }
    }
}
