package leetcode.medium;

import java.util.Arrays;

/**
 * 给你一个整数数组 nums，请你将该数组升序排列。
 */
public class LC912
{
    public static int[] bubbleSort(int[] nums)
    {
        boolean flag = false;

        for (int i = 0; i < nums.length - 1; i++)
        {
            for (int j = i + 1; j < nums.length; j++)
            {
                if (nums[i] > nums[j])
                {
                    int t = nums[j];
                    nums[j] = nums[i];
                    nums[i] = t;
                }
            }
        }

        return nums;
    }

    public static int[] sortArray(int[] nums)
    {
        Arrays.sort(nums);
        return nums;
    }

    public static void main(String[] args)
    {
        int[] nums = new int[]{0, 9, 5, 4, 1, 3, 5, 6, 7, 8, 0, 9, 1, 3, 5, 4};
        System.out.println(Arrays.toString(bubbleSort(nums)));
    }
}
