package leetcode.medium;

import java.util.LinkedList;
import java.util.List;

/**
 * 39. 组合总和 递归回溯,每个idx有选和不选两种情况
 */
public class LC39
{
    public List<List<Integer>> combinationSum(int[] candidates, int target)
    {
        List<List<Integer>> ret = new LinkedList<>();
        List<Integer> combine = new LinkedList<>();

        dfs(candidates, ret, combine, target, 0);

        return ret;
    }

    public void dfs(int[] candidates, List<List<Integer>> ret, List<Integer> combine, int target, int idx)
    {
        if (idx >= candidates.length)
        {
            return;
        }

        if (target == 0)
        {
            ret.add(new LinkedList<>(combine));
            return;
        }


        if (target - candidates[idx] >= 0)
        {
            //选idx
            combine.add(candidates[idx]);
            dfs(candidates, ret, combine, target - candidates[idx], idx);
            //回溯
            combine.remove(combine.size() - 1);
        }


        //不选idx
        dfs(candidates, ret, combine, target, idx + 1);
    }
}
