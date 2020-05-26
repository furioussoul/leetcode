package main.java.lc;

import java.util.*;

class Solution {
    public String longestDiverseString(int a, int b, int c) {



        List<String> ignoreList = new ArrayList<>();
        StringBuilder lds = new StringBuilder();

        Map<String, Integer> leftCountMap = new HashMap<>(3);
        leftCountMap.put("a", a);
        leftCountMap.put("b", b);
        leftCountMap.put("c", c);


        String last = "";
        int lastCount = 0;
        while (true) {

            //找leftcount最大的id
            String maxLeftChar = "";
            int maxLeftCount = 0;
            for (Map.Entry<String, Integer> entry : leftCountMap.entrySet()) {
                if (!ignoreList.contains(entry.getKey()) && entry.getValue() > maxLeftCount) {
                    maxLeftCount = entry.getValue();
                    maxLeftChar = entry.getKey();
                }
            }

            if (maxLeftCount == 0) {
                return lds.toString();
            }

            if (maxLeftChar.equals(last)) {
                if (lastCount ==  2) {
                    //找第二大的
                    ignoreList.add(maxLeftChar);
                    continue;
                }
            }else {
                lastCount = 0;
            }

            last = maxLeftChar;
            lastCount+=1;

            leftCountMap.put(maxLeftChar, maxLeftCount - 1);

            ignoreList.clear();

            lds.append(maxLeftChar);
        }
    }

    public static void main(String[] args) {
//        final main.java.lc.Solution solution = new main.java.lc.Solution();
//        final String s = solution.longestDiverseString(7, 1, 0);
//        System.out.println(s);
        int[] ids;
        String a = "a";
        System.out.println(a.substring(0,0));
    }
}