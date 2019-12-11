package Leetcode;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * 求两个数组中相同的元素，可以重复
 */
public class Solution_350 {
    public int[] intersect(int[] nums1, int[] nums2) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for(int i:nums1){
            if(map.containsKey(i)){
                map.put(i,map.get(i)+1);
            }else {
                map.put(i,1);
            }
        }

        ArrayList<Integer> list = new ArrayList<>();

        for(int i:nums2){
            if(map.containsKey(i) && map.get(i) > 0){
                list.add(i);
                map.put(i,map.get(i)-1);
            }
        }

        int index = 0;
        int[] result = new int[list.size()];
        for(int i:list){
            result[index++] = i;
        }

        return result;
    }
}
