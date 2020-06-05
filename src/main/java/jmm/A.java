package jmm;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

public class A {
    public int divide(int dividend, int divisor) {
        int i = 0;

        int flag = 0;
        if(divisor <= 0 && dividend <=0 || divisor > 0 && dividend > 0 ){
            flag = 1;
        }else{
            flag = -1;
        }
        long d = Math.abs((long)dividend);
        divisor = Math.abs(divisor);
        System.out.println(String.format("%d, %d", d, divisor));
        while(d - divisor >=0){
            i++;
            d = d- divisor;
        }

        return i* flag;
    }
    public static void main(String[] args) {
        System.out.println(new A().divide(-2147483648, -1));
    }
}
