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
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import models.RaffleCup;

public class YatzyGui extends Application {
    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Yatzy Game");
        GridPane pane = new GridPane();
        initContent(pane);
        Scene scene = new Scene(pane, 400, 700);
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

    // result section textfields
    TextField txfSum = new TextField();
    TextField txfBonus = new TextField();
    TextField txfTotal = new TextField();

    TextField[] txfResultSection = {txfSum, txfBonus, txfTotal};

    // result section labels
    Label lblSum = new Label("Sum:");
    Label lblBonus = new Label("Bonus:");
    Label lblTotal = new Label("Total:");

    Label[] lblResultSection = {lblSum, lblBonus, lblTotal};

    // restart button
    Button btnRestart = new Button("Restart Game");

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

            holdBoxes[index].setPrefWidth(80);
            holdBoxes[index].setGraphicTextGap(0);
            holdBoxes[index].setWrapText(false);

            VBox dieBox = new VBox(6);
            dieBox.setAlignment(Pos.CENTER);
            dieBox.getChildren().addAll(labels[index], holdBoxes[index]);

            diceLabels.getChildren().add(dieBox);

        }


        Label antalKastLabel = new Label("Antal kast tilbage:");
        antalKastLabel.setPrefWidth(160);
        antalKastLabel.setGraphicTextGap(0);
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
            scoreLabels[index].setPrefWidth(120);
            scoreLabels[index].setGraphicTextGap(0);
            pane.add(scoreLabels[index], 2, index + 3);
            pane.add(scoreTextFields[index], 3, index + 3);
        }

        // design for result section textfields and labels
        for (TextField txf : txfResultSection) {
            txf.setPrefWidth(80);
            txf.setEditable(false);
            txf.setStyle("""
                        -fx-background-color: #f9f9f9;
                        -fx-border-color: #d3d3d3;
                        -fx-border-width: 1;
                        -fx-padding: 5;
                    """);
        }
        for (Label lbl : lblResultSection) {
            lbl.setPrefWidth(80);
            lbl.setGraphicTextGap(0);
            lbl.setStyle("""
                        -fx-font-size: 14px;
                    -fx-font-weight: bold""");
        }
// result section init

        pane.addRow(scoreLabels.length + 3, lblSum, txfSum);
        pane.addRow(scoreLabels.length + 4, lblBonus, txfBonus);
        pane.addRow(scoreLabels.length + 5, lblTotal, txfTotal);

        // restart button design and action
        btnRestart.setPrefWidth(120);
        pane.add(btnRestart, 2, scoreLabels.length + 6, 2, 1);
        btnRestart.setOnAction(event -> {
            resetGame();
            antalKastValueLabel.setText(String.valueOf(antalKast));
        });
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

    private void captureScores() {
       models.YatzyResultCalculator calc = new models.YatzyResultCalculator(raffleCup.getDice());

       // upper section scores (1...6) - only write suggestion if field is empty
        for (int index = 0; index < upSecLabels.length; index++) {
            int eyeValue = index + 1;
            int score = calc.upperSectionScore(eyeValue);
            if (upSecTextFields[index].getText() == null || upSecTextFields[index].getText().isEmpty()){
                upSecTextFields[index].setText(String.valueOf(score));
            }
        }
        // compute sumUpper from whatever is currently in the fields
        int sumUpper = 0;
        for (TextField tf : upSecTextFields) {
            String txt = tf.getText();
            if(txt != null && !txt.isEmpty()) {
                try {
                    sumUpper += Integer.parseInt(txt);
                } catch (NumberFormatException ignored) {
                }
            }
        }
    }


    private void resetGame() {
        antalKast = 3;
        for (CheckBox holdBox : holdBoxes) {
            holdBox.setSelected(false);
        }
        for (Label label : labels) {
            label.setText("Dice");
        }
    }
}

