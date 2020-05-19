package lc.easy;

/**
 * 最长公共前缀
 *
 * 编写一个函数来查找字符串数组中的最长公共前缀。
 *
 * 如果不存在公共前缀，返回空字符串 ""。
 *
 * 示例 1:
 *
 * 输入: ["flower","flow","flight"]
 * 输出: "fl"
 * 示例 2:
 *
 * 输入: ["dog","racecar","car"]
 * 输出: ""
 * 解释: 输入不存在公共前缀。
 * 说明:
 *
 * 所有输入只包含小写字母 a-z 。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/longest-common-prefix
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LC14 {

    public String longestCommonPrefix(String[] strs) {

        if(strs.length == 0){
            return "";
        }else if(strs.length == 1){
            return strs[0];
        }

        int minLen = Integer.MAX_VALUE;
        for(String str : strs){
            if(str.length()<minLen){
                minLen = str.length();
            }
        }

        String ls = "";

        for(int c = 0; c < minLen; c++) {
            char ch = '#';
            for(int r = 0; r < strs.length; r++) {
                if(r == 0){
                    ch = strs[r].charAt(c);
                }else if(ch != strs[r].charAt(c)){
                    return ls;
                }else if(r == strs.length - 1){
                    ls += strs[r].charAt(c);
                }
            }
        }

        return ls;
    }
}
