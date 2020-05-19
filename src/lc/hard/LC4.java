package lc.hard;

/**
 * 寻找两个正序数组的中位数
 *
 * 给定两个大小为 m 和 n 的正序（从小到大）数组 nums1 和 nums2。
 *
 * 请你找出这两个正序数组的中位数，并且要求算法的时间复杂度为 O(log(m + n))。
 *
 * 你可以假设 nums1 和 nums2 不会同时为空。
 *
 *  
 *
 * 示例 1:
 *
 * nums1 = [1, 3]
 * nums2 = [2]
 *
 * 则中位数是 2.0
 * 示例 2:
 *
 * nums1 = [1, 2]
 * nums2 = [3, 4]
 *
 * 则中位数是 (2 + 3)/2 = 2.5
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/median-of-two-sorted-arrays
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class LC4 {

    //时间复杂度log(m+n)
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int k1 = (nums1.length+nums2.length+1)/2;
        int k2 = (nums1.length+nums2.length+2)/2;
        return (findKth(k1, 0,0, nums1, nums2)+findKth(k2, 0,0, nums1, nums2))*0.5;
    }

    public int findKth(int k, int bi1, int bi2, int[] nums1, int[] nums2){

        if(bi1>= nums1.length){
            return nums2[bi2+k-1];
        }else if (bi2 >= nums2.length){
            return nums1[bi1+k-1];
        }

        if(k==1){
            return Math.min(nums1[bi1], nums2[bi2]);
        }

        int v1 = bi1+k/2-1<nums1.length?nums1[bi1+k/2-1]:Integer.MAX_VALUE;
        int v2 = bi2+k/2-1<nums2.length?nums2[bi2+k/2-1]:Integer.MAX_VALUE;
        if(v1 < v2){
            return findKth(k-k/2, bi1+k/2, bi2, nums1, nums2);
        }else{
            return findKth(k-k/2, bi1, bi2+k/2, nums1, nums2);
        }
    }
}
