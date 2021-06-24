package leetcode.medium;

import leetcode.TreeNode;

/**
 * 验证二叉搜索树
 */
public class LC98
{
    public static boolean isValidBST(TreeNode root) {

        if(root == null) return true;

        return help(root.left, Long.MIN_VALUE, root.val) && help(root.right, root.val, Long.MAX_VALUE);
    }

    private static boolean help(TreeNode node, long lower, long upper)
    {
        if(node == null) return true;

        System.out.println(node.val + "," + lower + "," + upper);

        return node.val > lower && node.val < upper && help(node.left, lower, node.val) && help(node.right, node.val, upper);
    }
    public static void main(String[] args)
    {
        final TreeNode root = new TreeNode(-2147483648);
        root.left = null;
        root.right= new  TreeNode(2147483647);

        System.out.println(isValidBST(root));
    }
}
