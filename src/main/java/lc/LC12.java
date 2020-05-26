package main.java.lc;

/**
 * 整数转罗马数字
 *
 *
 */
public class LC12 {

    public String intToRoman(int num) {

        String s = "";

        int m = num / 1000;
        while(m-- >0){
            s+="M";
            num -= 1000;
        }

        m = num / 900;
        if(m > 0){
            s+="CM";
            num -= 900;
        }

        m = num / 500;
        if(m > 0){
            s+="D";
            num -= 500;
        }

        m = num / 400;
        if(m > 0){
            s+="CD";
            num -= 400;
        }

        m = num / 100;
        while(m-- >0){
            s+="C";
            num -= 100;
        }

        m = num / 90;
        if(m > 0){
            s+="XC";
            num -= 90;
        }

        m = num / 50;
        if(m > 0){
            s+="L";
            num -= 50;
        }

        m = num / 40;
        if(m > 0){
            s+="XL";
            num -= 40;
        }

        m = num / 10;
        while(m-- >0){
            s+="X";
            num -= 10;
        }

        m = num / 9;
        if(m > 0){
            s+="IX";
            num -= 9;
        }

        m = num / 5;
        if(m > 0){
            s+="V";
            num -= 5;
        }

        m = num / 4;
        if(m > 0){
            s+="IV";
            num -= 4;
        }

        while(num-- > 0){
            s+="I";
        }

        return s;
    }
}
