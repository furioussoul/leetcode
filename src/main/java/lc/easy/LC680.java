package main.java.lc.easy;

/**
 * 验证回文2
 */
public class LC680 {

    public boolean validPalindrome(String s) {
        if (s.length() < 2) {
            return true;
        }

        return vd(0, s);
    }

    public boolean vd(int deep, String s) {

        char[] chars = s.toCharArray();
        int l = 0, r = s.length() - 1;
        while (l < r) {
            if ((chars[l] + "").equals(chars[r] + "")) {
                l++;
                r--;
            } else {
                if (deep == 0) {
                    return vd(1,s.substring(l + 1, r + 1)) || vd(1,s.substring(l, r));
                }else{
                    return false;
                }
            }
        }
        return true;
    }
}
