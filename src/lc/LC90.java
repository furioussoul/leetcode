package lc;

import java.util.*;

public class LC90 {
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums);

        List<List<Integer>> rs = new ArrayList<>();
        Set<List<Integer>> set = new HashSet<>();

        if(nums.length == 0){
            return rs;
        }

        Stack<Integer> stack = new Stack();
        gen(0, nums, stack, set);

        rs.addAll(set);
        return rs;
    }

    public void gen(int i , int[] nums, Stack<Integer> stack, Set<List<Integer>> rs){

        if(i == nums.length){
            return;
        }

        stack.push(nums[i]);
        List<Integer> l = new ArrayList();
        l.addAll(stack);
        rs.add(l);
        System.out.println(stack.toString());


        gen(i+1, nums, stack, rs);

        stack.pop();

        gen(i+1, nums, stack, rs);
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1,2,2};
        new LC90().subsetsWithDup(nums);
    }
}
