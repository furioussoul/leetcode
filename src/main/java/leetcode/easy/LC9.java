package leetcode.easy;

/**
 * 回文数
 * 判断一个整数是否是回文数。回文数是指正序（从左向右）和倒序（从右向左）读都是一样的整数。
 * <p>
 * 示例 1:
 * <p>
 * 输入: 121
 * 输出: true
 * 示例 2:
 * <p>
 * 输入: -121
 * 输出: false
 * 解释: 从左向右读, 为 -121 。 从右向左读, 为 121- 。因此它不是一个回文数。
 * 示例 3:
 * <p>
 * 输入: 10
 * 输出: false
 * 解释: 从右向左读, 为 01 。因此它不是一个回文数。
 * 进阶:
 * <p>
 * 你能不将整数转为字符串来解决这个问题吗？
 * <p>
 * <p>
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/palindrome-number
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LC9
{

    //空间n，时间n
    public boolean isPalindrome(int x)
    {
        String rs = new StringBuilder(x + "").reverse().toString();
        return (x + "").equals(rs);
    }

    public static boolean isPr(int x)
    {
        String rs = x + "";
        char[] chars = rs.toCharArray();

        int start = 0, end = chars.length - 1;

        while (start < end)
        {
            if (chars[start] == chars[end])
            {
                start++;
                end--;
            }
            else
            {
                break;
            }
        }

        return start >= end;
    }

    public static void main(String[] args)
    {
        int num1 = 123321;
        int num2 = 123456;

        System.out.println(isPr(num1));
        System.out.println(isPr(num2));
    }
}
