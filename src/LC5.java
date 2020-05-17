import java.util.Arrays;

/**
 * 最长回文子串
 *
 * 给定一个字符串 s，找到 s 中最长的回文子串。你可以假设 s 的最大长度为 1000。
 *
 * 示例 1：
 *
 * 输入: "babad"
 * 输出: "bab"
 * 注意: "aba" 也是一个有效答案。
 * 示例 2：
 *
 * 输入: "cbbd"
 * 输出: "bb"
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/longest-palindromic-substring
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LC5 {

    //时间n^2,空间n^2
    public String longestPalindrome(String s) {
        if (s.length() <= 1) {
            return s;
        }
        char[] chars = s.toCharArray();
        int len = chars.length;

        int bi = 0, ei = 0;
        boolean[][] dp = new boolean[len][len];

        for(int r = 0; r< len; r++){
            dp[r][r] = true;
            for(int l = 0; l < r; l++){
                dp[l][r] = chars[l] == chars[r] && (dp[l+1][r-1] || r - l < 2 );
                if(dp[l][r] && r - l > ei - bi){
                    bi = l;
                    ei = r;

                }
            }
        }

        return s.substring(bi, ei+1);
    }
}
