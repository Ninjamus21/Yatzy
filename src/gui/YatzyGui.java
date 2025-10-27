package gui;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import models.Die;
import models.RaffleCup;

import javax.swing.text.LabelView;

public class YatzyGui extends Application {
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Yatzy Game");
        GridPane pane = new GridPane();
        initContent(pane);
        Scene scene = new Scene(pane, 400, 300);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private int antalKast = 3;

    RaffleCup raffleCup = new RaffleCup();

    Label diceLabel1 = new Label("Dice: 1");
    Label diceLabel2 = new Label("Dice: 2");
    Label diceLabel3 = new Label("Dice: 3");
    Label diceLabel4 = new Label("Dice: 4");
    Label diceLabel5 = new Label("Dice: 5");
    Label[] labels = {diceLabel1, diceLabel2, diceLabel3, diceLabel4, diceLabel5};

    CheckBox holdBox1 = new CheckBox("Hold");
    CheckBox holdBox2 = new CheckBox("Hold");
    CheckBox holdBox3 = new CheckBox("Hold");
    CheckBox holdBox4 = new CheckBox("Hold");
    CheckBox holdBox5 = new CheckBox("Hold");
    CheckBox[] holdBoxes = {holdBox1, holdBox2, holdBox3, holdBox4, holdBox5};

    Label lblUpSec1ere = new Label("1'ere:");
    Label lblUpSec2ere = new Label("2'ere:");
    Label lblUpSec3ere = new Label("3'ere:");
    Label lblUpSec4ere = new Label("4'ere:");
    Label lblUpSec5ere = new Label("5'ere:");
    Label lblUpSec6ere = new Label("6'ere:");
    Label[] upSecLabels = {lblUpSec1ere, lblUpSec2ere, lblUpSec3ere, lblUpSec4ere, lblUpSec5ere, lblUpSec6ere};

    TextField txtUpSec1ere = new TextField();
    TextField txtUpSec2ere = new TextField();
    TextField txtUpSec3ere = new TextField();
    TextField txtUpSec4ere = new TextField();
    TextField txtUpSec5ere = new TextField();
    TextField txtUpSec6ere = new TextField();
    TextField[] upSecTextFields = {txtUpSec1ere, txtUpSec2ere, txtUpSec3ere, txtUpSec4ere, txtUpSec5ere, txtUpSec6ere};

    Label lblScoreOnePair = new Label("Et par:");
    Label lblScoreTwoPair = new Label("To par:");
    Label lblScoreThreeSame = new Label("Tre ens:");
    Label lblScoreFourSame = new Label("Fire ens:");
    Label lblScoreSmallStraight = new Label("Lille straight:");
    Label lblScoreLargeStraight = new Label("Stor straight:");
    Label lblScoreFullHouse = new Label("Fuldt hus:");
    Label lblScoreChance = new Label("Chance:");
    Label lblScoreYatzy = new Label("Yatzy:");
    Label[] scoreLabels = {lblScoreOnePair, lblScoreTwoPair, lblScoreThreeSame, lblScoreFourSame,
            lblScoreSmallStraight, lblScoreLargeStraight, lblScoreFullHouse, lblScoreChance, lblScoreYatzy};

    TextField txtScoreOnePair = new TextField();
    TextField txtScoreTwoPair = new TextField();
    TextField txtScoreThreeSame = new TextField();
    TextField txtScoreFourSame = new TextField();
    TextField txtScoreSmallStraight = new TextField();
    TextField txtScoreLargeStraight = new TextField();
    TextField txtScoreFullHouse = new TextField();
    TextField txtScoreChance = new TextField();
    TextField txtScoreYatzy = new TextField();
    TextField[] scoreTextFields = {txtScoreOnePair, txtScoreTwoPair, txtScoreThreeSame, txtScoreFourSame,
            txtScoreSmallStraight, txtScoreLargeStraight, txtScoreFullHouse, txtScoreChance, txtScoreYatzy};

    private void initContent(GridPane pane) {
        pane.setGridLinesVisible(false);
        pane.setPadding(new Insets(20));
        pane.setHgap(10);
        pane.setVgap(10);

        HBox diceLabels = new HBox(15); // 15px spacing between dice
        diceLabels.setAlignment(Pos.CENTER); // center horizontally
        pane.add(diceLabels, 0, 0, 5, 1); // add HBox to GridPane

        for (int index = 0; index < labels.length; index++) {
            labels[index].setPrefSize(60, 60);
            labels[index].setAlignment(Pos.CENTER);
            labels[index].setStyle("""
        -fx-border-radius: 5;
        -fx-border-color: black;
        -fx-border-width: 2;
        -fx-background-radius: 5;
        -fx-background-color: linear-gradient(to bottom, white, #f0f0f0);
        -fx-font-size: 18px;
        -fx-font-weight: bold;
        -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.3), 5, 0, 2, 2);
    """);
            diceLabels.getChildren().add(labels[index]);
        }


        for (int index = 0; index < holdBoxes.length; index++) {
            pane.add(holdBoxes[index], index, 1);
        }

        Label antalKastLabel = new Label("Antal kast tilbage:");
        pane.add(antalKastLabel, 0, 2);

        Label antalKastValueLabel = new Label("3");
        pane.add(antalKastValueLabel, 1, 2);

        Button throwDiceButton = new Button("Throw Dice");
        pane.add(throwDiceButton, 2, 2, 2, 1);

        throwDiceButton.setOnAction(event -> {
            if (antalKast > 0) {
                updateCheckBoxes();
                updateDiceLabels();
                updateAntalKastLabel();
                antalKastValueLabel.setText(String.valueOf(antalKast));
            }
        });

        // Adding upper section labels
        for (int index = 0; index < upSecLabels.length; index++) {
            pane.add(upSecLabels[index], 0, index + 3);
            pane.add(upSecTextFields[index], 1, index + 3);

        }

        for (int index = 0; index < scoreLabels.length; index++) {
            pane.add(scoreLabels[index], 2, index + 3);
            pane.add(scoreTextFields[index], 3, index + 3);
        }

    }

    private void updateDiceLabels() {
        raffleCup.throwDice();
        for (int index = 0; index < labels.length; index++) {
            labels[index].setText(raffleCup.toString(index));
        }
    }

    private void updateAntalKastLabel() {
        antalKast--;
    }

    private void updateCheckBoxes() {
        for (int index = 0; index < holdBoxes.length; index++) {
            raffleCup.getDice()[index].setisHeld(holdBoxes[index].isSelected());

        }
    }
}

