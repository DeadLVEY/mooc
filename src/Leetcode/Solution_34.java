package Leetcode;

import java.util.Arrays;

/**
 * 一个升序排列的整数数组 nums，和一个目标值 target。找出给定目标值在数组中的开始位置和结束位置。不存在则返回 [-1, -1]
 * 时间复杂度必须是 O(log n) 级别(二分搜索)
 */
public class Solution_34 {
    //二分查左边界
    private int findLeftIndex(int[] nums, int target){
        int left=0,right=nums.length-1;
        while(left<right){
            int mid = left + (right-left)/2;
            if(nums[mid]>=target){
                right = mid;
            }else {
                left = mid+1;
            }
        }
        if(nums[left]!=target) {
            return -1;
        }else {
            return left;
        }
    }

    //二分查右边界
    private int findRightIndex(int[] nums, int target){
        int left=0,right=nums.length-1;
        while(left<right){
            int mid = (left + right +1)/2;  //！！这里加1是为了最后使right-- 例如target=10时
            if(nums[mid]<=target){
                left = mid;
            }else {
                right = mid-1;
            }
        }
        if(nums[right]!=target) {
            return -1;
        }else {
            return right;
        }
    }

    public int[] searchRange(int[] nums, int target) {
        int leftIndex = findLeftIndex(nums, target);
        int rightIndex = findRightIndex(nums, target);
        return new int[]{leftIndex,rightIndex};
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(new Solution_34().searchRange(new int[]{1, 2, 2, 4, 7, 9, 9, 9,10}, 0)));
    }
}
