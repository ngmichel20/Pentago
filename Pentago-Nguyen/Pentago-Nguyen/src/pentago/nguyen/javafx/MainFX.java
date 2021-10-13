package pentago.nguyen.javafx;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

/**
 * This is the class MainFX.
 *
 * @author Michel
 */
public class MainFX extends Application {

    private VBox vbox;
    private TextField namePlayer1;
    private TextField namePlayer2;
    private Button start;
    private Alert alert;
    private GridPane mainMenu;

    /**
     * This is the main method.
     *
     * @param args null.
     */
    public static void main(String args[]) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        this.vbox = new VBox();
        Scene scene = new Scene(vbox, 1100, 700);

        backgroundVBox();
        mainMenuStart();
        startClicked(scene);

        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    /**
     * This method allows to put a background on the VBox.
     */
    private void backgroundVBox() {
        BackgroundFill background_fill = new BackgroundFill(Color.rgb(128, 128, 128),
                CornerRadii.EMPTY, Insets.EMPTY);
        Background background = new Background(background_fill);
        vbox.setSpacing(10);
        vbox.setPadding(new Insets(150));
        vbox.setAlignment(Pos.CENTER);
        vbox.setBackground(background);
    }

    /**
     * This method is the main Menu start.
     */
    private void mainMenuStart() {
        mainMenu = new GridPane();
        mainMenu.setHgap(10);
        mainMenu.setVgap(10);
        mainMenu.setAlignment(Pos.CENTER);

        namePlayer1Layout();
        namePlayer2Layout();
        startButton();

        vbox.getChildren().addAll(welcomeToPentago(), mainMenu);
    }

    /**
     * This method allows to the button start to be clicked and it will start
     * the game.
     *
     * @param scene is the Scene.
     */
    private void startClicked(Scene scene) {
        start.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (namePlayer1.getText().isEmpty() || namePlayer2.getText().isEmpty()) {
                    alertMissPlayersNames();
                } else if (namePlayer1.getText().equals(namePlayer2.getText())) {
                    alertSameNamePlayers();
                } else {
                    PentagoFX pentagoFx = new PentagoFX(namePlayer1.getText(), namePlayer2.getText());
                    HBox hbox = pentagoFx.getHbox();
                    scene.setRoot(hbox);
                }
            }
        });
    }

    /**
     * This method allows to show the welcome label of Pentago.
     *
     * @return the label.
     */
    private Label welcomeToPentago() {
        Label lblWelcomeToPentago = new Label("Welcome to Pentago !");
        lblWelcomeToPentago.setFont(Font.font(50));
        mainMenu.add(lblWelcomeToPentago, 0, 0);
        lblWelcomeToPentago.setPrefHeight(300);

        return lblWelcomeToPentago;
    }

    /**
     * This method allows to show the name of the player 1 with all the layouts.
     */
    private void namePlayer1Layout() {
        Label lblPlayer1Name = new Label("Player 1's name (White Player) : ");
        lblPlayer1Name.setFont((Font.font(20)));
        GridPane.setHalignment(lblPlayer1Name, HPos.RIGHT);
        mainMenu.add(lblPlayer1Name, 0, 1);

        namePlayer1 = new TextField();
        namePlayer1.setPromptText("Enter the player one's name here");
        namePlayer1.setFont(Font.font(20));
        namePlayer1.setPrefColumnCount(17);
        mainMenu.add(namePlayer1, 1, 1);
    }

    /**
     * This method allows to show the name of the player 2 with all the layouts.
     */
    private void namePlayer2Layout() {
        Label lblPlayer2Name = new Label("Player 2's name (Black Player)  : ");
        lblPlayer2Name.setFont(Font.font(20));
        GridPane.setHalignment(lblPlayer2Name, HPos.RIGHT);
        mainMenu.add(lblPlayer2Name, 0, 2);

        namePlayer2 = new TextField();
        namePlayer2.setPromptText("Enter the player two's name here");
        namePlayer2.setFont(Font.font(20));
        namePlayer2.setPrefColumnCount(17);
        mainMenu.add(namePlayer2, 1, 2);
    }

    /**
     * This is the start button and his layouts.
     */
    private void startButton() {
        start = new Button("Start the game !");
        start.setFont(Font.font(20));
        GridPane.setHalignment(start, HPos.RIGHT);
        mainMenu.add(start, 1, 3);
    }

    /**
     * This is the alert when there is a miss of a player's name.
     */
    private void alertMissPlayersNames() {
        alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error alert");
        alert.setHeaderText("Cannot start the game");
        alert.setContentText("Please enter the players's names !");
        alert.showAndWait();
    }

    /**
     * This is the alert when there is a same player's name.
     */
    private void alertSameNamePlayers() {
        alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error alert");
        alert.setHeaderText("Cannot start the game");
        alert.setContentText("Please enter differents names for the players !");
        alert.showAndWait();
    }
}
