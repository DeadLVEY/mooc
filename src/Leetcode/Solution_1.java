package Leetcode;

import java.util.HashMap;

/**
 * 求一个无序数组中两个数和为target的索引(有序的话可以用两端指针)
 */
public class Solution_1 {
    //暴力法O(n^2) 用Map一遍Hash法 O(n)快了很多 但map.containsKey也会消耗很多时间
    public int[] twoSum(int[] nums, int target) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for(int i=0;i<nums.length;i++){
            int temp = target - nums[i];
            if(map.containsKey(temp)){
                return new int[]{map.get(temp),i};
            }else{
                map.put(nums[i],i);
            }
        }
        return null;
    }

    //两遍Hash法:先把所有元素存在map中，再判断是否存在target-nums[i]这个元素
    //比一遍Hash法略慢
    public int[] twoSum1(int[] nums, int target) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for(int i=0;i<nums.length;i++){
            //重复值会覆盖
            map.put(nums[i],i);
        }
        for(int i=0;i<nums.length;i++){
            int temp = target - nums[i];
            //不能为自身
            if(map.containsKey(temp) && map.get(temp)!=i){
                return new int[]{i,map.get(temp)};
            }
        }
        return null;
    }
}
