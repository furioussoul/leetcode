package leetcode.medium;

/**
 * 跳跃游戏 II 贪心
 */
public class LC45
{
    public static int jump(int[] nums)
    {
        if(nums.length == 1) return 0;

        int jumpMin = 1;
        int maxIndex = nums[0];
        int preMaxIndex = nums[0];

        for(int i = 1; i < nums.length; i++)
        {
            if (i > maxIndex)
            {
                maxIndex = preMaxIndex;
                jumpMin++;
            }

            if(nums[i] + i > preMaxIndex)
            {
                preMaxIndex = nums[i] + i;
            }
        }

        return jumpMin;
    }

    public static void main(String[] args)
    {
        int[] nums = {2,1,1,1};

        System.out.println(jump(nums));
    }
}
