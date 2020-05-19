package lc;

import java.util.*;

/**
 * 四数之和
 */
public class LC18 {

    public List<List<Integer>> fourSum(int[] nums, int target) {
        if(nums.length < 4 ){
            return new ArrayList();
        }
        Set<List<Integer>> rs = new HashSet<>(8);
        Arrays.sort(nums);

        for(int i = 0; i< nums.length;i++){
            if(i > 1  && nums[i] == nums[i-1] ){
                continue;
            }
            List<List<Integer>> ls =  threeSum(i+1, target - nums[i], nums);
            if(ls.size()>0){
                for(List<Integer> l : ls){
                    l.add(nums[i]);
                    rs.add(l);
                }
            }
        }

        List<List<Integer>> rls = new ArrayList<>();
        rls.addAll(rs);
        return rls;
    }

    public List<List<Integer>> threeSum(int j, int target, int[] nums) {

        List<List<Integer>> rs = new ArrayList<>(8);
        for(int i = j; i< nums.length;i++){

            if(i-1 >=j && nums[i] == nums[i-1] ){
                continue;
            }
            int l = i+1;
            int r = nums.length - 1;
            while(l<r){
                int v = nums[i] + nums[l] + nums[r];
                if(v == target){
                    List<Integer> ls = new ArrayList<>(4);
                    ls.add(nums[i]);
                    ls.add(nums[l]);
                    ls.add(nums[r]);
                    rs.add(ls);
                    while(l<r && nums[l] == nums[l+1]) l++;
                    l++;
                }else if(v < target){
                    l++;
                }else {
                    r--;
                }
            }
        }

        return rs;
    }
}
