package main.java.lc;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * 括号生成
 */
public class LC22 {

    public List<String> generateParenthesis(int n) {
        List<String> rs = new ArrayList<>();
        gen("", n, n, rs);
        return rs;
    }

    public void gen(String item, int left, int right, List<String> rs){
        if(left == 0 && right == 0){
            rs.add(item);
            return;
        }

        if(left>0){
            gen(item+"(", left-1, right, rs);
        }

        if(right > left){
            gen(item+")", left, right -1, rs);
        }
    }
}
