package leetcode.easy;

import java.util.HashMap;
import java.util.Map;

/**
 * 假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
 * <p>
 * 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
 * <p>
 * 注意：给定 n 是一个正整数。
 * <p>
 * 示例 1：
 * <p>
 * 输入： 2
 * 输出： 2
 * 解释： 有两种方法可以爬到楼顶。
 * 1.  1 阶 + 1 阶
 * 2.  2 阶
 * 示例 2：
 * <p>
 * 输入： 3
 * 输出： 3
 * 解释： 有三种方法可以爬到楼顶。
 * 1.  1 阶 + 1 阶 + 1 阶
 * 2.  1 阶 + 2 阶
 * 3.  2 阶 + 1 阶
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/climbing-stairs
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LC70
{
    Map<Integer, Integer> map = new HashMap<>();

    public int climbStairs(int n)
    {
        if (n == 0 || n == 1)
        {
            return n;
        }
        if (n == 2)
        {
            return 2;
        }

        if (map.containsKey(n))
        {
            return map.get(n);
        }

        int x = climbStairs(n - 2) + climbStairs(n - 1);

        map.putIfAbsent(n, x);

        return x;
    }

    // 自底向上 滚动数组   0 0 1 -> 0 1 1 ->
    public static int climbStairsWithRollingArray(int n)
    {
        int p, q = 0, r = 1;
        for (int i = 1; i <= n; i++)
        {
            p = q;
            q = r;
            r = p+q;
        }

        return r;
    }

    public static void main(String[] args)
    {
        System.out.println(climbStairsWithRollingArray(6));
    }
}
