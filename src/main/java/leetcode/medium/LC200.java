package leetcode.medium;

/**
 * 岛屿数量
 */
public class LC200
{
    static int[] xDirection = {-1, 0, 1, 0};
    static int[] yDirection = {0, 1, 0, -1};

    static int count = 0;

    public static int numIslands(char[][] grid)
    {
        int[][] mark = new int[grid.length][grid.length];

        for (int i = 0; i < grid.length; i++)
        {
            mark[i] = new int[grid[i].length];
        }

        for (int i = 0; i < grid.length; i++)
        {
            for (int j = 0; j < grid[i].length; j++)
            {
                if (grid[i][j] == '1' && mark[i][j] == 0)
                {
                    System.out.println("visit");
                    visit(grid, i, j, mark);

                    count++;
                }
            }
        }

        System.out.println(count);

        return count;
    }

    private static void visit(char[][] grid, int x, int y, int[][] mark)
    {
        if (x < 0 || x >= grid.length || y < 0 || y >= grid[0].length || grid[x][y] == '0' || mark[x][y] == 1)
        {
            return;
        }

        mark[x][y] = 1;

        System.out.println(x + ", " + y);

        for (int i = 0; i < xDirection.length; i++)
        {
            int newX = x + xDirection[i];
            int newY = y + yDirection[i];

            visit(grid, newX, newY, mark);
        }
    }

    public static int numIslands2(char[][] grid)
    {
        return 1;
    }


    static class Solution
    {
        static class UnionFind
        {
            int count;
            int[] parent;
            int[] rank;

            public UnionFind(char[][] grid)
            {

            }

            public int find(int i)
            {
                return 1;
            }

            public void union(int x, int y)
            {
                return;
            }

            public int getCount()
            {
                return 1;
            }
        }
    }


    public static void main(String[] args)
    {
        char[][] grid = {
                {'1', '1', '0', '0', '0'},
                {'1', '1', '0', '0', '0'},
                {'0', '0', '1', '0', '0'},
                {'0', '0', '0', '1', '1'}
        };

        System.out.println(numIslands2(grid));
    }
}
