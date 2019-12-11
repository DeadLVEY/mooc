package Leetcode;

/**
 * 给定一个含有 n 个正整数的数组和一个正整数 s ，找出该数组中满足其和 ≥ s 的长度最小的连续子数组。如果不存在符合条件的连续子数组，返回 0。
 */
public class solution_209 {
    public int minSubArrayLen(int s, int[] nums) {
        int result = nums.length + 1;

        int l=0,r=-1;
        int sum = 0;

        while(l<nums.length){
            if(r+1<nums.length && sum < s){
                sum += nums[++r];
            }else {
                sum -= nums[l++];
            }

            if(sum >= s){
                result = Math.min(result,r-l+1);
            }
        }

        //没有找到符合要求的子数组
        if(result == nums.length + 1){
            return 0;
        }
        return result;
    }
}
