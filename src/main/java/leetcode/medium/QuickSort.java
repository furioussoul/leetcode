package leetcode.medium;

import java.util.Random;

public class QuickSort {

    Random rd = new Random();

    public static void main(String[] args) {
        int[] origin = {5,1,1,2,0,0};
        final QuickSort quickSort = new QuickSort();
        quickSort.sortArray(origin);
        for (int x : origin) {
            System.out.print(x + " ");
        }
    }

    public int[] sortArray(int[] nums) {
        bubbleSort(nums);
        return nums;
    }



    public void bubbleSort(int[] nums){
        for(int i =0;i<nums.length-1;i++){
            for(int j = 1;j<nums.length-i;j++){
                if(nums[j]<nums[j-1]) swap(nums, j-1, j);
            }
        }
    }

    public int partition(int[] nums, int l, int r){
        if(l==r){
            return 0;
        }
        int pivot = l+rd.nextInt(r-l+1);
        swap(nums, pivot, r);
        int i = l-1;
        for(int j=l; j<r;j++){
            if(nums[j]<=nums[r]){
                i++;
                swap(nums, i,j);
            }
        }

        swap(nums, i+1, r);
        return i+1;
    }

    public void quickSort(int[] nums, int l, int r){
        if(l<r){

            int pivot = partition(nums, l, r);
            // System.out.println("pivot : " + pivot);
            // for(int n : nums){
            //     System.out.print(n+ ", ");
            // }
            // System.out.println();
            quickSort(nums, l, pivot-1);
            quickSort(nums, pivot+1, r);
        }
    }

    public void swap(int[] nums, int i, int j){
        if(i!=j){
            nums[i]=nums[i] ^ nums[j];
            nums[j]=nums[i] ^ nums[j];
            nums[i]=nums[i] ^ nums[j];
        }
    }
}
