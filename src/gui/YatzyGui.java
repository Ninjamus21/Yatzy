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
    private int remainingScores;

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

    // throw button set to field for the handlers
    Button throwDiceButton = new Button("Throw Dice");

    // confirmation tracking
    private boolean[] confirmedUp;
    private boolean[] confirmedLower;

    private void initContent(GridPane pane) {
        pane.setGridLinesVisible(false);
        pane.setPadding(new Insets(20));
        pane.setHgap(10);
        pane.setVgap(10);

        // initialize confirmation arrays and remaining score counter... check for checked scores
        confirmedUp = new boolean[upSecTextFields.length];
        confirmedLower = new boolean[scoreTextFields.length];
        remainingScores = upSecTextFields.length + scoreTextFields.length;

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


        pane.add(throwDiceButton, 2, 2, 2, 1);

        throwDiceButton.setOnAction(event -> {
            if (antalKast > 0) {
                updateCheckBoxes();
                updateDiceLabels();
                updateAntalKastLabel();
                antalKastValueLabel.setText(String.valueOf(antalKast));
                captureScores();
                if (antalKast == 0) {
                    throwDiceButton.setDisable(true);
                }
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
            resetGame(antalKastValueLabel);
        });

        // attach confirm handlers so user can lock in scores for both upper and lower part
        attachConfirmHandlers(antalKastValueLabel);
    }

    private void attachConfirmHandlers(Label antalKastValueLabel) {
        for (int i = 0; i < upSecTextFields.length; i++) {
            final int idx = i;
            upSecTextFields[i].setEditable(true);
            upSecTextFields[i].setOnMouseClicked(e -> {
                if (remainingScores <= 0) return;
                if (confirmedUp[idx]) return;
                String txt = upSecTextFields[idx].getText();
                if (txt == null || txt.isEmpty()) return; // nothing to confirm
                // confirm this upper score
                confirmedUp[idx] = true;
                upSecTextFields[idx].setEditable(false);
                upSecTextFields[idx].setStyle("-fx-background-color: lightgreen;");
                remainingScores--;
                // clear other unconfirmed suggestions
                clearUnconfirmedSuggestions();
                // reset throws for next round and UI
                antalKast = 3;
                antalKastValueLabel.setText(String.valueOf(antalKast));
                for (CheckBox cb : holdBoxes) cb.setSelected(false);
                for (Label lbl : labels) lbl.setText("Dice");
                throwDiceButton.setDisable(false);
                // update totals (confirmed field already contains value)
                captureScores();
                if (remainingScores == 0) {
                    throwDiceButton.setDisable(true);
                }
            });
        }
        // exactly the same but for score section lock in
        for (int i = 0; i < scoreTextFields.length; i++) {
            final int idx = i;
            scoreTextFields[i].setEditable(true);
            scoreTextFields[i].setOnMouseClicked(e -> {
                if (remainingScores <= 0) return;
                if (confirmedUp[idx]) return;
                String txt = scoreTextFields[idx].getText();
                if (txt == null || txt.isEmpty()) return;
                // confirm lower score
                confirmedLower[idx] = true;
                scoreTextFields[idx].setEditable(false);
                scoreTextFields[idx].setStyle("-fx-background-color: lightgreen;");
                remainingScores--;
                // clear the rest of the unconfirmed scores
                clearUnconfirmedSuggestions();
                // reset throw for next round and ui
                antalKast = 3;
                antalKastValueLabel.setText(String.valueOf(antalKast));
                for (CheckBox cb : holdBoxes) cb.setSelected(false);
                for (Label lbl : labels) {
                    lbl.setText("Dice");
                }
                throwDiceButton.setDisable(false);
                // update the totals
                captureScores();
                if (remainingScores == 0) {
                    throwDiceButton.setDisable(true);
                }
            });

        }
    }

    private void clearUnconfirmedSuggestions() {
        for (int i = 0; i < upSecTextFields.length; i++) {
            if (!confirmedUp[i]) {
                upSecTextFields[i].setText("");
                upSecTextFields[i].setStyle(null);
            }
        }
        for (int i = 0; i < scoreTextFields.length; i++) {
            if (!confirmedLower[i]) {
                scoreTextFields[i].setText("");
                scoreTextFields[i].setStyle(null);
            }
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

    private void captureScores() {
        // Suggest scores only for unconfirmed fields, then update totals
        models.YatzyResultCalculator calc = new models.YatzyResultCalculator(raffleCup.getDice());

        // upper section scores (1...6) - only write suggestion if field is empty and not confirmed
        for (int index = 0; index < upSecLabels.length; index++) {
            int eyeValue = index + 1;
            int score = calc.upperSectionScore(eyeValue);
            if (!confirmedUp[index] && (upSecTextFields[index].getText() == null || upSecTextFields[index].getText().isEmpty())) {
                upSecTextFields[index].setText(String.valueOf(score));
            }
        }

        // lower section suggestions - ensure order matches labels and only for unconfirmed
        int[] lowerScores = new int[]{
                calc.onePairScore(),
                calc.twoPairScore(),
                calc.threeOfAKindScore(),
                calc.fourOfAKindScore(),
                calc.smallStraightScore(),
                calc.largeStraightScore(),
                calc.fullHouseScore(),
                calc.chanceScore(),
                calc.yatzyScore()
        };

        for (int index = 0; index < scoreTextFields.length; index++) {
            if (!confirmedLower[index] && (scoreTextFields[index].getText() == null || scoreTextFields[index].getText().isEmpty())) {
                scoreTextFields[index].setText(String.valueOf(lowerScores[index]));
            }
        }

        // update totals after suggestions
        updateTotals();
    }

    private void updateTotals() {
        int sumUpper = 0;
        for (int i = 0; i < upSecTextFields.length; i++) {
            String txt = upSecTextFields[i].getText();
            if (txt != null && !txt.isEmpty()) {
                try {
                    sumUpper += Integer.parseInt(txt);
                } catch (NumberFormatException ignored) { }
            }
        }

        int sumLower = 0;
        for (int i = 0; i < scoreTextFields.length; i++) {
            String txt = scoreTextFields[i].getText();
            if (txt != null && !txt.isEmpty()) {
                try {
                    sumLower += Integer.parseInt(txt);
                } catch (NumberFormatException ignored) { }
            }
        }

        int bonus = (sumUpper >= 63) ? 50 : 0;
        int total = sumUpper + bonus + sumLower;

        txfSum.setText(String.valueOf(sumUpper));
        txfBonus.setText(String.valueOf(bonus));
        txfTotal.setText(String.valueOf(total));
    }



    private void resetGame(Label antalKastValueLabel) {
        antalKast = 3;
        remainingScores = upSecTextFields.length + scoreTextFields.length;
        confirmedUp = new boolean[upSecTextFields.length];
        confirmedLower = new boolean[scoreTextFields.length];

        for (CheckBox holdBox : holdBoxes) {
            holdBox.setSelected(false);
        }
        for (Label label : labels) {
            label.setText("Dice");
        }
        // clear fields and styles
        for (TextField tf : upSecTextFields) {
            tf.setText("");
            tf.setEditable(true);
            tf.setStyle(null);
        }
        for (TextField tf : scoreTextFields) {
            tf.setText("");
            tf.setEditable(true);
            tf.setStyle(null);
        }
        txfSum.setText("");
        txfBonus.setText("");
        txfTotal.setText("");

        antalKastValueLabel.setText(String.valueOf(antalKast));
        throwDiceButton.setDisable(false);
    }
}


