package leetcode.easy;

/**
 * 给你两个二进制字符串，返回它们的和（用二进制表示）。
 * <p>
 * 输入为 非空 字符串且只包含数字 1 和 0。
 * <p>
 *  
 * <p>
 * 示例 1:
 * <p>
 * 输入: a = "11", b = "1"
 * 输出: "100"
 * 示例 2:
 * <p>
 * 输入: a = "1010", b = "1011"
 * 输出: "10101"
 *  
 * <p>
 * 提示：
 * <p>
 * 每个字符串仅由字符 '0' 或 '1' 组成。
 * 1 <= a.length, b.length <= 10^4
 * 字符串如果不是 "0" ，就都不含前导零。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/add-binary
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LC67
{
    public static String addBinary(String a, String b)
    {
        char[] charsA = a.toCharArray();
        char[] charsB = b.toCharArray();

        int i = charsA.length - 1;
        int j = charsB.length - 1;

        StringBuilder sum = new StringBuilder();

        int carry = 0;

        while (i >= 0 && j >= 0)
        {
            carry += Character.getNumericValue(charsA[i]) + Character.getNumericValue(charsB[j]);
            sum.append(carry % 2);
            carry = carry / 2;
            i--;
            j--;
        }

        while (i >= 0)
        {
            carry += Character.getNumericValue(charsA[i]);
            sum.append(carry % 2);
            carry = carry / 2;
            i--;
        }

        while (j >= 0)
        {
            carry += Character.getNumericValue(charsB[j]);
            sum.append(carry % 2);
            carry = carry / 2;
            j--;
        }

        if (carry == 1)
        {
            sum.append(1);
        }

        return sum.reverse().toString();
    }

    public static void main(String[] args)
    {
        System.out.println(addBinary("111", "1"));
    }
}
