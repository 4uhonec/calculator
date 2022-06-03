package com.calculator;

import javafx.scene.control.Label;

public class CalculatorModel implements Enums{
    private Label screen;

    private double firstNum;
    private double secondNum;

    private boolean dotted;
    private StringBuffer inputStr = new StringBuffer();

    private State state;
    private Op operation;


    //constructor
    public CalculatorModel(Label screen) {
        this.screen = screen;
        clear();
    }

    //clear key was pressed
    public void clear(){
        screen.setText("0");
        dotted = false;
        this.state = State.firstNumInput;
        this.operation = Op.NONE_OP;
        inputStr.setLength(0);
    }

    //digit key was pressed
    public void digitInput(int digit){
        if(state == State.showResult){
            clear();
            inputStr.append(digit);
            showInputStr();
        }
        else if(state == State.opSelected){
            inputStr.setLength(0);
            state = State.secondNumInput;
            inputStr.append(digit);
            showInputStr();
        }
        else if(inputStr.length() < MAX_DIGITS && (state == State.firstNumInput || state == State.secondNumInput)){
            inputStr.append(digit);
            showInputStr();
        }
    }

    // operation key was pressed
    public void opInput(Op op){
        if(state == State.firstNumInput){
            operation = op;
            state = State.opSelected;
            firstNum = Double.parseDouble(inputStr.toString());
            inputStr.setLength(0);
            showInputStr(firstNum);
        } else if(state == State.opSelected){
            firstNum = calculate(firstNum, firstNum);
            operation = op;
            showInputStr(firstNum);
        } else if(state == State.showResult){
            operation = op;
            state = State.opSelected;
        } else if(state == State.secondNumInput){
            secondNum = Double.parseDouble(inputStr.toString());
            inputStr.setLength(0);
            firstNum = calculate(firstNum, secondNum);
            operation = op;
            showInputStr(firstNum);
        }
        dotted = false;
    }

    //equals key was pressed
    public void equalsInput(){
        if(state == State.secondNumInput){
            secondNum = Double.parseDouble(inputStr.toString());
            firstNum = calculate(firstNum, secondNum);
            showInputStr(firstNum);
        }else if(state == State.opSelected){
            firstNum = calculate(firstNum, firstNum);
            showInputStr(firstNum);
        }else if(state == State.firstNumInput){
            firstNum = Double.parseDouble(inputStr.toString());
        }
        inputStr.setLength(0);
        state = State.showResult;
        dotted = false;
    }

    //dot key was pressed
    public void dotInput(){
        if(!dotted &&  inputStr.length() < MAX_DIGITS){
            if(state == State.opSelected){
                state = State.secondNumInput;
            }
            if(inputStr.length() == 0) inputStr.append("0.");//if input of number starts with dot, without zero
            else inputStr.append(".");
            dotted = true;
            showInputStr();
        }
    }

    //switch +/- key was pressed
    public void switchSighInput(){
        equalsInput();
        firstNum = - firstNum;
        showInputStr(firstNum);
        dotted = false;
    }

    //calculating
    private double calculate(double first, double second){
        double answer;
        switch (operation){
            case PLUS_OP : 
                answer = first + second;
                break;
            case MINUS_OP: 
                answer = first - second;
                break;
            case MULT_OP: 
                answer = first * second;
                break;
            case DIVIDE_OP: 
                if(second != 0.0) answer = first / second;
                else answer = 0.0;//need to throw error divide by zero, for now it returns zero
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + operation);
        }
        return answer;
    }
    
    //private method to refresh calculator's screen value, from input buffer
    private void showInputStr(){
        if(!dotted) showInputStr(Double.parseDouble(inputStr.toString()));
        else screen.setText(inputStr.toString());
    }

    //overloaded showInputStr()
    //private method to refresh calculator's screen value, from (double)variable
    private void showInputStr(double num){
        if(num % 1.0 == 0.0){//there is no fracture part, only integer, so we remove .0 from the screen
            StringBuffer integer = new StringBuffer();
            integer.append(num);
            int len = integer.length();
            integer.setLength(len - 2);//cutting .0
            screen.setText(integer.toString());
        }else
            screen.setText(Double.toString(num));
    }
}
