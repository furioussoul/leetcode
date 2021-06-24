package leetcode.medium;

/**
 * 跳跃游戏 贪心
 */
public class LC55
{
    public static boolean canJump(int[] nums)
    {
        int maxIndex = nums[0];
        int jump = 0;

        while (jump <= maxIndex && jump < nums.length)
        {
            if (nums[jump] > maxIndex)
            {
                maxIndex = nums[jump];
            }

            jump++;
        }

        return jump == maxIndex + 1;
    }

    public static void main(String[] args)
    {
        int[] nums = {3, 2, 1, 0, 4};

        // false
        System.out.println(canJump(nums));
    }
}
