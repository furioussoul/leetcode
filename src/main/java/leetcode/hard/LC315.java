package leetcode.hard;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class LC315 {

    public static class KvPair{
        Integer k;
        Integer v;
        Integer i;
        public KvPair(Integer k, Integer v, Integer i){
            this.k = k;
            this.v = v;
            this.i = i;
        }

        @Override
        public String toString() {
            return "KvPair{" +
                    "k=" + k +
                    ", v=" + v +
                    '}';
        }
    }

    public List<Integer> countSmaller(int[] nums) {
        KvPair[] array = new KvPair[nums.length];
        for(int i = 0; i < nums.length; i++){
            array[i] = new KvPair(nums[i],0, i);
        }

        mergeSort(0, 0, nums.length - 1, array);
        List<Integer> rs = new ArrayList<>();
        rs.sort(new Comparator<Integer>(){
            public int compare(Integer o1, Integer o2){
                return o1-o2;
            }
        });
        System.out.println(Arrays.asList(array));
        return null;
    }

    public void mergeSort(int d, int bi, int ei, KvPair[] array){
        if(ei-bi == 1){
            if(array[ei].k < array[bi].k){
                array[bi].v = 1;
                KvPair tmp = array[ei];
                array[ei] = array[bi];
                array[bi] = tmp;
            }
            return;
        }
        if(ei==bi){
            return;
        }
        mergeSort(d+1, bi, (bi+ei)/2, array);
        mergeSort(d+1,(bi+ei)/2+1, ei, array);

        if(d==0){
            System.out.println();
        }
        KvPair[] rs = new KvPair[ei-bi+1];

        int i = 0;
        int l1 = bi;
        int l2 = (bi+ei)/2+1;
        while(l1<=(bi+ei)/2 && l2 <=ei){
            if(array[l1].k <= array[l2].k){
                //小的必先合并插入rs，所以右侧已合并的数量就是v
                rs[i] = array[l1++];
                rs[i].v += l2-((bi+ei)/2+1);
                i++;
            }else{
                rs[i++] = array[l2++];
            }
        }

        while(l1<=(bi+ei)/2){
            rs[i] = array[l1++];
            rs[i].v += l2-((bi+ei)/2+1);
            i++;
        }

        while(l2 <=ei){
            rs[i++] = array[l2++];
        }

        System.arraycopy(rs,0,array,bi,ei-bi+1);
    }

    public static void main(String[] args) {
        final LC315 lc315 = new LC315();
        lc315.countSmaller(new int[]{5,2,6,1,3,0,-6,-1});
    }
}
