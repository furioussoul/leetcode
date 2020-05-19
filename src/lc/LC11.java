package lc;

/**
 * 盛最多水的容器
 */
public class LC11 {
    //n
    public int maxArea(int[] height) {
        if(height.length == 1){
            return 0;
        }
        if(height.length == 2){
            return Math.min(height[0], height[1]);
        }
        int r = height.length - 1;
        int l = 0;
        int a = 0;

        while(l < r){
            int v= Math.min(height[l], height[r]) * (r-l);
            if(a < v){
                a = v;
            }
            if(height[l] < height[r]){
                l++;
            }else{
                r--;
            }

        }
        return a;
    }
}
