package Leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 求数组中三数之和为0的三个元素
 */
public class Solution_15 {
    public List<List<Integer>> threeSum(int[] nums) {
        //先排序
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();

        //k表示第一个指针  三个指针 k、i、j
        for(int k=0;k<nums.length-2;k++){
            if(nums[k] > 0) break;  //升序，nums[k]最小
            if(k >0 && nums[k]==nums[k-1]) continue;;   //已经将 nums[k - 1]的所有组合加入到结果中，本次搜索只会得到重复组合

            int i=k+1,j=nums.length-1;
            while(i<j){
                int sum = nums[k] + nums[i] + nums[j];
                if(sum < 0){
                    while(i < j && nums[i] == nums[++i]);
                }else if(sum > 0){
                    while(i < j && nums[j] == nums[--j]);
                }else {
                    res.add(new ArrayList<Integer>(Arrays.asList(nums[k],nums[i],nums[j])));
                    while(i < j && nums[i] == nums[++i]);
                    while(i < j && nums[j] == nums[--j]);
                }
            }
        }
        return res;
    }
}
