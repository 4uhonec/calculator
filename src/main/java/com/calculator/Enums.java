package com.calculator;

public interface Enums {
    int MAX_DIGITS = 14;
    enum State{firstNumInput, opSelected, secondNumInput, showResult}
    enum Op{NONE_OP, PLUS_OP, MINUS_OP, MULT_OP, DIVIDE_OP}
}
