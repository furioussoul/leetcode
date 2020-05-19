package lc;

import java.util.*;

/**
 * 子集
 */
public class LC78 {

    public List<List<Integer>> subsets(int[] nums) {
        return recallgen(nums);
    }

    //比特位
    public List<List<Integer>> bitgen(int[] nums){
        List<List<Integer>> rs = new ArrayList();
        if(nums.length == 0){
            return rs;
        }

        int allset = (1 << nums.length) - 1;
        for(int i = 0;i<= allset;i++){
            Stack<Integer> sub = new Stack();
            rs.add(sub);
            for(int j = 0; j < nums.length; j++){
                if((i&(1<<j))>0){
                    sub.add(nums[j]);
                }
            }
        }
        return rs;
    }

    //回溯
    public List<List<Integer>> recallgen(int[] nums){
        List<List<Integer>> rs = new ArrayList();

        if(nums.length == 0){
            return rs;
        }

        Stack<Integer> sub = new Stack();
        generate(nums, 0, sub, rs);
        rs.add(new ArrayList());
        return rs;
    }

    public void generate(int[] nums, int i, Stack<Integer> sub, List<List<Integer>> rs){

        if(i == nums.length){
            return;
        }
        sub.add(nums[i]);
        List<Integer> l = new ArrayList();
        l.addAll(sub);
        rs.add(l);
        generate(nums, i+1, sub, rs);
        sub.pop();
        //上一层级的sub和pop掉的sub相同，所以只保存最左路径
        generate(nums, i+1, sub, rs);
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1,2,2};
        new LC78().subsets(nums);
    }
}
