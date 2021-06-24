package leetcode.medium;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 验证二叉树
 */
public class LC1361
{
    public static boolean validateBinaryTreeNodes(int n, int[] leftChild, int[] rightChild)
    {
        List<Integer> inDegs = new ArrayList<>(n);
        for (int i = 0; i < n; i++)
        {
            inDegs.add(0);
        }

        for (int i = 0; i < n; i++)
        {
            if (leftChild[i] != -1)
            {
                inDegs.set(leftChild[i], inDegs.get(leftChild[i]) + 1);
            }

            if (rightChild[i] != -1)
            {
                inDegs.set(rightChild[i], inDegs.get(rightChild[i]) + 1);
            }
        }

        System.out.println(inDegs);

        int root = 0;
        boolean hasRoot = false;
        int zeroInDegreeNodeCount = 0;

        for (int i = 0; i < inDegs.size(); i++)
        {
            int inDeg = inDegs.get(i);

            if (inDeg > 1)
            {
                System.out.println("inDeg > 1");

                return false;
            }
            else if (inDeg == 0)
            {
                zeroInDegreeNodeCount++;
                hasRoot = true;
                root = i;
            }

            if (zeroInDegreeNodeCount > 1)
            {
                System.out.println("zeroInDegreeNodeCount > 1");

                return false;
            }
        }

        if (!hasRoot)
        {
            System.out.println("has no root");

            return false;
        }

        List<Integer> seenList = new ArrayList<>(n);
        for (int i = 0; i < n; i++)
        {
            seenList.add(0);
        }

        int visitCount = 0;
        Queue<Integer> q = new LinkedList<>();
        q.add(root);

        while (!q.isEmpty())
        {
            final Integer front = q.poll();

            visitCount++;

            if (visitCount > n)
            {
                System.out.println("visitCount > n");

                return false;
            }

            seenList.set(front, seenList.get(front) + 1);

            if (leftChild[front] != -1)
            {
                q.add(leftChild[front]);
            }

            if (rightChild[front] != -1)
            {
                q.add(rightChild[front]);
            }
        }

        if (visitCount < n)
        {
            System.out.println("visitCount < n");

            return false;
        }

        return true;
    }

    public static void main(String[] args)
    {
        LC1361.validateBinaryTreeNodes(4, new int[]{1, -1, 3, -1}, new int[]{2, -1, -1, -1});
        LC1361.validateBinaryTreeNodes(4, new int[]{1, -1, 3, -1}, new int[]{2, 3, -1, -1});
        LC1361.validateBinaryTreeNodes(2, new int[]{1, 0}, new int[]{-1, -1});
        LC1361.validateBinaryTreeNodes(6, new int[]{1, -1, -1, 4, -1, -1}, new int[]{2, -1, -1, 5, -1, -1});
        LC1361.validateBinaryTreeNodes(4, new int[]{1, 0, 3, -1}, new int[]{-1, -1, -1, -1});
    }
}
