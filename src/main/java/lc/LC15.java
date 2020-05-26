package main.java.lc;

import java.util.*;

/**
 * 三数之和
 *
 */
public class LC15 {
    public List<List<Integer>> threeSum(int[] nums) {
        if(nums.length <= 2 ){
            return new ArrayList();
        }

        Arrays.sort(nums);
        List<List<Integer>> rs = new ArrayList<>(32);
        for(int i = 0; i< nums.length;i++){
            if(i-1 >=0 && nums[i] == nums[i-1] ){
                continue;
            }
            int l = i+1;
            int r = nums.length - 1;
            while(l<r){
                int v = nums[i] + nums[l] + nums[r];
                if(v == 0){
                    List<Integer> ls = new ArrayList<>(3);
                    ls.add(nums[i]);
                    ls.add(nums[l]);
                    ls.add(nums[r]);
                    rs.add(ls);
                    while(l<r && nums[l] == nums[l+1]) l++;
                    l++;
                }else if(v < 0){
                    l++;
                }else {
                    r--;
                }
            }
        }

        List<List<Integer>> rls = new ArrayList<>(100);
        rls.addAll(rs);
        return rls;
    }
}
