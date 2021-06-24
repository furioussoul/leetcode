package leetcode.medium;


import leetcode.TreeNode;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class LC113
{
    static Deque<Integer> path = new LinkedList<>();
    static List<List<Integer>> ret = new ArrayList<>();

    public static List<List<Integer>> pathSum(TreeNode root, int targetSum)
    {
        dfs(root, targetSum);

        return ret;
    }

    private static void dfs(TreeNode node, int targetSum)
    {
        if (node == null)
        {
            return;
        }

        path.offerLast(node.val);

        targetSum -= node.val;

        if (node.left == null && node.right == null && targetSum == 0)
        {
            ret.add(new LinkedList<>(path));
        }

        dfs(node.left, targetSum);
        dfs(node.right, targetSum);

        path.pollLast();
    }

    public static void main(String[] args)
    {

    }
}
