package com.calculator;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class CalculatorController implements Enums{

    CalculatorModel calculator;

    @FXML
    private Label screen;

    //clear pressed
    @FXML
    void onClearButtonPressed(ActionEvent event) {
        calculator.clear();
    }

    //Operation selected
    @FXML
    void onPlusButtonPressed(ActionEvent event) {
        calculator.opInput(Op.PLUS_OP);
    }

    @FXML
    void onMinusButtonPressed(ActionEvent event) {
        calculator.opInput(Op.MINUS_OP);
    }

    @FXML
    void onMultButtonPressed(ActionEvent event) {
        calculator.opInput(Op.MULT_OP);
    }

    @FXML
    void onDivideButtonPressed(ActionEvent event) {
        calculator.opInput(Op.DIVIDE_OP);
    }

    //dot pressed
    @FXML
    void onDotButtonPressed(ActionEvent event) {
        calculator.dotInput();
    }

    //number pressed
    @FXML
    void onNineButtonPressed(ActionEvent event) {
        calculator.digitInput(9);
    }

    @FXML
    void onEightButtonPressed(ActionEvent event) {
        calculator.digitInput(8);
    }

    @FXML
    void onFiveButtonPressed(ActionEvent event) {
        calculator.digitInput(5);
    }

    @FXML
    void onFourButtonPressed(ActionEvent event) {
        calculator.digitInput(4);
    }

    @FXML
    void onOneButtonPressed(ActionEvent event) {
        calculator.digitInput(1);
    }

    @FXML
    void onSevenButtonPressed(ActionEvent event) {
        calculator.digitInput(7);
    }

    @FXML
    void onSixButtonPressed(ActionEvent event) {
        calculator.digitInput(6);
    }

    @FXML
    void onThreeButtonPressed(ActionEvent event) {
        calculator.digitInput(3);
    }

    @FXML
    void onTwoButtonPressed(ActionEvent event) {
        calculator.digitInput(2);
    }

    @FXML
    void onZeroButtonPressed(ActionEvent event) {
        calculator.digitInput(0);
    }

    @FXML
    void onEqualsButtonPressed(ActionEvent event) {
        calculator.equalsInput();
    }

    @FXML
    void onSwitchButtonPressed(ActionEvent event) {
        calculator.switchSighInput();
    }

    public void initialize(){
        this.calculator = new CalculatorModel(screen);
    }
}
