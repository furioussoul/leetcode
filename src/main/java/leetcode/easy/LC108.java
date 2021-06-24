package leetcode.easy;

import leetcode.TreeNode;

public class LC108
{
    public static TreeNode sortedArrayToBST(int[] nums)
    {
        TreeNode root = new TreeNode();
        if (nums.length == 1)
        {
            root.val = nums[0];
            return root;
        }

        return toTree(nums, 0, nums.length - 1);
    }

    public static TreeNode toTree(int[] nums, int i, int j)
    {
        if (i > j)
        {
            return null;
        }

        int mid = (j + i + 1) / 2;
        TreeNode root = new TreeNode(nums[mid]);

        root.left = toTree(nums, i, mid - 1);
        root.right = toTree(nums, mid + 1, j);

        return root;
    }

    public static void main(String[] args)
    {
        TreeNode treeNode = sortedArrayToBST(new int[]{-10, -3, 0, 5, 9});
        //
        treeNode.getClass();
    }
}
