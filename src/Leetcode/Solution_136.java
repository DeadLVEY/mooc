package Leetcode;

/**
 * 找出数组中只出现了一次的那个元素，其他元素均出现两次
 * a ^ a = 0
 * 0 ^ a = a
 * 直接对整个数组异或，得到的值就是孤立的单个元素的值
 */
public class Solution_136 {
    public int singleNumber(int[] nums) {
        int result = 0;
        for(int num : nums){
            result = result ^ num;
        }
        return result;
    }
}
