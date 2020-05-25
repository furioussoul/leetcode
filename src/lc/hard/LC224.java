package lc.hard;

import java.util.Stack;

public class LC224 {

    static int beginState = 0;
    static int numberState = 1;
    static int operationState = 2;

    int state = beginState;
    int computeFlag = 0;
    int number = 0;



    public boolean isOperator(char c){
        return c == '-' || c== '+';
    }

    public boolean isNumber(char c){
        return c >= '0' && c <= '9';
    }
}
