package Leetcode;

import java.util.TreeSet;

/**
 * 求两个数组中的公共元素
 */
public class Solution_349 {
    public int[] intersection(int[] nums1, int[] nums2) {
        TreeSet<Integer> set = new TreeSet<>();
        for(int i:nums1)
            set.add(i);

        TreeSet<Integer> resultSet = new TreeSet<>();
        for(int i:nums2){
            if(set.contains(i)){
                resultSet.add(i);
            }
        }

        int[] result = new int[resultSet.size()];
        int index = 0;
        for(int i:resultSet){
            result[index++] = i;
        }

        return result;
    }
}
