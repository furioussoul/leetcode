package leetcode.easy;

import java.util.Stack;

/**
 * 有效的括号
 */
public class LC20 {

    public boolean isValid(String s) {
        if(s.length() == 0) return true;

        char[] chars = s.toCharArray();
        Stack<Character> stack = new Stack<>();
        for(char c : chars){
            if(c == '{' || c == '[' || c == '('){
                stack.push(c);
            }else{
                if(stack.isEmpty()){
                    return false;
                }
                char p = stack.peek();
                if(c == '}' && p == '{'){
                    stack.pop();
                }else if(c == ']' && p == '['){
                    stack.pop();
                }else if(c == ')' && p == '('){
                    stack.pop();
                }else{
                    return false;
                }
            }
        }

        return stack.isEmpty();
    }
}
